package in.screenbiz.www.imageledger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class CloudLogin extends AppCompatActivity {


    LoginButton fbloginbutton;
    private CallbackManager fbcallbackManager;
    TextView textView;

    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    public static int APP_REQUEST_CODE = 99;

    Button account_kit_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplication().getApplicationContext());            //for initialising the sdk
        AppEventsLogger.activateApp(getApplication());

        AccountKit.initialize(getApplication());



        setContentView(R.layout.activity_cloud_login);








/*
*           Shared Preference Values in this Activity-->>
*               SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                editor.putInt("login_value",1);
 ------->>  0->not logedin ..... 1-->logedin or Skipped log in
*
*
*
*
*
*
* */












       //sha1- E2:8D:3F:C4:D3:3C:60:AF:36:F6:E8:5C:D1:E1:B5:3C:E2:7B:3D:35


/*
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("in.screenbiz.www.imageledger", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"hpsindia1430@gmail.com"};
                intent.putExtra(intent.EXTRA_EMAIL, to);
                intent.putExtra(intent.EXTRA_SUBJECT, "this email is being sent from my app.... regards harsh singh");
                intent.putExtra(intent.EXTRA_TEXT,something);
                intent.setType("message/rcf822");
                chooser = intent.createChooser(intent, "send email");
                startActivity(chooser);




                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }



*/


        textView = (TextView) findViewById(R.id.textView3);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                editor.putInt("login_value",1);
                editor.commit();
                Intent intent2 = new Intent(CloudLogin.this, Image_ledger_sign_in.class);
                startActivity(intent2);

            }
        });



        account_kit_button = (Button) findViewById(R.id.account_kit_button);
        account_kit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                this.onLoginPhone(LoginType.PHONE);

            }


            public void onLoginPhone(final LoginType view) {                                //View view argument in real documentation
                final Intent intent = new Intent(CloudLogin.this, AccountKitActivity.class);
                AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                        new AccountKitConfiguration.AccountKitConfigurationBuilder(
                                LoginType.PHONE,
                                AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN
                // ... perform additional configuration ...
                intent.putExtra(
                        AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                        configurationBuilder.build());
                startActivityForResult(intent, APP_REQUEST_CODE);
            }





        });


        fbcallbackManager = CallbackManager.Factory.create();

        SharedPreferences sharedPreferences = getSharedPreferences("loginfile", Context.MODE_PRIVATE);
        int login_value=sharedPreferences.getInt("login_value",0);

           if(login_value==1)
        {

            Intent intent2 = new Intent(CloudLogin.this,Image_ledger_sign_in.class);
            startActivity(intent2);


        }








        fbloginbutton= (LoginButton) findViewById(R.id.fb_login_button);


        fbloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                {


                    LoginManager.getInstance().registerCallback(fbcallbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {


                            SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                            editor.putInt("login_value",1);
                            editor.commit();
                            Intent intent2 = new Intent(CloudLogin.this, Image_ledger_sign_in.class);
                            startActivity(intent2);




                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(CloudLogin.this, "Login Process Cancelled..!!", Toast.LENGTH_SHORT).show();
                            finish();
                            System.exit(0);


                        }

                        @Override
                        public void onError(FacebookException error) {

                            new AlertDialog.Builder(CloudLogin.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Facebook Login Error")
                                    .setMessage("Error Occured....")
                                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            System.exit(0);
                                        }
                                    }).setNegativeButton("Retry", null).show();


                        }
                    });












                }

            }


        });










    }




    @Override
    protected void onResume() {
        super.onResume();



        SharedPreferences sharedPreferences = getSharedPreferences("loginfile",Context.MODE_PRIVATE);
        int login_value=sharedPreferences.getInt("login_value",0);

        if(login_value==1)
        {

            Intent intent2 = new Intent(CloudLogin.this, Image_ledger_sign_in.class);
            startActivity(intent2);


        }



    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        fbcallbackManager.onActivityResult(requestCode, resultCode, data);




        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                //   showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                    editor.putInt("login_value",1);
                    editor.commit();
                    Intent intent2 = new Intent(CloudLogin.this, Image_ledger_sign_in.class);
                    startActivity(intent2);

                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                    SharedPreferences.Editor editor = getSharedPreferences("loginfile", Context.MODE_PRIVATE).edit();
                    editor.putInt("login_value",1);
                    editor.commit();
                    Intent intent2 = new Intent(CloudLogin.this, Image_ledger_sign_in.class);
                    startActivity(intent2);

                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...
                //goToMyLoggedInActivity();
            }

            // Surface the result to your user in an appropriate way.


        }


    }












}
