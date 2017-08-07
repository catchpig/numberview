package com.zhuazhu.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuazhu.numberview.NumberView;

public class MainActivity extends AppCompatActivity {
    private NumberView mNumberView;
    private TextView textView;
    private EditText maxlength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.money);
        mNumberView = (NumberView) findViewById(R.id.number);
        maxlength = (EditText) findViewById(R.id.maxlengthEdit);
        mNumberView.setMaxLength(9);
//        mNumberView.setNumberDecimalPoint(true);
        mNumberView.setOnMoneyListener(new NumberView.OnMoneyListener() {
            @Override
            public void onMoneyValue(String money) {
                textView.setText(money);
            }
        });
//        mNumberView.setCustomTextVisible(View.VISIBLE);
//        mNumberView.setCustomText("清除");
//        mNumberView.setCustomTextSize(15);
//        mNumberView.setOnNumberListener(new NumberView.OnNumberListener() {
//            @Override
//            public void onNumber(String txt) {
//                Toast.makeText(MainActivity.this,txt,Toast.LENGTH_LONG).show();
//            }
//        });
//        mNumberView.setOnBackSpaceListener(new NumberView.OnBackSpaceListener() {
//            @Override
//            public void onBackSpace() {
//                Toast.makeText(MainActivity.this,"退格",Toast.LENGTH_LONG).show();
//            }
//        });
//        mNumberView.setOnCustomListener(new NumberView.OnCustomListener() {
//            @Override
//            public void onCustom() {
//                Toast.makeText(MainActivity.this,"自定义控件",Toast.LENGTH_LONG).show();
//            }
//        });

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumberView.setMaxLength(Integer.valueOf(maxlength.getText().toString().trim()));
            }
        });
    }
}
