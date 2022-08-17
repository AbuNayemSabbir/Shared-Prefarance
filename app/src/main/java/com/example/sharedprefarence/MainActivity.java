package com.example.sharedprefarence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView detailsTextView;
    private EditText userNameEditText,passwordEditText;
    private Button saveBtn,loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsTextView=findViewById(R.id.detailsTextViewId);
        userNameEditText=findViewById(R.id.userNameId);
        passwordEditText=findViewById(R.id.passwordId);
        saveBtn=findViewById(R.id.saveBtnId);
        loadBtn=findViewById(R.id.loadBtnId);

        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.saveBtnId)
        {
            String username=userNameEditText.getText().toString();
            String password=passwordEditText.getText().toString();

            if (username.equals("") && password.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Please Enter Some Data",Toast.LENGTH_SHORT).show();
            }
            else{
                SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("usernameKey",username);
                editor.putString("passwordKey",password);
                editor.commit();

                Toast.makeText(getApplicationContext(),"Data Save Successfully ",Toast.LENGTH_SHORT).show();

            }




        }
        else if(view.getId()==R.id.loadBtnId)
        {
            SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){
                String username=sharedPreferences.getString("usernameKey","Data not found");
                String password=sharedPreferences.getString("passwordKey","Password not found");
                detailsTextView.setText(username + " \n " + password);
            }

        }
    }
}