import java.util.ArrayList;
import java.util.Random;

public class Robot extends FlyingObject {
    /**
     * konstruktor, hozzáaddolja magát a singleton Game gameObjects listajahoz
     */
    public Robot() {
        Game.getInstance().addGameObject(this);
    }

    /**
     * step fv ami minden körébe lefut
     * done lokalis valtozo false alapbol,
     * ha tud furni akkor fur, és done true lesz,
     * ha nem tud(ezért done false) akkor move-ol a robot
     */
    public void step() {
        Skeleton.printFunc();
        
        boolean done = false;
        refreshView();
        
        done = drill();
        if (!done)
            move();

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
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean move() {
        Skeleton.printFunc();
        ArrayList<Field> current_neighbours = location.getNeighbours();
        if (current_neighbours.size() != 0) {
            Random random = new Random();
            int newLocationInt = random.nextInt(current_neighbours.size());
            Field newLocation = current_neighbours.get(newLocationInt);

            location.removeFlyingObject(this);
            newLocation.acceptFlyingObject(this);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    /**
     * meghalas eseten hivodik meg a fv, eltavolitja a Game gameObjects listajarol es a location onSurface listajarol magat
     */
    public void die() {
        Skeleton.printFunc();
        location.removeFlyingObject(this);
        Game.getInstance().removeGameObject(this);
        Skeleton.printFuncRet("");
    }

    /**
     * robbanas eseten hivodik meg a fv, meghivja a move fv-t
     */
    public void onExplode() {
        Skeleton.printFunc();
        move();
        Skeleton.printFuncRet("");
    }

    /**
     * napvihar eseten hivodik meg a fv, meghivja a die fv-t
     */
    public void onSolarStormCase() {
        Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
    }

    /**
     * visszaterunk a craftolásához szukseges anyagokkal
     * letrehzunk egy uj listat és hozzaadjuk a szukseges anyagokat
     *
     * @return ArrayList<Material> robotReq ezzel terunk vissza
     */
    public static ArrayList<Material> craftRobotReq() {
        Skeleton.printFunc();
        ArrayList<Material> robotReq = new ArrayList<Material>();
        robotReq.add(new Iron());
        robotReq.add(new Coal());
        robotReq.add(new Uranium());
        Skeleton.printFuncRet("robotReq");
        return robotReq;
    }
}
