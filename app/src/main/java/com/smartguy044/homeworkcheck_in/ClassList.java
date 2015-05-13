package com.smartguy044.homeworkcheck_in;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ClassList extends Activity {

    ListView lv;
    Cursor c;
    ArrayList<String> kids;
    SQLiteDatabase db;
    final Context context = this;
    ArrayAdapter<String> adapter;

    //ADD OVERFLOW MENU TO APP
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list);
        lv = (ListView) findViewById(R.id.listView1);

        //PreferenceManager.setDefaultValues(this, R.xml.preference, false);
        db = openOrCreateDatabase("CLASS", Context.MODE_PRIVATE, null);

        //READING THE DATA FROM THE TABLE CLASS AND SETTING IN AN ARRAYADAPTER
        c = db.rawQuery("SELECT * FROM CLASS; ", null);

        //final String
        kids = new ArrayList<String>(db.rawQuery("SELECT * FROM CLASS; ", null).getCount());

        while (c.moveToNext())
        {
            kids.add("" + c.getString(0));
        }

        // Declaring arrayadapter to store the items and return them as a view
        adapter = new ArrayAdapter<String>(this, R.layout.multiple_choice, kids);
        lv.setAdapter(adapter);

        db.close();

        /*lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long arg3) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("REMOVE STUDENT");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to REMOVE this student from the class list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, erase
                                // the selected student from class list

                                kids.remove(position);

                                //String delete = String.valueOf(position);

                                String delete = String.valueOf(adapter.getItemId(position));

                                adapter.remove(delete);

                                adapter.notifyDataSetChanged();

                                //lv.setAdapter(adapter);

                                startActivity(new Intent(getApplicationContext(),
                                        ClassList.class));

                            }

                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                return true;
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //GOING BACK TO MAIN ACTIVITY
            case R.id.back:
                startActivity(new Intent(getApplicationContext(),
                     MainActivity.class));
            return true;

            //CLEAR ALL CHECK MARKS
            case R.id.clear:

                lv.clearChoices();
                for (int i = 0; i < lv.getCount(); i++)
                    lv.setItemChecked(i, false);
                return true;

            //ERASE CLASS LIST
            case R.id.erase:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Erase Class List");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to ERASE the whole class list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, erase
                                // the entire class list
                                //delete all the data from the table
                                db = openOrCreateDatabase("CLASS", Context.MODE_PRIVATE, null);
                                db.delete("CLASS", null, null);
                                db.close();

                                startActivity(new Intent(getApplicationContext(),
                                        MainActivity.class));
                            }

                        })

                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                return true;

            //GOING TO SETTINGS
            /*case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;*/

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}