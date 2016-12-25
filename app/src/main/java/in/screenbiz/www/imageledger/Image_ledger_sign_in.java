package in.screenbiz.www.imageledger;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Image_ledger_sign_in extends AppCompatActivity implements View.OnClickListener{

    Button signin_button , go_register_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_ledger_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



        Intent intent = new Intent(Image_ledger_sign_in.this,Accounts.class);
        startActivity(intent);







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Image_ledger_sign_in.this).setIcon(android.R.drawable.sym_action_chat).setTitle("Secondary Password")
                        .setMessage("Login can be performed via secondary password for access to the default database.Secondary PasswordSecondary password is set only once per login and can be changed afterwards."+"\n"+"Important : Real Database would be lost if you Would Login via Secondary Password .. Login with care through secondary password")
                        .setNegativeButton("Got It", null).show();

            }
        });



        signin_button = (Button) findViewById(R.id.email_sign_in_button);
        signin_button.setOnClickListener(this);


        go_register_button = (Button) findViewById(R.id.email_Register_button);
        go_register_button.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

            if(view.getId()==R.id.email_Register_button)
            {
                Intent intent = new Intent(Image_ledger_sign_in.this,Image_ledger_sign_up.class);
                startActivity(intent);

            }

        else
                if (view.getId()==R.id.email_sign_in_button)
                {
                    Intent intent = new Intent(Image_ledger_sign_in.this,Accounts.class);
                    startActivity(intent);

                }


    }
}
