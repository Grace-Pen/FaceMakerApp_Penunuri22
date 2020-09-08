
// @author Grace Penunuri
import java.util.Random;

public class Face {
    //initialize variable
    int skinColor, eyeColor, hairColor, hairStyle; //hairstyle decides what type of eye style face has

    //randomozation
    void randomizeFeatures() {
        Random rand = new Random();
        this.skinColor = rand.nextInt(255);
        this.eyeColor = rand.nextInt(255);
        this.hairColor = rand.nextInt(255);
        this.hairStyle = rand.nextInt(255);
    }

    //constructor that calls to randomized initialized variables
    public Face() {
        randomizeFeatures();
    }
}
