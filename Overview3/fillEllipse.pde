void fillEllipse(float x, float y, float s, float perc){
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