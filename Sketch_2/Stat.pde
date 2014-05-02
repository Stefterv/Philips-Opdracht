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
	void draw(){
		pushMatrix();
		translate(loc.x,loc.y);
		for(int i = 0; i<maxSteps; i++){
			stroke(#75c7a2);
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
		color a = color(#75c7a2);
		color b = color(#3b74ba);
		float l = map(value,100,200,0,1);
		l = constrain(l,0,1);
		fill(lerpColor(a,b,l));
		noStroke();
		value = lerp(value,targetValue,0.1);
		fillEllipse(0,y,110,value);
		if(current()!=null)current().draw();
		popMatrix();
		
	}
	void update(){
		if(current()!=null){
			current().move();
			if(current().index>maxSteps+5){
				if(queue.size()>1)queue.remove(0);
			}
		}
	}
	Entry current(){
		return queue.get(0);
	}
}