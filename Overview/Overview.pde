// Settings
color foreground = color(#75c7a2);
color foreground2 = color(#3b74ba);

// Declarations

void setup(){
	size(displayWidth, displayHeight,P3D);
	foreground = color(#75c7a2);
	foreground2 = color(#3b74ba);
	initCam();
	currentCam.locTarget.z = 150;
	currentCam.locTarget.y = -50;
}

void draw(){
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