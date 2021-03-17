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
	}
	
	public void removeNeighbour(Field oldNeighbour)
	{
		neighbours.remove(oldNeighbour);
	}
	
	public abstract void onDrill();
	public abstract void onSolarStorm();
}
