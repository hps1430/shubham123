package in.screenbiz.www.imageledger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TransactionStatus extends AppCompatActivity {

    DatabaseHelper_Adapter databaseHelper_adapter;
    Button show_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_status);

        databaseHelper_adapter = new DatabaseHelper_Adapter(this);


        SharedPreferences sharedPreferences = getSharedPreferences("loginfile", Context.MODE_PRIVATE);
        String active_transaction_id = sharedPreferences.getString("active_transaction_id","0");



        String active_image1_name =  databaseHelper_adapter.getimage1_name(active_transaction_id);

        String active_image2_name =  databaseHelper_adapter.getimage2_name(active_transaction_id);

        String active_image3_name =  databaseHelper_adapter.getimage3_name(active_transaction_id);


        SharedPreferences.Editor editor = getSharedPreferences("loginfile",Context.MODE_PRIVATE).edit();
        editor.putString("active_image1_name",active_image1_name);
        editor.putString("active_image2_name",active_image2_name);
        editor.putString("active_image3_name",active_image3_name);
        editor.commit();




        show_images = (Button) findViewById(R.id.button_show_images);
        show_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TransactionStatus.this,Transaction_Images_Tabbed.class);
                startActivity(intent);



            }
        });


    }
}
