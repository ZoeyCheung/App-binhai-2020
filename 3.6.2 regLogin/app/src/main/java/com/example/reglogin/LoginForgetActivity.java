package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reglogin.bean.UserInfo;
import com.example.reglogin.database.UserDBHelper;
import com.example.reglogin.util.DateUtil;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_newpassword; // 声明一个编辑框对象
    private EditText et_ensurepassword; // 声明一个编辑框对象
    private EditText et_verification; // 声明一个编辑框对象
    private String myverification; // 验证码
    private String myPhone; // 手机号码
    private Button bt_confirm;

    private UserDBHelper myHelper; // 声明一个用户数据库的帮助器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);

        // 从布局文件中获取名叫et_password_first的编辑框
        et_newpassword = findViewById(R.id.et_newpassword);
        // 从布局文件中获取名叫et_password_second的编辑框
        et_ensurepassword = findViewById(R.id.et_ensurepassword);
        // 从布局文件中获取名叫et_verifycode的编辑框
        et_verification = findViewById(R.id.et_verification);

        bt_confirm=findViewById(R.id.bt_confirm);

        findViewById(R.id.bt_verification).setOnClickListener(this);
        findViewById(R.id.bt_confirm).setOnClickListener(this);

        // 从前一个页面获取要修改密码的手机号码
        myPhone = getIntent().getStringExtra("phone");

    }

     @Override
    protected void onResume() {
        super.onResume();
        // 获得用户数据库帮助器的一个实例
        myHelper = UserDBHelper.getInstance(this, 2);
        // 恢复页面，则打开数据库连接
        myHelper.openWriteLink();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 暂停页面，则关闭数据库连接
        myHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_verification) { // 点击了“获取验证码”按钮
            if (myPhone == null || myPhone.length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            // 生成六位随机数字的验证码
            myverification = String.format("%06d", (int) ((Math.random() * 9 + 1) * 100000));
            // 弹出提醒对话框，提示用户六位验证码数字
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号" + myPhone + "，本次验证码是" + myverification + "，请输入验证码");
            builder.setPositiveButton("好的", null);
            AlertDialog alert = builder.create();
            alert.show();
        } else if (v.getId() == R.id.bt_confirm) { // 点击了“确定”按钮
            String newpassword = et_newpassword.getText().toString();
            String ensurepassword = et_ensurepassword.getText().toString();
            if (newpassword.length() < 8 || ensurepassword.length() < 8) {
                Toast.makeText(this, "请输入正确的新密码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!newpassword.equals(ensurepassword)) {
                Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!et_verification.getText().toString().equals(myverification)) {
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                // 弹出提醒对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请重新输入验证码");
                builder.setMessage("手机号" + myPhone + "，本次所输入的验证码错误" +  "，请重新输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                UserInfo info = new UserInfo();
                info.phone = myPhone;
                info.pwd = newpassword;
                info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
                myHelper.update(info, "phone=" + myPhone);

                Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("密码修改成功");
                builder.setMessage("手机号" + myPhone + "，密码修改成功");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                //alert.show();
                // 把修改好的新密码返回给前一个页面

                //if (v.getId() == R.id.bt_register) {
                    Intent intent = new Intent();
                    intent.putExtra("new_password", newpassword);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                //}
            }
        }
    }
}