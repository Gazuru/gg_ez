import java.util.ArrayList;

public abstract class Field
{
	protected ArrayList<Field> neighbours;
	protected ArrayList<FlyingObject> onSurface;
	protected int number;
	protected Game game; 
	
	public Field()
	{
		neighbours = new ArrayList<Field>();
		onSurface = new ArrayList<FlyingObject>();
	}
	
	public int getNumber()
	{
		return number;
	}
	
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
	
	public abstract boolean onMine(Ship ship);
	public abstract boolean onDrill();
	public abstract void onSolarStorm();
	public abstract boolean teleport(Ship ship);
	public abstract boolean pickedUpBy(Ship ship);
	public abstract boolean fillBy(Ship ship);
}
