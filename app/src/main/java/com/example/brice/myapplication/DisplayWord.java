package com.example.brice.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Brice on 03/04/2016.
 */
public class DisplayWord extends Activity {
        int from_Where_I_Am_Coming = 0;
        private DBHelper mydb ;

        TextView estonian ;
        TextView french;
        TextView english;
        int id_To_Update = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_word);
            estonian = (TextView) findViewById(R.id.editTextEstonian);
            french = (TextView) findViewById(R.id.editTextFrench);
            english = (TextView) findViewById(R.id.editTextEnglish);

            mydb = new DBHelper(this);

            Bundle extras = getIntent().getExtras();
            if(extras !=null)
            {
                int Value = extras.getInt("id");

                if(Value>0){
                    //means this is the view part not the add contact part.
                    Cursor rs = mydb.getData(Value);
                    id_To_Update = Value;
                    rs.moveToFirst();

                    String esto = rs.getString(rs.getColumnIndex(DBHelper.WORDS_COLUMN_ESTONIAN));
                    String fren = rs.getString(rs.getColumnIndex(DBHelper.WORDS_COLUMN_FRENCH));
                    String engl = rs.getString(rs.getColumnIndex(DBHelper.WORDS_COLUMN_ENGLISH));

                    if (!rs.isClosed())
                    {
                        rs.close();
                    }
                    Button b = (Button)findViewById(R.id.button1);
                    b.setVisibility(View.INVISIBLE);

                    Button deleteButton = (Button)findViewById(R.id.buttonDeleteWord);
                    deleteButton.setVisibility(View.VISIBLE);

                    estonian.setText((CharSequence)esto);
                    estonian.setFocusable(false);
                    estonian.setClickable(false);

                    french.setText((CharSequence)fren);
                    french.setFocusable(false);
                    french.setClickable(false);

                    english.setText((CharSequence)engl);
                    english.setFocusable(false);
                    english.setClickable(false);
                }
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            Bundle extras = getIntent().getExtras();

            if(extras !=null)
            {
                int Value = extras.getInt("id");
                if(Value>0){
                    getMenuInflater().inflate(R.menu.display_word, menu);
                }

                else{
                    getMenuInflater().inflate(R.menu.menu_main, menu);
                }
            }
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item)
        {
            super.onOptionsItemSelected(item);
            switch(item.getItemId())
            {
                case R.id.Edit_Word:
                    Button b = (Button)findViewById(R.id.button1);
                    b.setVisibility(View.VISIBLE);
                    estonian.setEnabled(true);
                    estonian.setFocusableInTouchMode(true);
                    estonian.setClickable(true);

                    french.setEnabled(true);
                    french.setFocusableInTouchMode(true);
                    french.setClickable(true);

                    english.setEnabled(true);
                    english.setFocusableInTouchMode(true);
                    english.setClickable(true);

                    return true;
              /*  case R.id.Delete_Word:

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.deleteContact)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mydb.deleteContact(id_To_Update);
                                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    AlertDialog d = builder.create();
                    d.setTitle("Are you sure");
                    d.show();

                    return true;*/
                default:
                    return super.onOptionsItemSelected(item);

            }
        }

        public void run(View view)
        {
            Bundle extras = getIntent().getExtras();
            if(extras !=null)
            {
                int Value = extras.getInt("id");
               /* if(Value>0){
                    if(mydb.updateContact(id_To_Update,name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
                else{*/
                    if(mydb.insertWord(estonian.getText().toString(), french.getText().toString(), english.getText().toString())){
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
               // }
            }
        }

    public void run2(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.deleteContact)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mydb.deleteContact(id_To_Update);
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            AlertDialog d = builder.create();
            d.setTitle("Are you sure");
            d.show();
        }
    }
}
