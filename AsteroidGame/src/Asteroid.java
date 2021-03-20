import java.util.ArrayList;
import java.util.Random;

public class Asteroid extends Field
{
	private int layer;
	private Material core;
	private boolean inSunProximity;
	
	public Asteroid()
	{
		
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setNumber(int x)
	{
		number = x;
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public boolean getInSunProximity()
	{
		return inSunProximity;
	}
	
	public boolean onDrill()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("layer>1? y/n");
		String ans=Skeleton.getUserInput();
		
		if(ans.contains("y")) {
			this.decreaseLayer();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
			
			return true;
		} else {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
			return false;
		}
	}
	
	public void acceptCore(Material newCore)
	{
		core = newCore;
	}
	
	public void removeCore()
	{
		core = null;
	}
	
	public void decreaseLayer()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void explode()
	{
		for(int i = 0; i < onSurface.size(); i++)
			onSurface.get(i).onExplode();
		game.removeField(this);
	}
	
	public void onSolarStorm()
	{
		for(int i = 0; i < onSurface.size(); i++)
		{
			if(layer != 0 || core != null)
				onSurface.get(i).onSolarStormCase();
		}
	}
	
	public boolean fillBy(Ship ship)
	{
		if(layer == 0 && core == null)
		{
			acceptCore(ship.getMaterials().get(0));
			ship.removeMaterial(ship.getMaterials().get(0));
			System.out.println("A nyersanyag lerakása sikerült!");
			return true;
		}
		System.out.println("Ebbe az aszteroidába nem helyezhetõ jelenleg nyersanyag!");
		return false;
	}
	
	public boolean onMine(Ship ship)
	{
		if(layer != 0)
		{
			System.out.println("A bányászat nem sikerült, mert az aszteroida még nincs átfúrva!");
			return false;
		}
		if(core == null)
		{
			System.out.println("A bányászat nem sikerült, mert az aszteroidában nincs nyersanyag!");
			return false;
		}
		ship.addMaterial(core);
		removeCore();
		System.out.println("A bányászat sikerült!");
		return true;
	}
	
	public boolean teleport(Ship ship)
	{
		System.out.println("A teleportálás nem sikerült, mert a telepes egy aszteroidán van!");
		return false;
	}
	
	public boolean pickedUpBy(Ship ship)
	{
		System.out.println("Aszteroidát nem lehet felvenni! xD");
		return false;
	}
}
