import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Skeleton {
	public static String getUserInput() {
		// Scanner scanner = new Scanner(System.in);String n =
		// scanner.nextLine();scanner.close();
		Scanner scanner = new Scanner(System.in);
		String option = scanner.next();
		scanner.nextLine(); // Consume newline left-over
		String str1 = scanner.nextLine();
		// scanner.close();
		return option;
	}

	public static void testDrill() {
		// print fn name
		printFunc();
		// init objects & their respective attributes
		Asteroid a = new Asteroid();
		Ship sh = new Ship();
		sh.setLocation(a);
		a.acceptFlyingObject(sh);
		// call fns
		printInit();
		sh.drill();

		// returns
		printFuncRet("");
	}

	public static void testMeltOutcome() {
		printFunc();
		Asteroid a = new Asteroid();
		Ice i = new Ice();
		a.acceptCore(i);
		printInit();
		i.onDrillSpecial(a);
		// returns
		printFuncRet("");
	}

	public static void testRadOutcome() {
		printFunc();
		Asteroid a = new Asteroid();
		Uranium u = new Uranium();
		a.acceptCore(u);
		printInit();
		u.onDrillSpecial(a);
		// returns
		printFuncRet("");
	}

	public static void testExplode() {
		printFunc();
		Asteroid a = new Asteroid();
		Ship sh = new Ship();
		Game g = Game.getInstance();
		sh.setLocation(a);
		a.acceptFlyingObject(sh);
		g.addGameObject(sh);
		printInit();
		a.explode();
		// returns
		printFuncRet("");
	}

	public static void testIronOutcome() {
		printFunc();
		Asteroid a = new Asteroid();
		Iron i = new Iron();
		a.acceptCore(i);
		printInit();
		i.onDrillSpecial(a);
		// returns
		printFuncRet("");
	}

	public static void testDieFromSolarStorm() {
		printFunc();
		Asteroid a = new Asteroid();
		Ship sh = new Ship();
		Game g = Game.getInstance();
		sh.setLocation(a);
		a.acceptFlyingObject(sh);
		g.addGameObject(sh);
		printInit();
		a.onSolarStorm();
		// returns
		printFuncRet("");
	}

	public static void testPlaceGate() {
		printFunc();
		Asteroid location = new Asteroid();
		Asteroid a2 = new Asteroid();
		Ship sh = new Ship();
		Gate g = new Gate();
		location.acceptFlyingObject(sh);
		sh.setLocation(location);
		location.addNeighbour(a2);
		a2.addNeighbour(location);
		sh.addGate(g);

		printInit();
		sh.placeGate();
		// returns
		printFuncRet("");
	}

	public static void testUseGate() {
		printFunc();
		Ship sh = new Ship();
		Gate location = new Gate();
		Gate pair = new Gate();
		sh.setLocation(location);
		location.acceptFlyingObject(sh);
		location.setPair(pair);
		pair.setPair(location);

		printInit();
		sh.useGate();
		// returns
		printFuncRet("");
	}

	public static void testPickUpGate() {
		printFunc();
		Ship sh = new Ship();
		Gate location = new Gate();
		Asteroid neighbour = new Asteroid();
		location.acceptFlyingObject(sh);
		location.addNeighbour(neighbour);
		neighbour.addNeighbour(location);
		sh.setLocation(location);

		printInit();
		sh.pickUpGate();
		// returns
		printFuncRet("");
	}

	public static void testMine() {
		printFunc();
		Asteroid location = new Asteroid();
		Ship ship = new Ship();
		Iron i = new Iron();
		ship.setLocation(location);
		location.acceptFlyingObject(ship);
		location.acceptCore(i);

		printInit();
		ship.mine();
		// returns
		printFuncRet("");
	}

	public static void testPutMaterial() {
		printFunc();
		Asteroid location = new Asteroid();
		Ship ship = new Ship();
		Iron i = new Iron();
		ship.setLocation(location);
		location.acceptFlyingObject(ship);
		ship.addMaterial(i);

		printInit();
		ship.putMaterial(i);
		// returns
		printFuncRet("");
	}

	public static void testMove() {
		printFunc();
		Asteroid location = new Asteroid();
		Ship ship = new Ship();
		Asteroid neighbour = new Asteroid();
		ship.setLocation(location);
		location.acceptFlyingObject(ship);
		location.addNeighbour(neighbour);
		neighbour.addNeighbour(location);

		printInit();
		ship.move();
		// returns
		printFuncRet("");
	}

	public static void testOnSolarStorm() {
		printFunc();
		Asteroid a = new Asteroid();
		Ship ship = new Ship();
		Game g = Game.getInstance();
		ship.setLocation(a);
		a.acceptFlyingObject(ship);
		g.addGameObject(ship);

		printInit();
		a.onSolarStorm();
		// returns
		printFuncRet("");
	}

	// questionable
	public static void testCraftGate() {
		printFunc();
		Ship ship = new Ship();
		Uranium materialsUranium = new Uranium();
		ship.addMaterial(materialsUranium);

		printInit();
		ship.craftGatePair();
		// returns
		printFuncRet("");
	}

	// questionable
	public static void testCraftRobot() {
		printFunc();
		Ship ship = new Ship();
		Uranium materialsUranium = new Uranium();
		Asteroid a = new Asteroid();
		ship.addMaterial(materialsUranium);
		ship.setLocation(a);
		a.acceptFlyingObject(ship);

		printInit();
		ship.buildRobot();
		// returns
		printFuncRet("");
	}

	// questionable
	public static void testBuildBase() {
		printFunc();
		Ship ship = new Ship();
		Asteroid a = new Asteroid();
		Uranium materialsUranium = new Uranium();
		Game g = Game.getInstance();
		ship.addMaterial(materialsUranium);
		ship.setLocation(a);
		a.acceptFlyingObject(ship);
		g.addGameObject(ship);
		g.addField(a);

		printInit();
		ship.buildBase();
		// returns
		printFuncRet("");
	}

	public static void printFunc() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	public static void printFuncRet(String s) {
		switch (s) {
			case ("true") -> System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " returns true");
			case ("false") -> System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " returns false");
			case ("") -> System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " returns");
			default -> System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " returns " + s);
		}
	}

	private static void printInit() {
		System.out.println("\n" + "Object initialization finished, test starts: " + "\n");
	}

	public static void main(String[] args) {
		while(true) {
			System.out.println("Ird be a teszt eset számát");
			
		}
	}
}

