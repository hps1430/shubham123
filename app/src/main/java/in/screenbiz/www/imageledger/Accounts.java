package in.screenbiz.www.imageledger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Accounts extends AppCompatActivity {

    DatabaseHelper_Adapter database_important;
    private ArrayList<String> arrayList ;
    private ArrayAdapter<String> arrayAdapter ;
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        database_important = new DatabaseHelper_Adapter(this); //following line in DatabaseHelper_Adapter class would create database
        // that is get writable database function would create database.













        /*Shared Preferences---->>
        *
        *                 SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                          editor.putString("active_accountid",account_id);

        *
        *
        *
        *
        *
        * */




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(Accounts.this,NewAccounts.class);
                startActivity(intent);

            }
        });












        listView = (ListView) findViewById(R.id.listView1);
        arrayList = (ArrayList<String>) database_important.getallAccounts().clone();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {



                TextView textitem = (TextView) v.findViewById(android.R.id.text1);

                String text = textitem.getText().toString();

                String account_name = text.substring(0,text.indexOf("\n")).trim();

                String account_id = database_important.getaccountID(account_name);

                SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                editor.putString("active_accountid",account_id);
                editor.commit();
                Intent intent2 = new Intent(Accounts.this, AccountStatus.class);
                startActivity(intent2);








            }
        });







    }







    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String formatDateTime(Context context, String timeToFormat) {

        String finalDateTime = "";

        SimpleDateFormat iso8601Format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        Date date = null;
        if (timeToFormat != null) {
            try {
                date = iso8601Format.parse(timeToFormat);
            } catch (ParseException e) {
                date = null;
            }

            if (date != null) {
                long when = date.getTime();
                int flags = 0;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
                flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

                finalDateTime = android.text.format.DateUtils.formatDateTime(context,
                        when + TimeZone.getDefault().getOffset(when), flags);
            }
        }
        return finalDateTime;
    }


    @Override
    protected void onResume() {
        super.onResume();
        ListView listView;
        listView = (ListView) findViewById(R.id.listView1);
        arrayList = (ArrayList<String>) database_important.getallAccounts().clone();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }



    @Override
    public void onBackPressed() {




        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_accounts, menu);


//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

//if wants to add voice search in future.


        MenuItem search_item = menu.findItem(R.id.search_view_accounts);


        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search_item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {



                if (s.contentEquals("")) {

                    onResume();
                    return false;
                }
                else {

                    arrayAdapter.getFilter().filter(s);
                    listView.setAdapter(arrayAdapter);
                    return false;

                }



            }
        });










        return true;

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.




        switch(item.getItemId())
        {






            case R.id.settings:

                break;








        }
        return super.onOptionsItemSelected(item);
    }



}
