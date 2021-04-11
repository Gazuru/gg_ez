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

    public static void runCommand(String cmd) {
        cmd = cmd.toLowerCase();
        String[] params = cmd.split(" ");
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
                        return;
                    case "ice":
                        materials.add(new Testable(new Ice(), Integer.parseInt(params[2])));
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
                        gameObjects.add(ships.get(ships.size() - 1));
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
                for (Testable t : ships) {
                    if (t.num.equals(Integer.parseInt(params[2]))) {
                        for (Testable m : materials) {
                            if (m.num.equals(Integer.parseInt(params[1]))) {
                                if (((Ship) t.obj).addMaterial((Material) m.obj)) {
                                    log("Material " + m.num + " sikeresen hozzáadva a " + t.num + " telepeshez.");
                                } else {
                                    log("Nincs eleg hely a telepesnel!");
                                }
                                return;
                            }
                        }
                        log("Nincs ilyen ID-ju nyersanyag!");
                        return;
                    }
                }
                log("Nincs ilyen ID-ju telepes!");
                return;

            case "remove":
                if (params.length != 3) {
                    insParam();
                    return;
                }
                for (Testable t : ships) {
                    if (t.num.equals(Integer.parseInt(params[2]))) {
                        for (Testable m : materials) {
                            if (m.num.equals(Integer.parseInt(params[1]))) {
                                for (Material ma : ((Ship) t.obj).getMaterials()) {
                                    if (ma.equals((Material) m.obj)) {
                                        ((Ship) t.obj).removeMaterial(ma);
                                        log(m.num + " nyersanyag eltavolitva a " + t.num + " telepestol!");
                                        return;
                                    }
                                }
                                log("Nincs ilyen ID-ju nyersanyag a telepesnel!");
                                return;
                            }
                        }
                        log("Nincs ilyen ID-ju nyersanyag!");
                        return;
                    }
                }
                log("Nincs ilyen ID-ju telepes!");
                return;

            case "selectplayer":
                if (params.length != 2) {
                    insParam();
                    return;
                }
                for (Testable t : ships) {
                    if (t.num.equals(Integer.parseInt(params[1]))) {
                        current = t;
                        log("A kivalasztott telepes: " + current.num);
                        return;
                    }
                }
                log("Nincs ilyen ID-ju telepes!");
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
                for (Testable t : gameObjects) {
                    if (t.num.equals(Integer.parseInt(params[1]))) {
                        Class obj = t.getObj();
                        if (Ship.class.equals(obj)) {
                            ((Ship) t.obj).onSolarStormCase();
                            return;
                        } else if (Asteroid.class.equals(obj)) {
                            ((Asteroid) t.obj).onSolarStorm();
                            return;
                        } else if (Robot.class.equals(obj)) {
                            ((Robot) t.obj).onSolarStormCase();
                            return;
                        } else if (Gate.class.equals(obj)) {
                            ((Gate) t.obj).onSolarStorm();
                            return;
                        } else if (Ufo.class.equals(obj)) {
                            ((Ufo) t.obj).onSolarStormCase();
                            return;
                        }
                    }
                }
                log("Nincs ilyen ID-ju jatekelem!");
                return;

            case "explode":

                break;

            case "stat":

                break;

            case "putback":

                break;

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
        while (true) {
            runCommand(getCommand());
        }
    }

}
