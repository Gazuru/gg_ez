import java.util.ArrayList;

public abstract class Field
{
	private ArrayList<Field> neighbours;
	protected ArrayList<FlyingObject> onSurface;
	
	public ArrayList<Field> getNeighbours()
	{
		return neighbours;
	}
	
	public void removeFlyingObject(FlyingObject fo)
	{
		onSurface.remove(fo);
	}
	
	public void acceptFlyingObject(FlyingObject fo)
	{
		onSurface.add(fo);
	}
	
	public void addNeighbour(Field newNeighbour)
	{
		neighbours.add(newNeighbour);
		newNeighbour.getNeighbours().add(this);	
	}
	
	public void removeNeighbour(Field oldNeighbour)
	{
		neighbours.remove(oldNeighbour);
	}
	
	public ArrayList<FlyingObject> getOnSurface()
	{
		return onSurface;
	}
	
	public abstract void onMine(Ship ship);
	public abstract boolean onDrill();
	public abstract void onSolarStorm();
	public abstract void teleport(Ship ship);
	public abstract void pickedUpBy(Ship ship);
	public abstract void fillBy(Ship ship);
}
