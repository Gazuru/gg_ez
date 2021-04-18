import java.util.ArrayList;

public abstract class FlyingObject implements Steppable {
    protected Field location;
    /**
     * konstruktor, hozz�addolja mag�t a singleton Game gameObjects listajahoz
     * 
     */
    public FlyingObject() {
        Game.getInstance().addGameObject(this);
    }
    /**
    * lek�rj�k a location tagv�ltoz�t
    *
    * @return Field location ezzel t�r�nk vissza
    * 
    */
    
    public Field getLocation() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
        return location;
    }
    /**
    * be�llitjuk a location tagvaltozot a param�terk�nt kapott �rt�kre
    *
    * @param  Field newLocation erre �llitjuk be
    * 
    */
    public void setLocation(Field newLocation) {
        Skeleton.printFunc();
        location = newLocation;
        Skeleton.printFuncRet("");
    }
    /**
    * furast vegzunk, meghivjuk a location-on az onDrill-t, 
    * kimenet�t a to_return lok�lis valtozoba mentjuk, ezzel terunk vissza kesobb
    *
    * @return boolean to_return
    * 
    */
    public boolean drill() {
        Skeleton.printFunc();
        boolean to_return = location.onDrill();
        Skeleton.printFuncRet(Boolean.toString(to_return));
        return to_return;
    }
    /**
     * anyag hozzaadas eseten ezt hivjuk meg a FlyingObject-en, mivel a FlyingObject nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt mindig false-al ter vissza
     * 
     * @return boolean false jelen esetben mindig false-al ter vissza
     * 
     */
    public boolean addMaterial(Material material) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
     * anyag lekerdezes eseten ezt hivjuk meg a FlyingObject-en, mivel a FlyingObject nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt mindig null-al ter vissza
     * 
     * @return ArrayList<Material> null jelen esetben mindig null-al ter vissza
     * 
     */
    public ArrayList<Material> getMaterial() {
        return null;
    }
    /**
     * abstract fv mozgas eseten ezt hivjuk meg a FlyingObject-en, mivel a FlyingObject nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt nincs implementalva
     * 
     * @return boolean
     * 
     */
    public abstract boolean move();
    /**
     * abstract fv meghalas eseten ezt hivjuk meg a FlyingObject-en, mivel a FlyingObject nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt nincs implementalva
     * 
     */
    public abstract void die();
    /**
     * abstract fv felrobbanas eseten ezt hivjuk meg a FlyingObject-en, mivel a FlyingObject nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt nincs implementalva
     * 
     */
    public abstract void onExplode();
    /**
     * abstract fv napvihar eseten ezt hivjuk meg a FlyingObject-en, mivel a FlyingObject nem peldanyosithato, 
     * es ez a leszarmazottakban felul van definialva, ez itt nincs implementalva
     * 
     */
    public abstract void onSolarStormCase();
}
