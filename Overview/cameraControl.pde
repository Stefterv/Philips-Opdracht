// Camera Control V3.0
// By Stef Tervelde
// steftervelde.nl
// start camera by initCam();
// ever draw do setCam();
float snapDist = 0.25;
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
	void go(){
		if(PVector.dist(loc,locTarget)>snapDist){
			PVector changeLoc = PVector.sub(locTarget,loc);
			changeLoc.mult(0.05);
			changeLoc.limit(maxCameraSpeed);
			loc.add(changeLoc);
		}else{
			loc = locTarget.get();
		}
		if(PVector.dist(view,viewTarget)>snapDist){
			PVector changeView = PVector.sub(viewTarget,view);
			changeView.mult(0.05);
			changeView.limit(maxViewSpeed);
			view.add(changeView);
		}else{
			view = viewTarget.get();
		}
		camera(loc.x,loc.y,loc.z,view.x,view.y,view.z,0,1,0);
	}
}
void initCam(){
	currentCam = new cameraControl(new PVector(0,0,camDistance),new PVector(0,0,camDistance), new PVector(0,0,0), new PVector(0,0,0));
}
void setCam(){
	hint(ENABLE_DEPTH_TEST);
	currentCam.go();
}
void resetCam(){
	hint(DISABLE_DEPTH_TEST);
	camera();
}
