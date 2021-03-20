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
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void acceptFlyingObject(FlyingObject fo)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
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
	public boolean teleport(FlyingObject f) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns"+" false");
		return false;
	}
	public abstract boolean pickedUpBy(Ship ship);
	public abstract boolean fillBy(Ship ship);
}
