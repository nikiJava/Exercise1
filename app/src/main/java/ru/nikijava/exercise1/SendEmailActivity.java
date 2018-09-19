package ru.nikijava.exercise1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendEmailActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "key_message";
    private static final String emailTo = "nikrestinpiece@gmail.com";

    private Button btnEmail;
    private TextView tvMessage;

    private String message;

    public static void start(String message, Context context) {
        Intent intent = new Intent(context, SendEmailActivity.class);
        intent.putExtra(KEY_MESSAGE, message);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        initView();

        message = getIntent().getStringExtra(KEY_MESSAGE);
        tvMessage.setText(message);

        btnEmail.setOnClickListener(v -> startEmailClient());
    }

    private void initView() {
        btnEmail = findViewById(R.id.btnEmail);
        tvMessage = findViewById(R.id.tvMessage);
    }

    private void startEmailClient() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailTo));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello, guys!");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.message_no_email_client, Toast.LENGTH_LONG).show();
        }
    }

}
