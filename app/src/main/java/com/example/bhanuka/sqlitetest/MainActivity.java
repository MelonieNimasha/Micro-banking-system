package com.example.bhanuka.sqlitetest;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText edit_account_number, edit_type, edit_date, edit_time, edit_amount, edit_details;
    Button button_submit;
    Button viewdata;
    Button viewAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);


        edit_account_number = (EditText)findViewById(R.id.editText_account_number);
        edit_type = (EditText)findViewById(R.id.editText_type);
        edit_date = (EditText)findViewById(R.id.editText_date);
        edit_time = (EditText)findViewById(R.id.editText_time);
        edit_amount = (EditText)findViewById(R.id.editText_amount);
        edit_details = (EditText)findViewById(R.id.editText_details);
        button_submit = (Button) findViewById(R.id.button_submit);
        viewdata = (Button) findViewById(R.id.button_viewdata);
        viewAccounts = (Button) findViewById(R.id.button_account_data);
        AddData();
        viewData();

        //insert data to account
        AddToAccounts("1234", "962232531v","savings", "ok","45000","good account");
        AddToAccounts("9632", "975423641v","fixed", "ok","23000","good account");
        AddToAccounts("4789", "962145213x","joint", "ok","10000","good account");
        viewAccounts();
    }

    public void AddData(){
        button_submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(edit_account_number.getText().toString(),
                                edit_type.getText().toString(),
                                edit_date.getText().toString(),
                                edit_time.getText().toString(),
                                edit_amount.getText().toString(),
                                edit_details.getText().toString());
                        if (isInserted = true) {
                            makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                        } else
                            makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddToAccounts(String account_number, String customer_NIC, String account_type, String status, String current_balance, String account_details){
        boolean isInserted = myDB.insertToAccounts(account_number, customer_NIC, account_type, status, current_balance, account_details);
        if (isInserted = true)
            System.out.println("succefully added to accounts");
        else
            System.out.println("error occured");
    }

    public void viewData(){
        viewdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDB.getAllData();
                        if (result.getCount() == 0) {
                            showMessage("Error", "Nothing found");

                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()){
                            buffer.append("Account number : " + result.getString(1) + "\n");
                            buffer.append("Type : " + result.getString(2) + "\n");
                            buffer.append("Date :" + result.getString(3) + "\n");
                            buffer.append("Time :" + result.getString(4) + "\n");
                            buffer.append("Amount :" + result.getString(5) + "\n");
                            buffer.append("Details :" + result.getString(6) + "\n\n");
                        }

                        showMessage("Data",buffer.toString());
                    }

                }
        );
    }

    public void viewAccounts(){
        viewAccounts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDB.getAccounts();
                        if (result.getCount() == 0) {
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()){
                            buffer.append("Account number : " + result.getString(0) + "\n");
                            buffer.append("Type : " + result.getString(1) + "\n");
                            buffer.append("Status :" + result.getString(2) + "\n");
                            buffer.append("Current balance :" + result.getString(3) + "\n");
                            buffer.append("Account details :" + result.getString(4) + "\n\n");
                        }

                        showMessage("Accounts",buffer.toString());
                    }

                }
        );

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
