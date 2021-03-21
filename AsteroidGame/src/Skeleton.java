import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Skeleton
{
	public static String getUserInput(){
		//Scanner scanner = new Scanner(System.in);String n = scanner.nextLine();scanner.close();
		Scanner scanner = new Scanner(System.in);
		String option = scanner.next();
		scanner.nextLine();  // Consume newline left-over
		String str1 = scanner.nextLine();
		//scanner.close();
		return option;
	}
	
	public static void testDrill() {
		//print fn name
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		//init objects & their respective attributes
		Asteroid a=new Asteroid();
		Ship sh=new Ship();
		sh.setLocation(a);
		a.acceptFlyingObject(sh);
		//call fns
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		sh.drill();
		
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testMeltOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ice i=new Ice();
		a.acceptCore(i);
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		i.onDrillSpecial(a);
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testRadOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Uranium u=new Uranium();
		a.acceptCore(u);
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		u.onDrillSpecial(a);
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testExplode() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship sh=new Ship();
		Game g=new Game();
		sh.setLocation(a);
		a.acceptFlyingObject(sh);
		g.addGameObject(sh);
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		a.explode();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testIronOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Iron i=new Iron();
		a.acceptCore(i);
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		i.onDrillSpecial(a);
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	
	public static void testDieFromSolarStorm() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship sh=new Ship();
		Game g=new Game();
		sh.setLocation(a);
		a.acceptFlyingObject(sh);
		g.addGameObject(sh);
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		a.onSolarStorm();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	
	public static void testPlaceGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid location=new Asteroid();
		Asteroid a2=new Asteroid();
		Ship sh=new Ship();
		Gate g=new Gate();
		location.acceptFlyingObject(sh);
		sh.setLocation(location);
		location.addNeighbour(a2);
		a2.addNeighbour(location);
		sh.addGate(g);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		sh.placeGate();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testUseGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ship sh=new Ship();
		Gate location=new Gate();
		Gate pair=new Gate();
		sh.setLocation(location);
		location.acceptFlyingObject(sh);
		location.setPair(pair);
		pair.setPair(location);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		sh.useGate();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	
	public static void testPickUpGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ship sh=new Ship();
		Gate location=new Gate();
		Asteroid neighbour=new Asteroid();
		location.acceptFlyingObject(sh);
		location.addNeighbour(neighbour);
		neighbour.addNeighbour(location);
		
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		sh.pickUpGate();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testMine() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid location=new Asteroid();
		Ship ship=new Ship();
		Iron i=new Iron();
		ship.setLocation(location);
		location.acceptFlyingObject(ship);
		location.acceptCore(i);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		ship.mine();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testPutMaterial() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid location=new Asteroid();
		Ship ship=new Ship();
		Iron i=new Iron();
		ship.setLocation(location);
		location.acceptFlyingObject(ship);
		ship.addMaterial(i);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		ship.putMaterial(i);
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	
	public static void testMove() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid location=new Asteroid();
		Ship ship=new Ship();
		Asteroid neighbour=new Asteroid();
		ship.setLocation(location);
		location.acceptFlyingObject(ship);
		location.addNeighbour(neighbour);
		neighbour.addNeighbour(location);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		ship.move();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testOnSolarStorm() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship ship=new Ship();
		Game g=new Game();
		ship.setLocation(a);
		a.acceptFlyingObject(ship);
		g.addGameObject(ship);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		a.onSolarStorm();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	//TODO
	public static void testCraftGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Uranium materialsUranium=new Uranium();
		Ship ship=new Ship();
		
		
		
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		ship.craftGatePair();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	//TODO
	public static void testCraftRobot() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship ship=new Ship();
		Game g=new Game();
		ship.setLocation(a);
		a.acceptFlyingObject(ship);
		g.addGameObject(ship);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		a.onSolarStorm();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	//TODO
	public static void testBuildBase() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship ship=new Ship();
		Game g=new Game();
		ship.setLocation(a);
		a.acceptFlyingObject(ship);
		g.addGameObject(ship);
		
		System.out.println("\n"+"Object initialization finished, test starts: "+"\n");
		a.onSolarStorm();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void main(String[] args)
	{
		//TODO
		
		testPlaceGate();
	}
}
