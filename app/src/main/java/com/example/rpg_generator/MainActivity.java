package com.example.rpg_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.createbutton);
        Button close = (Button)findViewById(R.id.close);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Create.class));
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
    public boolean onTouchEvent(MotionEvent touchevent){
    switch (touchevent.getAction()){
        case MotionEvent.ACTION_DOWN:
            x1=touchevent.getX();
            y1=touchevent.getY();
            break;
        case  MotionEvent.ACTION_UP:
            x2=touchevent.getX();
            y2=touchevent.getY();
            if(x1>x2+150){
                Intent i = new Intent(MainActivity.this,Create.class);
                startActivity(i);
            }
            break;
    }
    return false;
    }

}
