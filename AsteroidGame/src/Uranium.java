public class Uranium extends RadioactiveMaterial {
    private int exposedN=0;

    public void increaseExposedN() {
    	Skeleton.printFunc();
        exposedN++;
        Skeleton.printFuncRet("");
    }

    public int getExposedN(){
        return exposedN;
    }

    public void onFill(Asteroid a) {
    	 Skeleton.printFunc();
    	 if(a.getInSunProximity()) {
    		 this.increaseExposedN();
    	 }
    	 if(this.exposedN==3) {
    		 a.explode();
    	 }
         Skeleton.printFuncRet("");
    }
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        if (a.getInSunProximity()) {
        	increaseExposedN();
        }
        if(this.exposedN==3) {
   		 a.explode();
        }
        Skeleton.printFuncRet("");
    }
}
