package in.screenbiz.www.imageledger;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by harsh singh on 23-10-2016.
 */

public class Message {

    public static void message_long(Context context, String string){


        Toast.makeText(context,string,Toast.LENGTH_LONG).show();



    }

    public static void message_short(Context context,String string){


        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();



    }



    public static void message_long_center_gravity(Context context, String string, View view)
    {

        Toast toast = new Toast(context);

        toast.makeText(context,string,Toast.LENGTH_LONG);
        toast.setView(view);                                                //code to be corrected
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();

    }


}
