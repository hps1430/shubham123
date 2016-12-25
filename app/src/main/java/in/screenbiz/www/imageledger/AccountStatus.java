package in.screenbiz.www.imageledger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountStatus extends AppCompatActivity {


    DatabaseHelper_Adapter database_important;
    ListView listView_transaction;
    TextView final_bal,final_bal_color_text;

    private ArrayList<String> arrayList,arraylist_transaction ;
    private ArrayAdapter<String> arrayAdapter_transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_status);


        database_important = new DatabaseHelper_Adapter(this); //following line in DatabaseHelper_Adapter class would create database
        // that is get writable database function would create database.


        /*
        * Shared Preferences--->>
        *
        *           SharedPreferences.Editor editor = getSharedPreferences("loginfile",Context.MODE_PRIVATE).edit();
                    editor.putString("active_final_balance",final_balance_onresume);
                    editor.commit();

                    editor.putString("active_account_name",account_name);
        *
        *
        *
        *                   editor.putString("active_transaction_id",active_transaction_id);

        *
        *
        * */














        SharedPreferences sharedPreferences = getSharedPreferences("loginfile", Context.MODE_PRIVATE);
        String active_accountid = sharedPreferences.getString("active_accountid","0");


        arrayList = (ArrayList<String>) database_important.getaccountstatus(active_accountid).clone();

        String account_name = arrayList.get(0);
        SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
        editor.putString("active_account_name",account_name);
        editor.commit();


        String initial_balance = arrayList.get(1);

        String account_description = arrayList.get(2);

        String last_transaction_amt = arrayList.get(3);

        String final_balance = arrayList.get(4);
        editor.putString("active_final_balance",final_balance);
        editor.commit();


        String last_transaction_date = arrayList.get(5);


        String account_creation_date = arrayList.get(6);



        TextView account_name_text = (TextView) findViewById(R.id.account_name_text);
        account_name_text.setText(account_name_text.getText()+account_name.toUpperCase());




        final_bal_color_text= (TextView) findViewById(R.id.acc_Balance_color_text);
        final_bal = (TextView) findViewById(R.id.final_balance_text);
        final_bal.setText(final_bal.getText().toString()+final_balance);

        final_bal_color_text.setText(final_balance);
        if(Float.valueOf(final_balance)<0)
        {

            final_bal_color_text.setTextColor(Color.parseColor("#F44336"));

        }
        else
        {

            final_bal_color_text.setTextColor(Color.parseColor("#4CAF50"));


        }






        TextView initial_bal = (TextView) findViewById(R.id.initial_balance_text);
        initial_bal.setText(initial_bal.getText().toString()+initial_balance+" On "+account_creation_date);




        TextView acc_desc = (TextView) findViewById(R.id.acc_desc_account_stat_text);
        acc_desc.setText(acc_desc.getText().toString()+account_description);






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(AccountStatus.this,NewTransaction.class);
                startActivity(intent);




                   }
        });


        listView_transaction = (ListView) findViewById(R.id.transaction_listview);
        arraylist_transaction = (ArrayList<String>) database_important.getallTransactions(active_accountid).clone();
        arrayAdapter_transaction = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist_transaction);
        listView_transaction.setAdapter(arrayAdapter_transaction);




        listView_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {



                TextView textitem = (TextView) v.findViewById(android.R.id.text1);

                String text = textitem.getText().toString();

                text = text.substring(0,text.indexOf("\n")).trim();

                String active_transaction_id = text.substring(10,text.length()).trim();


                SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                editor.putString("active_transaction_id",active_transaction_id);
                editor.commit();
                Intent intent2 = new Intent(AccountStatus.this, TransactionStatus.class);
                startActivity(intent2);





            }
        });













    }


    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences sharedPreferences = getSharedPreferences("loginfile", Context.MODE_PRIVATE);
        String active_accountid = sharedPreferences.getString("active_accountid","0");




        listView_transaction = (ListView) findViewById(R.id.transaction_listview);
        arraylist_transaction = (ArrayList<String>) database_important.getallTransactions(active_accountid).clone();
        arrayAdapter_transaction = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist_transaction);
        listView_transaction.setAdapter(arrayAdapter_transaction);




        arrayList = (ArrayList<String>) database_important.getaccountstatus(active_accountid).clone();

        String final_balance_onresume = arrayList.get(4);
        SharedPreferences.Editor editor = getSharedPreferences("loginfile",Context.MODE_PRIVATE).edit();
        editor.putString("active_final_balance",final_balance_onresume);
        editor.commit();


        final_bal_color_text.setText(final_balance_onresume);
        if(Float.valueOf(final_balance_onresume)<0)
        {

            final_bal_color_text.setTextColor(Color.parseColor("#F44336"));

        }

        else
        {

            final_bal_color_text.setTextColor(Color.parseColor("#4CAF50"));


        }





        final_bal.setText("A/c Balance: "+final_balance_onresume);



    }
}
