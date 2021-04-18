public class Uranium extends RadioactiveMaterial {
    private int exposedN=0;
    /**
    * noveljuk az exposedN tagvaltozot eggyel
    * 
    */
    public void increaseExposedN() {
    	Skeleton.printFunc();
        exposedN++;
        Skeleton.printFuncRet("");
    }
    /**
    * lekerjuk az exposedN tagvaltozo erteket
    * 
    * @return int exposedN ezzel terunk vissza
    * 
    */
    public int getExposedN(){
        return exposedN;
    }
    /**
    * mag visszarakas közben hivodik meg a magon, ha napkozelbe van a parameterkent kapott aszteroida,
 	* meghivjuk az increaseExposedN fv-t, amennyiben ez elerte a 3-at felrobbantjuk az aszteroidat
    *
    * @param Asteroid a ezt adjuk at parameterkent
 	* 
 	*/
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
    /**
	* fúrás közben hivodik meg a magon, ha napkozelbe van a parameterkent kapott aszteroida,
	* meghivjuk az increaseExposedN fv-t, amennyiben ez elerte a 3-at felrobbantjuk az aszteroidat
	*
	* @param Asteroid a ezt adjuk at parameterkent
	* 
	*/
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
