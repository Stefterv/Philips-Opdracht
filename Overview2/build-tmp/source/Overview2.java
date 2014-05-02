import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Overview2 extends PApplet {

// Settings
int foreground = color(0xff75c7a2);
int foreground2 = color(0xff3b74ba);

// Declarations

public void setup(){
	size(displayWidth, displayHeight,P2D);
	foreground = color(0xff75c7a2);
	foreground2 = color(0xff3b74ba);
	smooth();
}

public void draw(){
	background(255);
	translate(width/2,height*0.75f);
	scale(width/1440f);
	for(int x = -600; x<600; x+= 80){
		for(int y = 0; y<5; y+=1){
			pushMatrix();
			float value = noise(x,y)*2;
			float opacity = 255;
			if(value>1){
				fill(lerpColor(foreground,foreground2,value-1),opacity);
			}else{
				fill(foreground,opacity);
			}
			noStroke();
			translate(x,map(y,0,5,0,-450));
			fillEllipse(0,0,map(y,0,5,50,15),map(value,0,1,0,100));
			popMatrix();
		}
	}

}
public void fillEllipse(float x, float y, float s, float perc){
	pushMatrix();
	translate(0,y);
	beginShape();
	for(int i = 0; i<360; i+=5){
		PVector vert = PVector.fromAngle(radians(i));
		float maxY = map(perc,0,100,-1,1);
		if(vert.y > maxY){
			vert.y = maxY;
		}
		vert.mult(s/2f);
		vertex(vert.x,-vert.y);
	}
	endShape();
	popMatrix();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Overview2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
