// Settings

void setup(){
	size(displayWidth, displayHeight,P2D);
	background(255);
	for(int i = 0; i<5; i++){
		float x = map(i,0,5,720-400,720+400);
		new Stat(true,x,75);
	}
}

void draw(){
	background(255);
	scale(width/1440f);
	for(Stat sInst: statList){
		sInst.update();
		sInst.draw();
	}
}