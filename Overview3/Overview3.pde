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
	for(int x = -600; x<600; x+= 100){
		pushMatrix();
		float offset = grid(mouseX,100)/100f;
		println(offset);
		float currentValue = noise(x,offset)*2;
		float targetValue = noise(x,offset+1)*2;
		float value = lerp(currentValue,targetValue,map(mouseX%100,0,100,0,1));
		float opacity = 255;
		if(value>1){
			fill(lerpColor(foreground,foreground2,value-1),opacity);
		}else{
			fill(foreground,opacity);
		}
		noStroke();
		translate(x,0);
		fillEllipse(0,0,75,map(value,0,1,0,100));
		popMatrix();
	}

}
float grid(float value, float marker){
	return floor(value/marker)*marker;
}