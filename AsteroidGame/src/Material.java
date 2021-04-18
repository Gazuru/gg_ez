public abstract class Material{
	
	/**
	* drill soran meghivodhat ez a fv, de ez felül van a megfelelo helyen definialva ahol szukseges valamilyen funkciot ellatnia
	*
	* @param a ezt adjuk at parameterkent
	* 
	*/
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }
    /**
    * ellenorzi hogy tipusban megegyezik-e a kapott Material-al ez a Material
    *
    * @param other ezt adjuk parameterkent
    * 
    * @return boolean true ha egyezik a tipus
    * @return boolean false ha nem egyezik a tipus
    * 
    */
    public boolean compatibleWith(Material other) {
        Skeleton.printFunc();

        if (this.getClass() == other.getClass()) {
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;

    }
    /**
	* fill soran meghivodik ez a fv, de ez felül van a megfelelo helyen definialva ahol szukseges valamilyen funkciot ellatnia
	*
	* @param a ezt adjuk at parameterkent
	* 
	*/
    public void onFill(Asteroid a) {
    	 Skeleton.printFunc();
         Skeleton.printFuncRet("");
    }
}
