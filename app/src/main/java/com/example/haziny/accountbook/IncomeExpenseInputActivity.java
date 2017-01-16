package com.example.haziny.accountbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class IncomeExpenseInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expense_input);
/*
        final DBManager dbManager = new DBManager(getApplicationContext(), "AccountBook.db", null, 1);
        // DB에 저장 될 속성을 입력받는다
        final EditText editPrice = (EditText) findViewById(R.id.editPrice);

        // Insert
        Button btnInsert = (Button) findViewById(R.id.btnExpense);
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String name = "test";
                Integer nIconidx = 0;
                Integer nClassification = 0;
                String price = editPrice.getText().toString();
                dbManager.insert("insert into IncomeExpense values(null, '" + name + "', " + price + ", " + nIconidx + ", " + nClassification + ");");

            }
        });
        */
    }

    public void onClickExpense(View v)
    {
        final DBManager dbManager = new DBManager(getApplicationContext(), "AccountBook.db", null, 1);
        // DB에 저장 될 속성을 입력받는다
        final EditText editPrice = (EditText) findViewById(R.id.editPrice);

        String name = "test";
        Integer nIconidx = 0;
        Integer nClassification = 0;
        String price = editPrice.getText().toString();
        dbManager.insert("insert into IncomeExpense values(null, '" + name + "', " + price + ", " + nIconidx + ", " + nClassification + ");");

        Intent intent = new Intent(this, IncomeExpenseSelItem.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EditText editPrice = (EditText) findViewById(R.id.editPrice);
        editPrice.requestFocus();

        //키보드 보이게 하는 부분
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }
}
