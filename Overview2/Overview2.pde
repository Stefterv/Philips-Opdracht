// Settings
color foreground = color(#75c7a2);
color foreground2 = color(#3b74ba);

// Declarations

void setup(){
	size(displayWidth, displayHeight,P2D);
	foreground = color(#75c7a2);
	foreground2 = color(#3b74ba);
	smooth();
}

void draw(){
	background(255);
	translate(width/2,height*0.75);
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