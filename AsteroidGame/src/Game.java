import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game implements Steppable {
	private ArrayList<FlyingObject> gameObjects = new ArrayList<FlyingObject>();
	private int numShips;
	private ArrayList<Field> fields = new ArrayList<Field>();
	private int round = 0;
	private static boolean end = false;
	private static Game single_instance = null;
	
	
	private Game() {
		
	}
	
	public static Game getInstance() {
		if(single_instance == null)
			single_instance = new Game();
		
		return single_instance;
	}
	
	public static void setEnd(boolean _end) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		end = _end;
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public static void removeGameObject(FlyingObject fo) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public static void decreaseNumShips() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public void removeField(Field f) {
		fields.remove(f);
	}

	public void step() {
		System.out.println("A(z) " + round++ + ". kör!");
		System.out.println();
		for (int i = 0; i < gameObjects.size(); i++) {
			System.out.println("A(z) " + i + ". játékos!");
			System.out.println("Aszteroida: " + gameObjects.get(i).getLocation().getNumber());

			// csak saját teszthez
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
			for (int i = 0; i < fields.size(); i++)
				fields.get(i).onSolarStorm();
			if (numShips == 0) {
				System.out.println("A játék véget ért, mert minden telepes halott!");
				end = true;
			}
		}
		System.out.println();
	}

	public boolean solarStorm() {
		Random random = new Random();
		int rand = random.nextInt(200000);
		if (rand == 10) {
			return true;
		} else {
			return false;
		}
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
		System.out.println("Inicializálás!");
		System.out.print("A pálya mérete: ");
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
			newAsteroid.setGame(this);
			j++;
		}
		for (int k = 2; k < fields.size(); k++) {
			fields.get(k).addNeighbour(fields.get(k - 2));
			fields.get(k).addNeighbour(fields.get(k - 1));
		}
		System.out.print("Játékosok száma: ");
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
		Coal c1 = new Coal();
		Coal c2 = new Coal();
		Coal c3 = new Coal();
		Iron ir1 = new Iron();
		Iron ir2 = new Iron();
		Iron ir3 = new Iron();
		Ice i1 = new Ice();
		Ice i2 = new Ice();
		Ice i3 = new Ice();
		Uranium u1 = new Uranium();
		Uranium u2 = new Uranium();
		Uranium u3 = new Uranium();
		baseReq.add(c1);
		baseReq.add(c2);
		baseReq.add(c3);
		baseReq.add(ir1);
		baseReq.add(ir2);
		baseReq.add(ir3);
		baseReq.add(i1);
		baseReq.add(i2);
		baseReq.add(i3);
		baseReq.add(u1);
		baseReq.add(u2);
		baseReq.add(u3);
		return baseReq;
	}
}
