import java.util.ArrayList;
import java.util.Random;

public class Ufo extends FlyingObject {
    private ArrayList<Material> materials;

    public Ufo(){
        ArrayList<Field> locations = Game.getInstance().getFields();
        if (!locations.isEmpty()) {
            locations.get(new Random().nextInt(locations.size())).acceptFlyingObject(this);
        } else {
            System.out.println("Nincs aszteroida a jatekban!");
        }
    }

    /**
     * step fv ami minden körébe lefut
     * done lokalis valtozo false alapbol,
     * ha tud furni akkor fur, és done true lesz, 
     * ha nem tud(ezért done false) akkor vagy teleportal(ha tud) vagy move-ol az ufo 
     * 
     */
    public void step() {
        Skeleton.printFunc();
        boolean done = false;
        refreshView();
        done = drill();
        if (!done) {
            Random random = new Random();
            int randomI = random.nextInt(2);
            if (randomI == 0) {
                useGate();
            }
            if (randomI == 1) {
                move();
            }
        }
        Skeleton.printFuncRet("");
    }

    private void refreshView() {
		// TODO Auto-generated method stub
		
	}

	/**
     * mozgasert felelos fv, 
     * random kivalaszt egy a location aktualis szoszedjat, 
     * majd atallitja a locationjat arra, a megfelelo onSurface listakezelest pedig elvegzi
     *
     * @return boolean true ha sikerrel zajlott a mozgas
     * 
     * @return boolean false ha a feltetel nem teljesul
     * 
     */
    public boolean move() {
        Skeleton.printFunc();
        Field neighbour_asteroid = location.getNeighbours().get(0);
        if (neighbour_asteroid != null) {
            location.removeFlyingObject(this);
            neighbour_asteroid.acceptFlyingObject(this);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    /**
     * banyaszatert felelos fv, 
     * meghivja a locationon az onMine fv-t this parameterrel
     * 
     * @return boolean true ha sikerrel zajlott a banyaszat
     * 
     * @return boolean false ha a feltetel nem teljesul
     * 
     */
    public boolean mine() {
        Skeleton.printFunc();
        return location.onMine(this);
    }

    /**
     * meghalas eseten hivodik meg a fv, eltavolitja a Game gameObjects listajarol es a location onSurface listajarol magat
     *
     */
    public void die() {
        Skeleton.printFunc();
        location.removeFlyingObject(this);
        Game.getInstance().removeGameObject(this);
        Skeleton.printFuncRet("");
    }

    /**
     * robbanas eseten hivodik meg a fv, meghivja a die fv-t
     *
     */
    public void onExplode() {
        Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
    }

    /**
     * napvihar eseten hivodik meg a fv, meghivja a die fv-t
     *
     */
    public void onSolarStormCase() {
        Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
    }
    /**
    * hasznalja a kaput amin epp all
    *
    * @return boolean true ha sikeresen vegbe megy a teleport
    * 
    * @return boolean false ha a feltetel nem teljesul
    * 
    */
    public boolean useGate() {
        return location.teleport(this);
    }
    /**
     * furast vegzunk, de ez itt mivel az Ufo nem tud furni mindig false-al ter vissza 
     *
     * @return boolean false térünk vissza mindig
     * 
     */
    public boolean drill() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
     * anyag hozzaadas eseten ezt hivjuk meg az Ufo-n,
     * mivel az Ufo barmennyi anyagot fel tud venni mindig true
     * 
     * @return boolean true jelen esetben mindig true-al ter vissza
     * 
     */
    public boolean addMaterial(Material material) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("true");
        materials.add(material);
        return true;
    }
}
