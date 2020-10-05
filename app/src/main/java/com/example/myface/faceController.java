package com.example.myface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class faceController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener,  AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {
private Face face;
private int radioSelection=1;

    public faceController(Face faceView) {
        this.face = faceView;
    }

    @Override
    public void onClick(View view) {
        this.face.randomizeFeatures();
        this.face.invalidate();
    }

    /**
     External Citation
     Date: 10/03/2020
     Problem: I was running into problems with how to set up the randomization of rgb, and with the color class in general.
     Resource: Emily Hoppe
     Solution: Emily walked my through how she tackled this problem and I attempted it for my self with the same method Emily used.
     */
    /**
     * This randomizes colors for each selection of hair eyes or skin and changes the vaues for each rgb
     * USes the ID for each different seek bar to identify which one is red blue and green then randomizes a value
     * @param seekBar needed to retreive the different sek bars
     * @param prog shows the position
     * @param b
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int prog, boolean b) {
        int id = seekBar.getId(); // Which seekBar is being listened to
        int r,g,bl;
        int color=0;
        int [] rgb;
        switch (id) {
            case 2131230901: //Red seekBar
                //set red aspect to progress
                r = prog;
                //Pull current color
                if(radioSelection == 1) { color = face.getHairColor(); } //Hair
                else if(radioSelection == 2){ color = face.getEyeColor(); } //Eyes
                else if (radioSelection == 3) { color = face.getSkinColor(); } //Skin
                //Pull current aspects
                rgb = face.colorRGB(color);
                g = rgb[1];
                bl = rgb[2];
                break;
            case 2131230800: //Green seekBar
                //set green aspect to progress
                g = prog;
                //Pull current color
                if(radioSelection == 1) { color = face.getHairColor(); } //Hair
                else if(radioSelection == 2){ color = face.getEyeColor(); } //Eyes
                else if (radioSelection == 3) { color = face.getSkinColor(); } //Skin
                //Pull current aspects
                rgb = face.colorRGB(color);
                r = rgb[0];
                bl = rgb[2];
                break;
            case 2131230848: //Blue seekBar
                //set blue aspect to progress
                bl = prog;
                //Pull current color
                if(radioSelection == 1) { color = face.getHairColor(); } //Hair
                else if(radioSelection == 2){ color = face.getEyeColor(); } //Eyes
                else if (radioSelection == 3) { color = face.getSkinColor(); } //Skin
                //Pull current aspects
                rgb = face.colorRGB(color);
                r = rgb[0];
                g = rgb[1];
                break;
            default:
                return;
        }
//put all aspects back together
        color = face.RGBColor(r,g,bl);
//Set updated color in face
        if(radioSelection == 1) { face.setHairColor(color); } //Hair
        else if(radioSelection == 2){ face.setEyeColor(color); } //Eyes
        else if (radioSelection == 3) {face.setSkinColor(color); } //Skin
        face.invalidate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i == R.id.hairRB) { radioSelection = 1; this.face.setRadSelect(1); } //Hair
        else if ( i == R.id.eyesRB) { radioSelection = 2;this.face.setRadSelect(2);  } //Eyes
        else if(i == R.id.skinRB) { radioSelection = 3;this.face.setRadSelect(3);  } //Skin
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

        switch (pos) {
            case 0:
                this.face.setHairStyle(1);
                break;
            case 1:
                this.face.setHairStyle(2);
                break;
            case 2:
                this.face.setHairStyle(3);
                break;
        }
       this.face.invalidate();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //not used
    }


}
