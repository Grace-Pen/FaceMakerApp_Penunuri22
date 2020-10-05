package com.example.myface;
// @author Grace Penunuri

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.graphics.Color;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.text.AttributedCharacterIterator;
import java.util.Random;

import androidx.annotation.RequiresApi;


public class Face extends SurfaceView {



    //initialize variable
    int skinColor, eyeColor, hairColor; //hairstyle decides what type of eye style face has
    int hairStyle; // 0-2
    int skin, hair;
    public static final float faceShape = 400.0f;
    private int radSelect=1;
    //Set Paint colors

    //Paints
    Paint hairPaint, eyePaint;
    Paint whiteEye = new Paint();
    Paint redMouth = new Paint();

    //seekbars
    private Spinner hairSpin;
    private SeekBar redSeek;
    private SeekBar greenSeek;
    private SeekBar blueSeek;


    //randomozation
    void randomizeFeatures() {
        int g = (int) (Math.random()*256);
        int r = (int) (Math.random()*256);
        int b = (int) (Math.random()*256);
        this.skinColor = RGBColor(r,g,b);
        g = (int) (Math.random()*256);
        r = (int) (Math.random()*256);
        b = (int) (Math.random()*256);
        this.eyeColor = RGBColor(r,g,b);
        g = (int) (Math.random()*256);
        r = (int) (Math.random()*256);
        b = (int) (Math.random()*256);
        this.hairColor = RGBColor(r,g,b);

        this.hairStyle = (int) (Math.random() *3+1);

        hairPaint = new Paint(hairColor);
        eyePaint = new Paint(eyeColor);
    }

    public int[] colorRGB(int color){

        int b = Color.blue(color);
        int g = Color.green(color);
        int r = Color.red(color);
        int[] rgb = {r,g,b};
        return rgb;
    }

    public int RGBColor(int r, int g, int b){
        int color = Color.rgb(r,g,b);
        return color;
    }
    //draw face
    public void onDraw(Canvas canvas){
        setBackgroundColor(this.skinColor);
        drawHair(canvas);//draws the hair
        drawEyes(canvas);//draws the eyes
        drawSmile(canvas);//draws the smile
        setWidget(redSeek,greenSeek,blueSeek,hairSpin);
        widgetUpdate();//calls the seek bars to update based on color value of selected
    }


    //set skin color
    public void setSkinColor(int skinColor){this.skinColor = skinColor; }
    //get skin
    public int getSkinColor(){return skinColor;}
    //get hair color
    public int getHairColor() {
      return hairColor;
    }
    //set hair color
    public void setHairColor(int hairColor){
        this.hairColor = hairColor;
    }
    //get eye
    public int getEyeColor() {
        return eyeColor;
    }
    //set eye color
    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }
    //sets the hair color
    public void setHairStyle(int hairS){ this.hairStyle=hairS;}

    /**
     * Updates the widgets to show the correct values
     */
    public void widgetUpdate(){
       //decide the spinner selection value
        int[] rgb ={0,0,0};
        //for hair
        if(radSelect ==1){
            rgb = colorRGB(this.hairColor);
        }
        //for skin
        else if(radSelect==2) {
            rgb = colorRGB(this.skinColor);
        }
        //for eye
        else if(radSelect==3){
            rgb= colorRGB(this.eyeColor);
        }
        //now update the seek bars
        //this.redSeek.setProgress(rgb[0]);
        //this.greenSeek.setProgress(rgb[1]);
        //this.blueSeek.setProgress(rgb[2]);

        //this.hairSpin.setSelection((hairStyle-1));
}
    /**
     * @param rSB has the red seekbar
     * @param gSB has the green seekbar
     * @param bSB has the blue seekabr
     * @param hairSpin has the hair style spinner
     */


    /**
     * Creates the Background of the eye and the pupil which changes
     * @param canvas draws on
     */
    public void drawEyes(Canvas canvas){
        whiteEye = new Paint();
        whiteEye.setColor(0xffffffff);
        eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        //left and right background of the eye
        RectF rect = new RectF(400,300,500,500);
        RectF rect2 = new RectF(1000,300,1100,500);
       canvas.drawOval(rect,whiteEye);
        canvas.drawOval(rect2,whiteEye);
       //left pupil that changes when randomized
        canvas.drawCircle(450,400,45,eyePaint);
        //right pupil
        canvas.drawCircle(1050,400,45,eyePaint);
    }

    /**
     * Draws the different hair styles while switching them when called upon
     * @param canvas draws on this
     */
    public void drawHair(Canvas canvas){
        //create new pain tto change when randomized
        hairPaint = new Paint();
        hairPaint.setColor(hairColor);
        //switch between the different hair styles when one is slected
        switch (hairStyle) {
            case 1: //bald
                break;
            case 2: //short
                canvas.drawRect(0,0,2000,200,hairPaint);
                break;
            case 3: //straight
                canvas.drawRect(0,0,2000,200,hairPaint);
                canvas.drawRect(0,0,90,900,hairPaint);
                canvas.drawRect(1800,0,2000,900,hairPaint);
                break;

        }

    }

    /**
     * Draws a smile :)
     * @param canvas
     */
    public void drawSmile(Canvas canvas){
        redMouth = new Paint();
        redMouth.setColor(0xFF000000);
        //creates a smile and teeth on the face
        RectF smile = new RectF(400,400,1200,900);
        canvas.drawArc(smile,180F,-180F,false, redMouth);
        canvas.drawRect(500,650,550,700,whiteEye);
        canvas.drawRect(700,650,750,700,whiteEye);
    }

    /**
     * Updates the seek bars to the values at which either the hair eye or skin is randomized to same
     * @param seekBar
     * @param color
     */
    public void updateSeekBars(SeekBar seekBar, int color){
        //ubates the value of the seek bar to the randomized color chosen
        SeekBar red = findViewById(R.id.redSeekBar);
        SeekBar green = findViewById(R.id.greenSeekBar);
        SeekBar blue = findViewById(R.id.blueSeekBar);

        int[] rgb = {0,0,0};
        if(radSelect == 1) { //Hair
            rgb = colorRGB(this.hairColor);
        }
        else if(radSelect == 2){ //Eye
            rgb = colorRGB(this.eyeColor);
        }
        else if(radSelect == 3){ //Skin
            rgb = colorRGB(this.skinColor);
        }
        if(color == 1) {seekBar.setProgress(rgb[0]);}//sets first value so red
        else if(color == 2){seekBar.setProgress(rgb[1]);}//sets green value
        else if(color == 3){seekBar.setProgress(rgb[2]);}//sets blue value
    }

    public void setRadSelect(int i) {
         this.radSelect = i;
    }

    //constructor that calls to randomized initialized variables
    public Face(Context context, AttributeSet att) {
        super(context,att);
        randomizeFeatures();
        setWillNotDraw(false);
    }

    public void setWidget( SeekBar rSB, SeekBar gSB, SeekBar bSB, Spinner hairSpin){
        this.redSeek = rSB;
        this.greenSeek= gSB;
        this.blueSeek=bSB;
        this.hairSpin= hairSpin;
    }


}
