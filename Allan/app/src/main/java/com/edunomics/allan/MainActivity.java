package com.edunomics.allan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private EditText Username, Password;
    private Button LoginBtn;
    private String Name, Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        LoginBtn = findViewById(R.id.login_button);
        sp = getSharedPreferences("mypref", 0);
        edit = sp.edit();
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = Username.getText().toString();
                Pass = Password.getText().toString();
                if (Name.isEmpty()) {
                    Username.setError("Required");
                    Username.requestFocus();
                } else if (Pass.isEmpty()) {
                    Password.setError("Required");
                    Password.requestFocus();
                } else {
                    if (Name.equals("admin") && Pass.equals("admin")) {
                        edit.putBoolean("loggedIn", true);
                        edit.apply();
                        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username: admin, Password: admin", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
