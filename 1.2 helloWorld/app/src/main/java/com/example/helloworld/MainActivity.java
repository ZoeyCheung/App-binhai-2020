package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取id为text_title的TextView控件
        TextView text_title = findViewById(R.id.text_title);
        //设置文字内容
        text_title.setText("你好，世界！！！");
        //设置文字颜色为红色
        text_title.setTextColor(Color.RED);
        //设置文字大小
        text_title.setTextSize(30);
    }


}