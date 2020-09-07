package com.example.worthymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.butn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });



        button2 = (Button) findViewById(R.id.butn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
                //Toast.makeText(getApplicationContext(),"Button two Clicked",Toast.LENGTH_LONG).show();
            }
        });



        button3 = (Button) findViewById(R.id.butn3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Button three Clicked",Toast.LENGTH_LONG).show();
                openActivity3();
            }
        });

    }
    public void openActivity2(){
        Intent intent = new Intent(this, MapsActivity1.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this, MapsActivity2.class);
        startActivity(intent);
    }
    public void openActivity(){
        Intent intent = new Intent(this, MapsActivity3.class);
        startActivity(intent);
    }
}