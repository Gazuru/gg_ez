
public class Skeleton
{
	
	public static void testDrill() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship sh=new Ship();
	}
	
	public static void testMeltOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ice i=new Ice();
	}
	public static void testRadOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Uranium i=new Uranium();
	}
	public static void testExplode() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship sh=new Ship();
		Game g=new Game();
	}
	public static void testIronOutcome() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Iron i=new Iron();
	}
	public static void testDieFromSolarStorm() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a=new Asteroid();
		Ship sh=new Ship();
		Game g=new Game();
	}
	public static void testPlaceGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Asteroid a1=new Asteroid();
		Asteroid a2=new Asteroid();
		Ship sh=new Ship();
		Gate g=new Gate();
	}
	public static void testUseGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ship sh=new Ship();
		Gate location=new Gate();
		Gate pair=new Gate();
	}
	public static void testPickUpGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ship sh=new Ship();
		Gate location=new Gate();
		Asteroid neighbour=new Asteroid();
	}
	
	
	public static void main(String[] args)
	{
		testDrill();
		testPickUpGate();
		testDieFromSolarStorm();
	}
}
