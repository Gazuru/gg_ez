import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Proto {

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

    public static Testable findShip(int ID) {
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
    }

    public static void runCommand(String cmd) {
        cmd = cmd.toLowerCase();
        String[] params = cmd.split(" ");
        Testable ship = null;
        Testable material = null;
        Testable asteroid = null;
        Testable gameobject = null;
        Testable field = null;

        switch (params[0]) {
            case "create":

                if (params.length != 3) {
                    insParam();
                    return;
                }

                switch (params[1]) {
                    case "asteroid":
                        asteroids.add(new Testable(new Asteroid(), Integer.parseInt(params[2])));
                        gameObjects.add(asteroids.get(asteroids.size() - 1));
                        Game.addField((Asteroid) gameObjects.get(gameObjects.size() - 1).obj);
                        return;
                    case "ice":
                        materials.add(new Testable(new Ice(), Integer.parseInt(params[2])));
                        gameObjects.add(materials.get(materials.size() - 1));
                        return;
                    case "coal":
                        materials.add(new Testable(new Coal(), Integer.parseInt(params[2])));
                        gameObjects.add(materials.get(materials.size() - 1));
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
                        gameObjects.add(ships.get(ships.size() - 1));
                        Game.addGameObject((Ship) gameObjects.get(gameObjects.size() - 1).obj);
                        return;
                    default:
                        insParam();
                        return;
                }

            case "add":
                if (params.length != 3) {
                    insParam();
                    return;
                }

                ship = findShip(Integer.parseInt(params[2]));
                material = findMaterial(Integer.parseInt(params[1]));

                if (ship == null) {
                    log("Nem letezik ilyen ID-ju telepes!");
                    return;
                }
                if (material == null) {
                    log("Nem letezik ilyen ID-ju nyersanyag!");
                    return;
                }

                if (((Ship) ship.obj).addMaterial(((Material) material.obj))) {
                    log("Material " + material.num + " sikeresen hozzaadva a " + ship.num + " telepeshez.");
                } else {
                    log("Nincs eleg hely a telepesnel!");
                }
                return;

            case "remove":
                if (params.length != 3) {
                    insParam();
                    return;
                }

                ship = findShip(Integer.parseInt(params[2]));
                material = findMaterial(Integer.parseInt(params[1]));

                if (ship == null) {
                    log("Nem letezik ilyen ID-ju telepes!");
                    return;
                }
                if (material == null) {
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
                ship = findShip(Integer.parseInt(params[1]));

                if (ship == null) {
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
                            current = ships.get(0);
                        }
                        log("A kovetkezo telepes: " + current.num);
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
                if (Ship.class.equals(obj)) {
                    ((Ship) gameobject.obj).onSolarStormCase();
                } else if (Asteroid.class.equals(obj)) {
                    ((Asteroid) gameobject.obj).onSolarStorm();
                } else if (Robot.class.equals(obj)) {
                    ((Robot) gameobject.obj).onSolarStormCase();
                } else if (Gate.class.equals(obj)) {
                    ((Gate) gameobject.obj).onSolarStorm();
                } else if (Ufo.class.equals(obj)) {
                    ((Ufo) gameobject.obj).onSolarStormCase();
                } else {
                    log("Nem hajthato vegre napvihar a kijelolt elemen!");
                }
                return;

            case "explode":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                asteroid = findAsteroid(Integer.parseInt(params[1]));

                if (asteroid == null) {
                    log("Nincs ilyen ID-ju aszteroida!");
                    return;
                }

                log("A " + asteroid.num + " ID-ju aszteroida sikeresen felrobbantva!");
                ((Asteroid) asteroid.obj).explode();
                return;

            case "stat":
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

                if (Asteroid.class.equals(obj)) {
                    if (!((Asteroid) gameobject.obj).getNeighbours().isEmpty()) {
                        log("Neighbours ID: ");
                        for (Field f : ((Asteroid) gameobject.obj).getNeighbours()) {
                            field = findObject(f);
                            if (field != null) {
                                log("\t" + field.num);
                            }
                        }
                    }
                } else if (Gate.class.equals(obj)) {
                    log("Working: " + ((Gate) gameobject.obj).getWorking());
                    if (!((Asteroid) gameobject.obj).getNeighbours().isEmpty()) {
                        log("Neighbours ID: ");
                        for (Field f : ((Asteroid) gameobject.obj).getNeighbours()) {
                            field = findObject(f);
                            if (field != null) {
                                log("\t" + field.num);
                            }
                        }
                    }
                } else if (Ship.class.equals(obj)) {
                    if (((Ship) gameobject.obj).getLocation() != null) {
                        field = findObject(((Ship) gameobject.obj).getLocation());
                        if (field != null) {
                            log("Location: " + field.num);
                        }
                    } else {
                        log("Location: none");
                    }
                    if (gameobject.equals(current)) {
                        log("Currently their turn");
                    } else {
                        log("Not their turn");
                    }
                } else if (Robot.class.equals(obj)) {
                    if (((Robot) gameobject.obj).getLocation() != null) {
                        field = findObject(((Robot) gameobject.obj).getLocation());
                        if (field != null) {
                            log("Location: " + field.num);
                        }
                    } else {
                        log("Location: none");
                    }
                } else if (Ufo.class.equals(obj)) {
                    if (((Ufo) gameobject.obj).getLocation() != null) {
                        field = findObject(((Ufo) gameobject.obj).getLocation());
                        if (field != null) {
                            log("Location: " + field.num);
                        }
                    } else {
                        log("Location: none");
                    }
                }
                return;

            case "putback":
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
                }

                if (material != null) {
                    if (((Ship) current.obj).getLocation().fillBy((Ship) current.obj, (Material) material.obj)) {
                        log("Mag sikeresen berakva!");
                    } else {
                        log("A mag berakasa sikertelen!");
                    }
                    return;
                }
                log("Nincs a telepesnel az adott nyersanyagbol!");
                return;

            case "mine":

                break;

            case "move":

                break;

            case "craft":

                break;

            case "pickupgate":

                break;

            case "save":

                break;

            case "random":

                break;

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
        Game game = Game.getInstance();
        while (true) {
            runCommand(getCommand());
        }
    }

}
