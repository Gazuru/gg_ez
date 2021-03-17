import java.util.ArrayList;

public class Game implements Steppable
{
	private ArrayList<FlyingObject> gameObjects;
	private int numShips;
	private ArrayList<Field> fields;
	
	public void removeGameObject(FlyingObject fo)
	{
		gameObjects.remove(fo);
	}
	
	public void decreaseNumShips()
	{
		numShips--;
	}
	
	public void removeField(Field f)
	{
		fields.remove(f);
	}
	
	public void step()
	{
		
	}
	
	public boolean solarStorm()
	{
		return true;
	}
	
	public void startGame()
	{
		
	}
	
	public void initGame()
	{
		
	}
	
	public void addGameObject(FlyingObject fo)
	{
		gameObjects.add(fo);
	}
	
	public void addField(Field f)
	{
		fields.add(f);
	}
	
	public ArrayList<Material> craftBaseReq()
	{
		ArrayList<Material> G = new ArrayList<Material>();
		return G;
	}
}
