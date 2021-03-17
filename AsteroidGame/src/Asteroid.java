import java.util.ArrayList;

public class Asteroid extends Field
{
	private int layer;
	private ArrayList<Material> core;
	private boolean inSunProximity;
	private Game game;
	
	public void onDrill()
	{
		if(layer > 0)
			decreaseLayer();
		else
			System.out.println("A fúrás sikertelen, mert az aszteroida kérge már teljesen át van fúrva!");
	}
	
	public void acceptCore(Material newCore)
	{
		core.add(newCore);
	}
	
	public void removeCore()
	{
		core.remove(0);
	}
	
	public void decreaseLayer()
	{
		layer--;
	}
	
	public void explode()
	{
		for(int i = 0; i < onSurface.size(); i++)
			onSurface.get(i).onExplode();
		game.removeField(this);
	}
	
	public void onSolarStorm()
	{
		
	}
	
	public void fillBy(Ship ship)
	{
		
	}
	
	public void onMine(Ship ship)
	{
		
	}
}
