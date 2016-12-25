package in.screenbiz.www.imageledger;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Image_ledger_sign_up extends AppCompatActivity {

    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_ledger_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);





        register= (Button) findViewById(R.id.email_Register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message.message_short(Image_ledger_sign_up.this,"Registered Successfully");
                Intent intent = new Intent(Image_ledger_sign_up.this,Image_ledger_sign_in.class);
                startActivity(intent);





            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Image_ledger_sign_up.this).setIcon(android.R.drawable.sym_action_chat).setTitle("Secondary Password")
                        .setMessage("Login can be performed via this password for access to the default database.Secondary password is set only once per login and can be changed afterwards."+"\n"+"Important : Real Database would be lost if you Would Login via Secondary Password .. Login with care through secondary password")
                        .setNegativeButton("Got It", null).show();

            }
        });
    }

}
