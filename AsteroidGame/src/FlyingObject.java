public abstract class FlyingObject implements Steppable
{
	protected Field location;
	protected Game game;
	
	public Field getLocation()
	{
		return location;
	}
	
	public void setLocation(Field newLocation)
	{
		location = newLocation;
	}
	
	public void drill()
	{
		location.onDrill();
	}
	
	public abstract void move();
	public abstract void die();
	public abstract void onExplode();
	public abstract void onSolarStormCase();
}
