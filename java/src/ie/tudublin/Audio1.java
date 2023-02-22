// package ie.tudublin;

// import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
// import ddf.minim.Minim;
// import processing.core.PApplet;

// public class Audio1 extends PApplet
// {
//     Minim minim;
//     AudioPlayer ap;
//     AudioInput ai;
//     AudioBuffer ab;

//     int mode = 0;

//     float y = 0;
//     float smoothedY = 0;
//     float smoothedAmplitude = 0;

//     public void keyPressed() {
// 		if (key >= '0' && key <= '9') {
// 			mode = key - '0';
// 		}
// 		if (keyCode == ' ') {
//             if (ap.isPlaying()) {
//                 ap.pause();
//             } else {
//                 ap.rewind();
//                 ap.play();
//             }
//         }
// 	}

//     public void settings()
//     {
//         size(1024, 1000, P3D);
//         //fullScreen(P3D, SPAN);
//     }

//     public void setup()
//     {
//         minim = new Minim(this);
//         // Uncomment this to use the microphone
//         // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
//         // ab = ai.mix; 

//         // And comment the next two lines out
//         ap = minim.loadFile("heroplanet.mp3", 1024);
//         ap.play();
//         ab = ap.mix;
//         colorMode(HSB);

//         y = height / 2;
//         smoothedY = y;

//     }

//     float off = 0;

//     public void draw()
//     {
//         //background(0);
//         float halfH = height / 2;
//         float average = 0;
//         float sum = 0;
//         off += 1;
//         // Calculate sum and average of the samples
//         // Also lerp each element of buffer;
//         for(int i = 0 ; i < ab.size() ; i ++)
//         {
//             sum += abs(ab.get(i));
//         }
//         average= sum / (float) ab.size();

//         smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
//         float cx = width / 2;
//         float cy = height / 2;

//         switch (mode) {
// 			case 0:
//                 background(0);
//                 for(int i = 0 ; i < ab.size() ; i ++)
//                 {
//                     //float c = map(ab.get(i), -1, 1, 0, 255);
//                     float c = map(i, 0, ab.size(), 0, 255);
//                     stroke(c, 255, 255);
//                     float f = ab.get(i) * halfH;
//                     line(i, halfH + f, i, halfH - f);                    
//                 }
//                 break;
//         case 1:
//             background(0);            
//             break;

//         }
        


        
//         // Other examples we made in the class
//         /*
//         stroke(255);
//         fill(100, 255, 255);        
        
//         circle(width / 2, halfH, lerpedA * 100);

//         circle(100, y, 50);
//         y += random(-10, 10);
//         smoothedY = lerp(smoothedY, y, 0.1f);        
//         circle(200, smoothedY, 50);
//         */

//     }        
// }

package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {

    Minim minim; // Connect to minim
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples

    float[] lerpedBuffer;

    public void settings() {
        //size(1000, 1000, P3D);
        fullScreen(P3D, SPAN); // Try this for full screen multiple monitor support :-) Be careful of exceptions!
    }

    float y = 200;
    float lerpedY = y;

    int which = 0;

    public void setup() {
        minim = new Minim(this);
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("heroplanet.mp3", width);
        ap.play();
        ab = ap.mix; // Connect the buffer to the mp3 file
        //ab = ai.mix; 
        colorMode(HSB);
        lerpedBuffer = new float[width];

    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '6') {
            which = keyCode - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
        if (keyCode == UP)
        {
            twoCubes = ! twoCubes;
        }
    }

    float lerpedAverage = 0;
    private float angle = 0;

    private boolean twoCubes = false;

    public void draw() {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;

        // Calculate the average of the buffer
        for (int i = 0; i < ab.size(); i ++)
        {
            sum += abs(ab.get(i));
        }
        average = sum / ab.size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        switch (which)
        {
            case 0:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }        
                break;
            }   
            case 1:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
                }        
                break;
            }
            case 2:
            {
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    line(0, i, lerpedBuffer[i] * halfHeight * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfHeight * 4), i);
                    line(i, 0, i, lerpedBuffer[i] * halfHeight * 4);
                    line(i, height, i, height - (lerpedBuffer[i] * halfHeight * 4));
                }        
                break;
            }
            case 3:
            {
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);        
                strokeWeight(2);
                noFill();
                // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
                //ellipse(width / 4, 100, 50 + average * 500, 50 + average * 500);
                ellipse(width / 2, height / 2, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));                
                break;
            }
            case 4:
            {
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);        
                strokeWeight(2);
                noFill();
                rectMode(CENTER);
                float size = 50 + (lerpedAverage * 500);
                rect(width / 2, height / 2, size, size);
                break;
            }
            case 5:
            {
                float r = 1f;
                int numPoints = 3;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(2);                
                float lastX = width / 2, lastY = height / 2;
                for(int i = 0 ; i < 1000 ; i ++)
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255, 100);
                    float theta = i * (thetaInc + lerpedAverage * 5);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + lerpedAverage;
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
                // ??
                break;
            }
            case 6:
            {
                lights();
                strokeWeight(2);
                float c = map(lerpedAverage, 0, 1, 0, 255);
                stroke(c, 255, 255);
                noFill();
                //fill(100, 255, 255);
                angle += 0.01f;
                float s = 100 + (100 * lerpedAverage * 10);
                
                if (! twoCubes)
                {
                    translate(width / 2, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                }
                else
                {
                    pushMatrix();
                    translate(width / 4, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();

                    pushMatrix();
                    translate(width * 0.75f, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();
                }
            }
        }        
    }
}
