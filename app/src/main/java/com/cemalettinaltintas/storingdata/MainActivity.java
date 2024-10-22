package com.cemalettinaltintas.storingdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    SharedPreferences  sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        sharedPreferences=this.getSharedPreferences("com.cemalettinaltintas.storingdata", Context.MODE_PRIVATE);
        int storedAge=sharedPreferences.getInt("storedAge",0);

        if (storedAge==0){
            textView.setText("Your age :");
        }else {
            textView.setText("Your age :"+storedAge);
        }
        Toast.makeText(this,"Welcome to My Application",Toast.LENGTH_LONG).show();
    }
    public void save(View view){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Save");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!editText.getText().toString().matches("")){
                    int userAge=Integer.parseInt(editText.getText().toString());
                    textView.setText("Your age :"+userAge);
                    sharedPreferences.edit().putInt("storedAge",userAge).apply();

                    Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
                }
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Not Saved",Toast.LENGTH_LONG).show();
            }
        });
        alert.show();
    }
    public void delete(View view){
        int storedData=sharedPreferences.getInt("storedAge",0);
        if (storedData!=0){
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your age :");
        }
    }
}