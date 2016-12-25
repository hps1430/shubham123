package in.screenbiz.www.imageledger;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by harsh singh on 20-12-2016.
 */

public class CurrentTime {



    @RequiresApi(api = Build.VERSION_CODES.N)
    public CurrentTime(Context context){







    }






    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getdateformat(){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String date = simpleDateFormat.format(calendar.getTime()).toString();



        return date;



    }






    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getdatetimeFormat(){


        Calendar calendar2 = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy   h:mm a");
        String dateandtime = simpleDateFormat.format(calendar2.getTime()).toString();

        return dateandtime;
    }




}
