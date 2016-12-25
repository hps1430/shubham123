package in.screenbiz.www.imageledger;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccounts extends AppCompatActivity implements View.OnClickListener{

    EditText acc_name , initial_balance , account_description ;
    Button add_account , cancel ;
    DatabaseHelper_Adapter databaseHelper_adapter;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_accounts);

        databaseHelper_adapter = new DatabaseHelper_Adapter(this);




        acc_name = (EditText) findViewById(R.id.account_name);
        initial_balance = (EditText) findViewById(R.id.initial_edit);
        account_description = (EditText) findViewById(R.id.account_description);

        add_account = (Button) findViewById(R.id.button_add_imp);
        cancel = (Button) findViewById(R.id.button_cancel_imp);


        add_account.setOnClickListener(this);
        add_account.setEnabled(false);


        cancel.setOnClickListener(this);



        acc_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                add_account.setEnabled((!acc_name.getText().toString().trim().isEmpty()));


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.button_add_imp)
        {




            String acc_name_text =acc_name.getText().toString();

            String initial_balance_text = initial_balance.getText().toString();
            if(TextUtils.isEmpty(initial_balance_text)){


                initial_balance.setError("Enter Initial Balance");
                return;

            }
            String account_descripton = account_description.getText().toString();



            CurrentTime currentTime =new CurrentTime(this);


            String creation_date = currentTime.getdateformat();




            long id = databaseHelper_adapter.add_account(acc_name_text ,initial_balance_text ,account_descripton,creation_date);

            if(id<0)
            {
                Message.message_short(this,"Account Addition Failed...Please Try Again");

            }
            else if (id>0)
            {

                Message.message_long(this,"Account Added Successfully");
                super.onBackPressed();

            }







        }

        else if(view.getId()==R.id.button_cancel_imp)
        {

            super.onBackPressed();

        }




    }
}
