public class Uranium extends RadioactiveMaterial {
    private int exposedN=0;

    public void increaseExposedN() {
    	Skeleton.printFunc();
        exposedN++;
        Skeleton.printFuncRet("");
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
}
