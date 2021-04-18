import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Field {
    private int layer;
    private Material core;
    private boolean inSunProximity;

    /**
    * konstruktor, gener�lunk egy magot neki, egy layer vastags�got �s hogy napk�zeli-e random
    * 
    */
    
    public Asteroid() {
        genCore();
        layer = new Random().nextInt(10) + 1;
        inSunProximity = new Random().nextBoolean();
    }

    /**
    * az aszteroid�nak gener�lunk,be�llitunk egy random magot
    * 
    */
    public void genCore() {
        switch (new Random().nextInt(5)) {
            case 0:
                acceptCore(new Coal());
                break;
            case 1:
                acceptCore(new Iron());
                break;
            case 2:
                acceptCore(new Ice());
                break;
            case 3:
                acceptCore(new Uranium());
                break;
            case 4:
                core = null;
                break;
        }
    }
    /**
    * lek�rdezz�k az aszteroida magj�t
    * 
    * @return	Material core visszat�r�nk a maggal
    * 
    */
    public Material getCore() {
        return core;
    }
    /**
     * lek�rdezz�k az aszteroida k�penyvastags�g�t
     * 
     * @return	int layer visszat�r�nk a k�penyvastags�ggal
     * 
     */
    public int getLayer() {
        return layer;
    }
    /**
     * megadunk egy k�v�nt k�penyvastags�got
     *
     * @param _layer az �j k�v�nt �rt�k
     *
     */
    public void setLayer(int _layer) {
        layer = _layer;
    }
    /**
     * lek�rdezz�k az aszteroida napk�zelis�g�t
     * 
     * @return boolean inSunProximity visszat�r�nk a napk�zelis�g�vel
     * 
     */
    public boolean getInSunProximity() {
        Skeleton.printFunc();
        Skeleton.printFuncRet(Boolean.toString(inSunProximity));
        return inSunProximity;

    }
    /**
     * be�llitjuk az aszteroida napk�zelis�g�t
     * 
     * @param inSunProximity be�llitjuk a napk�zelis�g�t ezen param�terre
     * 
     */
    public void setInSunProximity(boolean inSunProximity) {
        Skeleton.printFunc();
        this.inSunProximity = inSunProximity;
        Skeleton.printFuncRet("");
    }
    /**
    * ha f�runk rajta akkor hivodik meg, ha sikeres cs�kkentj�k a layert 
    * ha nem semmit nem csin�lunk vele
    * 
    * @return bool true ha nagyobb a layer mint 1 ekkor csak cs�kkentjuk, 
    * �s akkor ha ==1 �s nem null a mag, ekkor onDrillSpecialt-t is hivunk a core-on
    * 
    * @return boolean false ha nem teljes�l egyik fenti felt�tel se
    * 
    */
    public boolean onDrill() {
        Skeleton.printFunc();
        if (layer > 1) {
            decreaseLayer();
            Skeleton.printFuncRet("true");
            return true;
        } else if (layer == 1) {
            decreaseLayer();
            if(core!=null)
                core.onDrillSpecial(this);
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }
    }
    /**
    * param�terk�nt kapott core-ra �llitjuk a magot
    *
    * @param newCore be�llitjuk erre a magot
    * 
    */
    public void acceptCore(Material newCore) {
        Skeleton.printFunc();
        core = newCore;
        Skeleton.printFuncRet("");
    }
    /**
    * elt�volitjuk a magot, nullra allitjuk
    * 
    */
    public void removeCore() {
        Skeleton.printFunc();
        core = null;
        Skeleton.printFuncRet("");
    }
    /**
    * csokkentjuk eggyel a layer vastagsagot
    * 
    */
    public void decreaseLayer() {
        Skeleton.printFunc();
        layer--;
        Skeleton.printFuncRet("");
    }
    /**
    * felrobbanaskor ez hivodik meg, a felszinen levo osszes FlyingObject onExplode fv-j�t meghivja, 
    * �s kitorli a szomszedsagi viszonyokat azokkal a Field-ekkel akik mellette vannak,
    *  majd eltavolitja a game-bol is magat
    *  
    */
    public void explode() {
        Skeleton.printFunc();
        for (int i = onSurface.size() - 1; i >= 0; i--) {
            onSurface.get(i).onExplode();
        }

        for (int i = neighbours.size() - 1; i >= 0; i--) {
            removeNeighbour(neighbours.get(i));
        }

        Game.getInstance().removeField(this);

        Skeleton.printFuncRet("");
    }
    /**
    * napvihar eset�n hivodik meg ez a fv, 
    * ha nem tud elbujni a felszinen levo FlyingObject meghivja az onSolarStormCase-�t az objektumnak
    *
    */
    public void onSolarStorm() {
        Skeleton.printFunc();
        if (layer != 0 || core != null) {
            for (int i = onSurface.size() - 1; i >= 0; i--) {
                onSurface.get(i).onSolarStormCase();
            }
        }
        Skeleton.printFuncRet("");
    }
    /**
    * ha belerak egy hajo egy core-t hivodik meg ez a fv
    * 
    *
    * @param ship ez a hajo aki belerakta a magot
    * @param m ez a mag amit beleraktak
    * 
    * @return boolean true ha nincs meg benne mag �s 0 a layer, ekkor accepteli a core-t 
    * kivevodik az a hajobol
    * majd a core-on az onFill meghivodik
    * 
    * @return boolean false ha nem teljesul a feltetel
    * 
    */
    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();
        if (layer == 0 && core == null) {
            acceptCore(m);
            ship.removeMaterial(m);
            core.onFill(this);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
    * banyaszas soran hivodik meg ez a fv
    *
    * @param f ez az a FlyingObject aki banyassza az aszteroidat
    * 
    * @return boolean true ha layer 0 �s nem null a core,
    * ekkor ha a hajo kepes felvenni a materialt, eltavolitjuk a core-t az aszteroidabol
    * 
    * @return boolean false ha nem teljesul a feltetel
    * 
    */
    public boolean onMine(FlyingObject f) {
        Skeleton.printFunc();
        if (layer == 0 && core != null) {
            if (f.addMaterial(core)) {
                removeCore();
                return true;
            }
        }
        return false;
    }
}
