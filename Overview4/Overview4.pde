// Settings
color foreground = color(#75c7a2);
color foreground2 = color(#3b74ba);

void setup(){
	size(displayWidth, displayHeight,P2D);
	foreground = color(#75c7a2);
	foreground2 = color(#3b74ba);
	smooth();
}

void draw(){
	background(255);
	translate(width/2,height/2f);
	scale(width/1440f);
	for(int x = -600; x<=600; x+= 100){
		pushMatrix();
		float offset = grid(mouseX,100)/100f;
		float currentValue = noise(x,offset)*2;
		float targetValue = noise(x,offset+1)*2;
		float lerp = map(mouseX%100,0,100,0,1);
		float value = lerp(currentValue,targetValue,lerp);

		translate(x,0);
		drawContainer(currentValue,75);

		fill(255);
		float s = map(lerp,0.05,0.9,0,75);
		s = constrain(s,0,75);
		ellipse(0,0,s,s);
		s = map(lerp,0.1,1,0,75);
		s = constrain(s,0,75);
		drawContainer(targetValue,s);
		popMatrix();
	}

}
void drawContainer(float value,float size){
	float opacity = 255;
	if(value>1){
		fill(lerpColor(foreground,foreground2,value-1),opacity);
	}else{
		fill(foreground,opacity);
	}
	noStroke();
	
	fillEllipse(0,0,size,map(value,0,1,0,100));
}
float grid(float value, float marker){
	return floor(value/marker)*marker;
}