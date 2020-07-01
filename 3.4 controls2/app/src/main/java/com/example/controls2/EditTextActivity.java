package com.example.controls2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.controls2.util.ViewUtil;

public class EditTextActivity extends AppCompatActivity {

    // 定义自动完成的提示文本数组
    private String[] hintArray = {"first ", "first time", "first coding", "first test", "second", "second time"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        // 从布局文件中获取名叫et_hint的编辑框
        EditText et_hint = findViewById(R.id.et_hint);
        // 从布局文件中获取名叫et_border的编辑框
        EditText et_border = findViewById(R.id.et_border);
        // 给et_hint编辑框添加文本变化监听器
        et_hint.addTextChangedListener(new JumpTextWatcher(et_hint, et_border));
        // 从布局文件中获取名叫et_phone的手机号码编辑框
        EditText et_phone = findViewById(R.id.et_phone);
        // 从布局文件中获取名叫et_password的密码编辑框
        EditText et_password = findViewById(R.id.et_password);
        // 给手机号码编辑框添加文本变化监听器
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone));
        // 给密码编辑框添加文本变化监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password));

        // 从布局文件中获取名叫ac_text的自动完成编辑框
        AutoCompleteTextView ac_text = findViewById(R.id.ac_text);
        // 声明一个自动完成时下拉展示的数组适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.item_dropdown, hintArray);
        // 设置自动完成编辑框的数组适配器
        ac_text.setAdapter(adapter);
    }

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量
        private CharSequence mStr; // 声明一个文本串

        public HideTextWatcher(EditText v) {
            //super()从子类中调用父类的构造方法
            super();
            mView = v;
            // 通过反射机制获取编辑框的最大长度
            mMaxLength = ViewUtil.getMaxLength(v);
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mStr = s;
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            //还没输入
            if (mStr == null || mStr.length() == 0)
                return;
            // 输入文本达到11位（如手机号码）时关闭输入法
            if (mStr.length() == 11 && mMaxLength == 11) {
                ViewUtil.hideAllInputMethod(EditTextActivity.this);
            }
            // 输入文本达到6位（如登录密码）时关闭输入法
            if (mStr.length() == 6 && mMaxLength == 6) {
                ViewUtil.hideOneInputMethod(EditTextActivity.this, mView);
            }
        }
    }

    // 定义一个编辑框监听器，在输入回车符时自动跳到下一个控件
    private class JumpTextWatcher implements TextWatcher {
        private EditText mThisView;  // 声明当前的编辑框对象
        private View mNextView; // 声明下一个视图对象

        public JumpTextWatcher(EditText vThis, View vNext) {
            super();
            mThisView = vThis;

            //不是最后一项则赋值
            if (vNext != null) {
                mNextView = vNext;
            }
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            // 发现输入回车符或换行符
            if (str.contains("\r") || str.contains("\n")) {
                // 去掉回车符和换行符
                mThisView.setText(str.replace("\r", "").replace("\n", ""));
                if (mNextView != null) {
                    // 让下一个视图获得焦点，即将光标移到下个视图
                    mNextView.requestFocus();
                    // 如果下一个视图是编辑框，则将光标自动移到编辑框的文本末尾
                    if (mNextView instanceof EditText) {
                        EditText et = (EditText) mNextView;
                        // 让光标自动移到编辑框内部的文本末尾
                        // 方式一：直接调用EditText的setSelection方法
                        et.setSelection(et.getText().length());
                        // 方式二：调用Selection类的setSelection方法
                        //Editable edit = et.getText();
                        //Selection.setSelection(edit, edit.length());
                    }
                }
            }
        }
    }
}