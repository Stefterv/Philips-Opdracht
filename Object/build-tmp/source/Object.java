import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Object extends PApplet {



PeasyCam cam;

public void setup(){
  size(displayWidth,displayHeight,P3D);
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(200);
  cam.setMaximumDistance(500);
}
public void draw(){
	background(0);
	int maxSides = floor(map(mouseX,0,width,0,31));
  for(int i = 0; i<maxSides; i++){
  	float a = map(i,0,maxSides,0,360);
  	float a2 = map(0.5f,0,maxSides,0,360);
  	int maxSteps = floor(map(mouseY,0,width,0,12));;
  	pushMatrix();
  	rotateY(radians(a));
  	noFill();
  	stroke(255);
  	beginShape();
  	for(int s = 0; s<=maxSteps; s++){
  		PVector point = PVector.fromAngle(map(s,0,maxSteps,0,PI)-PI/2f);
  		point.mult(30);
  		vertex(point.x,point.y);
  	}
  	endShape();
  	noFill();
  	stroke(255);
  	rotateY(radians(a2));
  	beginShape();
  	for(int s = 0; s<=maxSteps; s++){
  		PVector point = PVector.fromAngle(map(s,0,maxSteps,0,PI)-PI/2f);
  		point.x *= 25;
  		point.y *= 30;
  		vertex(point.x,point.y);
  	}
  	endShape();
  	popMatrix();
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Object" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
