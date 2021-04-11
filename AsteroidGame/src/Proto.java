import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Proto {

    private static ArrayList<Testable> gameObjects = new ArrayList<>();
    /*public static ArrayList<Testable<Ship, Integer>> ships = new ArrayList<>();
    public static ArrayList<Testable<Asteroid, Integer>> asteroids = new ArrayList<>();
    public static ArrayList<Testable<Material, Integer>> materials = new ArrayList<>();*/
    private static ArrayList<Ship> ships = new ArrayList<>();
    private static ArrayList<Asteroid> asteroids = new ArrayList<>();
    private static ArrayList<Material> materials = new ArrayList<>();

    public static void log(String s) {
        System.out.println(s);
    }

    public static void insParam() {
        log("Nem megfelelo parameter! Probalja ujra!");
    }

    public static void runCommand(String cmd) {
        cmd = cmd.toLowerCase();
        String[] params = cmd.split(" ");
        switch (params[0]) {
            case "create":

                if (params.length != 3) {
                    log("Nem megfelelo mennyisegu parametert adott meg!");
                    break;
                }

                switch (params[1]) {
                    case "asteroid":
                        asteroids.add(new Testable(new Asteroid(), Integer.parseInt(params[2])));
                        gameObjects.add(asteroids.get(asteroids.size() - 1));
                        break;
                    case "ice":
                        materials.add(new Testable(new Ice(), Integer.parseInt(params[2])));
                        gameObjects.add(materials.get(materials.size() - 1));
                        break;
                    case "iron":
                        materials.add(new Testable(new Iron(), Integer.parseInt(params[2])));
                        gameObjects.add(materials.get(materials.size() - 1));
                        break;
                    case "uranium":
                        materials.add(new Testable(new Uranium(), Integer.parseInt(params[2])));
                        gameObjects.add(materials.get(materials.size() - 1));
                        break;
                    case "ship":
                        ships.add(new Testable(new Ship(), Integer.parseInt(params[2])));
                        gameObjects.add(ships.get(ships.size() - 1));
                        break;
                    default:
                        insParam();
                        break;
                }
                break;

            case "add":
                if (params.length != 3) {
                    insParam();
                    break;
                }
                for (Testable t : ships) {
                    if (t.num.equals(Integer.parseInt(params[2]))) {
                        for (Testable m : materials) {
                            if (m.num.equals(Integer.parseInt(params[1]))) {
                                log("bruh");
                                t.obj.
                            }
                        }
                    }
                }

                break;

            case "remove":

                break;

            case "selectplayer":

                break;

            case "skipturn":

                break;

            case "solarstorm":

                break;

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
