package ru.nikijava.exercise1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etMessage;
    private Button btnPreview;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnPreview.setOnClickListener(this::showPreview);
        btnClear.setOnClickListener(v -> etMessage.setText(""));
    }

    private void initView() {
        etMessage = findViewById(R.id.etMessage);
        btnPreview = findViewById(R.id.btnPreview);
        btnClear = findViewById(R.id.btnClear);
    }

    private String getMessage() {
        return etMessage.getText().toString();
    }

    private void showKeyboard() {
        etMessage.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.showSoftInput(etMessage, InputMethodManager.SHOW_IMPLICIT);
    }


    @Override
    protected void onResume() {
        super.onResume();
        etMessage.postDelayed(this::showKeyboard, 300);
    }

    private void showPreview(View v) {
        if (!etMessage.getText().toString().trim().equals("")) {
            SendEmailActivity.start(getMessage(), this);
        } else {
            Toast.makeText(this, R.string.message_empty_enter, Toast.LENGTH_LONG).show();
        }
    }
}
