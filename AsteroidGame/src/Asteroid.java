import java.util.ArrayList;

public class Asteroid extends Field
{
	private int layer;
	private Material core;
	private boolean inSunProximity;
	private Game game;
	
	public boolean getInSunProximity()
	{
		return inSunProximity;
	}
	
	public boolean onDrill()
	{
		if(layer > 1)
		{
			decreaseLayer();
			return true;
		}
		if(layer == 1 && core == null)
		{
			decreaseLayer();
			return true;
		}
		if(layer == 1 && core != null)
		{
			decreaseLayer();
			core.onDrillSpecial(this);
			return true;
		}
		if(layer == 0)
		{
			System.out.println("A f�r�s sikertelen, mert az aszteroida k�rge m�r teljesen �t van f�rva!");
			return false;
		}
		else return false;
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
		for(int i = 0; i < onSurface.size(); i++)
		{
			if(layer != 0 || core != null)
				onSurface.get(i).onSolarStormCase();
		}
	}
	
	public void fillBy(Ship ship)
	{
		if(layer == 0 && core == null)
		{
			acceptCore(ship.getMaterials().get(0));
			ship.removeMaterial(ship.getMaterials().get(0));
		}
		else
			System.out.println("Ebbe az aszteroid�ba nem helyezhet� jelenleg nyersanyag!");
	}
	
	public void onMine(Ship ship)
	{
		ship.addMaterial(core);
		removeCore();
	}
	
	public void teleport(Ship ship)
	{
		System.out.println("A teleport�l�s nem siker�lt, mert a telepes egy aszteroid�n van!");
	}
	
	public void pickedUpBy(Ship ship)
	{
		System.out.println("Aszteroid�t nem lehet felvenni! xD");
	}
}
