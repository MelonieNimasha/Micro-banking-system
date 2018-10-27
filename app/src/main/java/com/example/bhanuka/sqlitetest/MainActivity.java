package com.example.bhanuka.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText edit_account_number, edit_type, edit_date, edit_time, edit_amount, edit_details;
    Button button_submit;

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
        AddData();
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
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
