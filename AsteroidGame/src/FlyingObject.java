import java.util.ArrayList;

public abstract class FlyingObject implements Steppable
{
	protected Field location;
	protected ArrayList<Material> materials;
	protected Game game;
	
	public FlyingObject()
	{
		
		materials = new ArrayList<Material>();
		
	}
	
	public Field getLocation()
	{
		return location;
	}
	
	public void setLocation(Field newLocation)
	{
		location = newLocation;
	}
	
	public ArrayList<Material> getMaterials()
	{
		return materials;
	}
	
	public void setMaterials(ArrayList<Material> newMaterials)
	{
		materials = newMaterials;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void setGame(Game newGame)
	{
		game = newGame;
	}
	
	public boolean drill()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean completed=location.onDrill();
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns "+completed);
		return completed;
	}
	
	public abstract boolean move();
	public abstract void die();
	public abstract void onExplode();
	public abstract void onSolarStormCase();
}
