package com.smartguy044.homeworkcheck_in;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import dreamers.graphics.RippleDrawable;

public class MainActivity extends Activity {


    //INITIALIZING THE VARIABLES...
    Button b1,b2;
    EditText e1;
    String text, u;
    //String kid[]={"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    String pas;
    Cursor c;
    int i = 0;
    String s,sqlquery;

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.editText1);
        //PreferenceManager.setDefaultValues(this, R.xml.preference, false);
        RippleDrawable.createRipple(b1, getResources().getColor(R.color.material_blue_600));
        RippleDrawable.createRipple(b2, getResources().getColor(R.color.material_blue_600));

        //ADD KID TO CLASS
        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                //CONVERTING THE TEXT IN TO STRING...

                text = e1.getText().toString();

                //CREATING A DATABASE OBJECT..HERE db is SQLITEDATABASE OBJECT AND
                //WE ARE USING IT IN WRITE MODE
                if(text.length() > 0) {
                    SQLiteDatabase db = openOrCreateDatabase("CLASS", Context.MODE_PRIVATE, null);

                    text = "'" + text + "'";

                    //SQL QUERY TO CREATE TABLE
                    s = "CREATE TABLE if not exists CLASS" + " (" + "list" + " VARCHAR(100)" + ");";
                    db.execSQL(s);

                    // QUERY TO INSERT KID IN CLASS
                    sqlquery = "INSERT INTO CLASS" + " VALUES" + "(" + text + ");";
                    db.execSQL(sqlquery);
                    e1.setText("");

                    Toast.makeText(getApplicationContext(), "Student Added To Class", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "No Student Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //THIS WILL CALL ANOTHER ACTIVITY NAMED AS ClassList.JAVA
        b2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ClassList.class));
            }
        });

    }

    //EXIT APP WITH BACK KEY ON MAIN ACTIVITY
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }



}