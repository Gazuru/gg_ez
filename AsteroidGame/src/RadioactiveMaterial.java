public abstract class RadioactiveMaterial extends Material {
	/**
	* f�r�s k�zben hivodik meg a magon, ha napkozelbe van a parameterkent kapott aszteroida,
	* felrobbantjuk
	*
	* @param Asteroid a ezt adjuk at parameterkent
	* 
	*/
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        if (a.getInSunProximity()) {
            a.explode();
        }
        Skeleton.printFuncRet("");
    }
}
