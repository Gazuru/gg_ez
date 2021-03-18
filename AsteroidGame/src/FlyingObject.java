import java.util.ArrayList;

public abstract class FlyingObject implements Steppable
{
	protected Field location;
	protected Game game;
	protected ArrayList<Material> materials;
	
	public Field getLocation()
	{
		return location;
	}
	
	public void setLocation(Field newLocation)
	{
		location = newLocation;
	}
	
	public boolean drill()
	{
		return location.onDrill();
	}
	
	public abstract void move();
	public abstract void die();
	public abstract void onExplode();
	public abstract void onSolarStormCase();
}
