import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Proto {

    public static void log(String s) {
        System.out.println(s);
    }

    public static void runCommand(String cmd) {
        cmd = cmd.toLowerCase();
        String[] params = cmd.split(" ");
        switch (params[0]) {
            case "create":

                if (params.length < 2 || params.length > 3) {
                    log("Nem megfelelo mennyisegu parametert adott meg!");
                    break;
                }

                switch (params[1]) {
                    case "asteroid":
                        break;
                    case "ice":
                        break;
                    case "iron":
                        break;
                    case "uranium":
                        break;
                    case "ship":
                        break;
                    default:
                        log("Nem megfelelo parameter! Probalja ujra!");
                        break;
                }
                break;

            case "add":

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
