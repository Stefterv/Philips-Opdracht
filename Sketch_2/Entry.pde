class Entry{
	float index = 0;
	float value = random(1,10);
	Stat parent;
	boolean added = false;
	Entry(Stat parent){
		this.parent = parent;
	}
	void move(){
		index+=0.3f;
		if(index>maxSteps && !added){
			parent.targetValue+=value;
			added = true;
		}
	}
	void draw(){
		for(int i = 0; i<5; i++){
			int ind = floor(index)-i;
			if(ind>=0 && ind<maxSteps){
				fill(#75c7a2,map(i,0,5,255,0));
				noStroke();
				float y = ind*25;
				float s = 20;
				if(ind == maxSteps-1){
					s=50;
					y+=15;
					if(parent.targetValue>100){
						fill(#3b74ba,map(i,0,5,255,0));
					}
				}
				ellipse(0,y,s,s);
			}
		}
	}
}