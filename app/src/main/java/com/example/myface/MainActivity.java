//@author Grace Penunuri 

package com.example.myface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String[] hairStyles = {"Bald", "Short", "Straight"};
    private Spinner hairSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hairSpinner = findViewById(R.id.hairStyleSpinner);
        ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, this.hairStyles);
        hairSpinner.setAdapter(hairAdapter);

        Face faceView = findViewById(R.id.faceView);

        faceController faceCont= new faceController(faceView);

        Button randomFaceB = findViewById(R.id.randomFaceButton);
        randomFaceB.setOnClickListener(faceCont);

        SeekBar redSeek = findViewById(R.id.redSeekBar);
        redSeek.setOnSeekBarChangeListener(faceCont);

        SeekBar greenSeek = findViewById(R.id.greenSeekBar);
        greenSeek.setOnSeekBarChangeListener(faceCont);

        SeekBar blueSeek = findViewById(R.id.blueSeekBar);
        blueSeek.setOnSeekBarChangeListener(faceCont);

        RadioGroup HairRadioGroup = findViewById(R.id.HairRadioGroup);
        HairRadioGroup.setOnCheckedChangeListener(faceCont);

        Spinner hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        hairStyleSpinner.setOnItemSelectedListener(faceCont);

        faceView.setWidget(redSeek,greenSeek,blueSeek,hairStyleSpinner);


        faceView.updateSeekBars(redSeek,1);
        faceView.updateSeekBars(greenSeek,2);
        faceView.updateSeekBars(blueSeek,3);

    }
}