import java.util.ArrayList;
import java.util.Random;

class Game implements Steppable, Runnable {
    /*
    LÃ©trehozunk egy arraylistet amiben game objectek, vannak, kezdetben a shipek szÃ¡mÃ¡t
    0-rÃ¡ Ã¡llÃ­tjuk, illetve ArrayListet a fielddel.
    az end Ã©rtÃ©k jelenti a jÃ¡tÃ©k vÃ©gÃ©t amit kezdetben hamisra Ã¡llÃ­tunk.
     */


    private static ArrayList<FlyingObject> gameObjects = new ArrayList<>();
    private static int numShips = 0;
    private static ArrayList<Field> fields = new ArrayList<>();
    private static boolean end = false;
    private static Game single_instance = null;

    /**
     * visszater az aktualisan soron levo shippel
     *
     * @return Ship
     * 
     */
    public static Ship getCurrent() {
        return current;
    }
    /**
     * visszater az aktualisan szamon levo shipekkell
     *
     * @return int
     * 
     */
    public int getNumShips() {
    	return numShips;
    }
    /**
     * tarolja az aktualisan soron levo ship-et
     * 
     */
    private static Ship current;

    private Game() {

    }

    /**
     * a jÃ¡tÃ©kot singletonnÃ¡ tesszÃ¼k, Ã­gy mindig le tudjuk kÃ©rni az adott jÃ¡tÃ©kot
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
     * visszatÃ©r a gameObjects-el
     *
     * @return ArrayList<FlyingObjects> gameObjects
     **/
    public ArrayList<FlyingObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * az setEnd osztÃ¡ly setteli a jÃ¡tÃ©k vÃ©gÃ©t, annak fÃ¼ggvÃ©nyÃ©ben, hogy milyen bool Ã©rtÃ©ket kap
     **/

    public void setEnd(boolean _end) {
        Skeleton.printFunc();
        end = _end;
        Skeleton.printFuncRet("");
    }

    /**
     * @param fo-t kitÃ¶rÃ¶ljÃ¼k a gameObjects listÃ¡jÃ¡bÃ³l.
     **/
    public void removeGameObject(FlyingObject fo) {
        Skeleton.printFunc();
        gameObjects.remove(fo);
        Skeleton.printFuncRet("");
    }

    /**
     * A metÃ³dus csÃ¶kkenti a shipeknek a szÃ¡mÃ¡t.
     **/
    public void decreaseNumShips() {
        Skeleton.printFunc();
        numShips--;
        Skeleton.printFuncRet("");
    }

    /**
     * @param f -t megkapja a metÃ³dus, amellyel a fields-bÅ‘l tÃ¶rli azt.
     */
    public void removeField(Field f) {
        Skeleton.printFunc();
        fields.remove(f);
        Skeleton.printFuncRet("");
    }

    /**
     * A metÃ³dus segÃ­tsÃ©gÃ©vel megkapjuk a field listÃ¡t.
     *
     * @return ArrayList<Field> fields
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * A step metÃ³dus fogja vÃ©grehajtani az egy kÃ¶rt reprezentÃ¡lÃ³ step fÃ¼ggvÃ©nyt, ahol minden gameObjectsnek lehetÅ‘sÃ©get kÃ­nÃ¡l a jÃ¡tÃ©k a cselekvÃ©sre.
     * TovÃ¡bbÃ¡ a napvihart ez fogja vÃ©ghezvinni, Ã©s meghÃ­vni a fieldekre.
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
            System.out.println("NAPVIHAR A " + num + " MEZÅ�N!");
        }
        if (numShips == 0) {
            end = true;
        }
        System.out.println("kor vege");
    }
    /**
     * visszater a befejezettséget jelző attributum ertekevel
     *
     * @return boolean
     * 
     */
    public boolean getEnd() {
    	return end;
    }
    /**
     * @return boolean rand
     * random Ã©rtÃ©k alapjÃ¡n kiÃ©rtÃ©keli hogy legyen-e napvihar
     */
    public boolean solarStorm() {
        Random random = new Random();
        int rand = random.nextInt(4);
        return rand == 1;
    }

    /**
     * elindÃ­tja a jÃ¡tÃ©kot, majd inicializÃ¡lja, egÃ©szen addig mÃ­g az end Ã©rtÃ©k @params end false
     * addig mindenkire meghÃ­vja a steppet.
     */
    public void startGame() {
        initGame();
    }

    /**
     * InicializÃ¡lja a jÃ¡tÃ©kot. LÃ©trehozza az aszteroidÃ¡kat, azokat hozzÃ¡adja a fieldhez. Fieldeknek majd beÃ¡llÃ­tja a szomszÃ©dait.
     * A megadott shipszÃ¡m alapjÃ¡n, lÃ©trehoz Ãºj shipeket.
     **/
    public void initGame() {
        for (int j = 0; j < 50; j++) {
            new Asteroid();
        }
        for (int k = 2; k < fields.size(); k++) {
            fields.get(k).addNeighbour(fields.get(k - 2));
            fields.get(k).addNeighbour(fields.get(k - 1));
        }

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
            new Ufo();
        }
    }

    /**
     * @param fo -t megkapja a metÃ³dus, amit hozzÃ¡ad a gameObjects-es listÃ¡hoz.
     **/
    public void addGameObject(FlyingObject fo) {
        gameObjects.add(fo);
    }

    /**
     * @param f -t hozzÃ¡adjuk a fields listÃ¡jÃ¡hoz.
     */
    public void addField(Field f) {
        fields.add(f);
    }

    /**
     * @return ArrayList<Material> baseReq
     * inicializÃ¡lja a bÃ¡zishoz szÃ¼ksÃ©ges nyersanyagokat
     * Ehhez hozzÃ¡ad egy szenet, vasat, urÃ¡nt, Ã©s egy vÃ­zjeget.
     */
    public ArrayList<Material> craftBaseReq() {
        ArrayList<Material> baseReq = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            baseReq.add(new Coal());
            baseReq.add(new Iron());
            baseReq.add(new Ice());
            baseReq.add(new Uranium());
        }
        return baseReq;
    }

    /**
     * a GUI száláért felelős run fv
     * step-eket hiv meg
     * 
     * 
     **/
    public void run() {
        //try {
        while (!end) // END
        {
            step();
            //Thread.sleep(200);
        }
        SpaceGameView.refresh();
        /*} catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
