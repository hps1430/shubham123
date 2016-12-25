package in.screenbiz.www.imageledger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by harsh singh on 23-10-2016.
 */


public class DatabaseHelper_Adapter{

    database_important database_imp;

    public DatabaseHelper_Adapter(Context context) {


        database_imp = new database_important(context);
        database_imp.getWritableDatabase();


    }





    public long add_account(String account_name , String initial_balance ,String account_description,String creation_date) {


        SQLiteDatabase db = database_imp.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        contentValues.put(database_important.Field_AccountName, account_name);
        contentValues.put(database_important.Field_FinalBalance, initial_balance);
        contentValues.put(database_important.Field_InitialBalance, initial_balance);
        contentValues.put(database_important.Field_account_description, account_description);
        contentValues.put(database_important.Field_Acc_creation_date,creation_date);

        contentValues.put(database_important.Field_acc_extra_1," ");
        contentValues.put(database_important.Field_acc_extra_2," ");
        contentValues.put(database_important.Field_acc_extra_3," ");
        contentValues.put(database_important.Field_acc_extra_4," ");
        contentValues.put(database_important.Field_acc_extra_5," ");
        contentValues.put(database_important.Field_LastTransactionAmount,"0");
        contentValues.put(database_important.Field_LastTransactionDate,creation_date);



        long id = 0;
        try {
            id = db.insert(database_important.Table_Name_Accounts, database_important.Field_LastTransactionAmount,contentValues);
        } catch (Exception e) {
            Message.message_long(database_imp.context,""+e);
        }


        db.close();

        return id;

    }


    public long add_transaction_credit(String account_id , float final_balance ,float transaction_amount ,String creation_date_time,String transaction_notes , String image1,String image2,String image3) {



        final_balance = final_balance-transaction_amount;

        String final_balance_string = String.valueOf(final_balance);



        SQLiteDatabase db = database_imp.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(database_important.Field_transactionAmount,transaction_amount);
        contentValues.put(database_important.Field_transactionType,"credit");
        contentValues.put(database_important.Field_date_time,creation_date_time);
        contentValues.put(database_important.Field_transactionAccountid,account_id);
        contentValues.put(database_important.Field_image1,image1);
        contentValues.put(database_important.Field_image2,image2);
        contentValues.put(database_important.Field_image3,image3);
        contentValues.put(database_important.Field_notes,transaction_notes);
        contentValues.put(database_important.Field_tra_extra_1," ");
        contentValues.put(database_important.Field_tra_extra_2," ");
        contentValues.put(database_important.Field_tra_extra_3," ");
        contentValues.put(database_important.Field_tra_extra_4," ");
        contentValues.put(database_important.Field_tra_extra_5," ");


/*
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(database_important.Field_FinalBalance,final_balance);
        contentValues1.put(database_important.Field_LastTransactionAmount,transaction_amount);
        contentValues1.put(database_important.Field_LastTransactionDate,creation_date_time);

*/

        long id = 0;
        try {
            db.execSQL("UPDATE "+database_important.Table_Name_Accounts+" SET "+database_important.Field_FinalBalance+
                    " = "+final_balance+" WHERE "+database_important.Field_Accountid+" = "+account_id+";");

            db.execSQL("UPDATE "+database_important.Table_Name_Accounts+" SET "+database_important.Field_LastTransactionAmount+
                    " = "+transaction_amount+" WHERE "+database_important.Field_Accountid+"="+account_id+";");

            db.execSQL("UPDATE "+database_important.Table_Name_Accounts+" SET "+database_important.Field_LastTransactionDate+
                    " = '"+creation_date_time+"' WHERE "+database_important.Field_Accountid+"="+account_id+";");


            id = db.insert(database_important.Table_Name_Transactions, database_important.Field_LastTransactionAmount,contentValues);

       //not suitable     id = db.update(database_important.Table_Name_Accounts,contentValues1,"WHERE "+database_important.Field_Accountid+" = "+account_id+";",null);




        } catch (Exception e) {

            Message.message_long(database_imp.context,""+e);
        }



        db.close();

        return id;

    }


    public long add_transaction_debit(String account_id , float final_balance ,float transaction_amount ,String creation_date_time,String transaction_notes , String image1,String image2,String image3) {


        final_balance = final_balance+transaction_amount;

        String final_balance_string = String.valueOf(final_balance);


        SQLiteDatabase db = database_imp.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(database_important.Field_transactionAmount,transaction_amount);
        contentValues.put(database_important.Field_transactionType,"debit");
        contentValues.put(database_important.Field_date_time,creation_date_time);
        contentValues.put(database_important.Field_transactionAccountid,account_id);
        contentValues.put(database_important.Field_image1,image1);
        contentValues.put(database_important.Field_image2,image2);
        contentValues.put(database_important.Field_image3,image3);
        contentValues.put(database_important.Field_notes,transaction_notes);
        contentValues.put(database_important.Field_tra_extra_1," ");
        contentValues.put(database_important.Field_tra_extra_2," ");
        contentValues.put(database_important.Field_tra_extra_3," ");
        contentValues.put(database_important.Field_tra_extra_4," ");
        contentValues.put(database_important.Field_tra_extra_5," ");


        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(database_important.Field_FinalBalance,final_balance);
        contentValues1.put(database_important.Field_LastTransactionAmount,transaction_amount);
        contentValues1.put(database_important.Field_LastTransactionDate,creation_date_time);




        long id = 0;
        try {

          db.execSQL("UPDATE "+database_important.Table_Name_Accounts+" SET "+database_important.Field_FinalBalance+
                    " = "+final_balance+" WHERE "+database_important.Field_Accountid+" = "+account_id+";");

            db.execSQL("UPDATE "+database_important.Table_Name_Accounts+" SET "+database_important.Field_LastTransactionAmount+
                    " = "+transaction_amount+" WHERE "+database_important.Field_Accountid+"="+account_id+";");

            db.execSQL("UPDATE "+database_important.Table_Name_Accounts+" SET "+database_important.Field_LastTransactionDate+
                    " = '"+creation_date_time+"' WHERE "+database_important.Field_Accountid+"="+account_id+";");

            id = db.insert(database_important.Table_Name_Transactions, database_important.Field_LastTransactionAmount,contentValues);

  //not suitable          id = db.update(database_important.Table_Name_Accounts,contentValues1," WHERE "+database_important.Field_Accountid+"="+account_id+";",null);


        } catch (Exception e) {
            Message.message_long(database_imp.context,""+e);
        }


        db.close();

        return id;

    }




    public ArrayList<String> getallTransactions(String active_account_id) {

        Message.message_long(database_imp.context,"get all Transactions called");
        ArrayList<String> data = new ArrayList<String>();
        String[] col = {database_important.Field_transactionId,database_important.Field_transactionAccountid,database_important.Field_transactionType,database_important.Field_notes,database_important.Field_date_time};
        SQLiteDatabase db = database_imp.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+database_important.Table_Name_Transactions+" Where "+database_important.Field_transactionAccountid+" = "+active_account_id+";",null);

    //    Cursor cursor = db.query(database_important.Table_Name_Transactions,col,database_important.Field_transactionAccountid+" = ?",new String[]{active_account_id},null,null,database_important.Field_transactionId);


        if (cursor!=null) {
            cursor.move(0);

        }
        while (cursor.moveToNext()) {

            int index_transaction_id   = cursor.getColumnIndex(database_important.Field_transactionId);
            int index_transaction_type = cursor.getColumnIndex(database_important.Field_transactionType);
            int index_transaction_amount = cursor.getColumnIndex(database_important.Field_transactionAmount);
            int index_transaction_notes = cursor.getColumnIndex(database_important.Field_notes);

            int index_transaction_date = cursor.getColumnIndex(database_important.Field_date_time);


            String transaction_id_listview = "5426652632"+cursor.getString(index_transaction_id);

            String transaction_type_amount = cursor.getString(index_transaction_type)+" "+cursor.getString(index_transaction_amount);

            String transaction_notes = cursor.getString(index_transaction_notes);

            String transaction_date = cursor.getString(index_transaction_date);



            String row = transaction_id_listview+"\n"+transaction_type_amount+"\n"+transaction_notes+"\n"+transaction_date;




            data.add(row);


        }

        db.close();
        cursor.close();

        Collections.reverse(data);         //reversing the list of transactions.

        return data;

    }













    public ArrayList<String> getallAccounts() {

        Message.message_long(database_imp.context,"get all Accounts called");
        ArrayList<String> data = new ArrayList<String>();
        String[] col = {database_important.Field_Accountid,database_important.Field_AccountName,database_important.Field_InitialBalance,
                database_important.Field_LastTransactionAmount,database_important.Field_FinalBalance,database_important.Field_LastTransactionDate};

        SQLiteDatabase db = database_imp.getReadableDatabase();
        Cursor cursor = db.query(database_important.Table_Name_Accounts,col,null,null,null,null,database_important.Field_AccountName);


        if (cursor!=null) {
            cursor.move(0);

        }
        while (cursor.moveToNext()) {

            int index_id = cursor.getColumnIndex(database_important.Field_Accountid);
            int index_acccount_name = cursor.getColumnIndex(database_important.Field_AccountName);
            int index_Final_balance = cursor.getColumnIndex(database_important.Field_FinalBalance);

            int index_creation_date = cursor.getColumnIndex(database_important.Field_LastTransactionDate);


            String cname = cursor.getString(index_acccount_name);

            String cbalance = cursor.getString(index_Final_balance);

            String cdate = cursor.getString(index_creation_date);



            String row = cname.toUpperCase()+"\n"+cbalance+"\n"+cdate;




            data.add(row);


        }

        db.close();
        cursor.close();
        return data;

    }


    public ArrayList<String> getaccountstatus(String active_accountid) {

        ArrayList<String> Account_status = new ArrayList<String>();

        SQLiteDatabase db = database_imp.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+database_important.Table_Name_Accounts+" Where "+database_important.Field_Accountid+" = "+active_accountid+";",null);

        if(cursor!=null)
        cursor.moveToFirst();


            int index_acccount_name = cursor.getColumnIndex(database_important.Field_AccountName);
            String cname = cursor.getString(index_acccount_name);
            Account_status.add(cname);


            String initial_balance = cursor.getString(cursor.getColumnIndex(database_important.Field_InitialBalance));
            Account_status.add(initial_balance);


            String account_description = cursor.getString(cursor.getColumnIndex(database_important.Field_account_description));
            Account_status.add(account_description);


            String last_transaction_amt = cursor.getString(cursor.getColumnIndex(database_important.Field_LastTransactionAmount));
            Account_status.add(last_transaction_amt);


            String final_balance = cursor.getString(cursor.getColumnIndex(database_important.Field_FinalBalance));
            Account_status.add(final_balance);

            String last_transaction_date = cursor.getString(cursor.getColumnIndex(database_important.Field_LastTransactionDate));
            Account_status.add(last_transaction_date);


            String account_creation_date = cursor.getString(cursor.getColumnIndex(database_important.Field_Acc_creation_date));
            Account_status.add(account_creation_date);




        db.close();
        cursor.close();
        return Account_status;

    }






    public String getaccountID(String account_name) {



        String accountid="";

        SQLiteDatabase db = database_imp.getReadableDatabase();

        Cursor cursor =   db.rawQuery("SELECT "+database_important.Field_Accountid+" FROM "+database_important.Table_Name_Accounts+" WHERE "+database_important.Field_AccountName+" LIKE '"+account_name+"';",null);



        cursor.moveToFirst();
        accountid = cursor.getString(cursor.getColumnIndex(database_important.Field_Accountid));



        cursor.close();

        return accountid;
    }

    public String getimage1_name(String active_transaction_id) {

        SQLiteDatabase db = database_imp.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+database_important.Field_image1+" FROM "+database_important.Table_Name_Transactions+" WHERE "+database_important.Field_transactionId+" = "+active_transaction_id+";",null);
        cursor.moveToFirst();

        String image1_name = cursor.getString(cursor.getColumnIndex(database_important.Field_image1));



        return image1_name;
    }


    public String getimage2_name(String active_transaction_id) {

        SQLiteDatabase db = database_imp.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+database_important.Field_image2+" FROM "+database_important.Table_Name_Transactions+" WHERE "+database_important.Field_transactionId+" = "+active_transaction_id+";",null);
        cursor.moveToFirst();

        String image2_name = cursor.getString(cursor.getColumnIndex(database_important.Field_image2));



        return image2_name;
    }



    public String getimage3_name(String active_transaction_id) {

        SQLiteDatabase db = database_imp.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+database_important.Field_image3+" FROM "+database_important.Table_Name_Transactions+" WHERE "+database_important.Field_transactionId+" = "+active_transaction_id+";",null);
        cursor.moveToFirst();

        String image3_name = cursor.getString(cursor.getColumnIndex(database_important.Field_image3));





        return image3_name;
    }









    static class database_important extends SQLiteOpenHelper {

        private Context context;
        private static final String Database_Name = "ImageLedger.db";
        private static final int Database_Version = 1;

        private static final String Table_Name_Accounts = "ACCOUNTS";
        private static final String Field_Accountid = "_id_account";
        private static final String Field_AccountName = "account_name";
        private static final String Field_InitialBalance = "initial_balance";
        private static final String Field_Acc_creation_date = "acc_creation_date";
        private static final String Field_account_description = "account_description";
        private static final String Field_LastTransactionAmount = "last_transaction_amount";
        private static final String Field_FinalBalance = "final_balance";
        private static final String Field_LastTransactionDate = "last_transaction_date";
        private static final String Field_acc_extra_1 = "acc_extra_1";
        private static final String Field_acc_extra_2 = "acc_extra_2";
        private static final String Field_acc_extra_3 = "acc_extra_3";
        private static final String Field_acc_extra_4 = "acc_extra_4";
        private static final String Field_acc_extra_5 = "acc_extra_5";









        private static final String Table_Name_Transactions = "TRANSACTIONS";
        private static final String Field_transactionId = "_id_transaction";
        private static final String Field_transactionAccountid = "transaction_account_id";
        private static final String Field_notes = "transaction_notes";
        private static final String Field_transactionType = "credit_or_debit";
        private static final String Field_image1 = "image1";
        private static final String Field_image2 = "image2";
        private static final String Field_image3 = "image3";
        private static final String Field_transactionAmount = "transaction_amount";
        private static final String Field_date_time = "date_time";
        private static final String Field_tra_extra_1 = "tra_extra_1";
        private static final String Field_tra_extra_2 = "tra_extra_2";
        private static final String Field_tra_extra_3 = "tra_extra_3";
        private static final String Field_tra_extra_4 = "tra_extra_4";
        private static final String Field_tra_extra_5 = "tra_extra_5";










        String create_accounts ="CREATE TABLE IF NOT EXISTS " + Table_Name_Accounts + "(" +Field_Accountid+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +Field_AccountName+ " VARCHAR(100) NOT NULL,"
                +Field_InitialBalance+ " INTEGER,"
                +Field_Acc_creation_date+ " TEXT,"
                +Field_account_description+ " TEXT,"
                +Field_acc_extra_1+ " TEXT,"
                +Field_acc_extra_2+ " TEXT,"
                +Field_acc_extra_3+ " TEXT,"
                +Field_acc_extra_4+ " TEXT,"
                +Field_acc_extra_5+ " TEXT,"
                +Field_LastTransactionAmount+" INTEGER,"
                +Field_FinalBalance+" INTEGER,"
                +Field_LastTransactionDate+" VARCHAR(100));";




        String create_transactions ="CREATE TABLE IF NOT EXISTS " + Table_Name_Transactions + "(" +Field_transactionId+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +Field_transactionAccountid+" INTEGER,"
                +Field_transactionAmount+" INTEGER,"
                +Field_notes+" TEXT,"
                +Field_transactionType+" TEXT,"
                +Field_tra_extra_1+ " TEXT,"
                +Field_tra_extra_2+ " TEXT,"
                +Field_tra_extra_3+ " TEXT,"
                +Field_tra_extra_4+ " TEXT,"
                +Field_tra_extra_5+ " TEXT,"
                +Field_image1+" TEXT,"
                +Field_image2+" TEXT,"
                +Field_image3+" TEXT,"
                +Field_date_time+" VARCHAR(100) NOT NULL);";







        public database_important(Context context) {
            super(context,Database_Name,null,Database_Version);
            this.context = context;

            Message.message_long(context,"constructor sqlite called");


        }

        @Override
        public void onCreate(SQLiteDatabase db) {


            // sqLiteDatabase = new database_important_adapter(context).getWritableDatabase();

            try {


                db.execSQL(create_accounts);


            } catch (Exception e) {
                Message.message_long(context,""+e);
            }



            try {

                db.execSQL(create_transactions);


            }
            catch (Exception e){


                Message.message_long(context,""+e);

            }





        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)

        {


            try {


                Message.message_long(context,"onupgrade sqlite called");

                db.execSQL("DROP TABLE IF EXISTS " + Table_Name_Accounts);

                db.execSQL("DROP TABLE IF EXISTS " + Table_Name_Transactions);

                onCreate(db);
            } catch (SQLException e) {

                Message.message_long(context,""+e);

            }


        }





    }


}
