import java.util.ArrayList;
import java.util.Random;

public class Robot extends FlyingObject
{
	public Robot(Field firstLocation, Game game)
	{
		super(firstLocation, game);
		location.acceptFlyingObject(this);
		game.addGameObject(this);
	}
	
	public void step()
	{
		boolean done = false;
		done = drill();
		if(!done)
			move();
	}
	
	public boolean move()
	{
		ArrayList<Field> moveable = new ArrayList<Field>();
		moveable = location.getNeighbours();
		Field newLocation;
		if(moveable.size() != 0)
		{
			Random random = new Random();
			int newLocationInt = random.nextInt(moveable.size());
			newLocation = moveable.get(newLocationInt);
			location.removeFlyingObject(this);
			newLocation.acceptFlyingObject(this);
			System.out.println("A mozgás sikeres!");
			return true;
		}
		System.out.println("A mozgás sikertelen, mert nincs szomszéd!");
		return false;
	}
	
	public void die()
	{
		location.removeFlyingObject(this);
		game.removeGameObject(this);
	}
	
	public void onExplode()
	{
		move();
	}
	
	public void onSolarStormCase()
	{
		die();
	}
	
	public static ArrayList<Material> craftRobotReq()
	{
		ArrayList<Material> robotReq = new ArrayList<Material>();
		Iron i = new Iron();
		Coal c = new Coal();
		Uranium u = new Uranium();
		robotReq.add(i);
		robotReq.add(c);
		robotReq.add(u);
		return robotReq;
	}
}
