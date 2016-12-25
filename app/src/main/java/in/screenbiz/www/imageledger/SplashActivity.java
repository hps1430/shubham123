package in.screenbiz.www.imageledger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    DatabaseHelper_Adapter database_important;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Handler handler;


        handler = new Handler(getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {

              setContentView(R.layout.activity_splash);


            }
        });


        {
            Thread t = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(SplashActivity.this,CloudLogin.class);
                    startActivity(intent);

                }
            };
            t.start();
        }





    }
}
