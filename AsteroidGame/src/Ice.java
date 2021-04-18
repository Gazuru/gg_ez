public class Ice extends NonRadioactiveMaterial {
	/**
	* fúrás közben hivodik meg a magon, ha napkozelbe van a parameterkent kapott aszteroida,
	* eltavolitjuk a magot belole
	*
	* @param a ezt adjuk at parameterkent
	* 
	*/
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        if (a.getInSunProximity()) {
            a.removeCore();
        }
        Skeleton.printFuncRet("");
    }
    /**
    * mag visszarakas közben hivodik meg a magon, ha napkozelbe van a parameterkent kapott aszteroida,
	* eltavolitjuk a magot belole
    *
    * @param a ezt adjuk at parameterkent
	* 
	*/
    public void onFill(Asteroid a) {
        Skeleton.printFunc();
        if (a.getInSunProximity()) {
            a.removeCore();
        }
        Skeleton.printFuncRet("");
    }
    
}
