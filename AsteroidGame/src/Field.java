import java.util.ArrayList;

public abstract class Field{
    protected ArrayList<Field> neighbours = new ArrayList<>();
    protected ArrayList<FlyingObject> onSurface = new ArrayList<>();

    /**
     * konstruktor, hozzáaddolja magát a singleton Game fields listajahoz
     * 
     */
    public Field() {
        Game.getInstance().addField(this);
    }
    /**
     * lekerjuk a neighbours listat
     * 
     * @return ArrayList<Field> neighbours
     */
    public ArrayList<Field> getNeighbours() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("neighbours");
        return neighbours;
    }
    /**
    * eltavolitjuk a parameterkent kapott FlyingObject-et az onSurface listáról,
    * majd null-ra állitjuk annak location-jat
    * 
    * @param fo ezt adjuk át parameterkent
    * 
    */
    public void removeFlyingObject(FlyingObject fo) {
        Skeleton.printFunc();
        onSurface.remove(fo);
        fo.setLocation(null);
        Skeleton.printFuncRet("");
    }
    /**
    * a paraméterként kapott FlyingObject-et hozzáadjuk az onSurface listánhoz,
    * majd beállitjuk annak location-jét erre a Field-re
    *
    * @param fo ezt adjuk át parameterkent
    * 
    */
    public void acceptFlyingObject(FlyingObject fo) {
        Skeleton.printFunc();
        onSurface.add(fo);
        fo.setLocation(this);
        Skeleton.printFuncRet("");
    }
    /**
    * a paraméterként kapott Field-et hozzáadjuk a neighbours listánhoz,
    * majd annak neighbourjeihez ezt a Field-et addoljuk
    * 
    * @param n ezt adjuk át parameterkent
    * 
    */
    public void addNeighbour(Field n) {
        Skeleton.printFunc();
        neighbours.add(n);
        n.neighbours.add(this);
        Skeleton.printFuncRet("");
    }
    /**
     * a paraméterként kapott Field-et kitoroljuk a neighbours listabol,
     * majd annak a neighbourjei kozul ezt a Field-et is kitoroljuk
     * 
     * @param oldNeighbour ezt adjuk át parameterkent
     * 
     */
    public void removeNeighbour(Field oldNeighbour) {
    	Skeleton.printFunc();
        neighbours.remove(oldNeighbour);
        oldNeighbour.neighbours.remove(this);
        Skeleton.printFuncRet("");
    }
    /**
    * lekerjuk a felszinen levo FlyingObject-eket
    *
    * @return ArrayList<FlyingObject> onSurface ezzel terunk vissza
    * 
    */
    public ArrayList<FlyingObject> getOnSurface() {
    	Skeleton.printFunc();
    	Skeleton.printFuncRet("");
        return onSurface;
    }
    /**
    * mine-olas eseten ezt hivjuk meg a Field-en, mivel a Field nem peldanyosithato, 
    * es ez a leszarmazottakban felul van definialva, ez itt mindig false-al ter vissza
    *
    * @param f ezt adjuk at parameterkent
    * 
    * @return boolean false jelen esetben mindig false-al ter vissza
    * 
    */
    public boolean onMine(FlyingObject f){
    	Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
     * drill-eles eseten ezt hivjuk meg a Field-en, mivel a Field nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt mindig false-al ter vissza
     * 
     * @return boolean false jelen esetben mindig false-al ter vissza
     * 
     */
    public boolean onDrill(){
    	Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
     * napvihar eseten hivodik meg abstract fv, leszarmazottakban felul van definialva
     * 
     */
    public abstract void onSolarStorm();
    /**
     * teleport-olas eseten ezt hivjuk meg a Field-en, mivel a Field nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt mindig false-al ter vissza
     *
     * @param f ezt adjuk at parameterkent
     * 
     * @return boolean false jelen esetben mindig false-al ter vissza
     * 
     */
    public boolean teleport(FlyingObject f) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
     * felvevés eseten ezt hivjuk meg a Field-en, mivel a Field nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt mindig false-al ter vissza
     *
     * @param ship ezt adjuk at parameterkent
     * 
     * @return boolean false jelen esetben mindig false-al ter vissza
     * 
     */
    public boolean pickedUpBy(Ship ship) {
    	 Skeleton.printFunc();
         Skeleton.printFuncRet("false");
        return false;
    }
    /**
     * mag berakas eseten ezt hivjuk meg a Field-en, mivel a Field nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt mindig false-al ter vissza
     *
     * @param ship ezt adjuk at parameterkent
     * @param m ezt adjuk at parameterkent
     * 
     * @return boolean false jelen esetben mindig false-al ter vissza
     * 
     */
    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
}
