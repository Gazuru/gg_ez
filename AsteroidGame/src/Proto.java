import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Proto {

    private static boolean running = true;
    private static boolean random = false;
    private static boolean saving = false;
    private static String save_path = null;
    private static ArrayList<Testable> gameObjects = new ArrayList<>();
    private static ArrayList<Testable<Ship, Integer>> ships = new ArrayList<>();
    private static ArrayList<Testable<Asteroid, Integer>> asteroids = new ArrayList<>();
    private static ArrayList<Testable<Material, Integer>> materials = new ArrayList<>();
    private static Testable<Ship, Integer> current = null;
    /*private static ArrayList<Ship> ships = new ArrayList<>();
    private static ArrayList<Asteroid> asteroids = new ArrayList<>();
    private static ArrayList<Material> materials = new ArrayList<>();*/

    public static void log(String s) {
        System.out.println(s);
    }

    public static void insParam() {
        log("Nem megfelelo parameterezes! Probalja ujra!");
    }

    public static Testable findGameObject(int ID) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            if (gameObjects.get(i).num.equals(ID))
                return gameObjects.get(i);
        }
        return null;
    }

    public static Testable findObject(Object t) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            if (((Object) gameObjects.get(i).obj).equals(t)) {
                return gameObjects.get(i);
            }
        }
        return null;
    }

    public static Testable findObjectByClass(Class c) {
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            if (c.equals(gameObjects.get(i).getObj())) {
                return gameObjects.get(i);
            }
        }
        return null;
    }

    /*public static Testable findShip(int ID) {
        for (int i = ships.size() - 1; i >= 0; i--) {
            if (ships.get(i).num.equals(ID))
                return ships.get(i);
        }
        return null;
    }

    public static Testable findAsteroid(int ID) {
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            if (asteroids.get(i).num.equals(ID))
                return asteroids.get(i);
        }
        return null;
    }

    public static Testable findMaterial(int ID) {
        for (int i = materials.size() - 1; i >= 0; i--) {
            if (materials.get(i).num.equals(ID))
                return materials.get(i);
        }
        return null;
    }*/

    public static void next() {
        runCommand("skipturn");
    }

    public static void noCurr() {
        log("Nincsen aktualis telepes a jatekban!");
    }

    public static boolean isMaterial(Testable t) {
        return (t.getObj().equals(Ice.class) || t.getObj().equals(Iron.class) || t.getObj().equals(Coal.class) || t.getObj().equals(Uranium.class));
    }

    public static boolean isField(Testable t) {
        return (t.getObj().equals(Asteroid.class) || t.getObj().equals(Gate.class));
    }

    public static boolean isFlyingObject(Testable t) {
        return (t.getObj().equals(Robot.class) || t.getObj().equals(Ship.class) || t.getObj().equals(Ufo.class));
    }

    public static void checkGameObjects() {
        for (Testable s : ships) {
            boolean in = false;
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

    public static void runCommand(String cmd) {
        cmd = cmd.toLowerCase();
        String[] params = cmd.split(" ");
        Testable ship = null;
        Testable material = null;
        Testable asteroid = null;
        Testable gameobject = null;
        Testable field = null;

        checkGameObjects();

        switch (params[0]) {
            case "create":

                if (params.length != 3) {
                    insParam();
                    return;
                }

                if (findGameObject(Integer.parseInt(params[2])) != null) {
                    log("Az ID mar hasznalva van!");
                    return;
                } else {
                    switch (params[1]) {
                        case "asteroid":
                            asteroids.add(new Testable(new Asteroid(), Integer.parseInt(params[2])));
                            gameObjects.add(asteroids.get(asteroids.size() - 1));
                            return;
                        case "ice":
                            materials.add(new Testable(new Ice(), Integer.parseInt(params[2])));
                            gameObjects.add(materials.get(materials.size() - 1));
                            return;
                        case "coal":
                            materials.add(new Testable(new Coal(), Integer.parseInt(params[2])));
                            gameObjects.add(materials.get(materials.size() - 1));
                            return;
                        case "iron":
                            materials.add(new Testable(new Iron(), Integer.parseInt(params[2])));
                            gameObjects.add(materials.get(materials.size() - 1));
                            return;
                        case "uranium":
                            materials.add(new Testable(new Uranium(), Integer.parseInt(params[2])));
                            gameObjects.add(materials.get(materials.size() - 1));
                            return;
                        case "ship":
                            ships.add(new Testable(new Ship(), Integer.parseInt(params[2])));
                            if (ships.size() == 1)
                                current = ships.get(0);
                            gameObjects.add(ships.get(ships.size() - 1));
                            return;
                        default:
                            insParam();
                            return;
                    }
                }

            case "add":
                if (params.length != 3) {
                    insParam();
                    return;
                }

                ship = findGameObject(Integer.parseInt(params[2]));
                material = findGameObject(Integer.parseInt(params[1]));

                if (ship == null) {
                    log("Nem letezik ilyen ID-ju telepes/field!");
                    return;
                }
                if (material == null) {
                    log("Nem letezik ilyen ID-ju nyersanyag/field!");
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
                }
                return;

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
                    if (m.equals((Material) material.obj)) {
                        ((Ship) ship.obj).removeMaterial((Material) material.obj);
                        log(material.num + " nyersanyag sikeresen eltavolitva a " + ship.num + " telepestol!");
                        return;
                    }
                }
                log("Nincs ilyen ID-ju nyersanyag a telepesnel!");
                return;

            case "selectplayer":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                ship = findGameObject(Integer.parseInt(params[1]));

                if (ship == null || !ship.getObj().equals(Ship.class)) {
                    log("Nincs ilyen ID-ju telepes!");
                    return;
                }

                current = ship;
                log("A kivalasztott telepes: " + current.num);
                return;

            case "skipturn":
                if (current == null) {
                    log("Nincs meg kivalasztott telepes!");
                    return;
                }
                for (Testable t : ships) {
                    if (t.num.equals(current.num)) {
                        if (ships.indexOf(t) < ships.size() - 1) {
                            current = ships.get(ships.indexOf(t) + 1);
                        } else {
                            for (Testable r : gameObjects) {
                                if (isFlyingObject(r) && !r.getObj().equals(Ship.class)) {
                                    ((FlyingObject) r.obj).step();
                                }
                            }
                            current = ships.get(0);
                        }
                        log("A kovetkezo korben a telepes: " + current.num);
                        return;
                    }
                }
                return;

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

                Class obj = gameobject.getObj();
                if (isFlyingObject(gameobject)) {
                    ((FlyingObject) gameobject.obj).onSolarStormCase();
                } else if (isField(gameobject)) {
                    ((Field) gameobject.obj).onSolarStorm();
                } else {
                    log("Nem hajthato vegre napvihar a kijelolt elemen!");
                }
                return;

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

                obj = gameobject.getObj();

                log("Class: " + gameobject.getObj().toString().split(" ")[1]);
                log("ID: " + gameobject.num);

                if (isField(gameobject)) {
                    if (obj.equals(Gate.class)) {
                        log("Working: " + ((Gate) gameobject.obj).getWorking());
                    } else if (((Asteroid) gameobject.obj).getCore() != null) {
                        log("Core: " + ((Asteroid) gameobject.obj).getCore().getClass().toString().split(" ")[1]);
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
                        if (gameobject.equals(current)) {
                            log("Currently their turn");
                        } else {
                            log("Not their turn");
                        }
                    }
                } else if (gameobject.getObj().equals(Uranium.class)){
                    log("ExposedN: " + ((Uranium)gameobject.obj).getExposedN());
                }
                return;

            case "putback":
                if (current == null) {
                    noCurr();
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

                    if (material != null) {
                        if (((Ship) current.obj).getLocation().fillBy((Ship) current.obj, (Material) material.obj)) {
                            log("Mag sikeresen berakva!");
                            next();
                        } else {
                            log("A mag berakasa sikertelen!");
                        }
                        return;
                    }
                    log("Nincs a telepesnel az adott nyersanyagbol!");
                    return;
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

                    if (material != null) {
                        if (((Ship) current.obj).getLocation().fillBy((Ship) current.obj, (Material) material.obj)) {
                            log("Mag sikeresen berakva!");
                            next();
                        } else {
                            log("A mag berakasa sikertelen!");
                        }
                        return;
                    }
                    log("Nincs a telepesnel az adott nyersanyagbol!");
                    return;
                }

            case "mine":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (current == null) {
                    noCurr();
                    return;
                }
                if (((Ship) current.obj).mine()) {
                    log("Sikeres banyaszat!");
                    next();
                } else {
                    log("Sikertelen banyaszat!");
                }
                return;

            case "drill":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (current == null) {
                    noCurr();
                    return;
                }
                if (((Ship) current.obj).drill()) {
                    log("Sikeres furas!");
                    next();
                    return;
                }
                log("Sikertelen furas!");
                return;

            case "move":
                if (current == null) {
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

                    for (Field f : ((Ship) current.obj).getLocation().getNeighbours()) {
                        gameobject = findObject(f);
                        if (gameobject != null && gameobject.equals(field)) {
                            log("Telepes atmozgatva " + findObject(current.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");
                            ((Ship) current.obj).getLocation().removeFlyingObject((Ship) current.obj);
                            ((Field) gameobject.obj).acceptFlyingObject((Ship) current.obj);
                            next();
                            return;
                        }
                    }
                    log("Nincs ilyen ID-ju szomszedja a helyszinnek!");
                    return;
                }else{
                    if (!((Ship) current.obj).getLocation().getNeighbours().isEmpty()){
                        int random = new Random().nextInt(((Ship) current.obj).getLocation().getNeighbours().size());
                        gameobject = findObject(((Ship) current.obj).getLocation().getNeighbours().get(random));
                        log("Telepes atmozgatva " + findObject(current.obj.getLocation()).num + " Field-rol a " + gameobject.num + " Field-re!");
                        ((Ship) current.obj).getLocation().removeFlyingObject((Ship) current.obj);
                        ((Field) gameobject.obj).acceptFlyingObject((Ship) current.obj);
                        next();
                        return;
                    }
                    log("Nincs szomszedja a fieldnek!");
                    return;
                }

            case "craft":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                if (current == null) {
                    noCurr();
                    return;
                }

                switch (params[1]) {
                    case "base":
                        if (((Ship) current.obj).buildBase()) {
                            log("Bazis sikeresen megepitve, GG!");
                        } else {
                            log("A bazis megepitese sikertelen!");
                        }
                        return;
                    case "robot":
                        if (((Ship) current.obj).buildRobot()) {
                            Robot r = new Robot();
                            ((Ship) current.obj).getLocation().acceptFlyingObject(r);
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
                        if (((Ship) current.obj).craftGatePair()) {
                            Gate g1 = new Gate();
                            Gate g2 = new Gate();
                            g1.setPair(g2);
                            g2.setPair(g1);
                            ((Ship) current.obj).addGate(g1);
                            ((Ship) current.obj).addGate(g2);
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

            case "usegate":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (current == null) {
                    noCurr();
                    return;
                }

                if (((Ship) current.obj).useGate()) {
                    log("Kapu sikeresen hasznalva!");
                    next();
                } else {
                    log("Kapu hasznalata sikertelen!");
                }
                return;

            case "pickupgate":
                if (params.length != 1) {
                    insParam();
                    return;
                }
                if (current == null) {
                    noCurr();
                    return;
                }
                if (((Ship) current.obj).pickUpGate()) {
                    log("Kapu sikeresen felveve!");
                    next();
                    return;
                }
                log("Kapu felvetele siekrtelen!");
                return;

            case "load":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                try {
                    BufferedReader br = new BufferedReader(new FileReader(params[1]));
                    for (String line = br.readLine(); line != null; line = br.readLine()) {
                        runCommand(line);
                    }
                    br.close();
                    log(params[1] + " fajl valtoztatasai betoltve!");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

            case "random":
                if (random) {
                    random = false;
                    log("Random kikapcsolva.");
                } else {
                    random = true;
                    log("Random bekapcsolva.");
                }
                return;
            case "help":
                log("Parancsok:\n");
                log("create <parameter1> <parameter1_id>\nLetrehoz az adott parameterekhez huen egy objektumot.\nPl.: create asteroid 103, create asteroid 104, create ice 12, create iron 14, ..., create ship 1\n");
                log("add <mit_id> <mihez_id>\nHozzaadja a mit_id-vel rendelkezo elemet a hova_id-vel rendelkezo elemhez.\nPl.: add 12 1 (jeg hozzaadasa a hajo tarolojaba), add 103 104 (aszteroidak szomszedba allitasa)\n");
                log("remove <mit_id> <honnan_id>\nKiveszuk a mit_id-vel rendelkezo elemet a honnan_id-val rendelkezo elembol.\nPl: remove 12 1 (jeg kivetele a hajo tarolojabol)\n");
                log("selectplayer <player_id>\nValtunk a parameterkent kapott jatekosra.\nPl.: selectplayer 1\n");
                log("skipturn\nAz aktualis jatekos skip-eli a köret, nem csinal semmit.\nPl.: skipturn\n");
                log("solarstorm <id>\nSolarstormot hajtunk vegre a parameterkent kapott objektumra.\nPl.:solarstorm 1\n");
                log("explode <id>\nRobbanast hajtunk vegre a parameterkent kapott aszteroidan.\nPl.: explode 23\n");
                log("stat <id>\nA parameterkent kapott id-ju objektum minden tulajdonsagat kiírja.\nstat 1\n");
                log("putback <material>\nA kivalasztott materialt visszahelyezi az aszteroidaba, a telepes inventoryjabol ekkor ez az adott nyersanyag törlodik.\nPl.: putback iron, putback coal\n");
                log("mine\nAz location-bol, ha az aszteroida, kiveszi a magjat, ha van neki, azt hozzaadja az inventory-hoz, ha nincs nala mar 10 db.\nPl.: mine\n");
                log("drill\nHa aszteroidan all, akkor az aszteroida kerget atfurja 1 egyseggel, ha meg nem 0.\nPl.: drill\n");
                log("move <id>\natlep egy szomszedos mezore, ha van olyan.\nPl.: move 2, move 3\n");
                log("craft <parameter>\nCraftol a jatekos a nala levo nyersanyagokbol egy adott jatekelemet.\nPl.: craft base, craft robot, craft gate\n");
                log("usegate\nHa kapun all, akkor hasznalja a kapu, ami atteleportalja a parjahoz, ha az is  muködik.\nPl.: usegate\n");
                log("pickupgate\nHa kapun all, felveszi a kaput, ha nincs nala meg 3 db.\nPl.: pickupgate\n");
                log("load <fajlnev>\nA megadott fajlbol beolvassa a fentebb talalhato parancsok alapjan a jatek egy allasat.\nPl.: load test.txt\n");
                log("save <fajlnev>\nA futas elejen szukseges bekapcsolni ezt az opciot, ez esetben minden ezutan futtatott parancsot kiír a megadott fajlba.\nPl.: save test.txt\n");
                log("random\nA random mukodest ki/be tudjuk kapcsolni vele.\nPl.: random\n");
                return;
            case "exit":
                running = false;
                return;
            default:
                log("Ez a parancs nem letezik! A parancsok listazasahoz irja be a \"help\" parancsot.");
                break;
        }

    }

    public static String getCommand() {
        try {
            System.out.print("Kerlek irj be egy parancsot: ");
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void readFile(String filepath) {
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

    public static void main(String[] args) {
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
    }

}
