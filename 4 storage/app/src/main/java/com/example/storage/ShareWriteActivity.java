package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.storage.util.DateUtil;

public class ShareWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mShared; // 声明一个共享参数对象
    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private boolean bMarried = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        Spinner sp_married = findViewById(R.id.sp_married);
        sp_married.setOnItemSelectedListener(new TypeSelectedListener());
        findViewById(R.id.btn_save).setOnClickListener(this);

        // 从share.xml中获取共享参数对象
        mShared = getSharedPreferences("share", MODE_PRIVATE);
    }

    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bMarried = (arg2 == 0) ? false : true;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = et_name.getText().toString();
            String age = et_age.getText().toString();
            String height = et_height.getText().toString();
            String weight = et_weight.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请先填写姓名");
                return;
            } else if (TextUtils.isEmpty(age)) {
                showToast("请先填写年龄");
                return;
            } else if (TextUtils.isEmpty(height)) {
                showToast("请先填写身高");
                return;
            } else if (TextUtils.isEmpty(weight)) {
                showToast("请先填写体重");
                return;
            }

            SharedPreferences.Editor editor = mShared.edit(); // 获得编辑器的对象
            editor.putString("name", name); // 添加一个名叫name的字符串参数
            editor.putInt("age", Integer.parseInt(age)); // 添加一个名叫age的整型参数
            editor.putLong("height", Long.parseLong(height)); // 添加一个名叫height的长整型参数
            editor.putFloat("weight", Float.parseFloat(weight)); // 添加一个名叫weight的浮点数参数
            editor.putBoolean("married", bMarried); // 添加一个名叫married的布尔型参数
            editor.putString("update_time", DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
            editor.commit(); // 提交编辑器中的修改

            Intent intent = new Intent(this, ShareReadActivity.class);
            // 期望接收下个页面的返回数据
            startActivityForResult(intent, 0);
        }
    }

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}