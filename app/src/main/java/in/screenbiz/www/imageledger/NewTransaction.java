package in.screenbiz.www.imageledger;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewTransaction extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageButton photoButton3,photoButton1,photoButton2;
    TextView account_name_text;
    Button debit_button,credit_button;
    EditText transaction_amount_edit , date_edit , notes_edit;

    DatabaseHelper_Adapter databaseHelper_adapter;
    String image1_path,image2_path,image3_path;

    int day , month, year;

    int yearfinal,monthfinal,dayfinal;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);


        databaseHelper_adapter = new DatabaseHelper_Adapter(this);


        SharedPreferences sharedPreferences = getSharedPreferences("loginfile", Context.MODE_PRIVATE);
        String active_account_name = sharedPreferences.getString("active_account_name","");
        final String active_accountid = sharedPreferences.getString("active_accountid","0");
        final Float final_balance_float = Float.parseFloat(sharedPreferences.getString("active_final_balance","0"));




        //ImageView ima = new ImageView(this);
       // ima.setBackgroundResource(R.mipmap.accimage);


        image1_path="R.mipmap.accimage";
        image2_path="R.mipmap.accimage";
        image3_path="R.mipmap.accimage";











        account_name_text = (TextView) findViewById(R.id.account_name_text);
        account_name_text.setText(account_name_text.getText()+active_account_name.toUpperCase());


        transaction_amount_edit = (EditText) findViewById(R.id.amount_edit);




        CurrentTime currentTime = new CurrentTime(this);



        date_edit = (EditText) findViewById(R.id.transaction_date);
        date_edit.setText(currentTime.getdatetimeFormat().toString());
        date_edit.getFreezesText();

        date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog =new DatePickerDialog(NewTransaction.this,NewTransaction.this,
                        year,month,day);

                datePickerDialog.show();
            }
        });






        notes_edit = (EditText) findViewById(R.id.transaction_notes);


        debit_button = (Button) findViewById(R.id.button_debit);
        debit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               int check_result = check_arguments();


                if(check_result==0)
                    return;
                else{

                    float transaction_amount_float = Float.parseFloat(transaction_amount_edit.getText().toString());

                    long id = databaseHelper_adapter.add_transaction_debit(active_accountid,final_balance_float,transaction_amount_float,date_edit.getText().toString(),notes_edit.getText().toString(),image1_path,image2_path,image3_path);

                    if(id<0)
                    {
                        Message.message_short(NewTransaction.this,"Transaction Failed");

                    }
                    else if (id>0)
                    {

                        Message.message_long(NewTransaction.this,"Transaction Successful");
                        NewTransaction.super.onBackPressed();

                    }











                }



            }
        });




        credit_button = (Button) findViewById(R.id.button_credit);
        credit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int check_result = check_arguments();

                if(check_result==0)
                    return;
                else{
                    float transaction_amount_float = Float.parseFloat(transaction_amount_edit.getText().toString());


                    long id = databaseHelper_adapter.add_transaction_credit(active_accountid,final_balance_float,transaction_amount_float,date_edit.getText().toString(),notes_edit.getText().toString(),image1_path,image2_path,image3_path);

                    if(id<0)
                    {
                        Message.message_short(NewTransaction.this,"Transaction Failed");

                    }
                    else if (id>0)
                    {

                        Message.message_long(NewTransaction.this,"Transaction Successful");
                        NewTransaction.super.onBackPressed();

                    }

















                }






            }
        });








        photoButton1 = (ImageButton) this.findViewById(R.id.imagebutton1);
        photoButton2 = (ImageButton) this.findViewById(R.id.imagebutton2);
        photoButton3 = (ImageButton) this.findViewById(R.id.imagebutton3);


        photoButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent cameraIntent1 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent1, 1);
            }
        });
        photoButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent2 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent2, 2);
            }
        });
        photoButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent3 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent3, 3);
            }
        });




    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


        yearfinal=i;

        monthfinal = i1+1;
        dayfinal = i2;

        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String date = simpleDateFormat.format(calendar.getTime()).toString();





        date_edit.setText(date);





    }


    private int check_arguments() {


        int result=1;

        String strUserName = transaction_amount_edit.getText().toString();

        if(TextUtils.isEmpty(strUserName)) {
            transaction_amount_edit.setError("Input Transaction Amount");
            result = 0;
        }


        if(TextUtils.isEmpty(date_edit.getText().toString())){

            date_edit.setError("Input Date");
            result = 0;
        }




        return result;


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            Bitmap photo1 = (Bitmap) data.getExtras().get("data");
            {

                try {
                    image1_path ="image1"+String.valueOf(System.currentTimeMillis())+".png";
                    FileOutputStream fos = openFileOutput(image1_path, Context.MODE_PRIVATE);
                    photo1.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                    Message.message_long(this,"Image 1 is saved successfully");
                    photoButton1.setImageBitmap(photo1);

                } catch (Exception e) {
                    Toast.makeText(NewTransaction.this, "First image Saved To Internal Storage Failed", Toast.LENGTH_SHORT).show();;

                }
            }



        }

        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {

            Bitmap photo2 = (Bitmap) data.getExtras().get("data");
            {

                try {
                    image2_path ="image2"+String.valueOf(System.currentTimeMillis())+".png";

                    FileOutputStream fos = openFileOutput(image2_path, Context.MODE_PRIVATE);
                    photo2.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                    Message.message_long(this,"Image 2 is saved successfully");
                    photoButton2.setImageBitmap(photo2);


                } catch (Exception e) {

                }
            }


        }



        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {

            Bitmap photo3 = (Bitmap) data.getExtras().get("data");
            {

                try {
                    image3_path ="image3"+String.valueOf(System.currentTimeMillis())+".png";
                    FileOutputStream fos = openFileOutput(image3_path, Context.MODE_PRIVATE);
                    photo3.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.close();
                    Message.message_long(this,"Image 3 is saved successfully");
                    photoButton3.setImageBitmap(photo3);


                } catch (Exception e) {

                }
            }


        }

    }


}
