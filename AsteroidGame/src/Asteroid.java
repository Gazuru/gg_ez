import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Field {
    private int layer;
    private Material core;
    private boolean inSunProximity;

    /**
    * konstruktor, generálunk egy magot neki, egy layer vastagságot és hogy napközeli-e random
    * 
    */
    
    public Asteroid() {
        genCore();
        layer = new Random().nextInt(10) + 1;
        inSunProximity = new Random().nextBoolean();
    }

    /**
    * az aszteroidának generálunk,beállitunk egy random magot
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
    * lekérdezzük az aszteroida magját
    * 
    * @return	Material core visszatérünk a maggal
    * 
    */
    public Material getCore() {
        return core;
    }
    /**
     * lekérdezzük az aszteroida köpenyvastagságát
     * 
     * @return	int layer visszatérünk a köpenyvastagsággal
     * 
     */
    public int getLayer() {
        return layer;
    }
    /**
     * megadunk egy kívánt köpenyvastagságot
     *
     * @param _layer az új kívánt érték
     *
     */
    public void setLayer(int _layer) {
        layer = _layer;
    }
    /**
     * lekérdezzük az aszteroida napközeliségét
     * 
     * @return boolean inSunProximity visszatérünk a napközeliségével
     * 
     */
    public boolean getInSunProximity() {
        Skeleton.printFunc();
        Skeleton.printFuncRet(Boolean.toString(inSunProximity));
        return inSunProximity;

    }
    /**
     * beállitjuk az aszteroida napközeliségét
     * 
     * @param inSunProximity beállitjuk a napközeliségét ezen paraméterre
     * 
     */
    public void setInSunProximity(boolean inSunProximity) {
        Skeleton.printFunc();
        this.inSunProximity = inSunProximity;
        Skeleton.printFuncRet("");
    }
    /**
    * ha fúrunk rajta akkor hivodik meg, ha sikeres csökkentjük a layert 
    * ha nem semmit nem csinálunk vele
    * 
    * @return bool true ha nagyobb a layer mint 1 ekkor csak csökkentjuk, 
    * és akkor ha ==1 és nem null a mag, ekkor onDrillSpecialt-t is hivunk a core-on
    * 
    * @return boolean false ha nem teljesül egyik fenti feltétel se
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
    * paraméterként kapott core-ra állitjuk a magot
    *
    * @param newCore beállitjuk erre a magot
    * 
    */
    public void acceptCore(Material newCore) {
        Skeleton.printFunc();
        core = newCore;
        Skeleton.printFuncRet("");
    }
    /**
    * eltávolitjuk a magot, nullra allitjuk
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
    * felrobbanaskor ez hivodik meg, a felszinen levo osszes FlyingObject onExplode fv-jét meghivja, 
    * és kitorli a szomszedsagi viszonyokat azokkal a Field-ekkel akik mellette vannak,
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
    * napvihar esetén hivodik meg ez a fv, 
    * ha nem tud elbujni a felszinen levo FlyingObject meghivja az onSolarStormCase-ét az objektumnak
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
    * @return boolean true ha nincs meg benne mag és 0 a layer, ekkor accepteli a core-t 
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
    * @return boolean true ha layer 0 és nem null a core,
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
