package com.example.haziny.accountbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.haziny.accountbook.MainActivity.KEY_NAME;
import static com.example.haziny.accountbook.MainActivity.KEY_PRICE;

public class MainActivity extends AppCompatActivity
{
    DBManager mDBManager;
//    SQLiteDatabase db;
    Cursor cursor;
    MyCursorAdapter myAdapter;

    private final String dbName = "AccountBook";
    private final String tableName = "IncomeExpense";
    final static String KEY_NAME = "name";
    final static String KEY_PRICE = "price";

    ListView lvIncomeExpenseList;
    List IncomeExpenseList = new ArrayList();
    SQLiteDatabase AccountBookDB = null;
//    ListAdapter adapter;

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvIncomeExpenseList = (ListView) findViewById(R.id.listView);

        mDBManager = new DBManager(getApplicationContext(), "AccountBook.db", null, 1);
        AccountBookDB = mDBManager.getWritableDatabase();

        //mDBManager.createTable();

        try
        {
                //AccountBookDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
               //AccountBookDB.close();
        }
        catch (SQLiteException se)
        {
                Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("", se.getMessage());
        }

        ShowList();
    }

    protected void ShowList()
    {
       try
       {
           SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

           //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
//           Cursor cursor = ReadDB.rawQuery("SELECT * FROM " + tableName, null);
            Cursor cursor = AccountBookDB.rawQuery("select * from IncomeExpense", null);
           IncomeExpense IETest= null;

           if (cursor != null)
           {
                if (cursor.moveToFirst())
                {
                    do
                    {
                        IETest = new IncomeExpense();
                        IETest.set_id(cursor.getInt(0));
                        IETest.setName(cursor.getString(1));
                        IETest.setAge(cursor.getInt(2));
                        IETest.setPhone(cursor.getInt(3));
                        IETest.setPhone(cursor.getInt(4));

                        //ArrayList에 추가합니다..
                        IncomeExpenseList.add(IETest);
                    } while (cursor.moveToNext());
                }
            }

           //화면에 보여주기 위해 Listview에 연결a합니다.
           lvIncomeExpenseList.setAdapter(myAdapter);

           SimpleCursorAdapter Adapter = null;
           Adapter = new SimpleCursorAdapter(this, android.R.Iayout.simple_list_item_2, );


            ReadDB.close();
        }
       catch (SQLiteException se)
       {
            Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }
    }

    public void onClickIncomeExpense(View v)
    {
        Intent intent = new Intent(this, InputIncomExpense.class);
        startActivity(intent);
    }


    class MyCursorAdapter extends CursorAdapter
    {
        @SuppressWarnings("deprecation")
        public MyCursorAdapter(Context context, Cursor c)
        {
            super(context, c);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor)
        {
            TextView tvName = (TextView) view.findViewById( R.id.tv_name );
            TextView tvAge = (TextView) view.findViewById( R.id.tv_price );
            String name = cursor.getString( cursor.getColumnIndex( KEY_NAME ) );
            String price = cursor.getString( cursor.getColumnIndex( KEY_PRICE) );
            Log.d("스트링 확인", name + ", " + price);
            tvName.setText( name );
            tvAge.setText( price );
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from( context );
            View v = inflater.inflate( R.layout.list_item, parent, false );
            return v;
        }

    }
}

