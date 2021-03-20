import java.util.Scanner;

public class Skeleton
{
	public static String getUserInput() {
		Scanner reader = new Scanner(System.in);
		String n = reader.next();
		reader.close();
		return n;
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
		sh.drill();
		
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testMeltOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ice i=new Ice();
		a.acceptCore(i);
		
		i.onDrillSpecial(a);
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testRadOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Uranium u=new Uranium();
		a.acceptCore(u);
		
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
		
		a.explode();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public static void testIronOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Iron i=new Iron();
		a.acceptCore(i);
		
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
		
		a.onSolarStorm();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	//TODO
	public static void testPlaceGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a1=new Asteroid();
		Asteroid a2=new Asteroid();
		Ship sh=new Ship();
		Gate g=new Gate();
		
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
		
		
		sh.useGate();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	//TODO
	public static void testPickUpGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ship sh=new Ship();
		Gate location=new Gate();
		Asteroid neighbour=new Asteroid();
		
		
		sh.pickUpGate();
		//returns
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	
	public static void main(String[] args)
	{
		//TODO
		
		testDieFromSolarStorm();
	}
}
