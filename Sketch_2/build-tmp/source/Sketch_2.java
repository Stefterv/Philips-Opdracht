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

public class Sketch_2 extends PApplet {

// Settings

public void setup(){
	size(displayWidth, displayHeight,P2D);
	background(255);
	for(int i = 0; i<5; i++){
		float x = map(i,0,5,720-400,720+400);
		new Stat(true,x,75);
	}
}

public void draw(){
	background(255);
	scale(width/1440f);
	for(Stat sInst: statList){
		sInst.update();
		sInst.draw();
	}
}
class Entry{
	float index = 0;
	float value = random(1,10);
	Stat parent;
	boolean added = false;
	Entry(Stat parent){
		this.parent = parent;
	}
	public void move(){
		index+=0.3f;
		if(index>maxSteps && !added){
			parent.targetValue+=value;
			added = true;
		}
	}
	public void draw(){
		for(int i = 0; i<5; i++){
			int ind = floor(index)-i;
			if(ind>=0 && ind<maxSteps){
				fill(0xff75c7a2,map(i,0,5,255,0));
				noStroke();
				float y = ind*25;
				float s = 20;
				if(ind == maxSteps-1){
					s=50;
					y+=15;
					if(parent.targetValue>100){
						fill(0xff3b74ba,map(i,0,5,255,0));
					}
				}
				ellipse(0,y,s,s);
			}
		}
	}
}
int maxSteps = 25;
ArrayList<Stat> statList = new ArrayList<Stat>();
class Stat{
	float targetValue;
	float value;
	// Value in 0-200%
	
	PVector loc = new PVector(720,75);
	ArrayList<Entry> queue = new ArrayList<Entry>();
	Stat(boolean save, float x, float y){
		int startE = round(random(1,25));
		for(int i = 0; i<startE; i++){
			Entry eInst = new Entry(this);
			queue.add(eInst);
		}
		loc = new PVector(x,y);
		if(save)statList.add(this);
	}
	public void draw(){
		pushMatrix();
		translate(loc.x,loc.y);
		for(int i = 0; i<maxSteps; i++){
			stroke(0xff75c7a2);
			noFill();
			float y = i*25;
			float s = 20;
			if(i == maxSteps-1){
				s=50;
				y+=15;
			}
			ellipse(0,y,s,s);
		}
		float y = 700;
		float s = 110;
		ellipse(0,y,s,s);
		int a = color(0xff75c7a2);
		int b = color(0xff3b74ba);
		float l = map(value,100,200,0,1);
		l = constrain(l,0,1);
		fill(lerpColor(a,b,l));
		noStroke();
		value = lerp(value,targetValue,0.1f);
		fillEllipse(0,y,110,value);
		if(current()!=null)current().draw();
		popMatrix();
		
	}
	public void update(){
		if(current()!=null){
			current().move();
			if(current().index>maxSteps+5){
				if(queue.size()>1)queue.remove(0);
			}
		}
	}
	public Entry current(){
		return queue.get(0);
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
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Sketch_2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
