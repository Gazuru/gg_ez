import java.util.ArrayList;

public class Robot extends FlyingObject
{
	public void step()
	{
		
	}
	
	public void move()
	{
		ArrayList<Field> moveable = new ArrayList<Field>();
		moveable = location.getNeighbours();
		Field newLocation;
		if(moveable.size() != 0)
		{
			//am�gy random v�laszt�s, ez most csak egy pr�ba
			newLocation = moveable.get(0);
			location.removeFlyingObject(this);
			newLocation.acceptFlyingObject(this);
		}
		else
			System.out.println("A mozg�s sikertelen, mert nincs szomsz�d!");
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
	
	public ArrayList<Material> craftRobotReq()
	{
		ArrayList<Material> G = new ArrayList<Material>();
		return G;
	}
}
