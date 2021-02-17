package com.example.rpg_generator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Random;

public class Create extends AppCompatActivity {
    float x1,x2,y1,y2;
    Random random = new Random();
    int randomNumberType = random.nextInt(5 - 1) + 1;
    //Firebase firebase;
     double hits=0;
     int level=0;
     int finalValue=0;


    DecimalFormat formazas = new DecimalFormat("###,###.##");


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Read values from the "savedInstanceState"-object and put them in your textview
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
      //  Firebase.setAndroidContext(this);


        final EditText paramether = (EditText) findViewById(R.id.paramether);
        final Button General = (Button) findViewById(R.id.buttonGenerate);
        final Button saveToJson = (Button) findViewById(R.id.saveToJson);

        final Button btnReturn1 = (Button) findViewById(R.id.button);

        final TextView textViewLevel = (TextView) findViewById(R.id.textViewLevel);
        final TextView textViewType = (TextView) findViewById(R.id.textViewType);
        final TextView textViewHit = (TextView) findViewById(R.id.textViewHit);
        final TextView mettol = (TextView) findViewById(R.id.mettol);
        textViewType.setVisibility(View.INVISIBLE);
        textViewLevel.setVisibility(View.INVISIBLE);
        textViewHit.setVisibility(View.INVISIBLE);

      //KEPEK
        final ImageView imageView3 = (ImageView) findViewById(R.id.imageMage);
        final ImageView imageSupport = (ImageView) findViewById(R.id.imageSupport);
        final ImageView imageWarrior = (ImageView) findViewById(R.id.imageWarrior);
        final ImageView imageTank = (ImageView) findViewById(R.id.imageTank);
        final ImageView imageNinja = (ImageView) findViewById(R.id.imageNinja);

        imageView3.setVisibility(View.GONE);
        imageSupport.setVisibility(View.GONE);
        imageNinja.setVisibility(View.GONE);
        imageTank.setVisibility(View.GONE);
        imageWarrior.setVisibility(View.GONE);


//back to the main page button
        btnReturn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        saveToJson.setVisibility(View.INVISIBLE);

        General.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override


            public void onClick(View v) {



                //firebase
                String value = paramether.getText().toString();
                 finalValue = Integer.parseInt(value);

                if (finalValue > 5 || finalValue < 1 ) {
                    Toast.makeText(getApplicationContext(), "1 és 5 közötti számot :)", Toast.LENGTH_LONG).show();

                } else {
                    //generate level
                    //generate Random cast
                    // generate Random weapon
                    textViewLevel.setText("A karakter szintje: " + generateLevel(100, 1));
                    textViewType.setText("A karakter egy " + generateType(5, 1));
                    textViewHit.setText("A karakter sebzése " + formazas.format(hits) + " hp");

                    EditText editTextCity = (EditText) findViewById(R.id.editTextCity);
                    EditText editTextBackground = (EditText) findViewById(R.id.editTextBackground);
                    EditText editTextName = (EditText) findViewById(R.id.editTextName);

                    General.setVisibility(View.INVISIBLE);
                    saveToJson.setVisibility(View.VISIBLE);

                    editTextCity.setVisibility(View.GONE);
                    editTextBackground.setVisibility(View.GONE);
                    editTextName.setVisibility(View.GONE);


                    //lathatalan?
                    if (textViewType.getVisibility() != View.VISIBLE) {
                        textViewType.setVisibility(View.VISIBLE);
                        textViewLevel.setVisibility(View.VISIBLE);
                        textViewHit.setVisibility(View.VISIBLE);
                        editTextCity.setVisibility(View.VISIBLE);
                        editTextBackground.setVisibility(View.VISIBLE);
                        paramether.setVisibility(View.GONE); //parameter
                        editTextName.setVisibility(View.VISIBLE);
                        mettol.setVisibility(View.INVISIBLE);
                        if (randomNumberType == 1) {

                            imageWarrior.setVisibility(View.VISIBLE);
                        } else if (randomNumberType == 2) {
                            imageNinja.setVisibility(View.VISIBLE);
                        } else if (randomNumberType == 3) {
                            imageView3.setVisibility(View.VISIBLE);
                        } else if (randomNumberType == 4) {
                            imageSupport.setVisibility(View.VISIBLE);
                        } else if (randomNumberType == 5) {
                            imageTank.setVisibility(View.VISIBLE);

                        }

                    }
                }
            }
        });


        ///// JSONBA MENTES
        saveToJson.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                JSONArray jArr = new JSONArray();
                JSONObject jObj = new JSONObject();
                TextView textViewLevel = (TextView) findViewById(R.id.textViewLevel);
                TextView textViewType = (TextView) findViewById(R.id.textViewType);
                TextView textViewHit = (TextView) findViewById(R.id.textViewHit);

                EditText editTextCity = (EditText) findViewById(R.id.editTextCity);
                EditText editTextBackground = (EditText) findViewById(R.id.editTextBackground);
                EditText editTextName = (EditText) findViewById(R.id.editTextName);


                //firebase



                    try {
                        jObj.put("name", editTextName.getText());
                        jObj.put("level", textViewLevel.getText());
                        jObj.put("hit", textViewHit.getText());
                        jObj.put("city", editTextCity.getText());
                        jObj.put("type", textViewType.getText());
                        jObj.put("background", editTextBackground.getText());


                        jArr.put(jObj);


                    } catch (Exception e) {
                        System.out.println("Error:" + e);
                    }



                try {
                    Random r2 = new Random();
                    int rKarakter = r2.nextInt(1000 - 1) + 1;
                    Random random = new Random();
                    int randomNumberType = random.nextInt(1000 - 1) + 1;
                    Writer output;
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                   // File file = new File(path, randomNumberType+mentes_nev+".json");  //mentes nev letrehoz !!
                    File file = new File(path, "karakter"+rKarakter+".json");  //mentes nev letrehoz !!
                    output = new BufferedWriter(new FileWriter(file));
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("karakter"+rKarakter);

                    myRef.setValue("Hello, World!");
                    myRef.setValue(jArr.toString());
                    output.write(jArr.toString());
                    output.close();
                    Toast.makeText(getApplicationContext(), "Mentes sikeres " + "'karakter"+rKarakter + "' néven.", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });


    }
    private String generateLevel(int max, int min) {
        Random random = new Random();
        level = random.nextInt(max - min) + min;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(level);

        return stringBuilder.toString();
    }

    private String generateType(int max, int min) {

        if (randomNumberType == min) { //1
            String Type = "Harcos";

            hits= 0.5*this.level*0.5/finalValue;
            if(hits<0 ) hits = 1;
            return Type;


        } else if (randomNumberType == 2) {
            String Type = "Nindzsa";
            hits= 0.5*this.level*0.4/finalValue;
            if(hits<0 ) hits = 1;
            return Type;

        } else if (randomNumberType == 3) {
            String Type = "Saman";
            hits= 0.5*this.level*0.2/finalValue;
            if(hits<0 ) hits = 1;
            return Type;

        } else if (randomNumberType == 4) {
            String Type = "Support";
            hits= 0.5*this.level*0.1/finalValue;
            if(hits<0 ) hits = 1;
            return Type;
        } else { //5
            String Type = "Tank";
            hits= 0.5*this.level*0.25/finalValue;
            if(hits<0 ) hits = 1;
            return Type;
        }
    }

///JSONBA MENTES VEGE
///GENERALASOK

//GENERALASOK VEGE





}
