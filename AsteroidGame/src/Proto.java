import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Proto {

    private static boolean running = true;
    private static boolean random = false;
    private static boolean saving = false;
    private static String save_path = null;
    private static ArrayList<Testable> gameObjects = new ArrayList<>();
    private static ArrayList<Testable<Ship, Integer>> ships = new ArrayList<>();
    private static Testable<Ship, Integer> current = null;
    private static Testable<Robot, Integer> currentRobot = null;
    private static Testable<Ufo, Integer> currentUfo = null;
    private static Class currType;

    /**
     * System.out.println(s) ki�r�s leegyszer�s�t�se
     * @param s
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Nem megfelel� param�terez�s eset�n �r�dik ki, sok haszn�lat miatt kihozva ide
     */
    public static void insParam() {
        log("Nem megfelelo parameterezes! Probalja ujra!");
    }

    /**
     * Bizonyos ID-j� elemek megkeres�s�hez van erre sz�ks�g
     * @param ID-j� Testable objectet megkeresi a gameObjects list�ban
     * @return gameObjects lista megfelel� elem�t
     */
    public static Testable findGameObject(int ID) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            if (gameObjects.get(i).num.equals(ID))
                return gameObjects.get(i);
        }
        return null;
    }

    /**
     * Bizonyos elemek megl�t�nek vizsg�lat�hoz van erre sz�ks�g
     * @param t objektumot megkeresi a gameObjects list�ban
     * @return gameObjects lista megfelel� elem�t
     */
    public static Testable findObject(Object t) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            if ((gameObjects.get(i).obj).equals(t)) {
                return gameObjects.get(i);
            }
        }
        return null;
    }

    /**
     * Megtal�lunk egy bizonyos oszt�ly� objektumot
     * @param c oszt�ly� objektumot keress�k
     * @return gameObjects lista megfelel� elem�t
     */
    public static Testable findObjectByClass(Class c) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            if (c.equals(gameObjects.get(i).getObj())) {
                return gameObjects.get(i);
            }
        }
        return null;
    }

    /**
     * A k�r ell�ptet�se a k�vetkez� j�t�kosra
     */
    public static void next() {
        runCommand("skipturn");
    }

    /**
     * Nincsen aktu�lis kivalasztott FlyingObject, ezt pedig kilogolja a fv
     */
    public static void noCurr() {
        log("Nincsen aktualis FlyingObject kivalasztva!");
    }

    /**
     * Megadja, hogy a param�terk�nt adott Testable objektum egy Material-t tartalmaz-e
     * @param t a param�terk�nt adott Testable
     * @return true, amennyiben Material
     * @return false, amennyiben nem az
     */
    public static boolean isMaterial(Testable t) {
        return (t.getObj().equals(Ice.class) || t.getObj().equals(Iron.class) || t.getObj().equals(Coal.class) || t.getObj().equals(Uranium.class));
    }

    /**
     * Megadja, hogy a param�terk�nt adott Testable objektum egy Field-et tartalmaz-e
     * @param t a param�terk�nt adott Testable
     * @return true, amennyiben Field
     * @return false, amennyiben nem az
     */
    public static boolean isField(Testable t) {
        return (t.getObj().equals(Asteroid.class) || t.getObj().equals(Gate.class));
    }

    /**
     * Megadja, hogy a param�terk�nt adott Testable objektum egy FlyingObject-et tartalmaz-e
     * @param t a param�terk�nt adott Testable
     * @return true, amennyiben FlyingObject
     * @return false, amennyiben nem az
     */
    public static boolean isFlyingObject(Testable t) {
        return (t.getObj().equals(Robot.class) || t.getObj().equals(Ship.class) || t.getObj().equals(Ufo.class));
    }

    /**
     * Ez a f�ggv�ny felel az�rt, hogyha egy telepes kib�ny�szik valami, az inventoryj�ban l�v� elem beker�lj�n
     * a j�t�kelemek k�z� is. Ugyanez az eset a Gate-kkel, melyeket hogyha a j�t�kos craftol, beker�lnek a gameObjects
     * list�ba.
     * Valamint ha egy FlyingObject meghalna, vagy egy Aszteroida felrobbanna, abban az esetben ez a f�ggv�ny t�vol�tja
     * el az adott elemeket a gameObjects t�mbb�l.
     */
    public static void checkGameObjects() {
        for (Testable s : ships) {
            for (Material m : ((Ship) s.obj).getMaterial()) {
                if (findObject(m) == null) {
                    Integer idx = 0;
                    for (Testable g : gameObjects) {
                        if ((Integer) g.num > idx) {
                            idx = (Integer) g.num;
                        }
                    }
                    gameObjects.add(new Testable(m, idx + 1));
                }
            }
            for (Gate gate : ((Ship) s.obj).getGates()) {
                if (findObject(gate) == null) {
                    Integer idx = 0;
                    for (Testable g : gameObjects) {
                        if ((Integer) g.num > idx) {
                            idx = (Integer) g.num;
                        }
                    }
                    gameObjects.add(new Testable(gate, idx + 1));
                }
            }
        }

        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            boolean in = false;
            Testable t = gameObjects.get(i);
            inner:
            {
                for (Field f : Game.getInstance().getFields()) {
                    if (t.obj.equals(f)) {
                        in = true;
                        break inner;
                    }
                }
                for (FlyingObject flyingObject : Game.getInstance().getGameObjects()) {
                    if (t.obj.equals(flyingObject)) {
                        in = true;
                        break inner;
                    }
                }
            }
            if (!in && !isMaterial(t)) {
                gameObjects.remove(t);
            }
        }
    }

    /**
     * Parancsok futtat�sa
     * @param cmd a felhaszn�l� �ltal megadfott parancs
     */
    public static void runCommand(String cmd) {
        cmd = cmd.toLowerCase();
        String[] params = cmd.split(" ");
        Testable ship;
        Testable material = null;
        Testable asteroid;
        Testable gameobject;
        Testable field;

        checkGameObjects();

        switch (params[0]) {

            //Az elem l�trehoz�st megval�s�t� parancs implement�ci�ja

            case "create":

                if ((params.length != 3 && !params[1].equals("asteroid")) || ((params[1].equals("asteroid") && random && params.length != 3) || (params[1].equals("asteroid") && !random && params.length != 6))) {
                    insParam();
                    return;
                }

                if (findGameObject(Integer.parseInt(params[2])) != null) {
                    log("Az ID mar hasznalva van!");
                    return;
                } else {
                    switch (params[1]) {
                        case "asteroid":
                            Asteroid tmp = new Asteroid();
                            if (!random) {
                                switch (params[3]) {
                                    case "none":
                                        tmp.acceptCore(null);
                                        break;
                                    case "ice":
                                        tmp.acceptCore(new Ice());
                                        break;
                                    case "iron":
                                        tmp.acceptCore(new Iron());
                                        break;
                                    case "coal":
                                        tmp.acceptCore(new Coal());
                                        break;
                                    case "uranium":
                                        tmp.acceptCore(new Uranium());
                                        break;
                                    default:
                                        log("Nincs ilyen nyersanyag!");
                                        return;
                                }
                                tmp.setInSunProximity(Boolean.parseBoolean(params[4]));
                                tmp.setLayer(Integer.parseInt(params[5]));
                            }
                            gameObjects.add(new Testable(tmp, Integer.parseInt(params[2])));
                            return;
                        case "ice":
                            gameObjects.add(new Testable(new Ice(), Integer.parseInt(params[2])));
                            return;
                        case "coal":
                            gameObjects.add(new Testable(new Coal(), Integer.parseInt(params[2])));
                            return;
                        case "iron":
                            gameObjects.add(new Testable(new Iron(), Integer.parseInt(params[2])));
                            return;
                        case "uranium":
                            gameObjects.add(new Testable(new Uranium(), Integer.parseInt(params[2])));
                            return;
                        case "ship":
                            ships.add(new Testable(new Ship(), Integer.parseInt(params[2])));
                            if (currType == null) {
                                current = ships.get(0);
                                currType = Ship.class;
                            }
                            gameObjects.add(ships.get(ships.size() - 1));
                            return;
                        case "ufo":
                            gameObjects.add(new Testable(new Ufo(), Integer.parseInt(params[2])));
                            if (currType == null) {
                                currType = Ufo.class;
                                currentUfo = gameObjects.get(gameObjects.size() - 1);
                            }
                            return;
                        default:
                            insParam();
                            return;
                    }
                }

            // A material hozz�ad�sa Telepeshez, Field szomsz�ds�g be�ll�t�sa, FlyingObject hozz�ad�sa Field-hez
            // megval�s�t�sa

            case "add":
                if (params.length != 3) {
                    insParam();
                    return;
                }

                ship = findGameObject(Integer.parseInt(params[2]));
                material = findGameObject(Integer.parseInt(params[1]));

                if (ship == null) {
                    insParam();
                    return;
                }
                if (material == null) {
                    insParam();
                    return;
                }

                if (ship.getObj().equals(Ship.class) && isMaterial(material)) {
                    if (((Ship) ship.obj).addMaterial(((Material) material.obj))) {
                        log("Material " + material.num + " sikeresen hozzaadva a " + ship.num + " telepeshez.");
                    } else {
                        log("Nincs eleg hely a telepesnel!");
                    }
                } else if (isField(ship) && isField(material)) {
                    ((Field) ship.obj).addNeighbour((Field) material.obj);
                } else if (isField(ship) && isFlyingObject(material)) {
                    if (((FlyingObject) material.obj).getLocation() != null)
                        ((FlyingObject) material.obj).getLocation().removeFlyingObject((FlyingObject) material.obj);
                    ((Field) ship.obj).acceptFlyingObject((FlyingObject) material.obj);
                }
                return;

            // Nyersanyag elt�vol�t�s�nak megold�sa a telepest�l

            case "remove":
                if (params.length != 3) {
                    insParam();
                    return;
                }

                ship = findGameObject(Integer.parseInt(params[2]));
                material = findGameObject(Integer.parseInt(params[1]));

                if (ship == null || !ship.getObj().equals(Ship.class)) {
                    log("Nem letezik ilyen ID-ju telepes!");
                    return;
                }
                if (material == null || !isMaterial(material)) {
                    log("Nem letezik ilyen ID-ju nyersanyag!");
                    return;
                }

                for (Material m : ((Ship) ship.obj).getMaterials()) {
                    if (m.equals(material.obj)) {
                        ((Ship) ship.obj).removeMaterial((Material) material.obj);
                        log(material.num + " nyersanyag sikeresen eltavolitva a " + ship.num + " telepestol!");
                        return;
                    }
                }
                log("Nincs ilyen ID-ju nyersanyag a telepesnel!");
                return;

            // A megadott ID-j� FlyingObject kiv�laszt�sa

            case "selectplayer":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                ship = findGameObject(Integer.parseInt(params[1]));

                if (ship == null || !isFlyingObject(ship)) {
                    log("Nincs ilyen ID-ju FlyingObject!");
                    return;
                }
                setCurrent(ship);

                return;

            // A k�r ell�ptet�s�nek megval�s�t�sa

            case "skipturn":
                if (noCurrentSelected()) {
                    log("Nincs meg kivalasztott FlyingObject!");
                    return;
                }
                if (gameObjects.indexOf(findObject(current)) < gameObjects.size() - 1) {
                    for (int i = gameObjects.indexOf(findObject(current)) + 1; i < gameObjects.size(); i++) {
                        if (isFlyingObject(gameObjects.get(i))) {
                            currType = gameObjects.get(i).getObj();
                            setCurrent(gameObjects.get(i));
                            return;
                        }
                    }
                } else {
                    for (int i = 0; i <= gameObjects.indexOf(findObject(current)); i++) {
                        if (isFlyingObject(gameObjects.get(i))) {
                            currType = gameObjects.get(i).getObj();
                            setCurrent(gameObjects.get(i));
                            return;
                        }
                    }
                }
                return;

            // A napvihar megval�s�t�sa egy adott Field-re

            case "solarstorm":
                if (params.length != 2) {
                    insParam();
                    return;
                }

                gameobject = findGameObject(Integer.parseInt(params[1]));

                if (gameobject == null) {
                    log("Nincs ilyen ID-ju jatekelem!");
                    return;
                }

                if (isFlyingObject(gameobject)) {
                    ((FlyingObject) gameobject.obj).onSolarStormCase();
                } else if (isField(gameobject)) {
                    ((Field) gameobject.obj).onSolarStorm();
                } else {
                    log("Nem hajthato vegre napvihar a kijelolt elemen!");
                }
                return;

            // Egy adott aszteroida felrobbant�sa

            case "explode":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                asteroid = findGameObject(Integer.parseInt(params[1]));

                if (asteroid == null || !asteroid.getObj().equals(Asteroid.class)) {
                    log("Nincs ilyen ID-ju aszteroida!");
                    return;
                }

                log("A " + asteroid.num + " ID-ju aszteroida sikeresen felrobbantva!");
                ((Asteroid) asteroid.obj).explode();
                return;

            // A j�t�kelemek list�z�sa, vagy ID param�terez�s eset�n a megadott j�t�kelem inform�ci�inak list�z�sa

            case "stat":
                if (params.length == 1) {
                    if (!gameObjects.isEmpty()) {
                        log("Letrehozott jatekelemek listaja:\n(Bovebb informacioert hasznalja a \"stat <ID>\" parancsot)\n");
                        for (Testable t : gameObjects) {
                            log("Class: " + t.getObj().toString().split(" ")[1]);
                            log("ID: " + t.num);
                        }
                    } else {
                        log("Nincsen meg letrehozott jatekelem!");
                    }
                    return;
                }
                if (params.length != 2) {
                    insParam();
                    return;
                }

                gameobject = findGameObject(Integer.parseInt(params[1]));

                if (gameobject == null) {
                    log("Nincs ilyen ID-ju jatekelem!");
                    return;
                }

                Object obj = gameobject.getObj();

                log("Class: " + gameobject.getObj().toString().split(" ")[1]);
                log("ID: " + gameobject.num);

                if (isField(gameobject)) {
                    if (obj.equals(Gate.class)) {
                        log("Working: " + ((Gate) gameobject.obj).getWorking());
                    } else if (((Asteroid) gameobject.obj).getCore() != null) {
                        log("Core: " + ((Asteroid) gameobject.obj).getCore().getClass().toString().split(" ")[1]);
                        if(((Asteroid)gameobject.obj).getCore().getClass().equals(Uranium.class)){
                            log("\tExposedN: "+ ((Uranium)((Asteroid) gameobject.obj).getCore()).getExposedN());
                        }
                    } else {
                        log("Core: none");
                    }

                    if (obj.equals(Asteroid.class)) {
                        log("InSunProximity: " + ((Asteroid) gameobject.obj).getInSunProximity());
                        log("Layer thickness: " + ((Asteroid) gameobject.obj).getLayer());
                    }

                    if (!((Field) gameobject.obj).getOnSurface().isEmpty()) {
                        log("OnSurface ID: ");
                        for (FlyingObject flyingObject : ((Field) gameobject.obj).getOnSurface()) {
                            ship = findObject(flyingObject);
                            if (ship != null) {
                                log("\t" + ship.num);
                            }
                        }
                    }

                    if (!((Field) gameobject.obj).getNeighbours().isEmpty()) {
                        log("Neighbours ID: ");
                        for (Field f : ((Field) gameobject.obj).getNeighbours()) {
                            field = findObject(f);
                            if (field != null) {
                                log("\t" + field.num);
                            }
                        }
                    }
                } else if (isFlyingObject(gameobject)) {
                    if (((FlyingObject) gameobject.obj).getLocation() != null) {
                        field = findObject(((FlyingObject) gameobject.obj).getLocation());
                        if (field != null) {
                            log("Location: " + field.num);
                        }
                    } else {
                        log("Location: none");
                    }
                    if (gameobject.getObj().equals(Ship.class)) {
                        if (!((Ship) gameobject.obj).getMaterials().isEmpty()) {
                            log("Inventory: ");
                            for (Material m : ((Ship) gameobject.obj).getMaterials()) {
                                material = findObject(m);
                                if (material != null) {
                                    log("\tClass: " + material.getObj().toString().split(" ")[1] + ", ID: " + material.num);
                                }
                            }
                        }
                    }
                    if ((currType.equals(Ship.class) && gameobject.equals(current)) || (currType.equals(Robot.class) && gameobject.equals(currentRobot)) || (currType.equals(Ufo.class) && gameobject.equals(currentUfo))) {
                        log("Currently their turn");
                    } else {
                        log("Not their turn");
                    }
                } else if (gameobject.getObj().equals(Uranium.class)) {
                    log("ExposedN: " + ((Uranium) gameobject.obj).getExposedN());
                }
                return;

            // Material visszarak�sa aszteroida belsej�be

            case "putback":
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (!currType.equals(Ship.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }
                if (!random) {
                    if (params.length != 2) {
                        insParam();
                        return;
                    }
                    switch (params[1]) {
                        case "iron":
                            material = findObjectByClass(Iron.class);
                            break;
                        case "coal":
                            material = findObjectByClass(Coal.class);
                            break;
                        case "ice":
                            material = findObjectByClass(Ice.class);
                            break;
                        case "uranium":
                            material = findObjectByClass(Uranium.class);
                            break;
                        default:
                            insParam();
                            return;
                    }

                } else {
                    int random = new Random().nextInt(4);
                    switch (random) {
                        case 0:
                            material = findObjectByClass(Iron.class);
                            break;
                        case 1:
                            material = findObjectByClass(Coal.class);
                            break;
                        case 2:
                            material = findObjectByClass(Ice.class);
                            break;
                        case 3:
                            material = findObjectByClass(Uranium.class);
                            break;
                    }

                }
                if (material != null) {
                    if (current.obj.getLocation().fillBy(current.obj, (Material) material.obj)) {
                        log("Mag sikeresen berakva!");
                        next();
                    } else {
                        log("A mag berakasa sikertelen!");
                    }
                    return;
                }
                log("Nincs a telepesnel az adott nyersanyagbol!");
                return;

            // UFO/Ship eset�ben b�ny�szat megold�sa

            case "mine":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (currType.equals(Robot.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }
                if (currType.equals(Ship.class) && current.obj.mine() || currType.equals(Ufo.class) && currentUfo.obj.mine()) {
                    log("Sikeres banyaszat!");
                    next();
                } else {
                    log("Sikertelen banyaszat!");
                }
                return;

            // Robot/Ship eset�ben a f�r�s megold�sa

            case "drill":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (currType.equals(Ufo.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }
                if (currType.equals(Ship.class) && current.obj.drill() || currType.equals(Robot.class) && currentRobot.obj.drill()) {
                    log("Sikeres furas!");
                    next();
                    return;
                }
                log("Sikertelen furas!");
                return;

            // FlyingObjectek sz�m�ra a szomsz�dos Field-re t�rt�n� mozg�s megold�sa

            case "move":
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (!random) {
                    if (params.length != 2) {
                        insParam();
                        return;
                    }

                    field = findGameObject(Integer.parseInt(params[1]));

                    if (field == null) {
                        log("Nem letezik ilyen ID-ju Field!");
                        return;
                    }
                    if (!moveCurrent(field)) {
                        log("Nincs ilyen ID-ju szomszedja a helyszinnek!");
                    }
                } else {
                    if (!moveRandomly()) {
                        log("Nincs szomszedja a fieldnek!");
                    }
                }
                return;

            // A craftol�sok megval�s�t�sa robot, gate, base eset�ben

            case "craft":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (!currType.equals(Ship.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }
                switch (params[1]) {
                    case "base":
                        if (current.obj.buildBase()) {
                            log("Bazis sikeresen megepitve, GG!");
                        } else {
                            log("A bazis megepitese sikertelen!");
                        }
                        return;
                    case "robot":
                        if (current.obj.buildRobot()) {
                            Robot r = new Robot();
                            current.obj.getLocation().acceptFlyingObject(r);
                            Integer idx = 0;
                            for (Testable t : gameObjects) {
                                if ((Integer) t.num > idx)
                                    idx = (Integer) t.num;
                            }
                            gameObjects.add(new Testable(r, idx + 1));
                            log("Robot sikeresen megepitve.");
                            next();
                        } else {
                            log("A robot megepitese sikertelen!");
                        }
                        return;
                    case "gate":
                        if (current.obj.craftGatePair()) {
                            Gate g1 = new Gate();
                            Gate g2 = new Gate();
                            g1.setPair(g2);
                            g2.setPair(g1);
                            current.obj.addGate(g1);
                            current.obj.addGate(g2);
                            log("Kapu-par sikeresen megepitve.");
                            next();
                        } else {
                            log("A kapu-par megepitese sikertelen!");
                        }
                        return;
                    default:
                        insParam();
                        return;
                }

            // Teleport-Kapu haszn�lat�nak megval�s�t�sa Ship/UFO eset�ben

            case "usegate":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (currType.equals(Robot.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }

                if (current.obj.useGate()) {
                    log("Kapu sikeresen hasznalva!");
                    next();
                } else {
                    log("Kapu hasznalata sikertelen!");
                }
                return;

            // Teleport-Kapu felv�tel�nek megold�sa

            case "pickupgate":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (!currType.equals(Ship.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }
                if (current.obj.pickUpGate()) {
                    log("Kapu sikeresen felveve!");
                    next();
                    return;
                }
                log("Kapu felvetele siekrtelen!");
                return;

            // Teleport-Kapu lerak�sa

            case "placegate":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (noCurrentSelected()) {
                    noCurr();
                    return;
                }
                if (!currType.equals(Ship.class)) {
                    log("Nem hajthato vegre ez a parancs.");
                    return;
                }
                if (current.obj.placeGate()) {
                    log("Kapu sikeresen lerakva!");
                    next();
                    return;
                }
                log("Kapu lerakasa sikertelen!");
                return;

            // Napk�zels�g ellent�tesre �ll�t�sa

            case "sun":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                field = findGameObject(Integer.parseInt(params[1]));

                if (field == null || !field.getObj().equals(Asteroid.class)) {
                    insParam();
                    return;
                }

                ((Asteroid) field.obj).setInSunProximity(!((Asteroid) field.obj).getInSunProximity());
                log(field.num.toString() + " ID napkozelsegi erteke: " + ((Asteroid) field.obj).getInSunProximity());
                return;

            // Egy sz�veges konfigur�ci�s tesztf�jl bet�lt�s�nek megval�s�t�sa

            case "load":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                readFile(params[1]);
                return;

            // Egy sz�veges f�jlba parancsok lement�s�nek megval�s�t�sa

            case "save":
                if (saving && params.length == 1) {
                    saving = false;
                    save_path = null;
                    log("Mentes kikapcsolva");
                    return;
                }
                if (params.length != 2) {
                    insParam();
                    return;
                }

                saving = true;
                save_path = params[1];

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(save_path));
                    bw.write("");
                    bw.close();
                    log("Mentes bekapcsolva a " + save_path + " fajlba!");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return;

            // A random be�ll�t�sa ellent�tes �rt�k�re

            case "random":
                if (random) {
                    random = false;
                    log("Random kikapcsolva.");
                } else {
                    random = true;
                    log("Random bekapcsolva.");
                }
                return;

            // A be�ll�t�sok null�z�sa, mintha �jraind�tottuk volna a programot

            case "purge":
                random = false;
                saving = false;
                save_path = null;
                gameObjects = new ArrayList<>();
                ships = new ArrayList<>();
                current = null;
                currentRobot = null;
                currentUfo = null;
                currType = null;
                return;

            // A parancsok list�z�sa, le�r�sainak ki�r�sa

            case "help":
                log("Parancsok:\n");
                log("create <parameter1> <parameter1_id>\nLetrehoz az adott parameterekhez huen egy objektumot.\nPl.: create asteroid 103, create asteroid 104, create ice 12, create iron 14, ..., create ship 1\n");
                log("add <mit_id> <mihez_id>\nHozzaadja a mit_id-vel rendelkezo elemet a hova_id-vel rendelkezo elemhez.\nPl.: add 12 1 (jeg hozzaadasa a hajo tarolojaba), add 103 104 (aszteroidak szomszedba allitasa)\n");
                log("remove <mit_id> <honnan_id>\nKiveszuk a mit_id-vel rendelkezo elemet a honnan_id-val rendelkezo elembol.\nPl: remove 12 1 (jeg kivetele a hajo tarolojabol)\n");
                log("selectplayer <player_id>\nValtunk a parameterkent kapott jatekosra.\nPl.: selectplayer 1\n");
                log("skipturn\nAz aktualis jatekos skip-eli a k�ret, nem csinal semmit.\nPl.: skipturn\n");
                log("solarstorm <id>\nSolarstormot hajtunk vegre a parameterkent kapott objektumra.\nPl.:solarstorm 1\n");
                log("explode <id>\nRobbanast hajtunk vegre a parameterkent kapott aszteroidan.\nPl.: explode 23\n");
                log("stat <id>\nA parameterkent kapott id-ju objektum minden tulajdonsagat ki�rja.\nstat 1\n");
                log("putback <material>\nA kivalasztott materialt visszahelyezi az aszteroidaba, a telepes inventoryjabol ekkor ez az adott nyersanyag t�rlodik.\nPl.: putback iron, putback coal\n");
                log("mine\nAz location-bol, ha az aszteroida, kiveszi a magjat, ha van neki, azt hozzaadja az inventory-hoz, ha nincs nala mar 10 db.\nPl.: mine\n");
                log("drill\nHa aszteroidan all, akkor az aszteroida kerget atfurja 1 egyseggel, ha meg nem 0.\nPl.: drill\n");
                log("move <id>\natlep egy szomszedos mezore, ha van olyan.\nPl.: move 2, move 3\n");
                log("craft <parameter>\nCraftol a jatekos a nala levo nyersanyagokbol egy adott jatekelemet.\nPl.: craft base, craft robot, craft gate\n");
                log("usegate\nHa kapun all, akkor hasznalja a kapu, ami atteleportalja a parjahoz, ha az is  muk�dik.\nPl.: usegate\n");
                log("placegate\nKapu lehelyez�se.\nPl.: placegate\n");
                log("pickupgate\nHa kapun all, felveszi a kaput, ha nincs nala meg 3 db.\nPl.: pickupgate\n");
                log("load <fajlnev>\nA megadott fajlbol beolvassa a fentebb talalhato parancsok alapjan a jatek egy allasat.\nPl.: load test.txt\n");
                log("save <fajlnev>\nA futas elejen szukseges bekapcsolni ezt az opciot, ez esetben minden ezutan futtatott parancsot ki�r a megadott fajlba.\nPl.: save test.txt\n");
                log("random\nA random mukodest ki/be tudjuk kapcsolni vele.\nPl.: random\n");
                log("purge\nMinden beallitast visszaallit, mintha ujraindulna a program.\nPl.: purge\n");
                return;

            // Kil�p�s az alkalmaz�sb�l

            case "exit":
                running = false;
                return;

            // Amennyiben nincs az inputban megadott parancs, ez fut le

            default:
                log("Ez a parancs nem letezik! A parancsok listazasahoz irja be a \"help\" parancsot.");
                break;
        }

    }

    /**
     * A jelenlegi objektum mozg�s�nak megold�sa a field param�terre
     * @param field ahova mozgatni szeretn�nk a jelenlegi elemet
     * @return true, amennyiben a mozg�s siker�lt
     * @return false az ellenkez� esetben
     */
    private static boolean moveCurrent(Testable field) {
        Testable gameobject;
        if (currType.equals(Ship.class) && !current.obj.getLocation().getNeighbours().isEmpty()) {
            for (Field f : current.obj.getLocation().getNeighbours()) {
                gameobject = findObject(f);
                if (gameobject != null && gameobject.equals(field)) {
                    log("FlyingObject atmozgatva " + findObject(current.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");

                    current.obj.getLocation().removeFlyingObject(current.obj);
                    ((Field) gameobject.obj).acceptFlyingObject(current.obj);

                    next();
                    return true;
                }
            }
        } else if (currType.equals(Robot.class) && !currentRobot.obj.getLocation().getNeighbours().isEmpty()) {
            for (Field f : currentRobot.obj.getLocation().getNeighbours()) {
                gameobject = findObject(f);
                if (gameobject != null && gameobject.equals(field)) {
                    log("FlyingObject atmozgatva " + findObject(currentRobot.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");

                    currentRobot.obj.getLocation().removeFlyingObject(currentRobot.obj);
                    ((Field) gameobject.obj).acceptFlyingObject(currentRobot.obj);

                    next();
                    return true;
                }
            }
        } else if (currType.equals(Ufo.class) && !currentUfo.obj.getLocation().getNeighbours().isEmpty()){
            for (Field f : currentUfo.obj.getLocation().getNeighbours()) {
                gameobject = findObject(f);
                if (gameobject != null && gameobject.equals(field)) {
                    log("FlyingObject atmozgatva " + findObject(currentUfo.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");

                    currentUfo.obj.getLocation().removeFlyingObject(currentUfo.obj);
                    ((Field) gameobject.obj).acceptFlyingObject(currentUfo.obj);

                    next();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Amennyiben a random boolean be van �ll�tva, ez fut le, �s v�laszt egy random helyet, ahova mozoghat a
     * FlyingObject
     * @return true, amennyiben siker�lt mozogni
     * @return false ellenkez� esetben
     */
    private static boolean moveRandomly() {
        int random;
        Testable gameobject;
        if (currType.equals(Ship.class) && !current.obj.getLocation().getNeighbours().isEmpty()) {
            random = new Random().nextInt(current.obj.getLocation().getNeighbours().size());
            gameobject = findObject(current.obj.getLocation().getNeighbours().get(random));
            log("FlyingObject atmozgatva " + findObject(current.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");
            current.obj.getLocation().removeFlyingObject(current.obj);
            ((Field) gameobject.obj).acceptFlyingObject(current.obj);
            next();
            return true;
        } else if (currType.equals(Robot.class) && !currentRobot.obj.getLocation().getNeighbours().isEmpty()) {
            random = new Random().nextInt(currentRobot.obj.getLocation().getNeighbours().size());
            gameobject = findObject(currentRobot.obj.getLocation().getNeighbours().get(random));
            log("FlyingObject atmozgatva " + findObject(currentRobot.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");
            currentRobot.obj.getLocation().removeFlyingObject(currentRobot.obj);
            ((Field) gameobject.obj).acceptFlyingObject(currentRobot.obj);
            next();
            return true;
        } else if (currType.equals(Ufo.class) && !currentUfo.obj.getLocation().getNeighbours().isEmpty()) {
            random = new Random().nextInt(currentUfo.obj.getLocation().getNeighbours().size());
            gameobject = findObject(currentUfo.obj.getLocation().getNeighbours().get(random));
            log("FlyingObject atmozgatva " + findObject(currentUfo.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");
            currentUfo.obj.getLocation().removeFlyingObject(currentUfo.obj);
            ((Field) gameobject.obj).acceptFlyingObject(currentUfo.obj);
            next();
            return true;
        }
        return false;
    }

    /**
     * A jelenlegi objektum be�ll�t�sa a t param�ter �ltal t�rolt elemre
     * @param t a leend� jelenlegi elem
     */
    private static void setCurrent(Testable t) {
        if (Robot.class.equals(t.getObj())) {
            currType = Robot.class;
            currentRobot = t;
            current = null;
            currentUfo = null;
            log("A kivalasztott FlyingObject: " + currentRobot.num);
        } else if (Ship.class.equals(t.getObj())) {
            currType = Ship.class;
            current = t;
            currentUfo = null;
            currentRobot = null;
            log("A kivalasztott FlyingObject: " + current.num);
        } else if (Ufo.class.equals(t.getObj())) {
            currType = Ufo.class;
            currentUfo = t;
            current = null;
            currentRobot = null;
            log("A kivalasztott FlyingObject: " + currentUfo.num);
        }
    }

    /**
     * Visszat�r�si �rt�k�vel jelzi, van-e jelenlegi FlyingObject
     * @return true, amennyiben van
     * @return false ellenkez� esetben
     */
    private static boolean noCurrentSelected() {
        return (currType.equals(Robot.class) && currentRobot == null) || (currType.equals(Ship.class) && current == null) || (currType.equals(Ufo.class) && currentUfo == null);
    }

    /**
     * Parancs bek�r�s�nek megval�s�t�sa
     * @return a beolvasott sort, vagy hiba eset�n
     * @return null-t
     */
    public static String getCommand() {
        try {
            System.out.print("Kerlek irj be egy parancsot: ");
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fileb�l beolvas�s implement�ci�ja a filepath �tvonalon tal�lhat� f�jlb�l
     * @param filepath az el�r�si �t
     */
    public static void readFile(String filepath) {
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                runCommand(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * A program bel�p�si pontja, v�gtelen ciklusban fut a lek�rdez�s, am�g a running true. Az exit paranccsal ez
     * false-ra �ll�that�.
     * @param args bemeneti param�terek a program ind�t�s�n�l
     */
    /*public static void main(String[] args) {
        Game.getInstance();
        log("A segitseghez irja be a \"help\" parancsot, vagy forduljon a dokumentaciohoz.\n");
        while (running) {
            if (!saving) {
                runCommand(getCommand());
            } else {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(save_path, true));
                    String str = getCommand();
                    runCommand(str);
                    bw.append(str + "\n");
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}
