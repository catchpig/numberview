package com.zhuazhu.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhuazhu.numberview.NumberView;

public class MainActivity extends AppCompatActivity {
    private NumberView mNumberView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumberView = (NumberView) findViewById(R.id.number);
        mNumberView.setTextSize(20);
        mNumberView.setCustomTextVisible(View.VISIBLE);
        mNumberView.setCustomText("清除");
        mNumberView.setCustomTextSize(15);
        mNumberView.setOnNumberListener(new NumberView.OnNumberListener() {
            @Override
            public void onNumber(String txt) {
                Toast.makeText(MainActivity.this,txt,Toast.LENGTH_LONG).show();
            }
        });
        mNumberView.setOnBackSpaceListener(new NumberView.OnBackSpaceListener() {
            @Override
            public void onBackSpace() {
                Toast.makeText(MainActivity.this,"退格",Toast.LENGTH_LONG).show();
            }
        });
        mNumberView.setOnCustomListener(new NumberView.OnCustomListener() {
            @Override
            public void onCustom() {
                Toast.makeText(MainActivity.this,"自定义控件",Toast.LENGTH_LONG).show();
            }
        });
    }
}
