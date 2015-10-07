package com.example.owner.helloworld;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    private int numberOfCoffees = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //createOrderSumary();
       // Intent intent = new Intent(this,Payment.class );
       // startActivity(intent);
        EditText NameField = (EditText) findViewById(R.id.Name_text);
        String Name = NameField.getText().toString();
        CheckBox Whippe = (CheckBox) findViewById(R.id.checkBox_WhippeCream);
        boolean isWhippeCream = Whippe.isChecked();

        CheckBox choco = (CheckBox) findViewById(R.id.checkBox_Chocolate);
        boolean isChocolate = choco.isChecked();

        int price = calculatePrice(isWhippeCream, isChocolate);
        String result = createOrderSummary(Name, price, isWhippeCream, isChocolate);
        String subject = "Just Java Order of " + Name ;
        composeEmail(subject ,result);
        //displayMessage(result);
    }


    /*
    *this method is called when the Order button is pressed
    * it 's going to send a Mail
     */
    public void composeEmail(String subject, String attachment) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, attachment);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     */
    private String createOrderSummary(String name, int price, boolean whippe, boolean choco) {

        String summary = "Name: " + name +
                "\nAdd Whipped Cream? "+ whippe +
                "\nAdd Chocolate? "+ choco +
                "\nQuantity: " + numberOfCoffees +
                "\nTotal: $" + price + "\nThank you!";
        return summary;
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhipped is the number of cups of coffee ordered
     * @param hasChocolate is the price of one cup
     */
    private int calculatePrice(boolean hasWhipped, boolean hasChocolate) {

        int pricePerCup = 5;
        if(hasWhipped)
        {
            pricePerCup += 1;
        }

        if(hasChocolate)
        {
            pricePerCup += 2;
        }

        int price = numberOfCoffees * pricePerCup;

        return price;
    }

    /*
    * this method is called when the plus  button is cliked
    */
    public void increment(View view)
    {
        if(numberOfCoffees < 100)
        {
        numberOfCoffees++;
        display(numberOfCoffees);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "You can't have above 100 coffees!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //displayMessage(totalOrder());
    }

    /*
    * this Method is called when minus button is cliked
     */
    public void decrement(View view)
    {
        if(numberOfCoffees > 1 )
        {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "You can't have bellow 1 coffee!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
       // displayMessage(totalOrder());
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}