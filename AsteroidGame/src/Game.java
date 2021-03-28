import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game implements Steppable {
    private ArrayList<FlyingObject> gameObjects = new ArrayList<FlyingObject>();
    private int numShips;
    private ArrayList<Field> fields = new ArrayList<Field>();
    //private int round = 0;
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
        Skeleton.printFuncRet("");
    }

    public static void decreaseNumShips() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }

    public void removeField(Field f) {
        fields.remove(f);
    }

    public void step() {
        //System.out.println("A(z) " + round++ + ". k�r!");
        System.out.println();
        for (int i = 0; i < gameObjects.size(); i++) {
            System.out.println("A(z) " + i + ". j�t�kos!");

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

            gameObjects.get(i).step();
        }
        if (solarStorm()) {
            for (Field field : fields) field.onSolarStorm();
            if (numShips == 0) {
                System.out.println("A j�t�k v�get �rt, mert minden telepes halott!");
                //end = true;
            }
        }
        System.out.println();
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
        int j = 0;
        while (j != choose) {
            Asteroid newAsteroid = new Asteroid();
            Random random = new Random();
            int rand = random.nextInt(4);
            switch (rand) {
                case 0:
                    Coal c = new Coal();
                    newAsteroid.acceptCore(c);
                    break;
                case 1:
                    Iron ir = new Iron();
                    newAsteroid.acceptCore(ir);
                    break;
                case 2:
                    Ice i = new Ice();
                    newAsteroid.acceptCore(i);
                    break;
                case 3:
                    Uranium u = new Uranium();
                    newAsteroid.acceptCore(u);
                    break;
            }
            addField(newAsteroid);
            j++;
        }
        for (int k = 2; k < fields.size(); k++) {
            fields.get(k).addNeighbour(fields.get(k - 2));
            fields.get(k).addNeighbour(fields.get(k - 1));
        }
        System.out.print("J�t�kosok sz�ma: ");
        choose = s.nextInt();
        numShips = choose;
        while (gameObjects.size() != numShips) {
            Random r = new Random();
            int nr = r.nextInt(fields.size());
            Ship newShip = new Ship();
            addGameObject(newShip);
        }
    }

    public void addGameObject(FlyingObject fo) {
        gameObjects.add(fo);
    }

    public void addField(Field f) {
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
