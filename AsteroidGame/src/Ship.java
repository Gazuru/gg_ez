import java.util.ArrayList;

public class Ship extends FlyingObject
{
	private ArrayList<Gate> gates;
	private ArrayList<Material> materials;
	
	public void mine()
	{
		
	}
	
	public void craftGatePair()
	{
		
	}
	
	public void placeGate()
	{
		
	}
	
	public void useGate()
	{
		
	}
	
	public void pickUpGate()
	{
		
	}
	
	public void buildRobot()
	{
		
	}
	
	public void BuildBase()
	{
		
	}
	
	public void putMaterial(Material material)
	{
		
	}
	
	public void removeMaterial(Material material)
	{
		materials.remove(material);
	}
	
	public void addMaterial(Material material)
	{
		materials.add(material);
	}
	
	public void move()
	{
		ArrayList<Field> moveable = new ArrayList<Field>();
		moveable = location.getNeighbours();
		Field newLocation;
		if(moveable.size() != 0)
		{
			//am�gy user-input alapj�n v�laszt�s, ez most csak egy pr�ba
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
		game.decreaseNumShips();
	}
	
	public void onExplode()
	{
		die();
	}
	
	public void onSolarStormCase()
	{
		die();
	}
	
	public void addGate(Gate newGate)
	{
		gates.add(newGate);
	}
	
	public void removeGate(Gate gate)
	{
		gates.remove(gate);
	}
	
	public void step()
	{
		
	}
}
