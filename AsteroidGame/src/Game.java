import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game implements Steppable {
    private static ArrayList<FlyingObject> gameObjects = new ArrayList<FlyingObject>();
    private static int numShips;
    private static ArrayList<Field> fields = new ArrayList<Field>();
    private static boolean end = false;
    private static Game single_instance = null;


    private Game() {

    }

    public static Game getInstance() {
        if (single_instance == null)
            single_instance = new Game();

        return single_instance;
    }

    public static void setEnd(boolean _end) {
        Skeleton.printFunc();
        end = _end;
        Skeleton.printFuncRet("");
    }

    public static void removeGameObject(FlyingObject fo) {
        Skeleton.printFunc();
        gameObjects.remove(fo);
        Skeleton.printFuncRet("");
    }

    public static void decreaseNumShips() {
        Skeleton.printFunc();
        numShips--;
        Skeleton.printFuncRet("");
    }

    public static void removeField(Field f) {
    	Skeleton.printFunc();
        fields.remove(f);
        Skeleton.printFuncRet("");
    }

    public ArrayList<Field> getFields(){ return fields; }

    public void step() {
        //System.out.println("A(z) " + round++ + ". k�r!");
        System.out.println();
        for (int i = 0; i < gameObjects.size(); i++) {
            /*System.out.println("A(z) " + i + ". j�t�kos!");

            // csak saj�t teszthez
            int dbic = 0;
            int dbir = 0;
            int dbu = 0;
            int dbc = 0;
            Ice ice = new Ice();
            Iron iron = new Iron();
            Uranium uranium = new Uranium();
            Coal coal = new Coal();
            for (int l = 0; l < gameObjects.get(i).getMaterials().size(); l++) {
                if (gameObjects.get(i).getMaterials().get(l).getClass() == ice.getClass())
                    dbic++;
                if (gameObjects.get(i).getMaterials().get(l).getClass() == iron.getClass())
                    dbir++;
                if (gameObjects.get(i).getMaterials().get(l).getClass() == uranium.getClass())
                    dbu++;
                if (gameObjects.get(i).getMaterials().get(l).getClass() == coal.getClass())
                    dbc++;
            }
            System.out.println("Ice: " + dbic + "Iron: " + dbir + "Coal: " + dbc + "Uranium: " + dbu);
*/
            gameObjects.get(i).step();
        }
        if (solarStorm()) {
            for (Field field : fields)
                field.onSolarStorm();

        }
        if (numShips == 0) {
            //System.out.println("A j�t�k v�get �rt, mert minden telepes halott!");
            end = true;
        }
        //System.out.println();
    }

    public boolean solarStorm() {
        Random random = new Random();
        int rand = random.nextInt(200000);
        return rand == 10;
    }

    public void startGame() {
        System.out.println("Asteroid Game");
        System.out.println();
        initGame();
        System.out.println();
        while (!end) // END
        {
            step();
        }
    }

    public void initGame() {
        System.out.println("Inicializ�l�s!");
        System.out.print("A p�lya m�rete: ");
        Scanner s = new Scanner(System.in);
        int choose = s.nextInt();
        for (int j = 0; j < choose; j++) {
            Asteroid newAsteroid = new Asteroid();
            addField(newAsteroid);
        }
        for (int k = 2; k < fields.size(); k++) {
            fields.get(k).addNeighbour(fields.get(k - 2));
            fields.get(k).addNeighbour(fields.get(k - 1));
        }
        System.out.print("J�t�kosok sz�ma: ");
        choose = s.nextInt();
        numShips = choose;
        while (gameObjects.size() != numShips) {
            Ship newShip = new Ship();
            addGameObject(newShip);
        }
    }

    public static void addGameObject(FlyingObject fo) {
        gameObjects.add(fo);
    }

    public static void addField(Field f) {
        fields.add(f);
    }

    public static ArrayList<Material> craftBaseReq() {
        ArrayList<Material> baseReq = new ArrayList<Material>();
        for (int i = 0; i < 3; i++) {
            baseReq.add(new Coal());
            baseReq.add(new Iron());
            baseReq.add(new Ice());
            baseReq.add(new Uranium());
        }
        return baseReq;
    }
}
