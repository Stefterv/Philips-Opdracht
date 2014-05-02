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

public class Overview extends PApplet {

// Settings
int foreground = color(0xff75c7a2);
int foreground2 = color(0xff3b74ba);

// Declarations

public void setup(){
	size(displayWidth, displayHeight,P3D);
	foreground = color(0xff75c7a2);
	foreground2 = color(0xff3b74ba);
	initCam();
	currentCam.locTarget.z = 150;
	currentCam.locTarget.y = -50;
}

public void draw(){
	background(255);
	setCam();
	for(int x = -200; x<200; x+= 20){
		for(int z = 0; z<500; z+=50){
			pushMatrix();
			float value = noise(x,z)*2;
			if(value>1){
				fill(lerpColor(foreground,foreground2,value-1));
			}else{
				fill(foreground);
			}
			
			noStroke();
			translate(x,0,-z);
			fillEllipse(0,0,10,map(value,0,1,0,100));
			popMatrix();
		}
	}

}
// Camera Control V3.0
// By Stef Tervelde
// steftervelde.nl
// start camera by initCam();
// ever draw do setCam();
float snapDist = 0.25f;
float maxCameraSpeed =14;
float maxViewSpeed = 20;
cameraControl currentCam;
float camDistance = 1750;
class cameraControl{
	PVector loc;
	PVector locTarget;
	PVector view;
	PVector viewTarget;
	cameraControl(PVector loc, PVector locTarget, PVector view, PVector viewTarget){
		this.loc = loc;
		this.locTarget = locTarget;
		this.view = view;
		this.viewTarget = viewTarget;
		currentCam = this;
	}
	public void go(){
		if(PVector.dist(loc,locTarget)>snapDist){
			PVector changeLoc = PVector.sub(locTarget,loc);
			changeLoc.mult(0.05f);
			changeLoc.limit(maxCameraSpeed);
			loc.add(changeLoc);
		}else{
			loc = locTarget.get();
		}
		if(PVector.dist(view,viewTarget)>snapDist){
			PVector changeView = PVector.sub(viewTarget,view);
			changeView.mult(0.05f);
			changeView.limit(maxViewSpeed);
			view.add(changeView);
		}else{
			view = viewTarget.get();
		}
		camera(loc.x,loc.y,loc.z,view.x,view.y,view.z,0,1,0);
	}
}
public void initCam(){
	currentCam = new cameraControl(new PVector(0,0,camDistance),new PVector(0,0,camDistance), new PVector(0,0,0), new PVector(0,0,0));
}
public void setCam(){
	hint(ENABLE_DEPTH_TEST);
	currentCam.go();
}
public void resetCam(){
	hint(DISABLE_DEPTH_TEST);
	camera();
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
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Overview" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
