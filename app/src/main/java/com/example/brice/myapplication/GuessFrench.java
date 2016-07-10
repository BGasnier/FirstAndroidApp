package com.example.brice.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Brice on 10/04/2016.
 */
public class GuessFrench extends Activity {
    private DBHelper mydb ;
    TextView estonian;
    TextView french;
    String fren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_french);

        estonian = (TextView) findViewById(R.id.editTextEstonian);
        french = (TextView) findViewById(R.id.editTextFrench);

        mydb = new DBHelper(this);

        Cursor rs = mydb.getRandom();
        rs.moveToFirst();

        String esto = rs.getString(rs.getColumnIndex(DBHelper.WORDS_COLUMN_ESTONIAN));
        fren = rs.getString(rs.getColumnIndex(DBHelper.WORDS_COLUMN_FRENCH));
        String engl = rs.getString(rs.getColumnIndex(DBHelper.WORDS_COLUMN_ENGLISH));

        if (!rs.isClosed())
        {
            rs.close();
        }

        estonian.setText((CharSequence)esto);
        estonian.setFocusable(false);
        estonian.setClickable(false);
    }

    public void run(View view)
    {
        french.setText((CharSequence)fren);
        french.setFocusable(false);
        french.setClickable(false);


            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
    }

    public void run2(View view)
    {
        if(french.getText().toString().equals(fren)) {

            french.setText((CharSequence) fren);
            french.setFocusable(false);
            french.setClickable(false);


            Intent intent = new Intent(getApplicationContext(), GuessFrench.class);
            startActivity(intent);
        }
    }

}
