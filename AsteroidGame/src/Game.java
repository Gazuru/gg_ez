import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game implements Steppable, Runnable {
    /*
    Létrehozunk egy arraylistet amiben game objectek, vannak, kezdetben a shipek számát
    0-rá állítjuk, illetve ArrayListet a fielddel.
    az end érték jelenti a játék végét amit kezdetben hamisra állítunk.
     */


    private static ArrayList<FlyingObject> gameObjects = new ArrayList<FlyingObject>();
    private static int numShips = 0;
    private static ArrayList<Field> fields = new ArrayList<Field>();
    private static boolean end = false;
    private static Game single_instance = null;

    public static Ship getCurrent() {
        return current;
    }
    
    public int getNumShips() {
    	return numShips;
    }

    public static void setCurrent(Ship current) {
        Game.current = current;
    }

    private static Ship current;

    private Game() {

    }

    /**
     * a játékot singletonná tesszük, így mindig le tudjuk kérni az adott játékot
     *
     * @return static single_instance
     **/
    public static Game getInstance() {
        if (single_instance == null) {
            single_instance = new Game();
            single_instance.startGame();
        }
        return single_instance;
    }

    /**
     * visszatér a gameObjects-el
     *
     * @return ArrayList<FlyingObjects> gameObjects
     **/
    public ArrayList<FlyingObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * az setEnd osztály setteli a játék végét, annak függvényében, hogy milyen bool értéket kap
     **/

    public void setEnd(boolean _end) {
        Skeleton.printFunc();
        end = _end;
        Skeleton.printFuncRet("");
    }

    /**
     * @param fo-t kitöröljük a gameObjects listájából.
     **/
    public void removeGameObject(FlyingObject fo) {
        Skeleton.printFunc();
        gameObjects.remove(fo);
        Skeleton.printFuncRet("");
    }

    /**
     * A metódus csökkenti a shipeknek a számát.
     **/
    public void decreaseNumShips() {
        Skeleton.printFunc();
        numShips--;
        Skeleton.printFuncRet("");
    }

    /**
     * A metódus növeli a shipeknek a számát.
     **/
    public void incrNumShips() {
        numShips++;
    }

    /**
     * @param f -t megkapja a metódus, amellyel a fields-ből törli azt.
     */
    public void removeField(Field f) {
        Skeleton.printFunc();
        fields.remove(f);
        Skeleton.printFuncRet("");
    }

    /**
     * A metódus segítségével megkapjuk a field listát.
     *
     * @return ArrayList<Field> fields
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * A step metódus fogja végrehajtani az egy kört reprezentáló step függvényt, ahol minden gameObjectsnek lehetőséget kínál a játék a cselekvésre.
     * Továbbá a napvihart ez fogja véghezvinni, és meghívni a fieldekre.
     */
    public void step() {

        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i).getClass().equals(Ship.class))
                current = (Ship) gameObjects.get(i);
            gameObjects.get(i).step();
        }
        if (solarStorm()) {
            int num = new Random().nextInt(fields.size());
            fields.get(num).onSolarStorm();
            for(Field f: fields.get(num).getNeighbours())
                f.onSolarStorm();
            System.out.println("NAPVIHAR A " + num + " MEZŐN!");
        }
        if (numShips == 0) {
            end = true;
        }
        System.out.println("kor vege");
    }

    public boolean getEnd() {
    	return end;
    }
    /**
     * @return boolean rand
     * random érték alapján kiértékeli hogy legyen-e napvihar
     */
    public boolean solarStorm() {
        Random random = new Random();
        int rand = random.nextInt(200000);
        return rand == 10;
    }

    /**
     * elindítja a játékot, majd inicializálja, egészen addig míg az end érték @params end false
     * addig mindenkire meghívja a steppet.
     */
    public void startGame() {
        initGame();
    }

    /**
     * Inicializálja a játékot. Létrehozza az aszteroidákat, azokat hozzáadja a fieldhez. Fieldeknek majd beállítja a szomszédait.
     * A megadott shipszám alapján, létrehoz új shipeket.
     **/
    public void initGame() {
        for (int j = 0; j < 50; j++) {
            Asteroid newAsteroid = new Asteroid();
        }
        for (int k = 2; k < fields.size(); k++) {
            fields.get(k).addNeighbour(fields.get(k - 2));
            fields.get(k).addNeighbour(fields.get(k - 1));
        }
        /*System.out.print("J�t�kosok sz�ma: ");
        choose = s.nextInt();
        numShips = choose;*/
        numShips = Vars.NUM_OF_PLAYERS;
        while (gameObjects.size() != numShips) {
            Ship newShip = new Ship();
            for (int i = 0; i < 4; i++) {
                newShip.addMaterial(new Coal());
                newShip.addMaterial(new Ice());
                newShip.addMaterial(new Iron());
                newShip.addMaterial(new Uranium());
            }
        }
        for (int i = 0; i < Math.ceil(Vars.NUM_OF_PLAYERS); i++) {
            Ufo ufo = new Ufo();
        }
    }

    /**
     * @param fo -t megkapja a metódus, amit hozzáad a gameObjects-es listához.
     **/
    public void addGameObject(FlyingObject fo) {
        gameObjects.add(fo);
    }

    /**
     * @param f -t hozzáadjuk a fields listájához.
     */
    public void addField(Field f) {
        fields.add(f);
    }

    /**
     * @return ArrayList<Material> baseReq
     * inicializálja a bázishoz szükséges nyersanyagokat
     * Ehhez hozzáad egy szenet, vasat, uránt, és egy vízjeget.
     */
    public ArrayList<Material> craftBaseReq() {
        ArrayList<Material> baseReq = new ArrayList<Material>();
        for (int i = 0; i < 3; i++) {
            baseReq.add(new Coal());
            baseReq.add(new Iron());
            baseReq.add(new Ice());
            baseReq.add(new Uranium());
        }
        return baseReq;
    }

    @Override
    public void run() {
        //try {
        while (!end) // END
        {
            step();
            //Thread.sleep(200);
        }
        /*} catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
