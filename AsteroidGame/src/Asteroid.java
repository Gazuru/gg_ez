import java.util.ArrayList;
import java.util.Random;

public class Asteroid extends Field
{
	private int layer;
	private Material core;
	private boolean inSunProximity;
	
	public Asteroid(int number, Game game)
	{
		super(number, game);
		Random rand = new Random();
		layer = rand.nextInt(5);
		int x = rand.nextInt(20);
		if(x == 10)
			inSunProximity = true;
		else
			inSunProximity = false;
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
		if(layer > 1)
		{
			decreaseLayer();
			System.out.println("A f�r�s sikeres");
			return true;
		}
		if(layer == 1 && core == null)
		{
			decreaseLayer();
			System.out.println("A f�r�s sikeres");
			return true;
		}
		if(layer == 1 && core != null)
		{
			decreaseLayer();
			core.onDrillSpecial(this);
			System.out.println("A f�r�s sikeres");
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
	
	public boolean fillBy(Ship ship)
	{
		if(layer == 0 && core == null)
		{
			acceptCore(ship.getMaterials().get(0));
			ship.removeMaterial(ship.getMaterials().get(0));
			System.out.println("A nyersanyag lerak�sa siker�lt!");
			return true;
		}
		System.out.println("Ebbe az aszteroid�ba nem helyezhet� jelenleg nyersanyag!");
		return false;
	}
	
	public boolean onMine(Ship ship)
	{
		if(layer != 0)
		{
			System.out.println("A b�ny�szat nem siker�lt, mert az aszteroida m�g nincs �tf�rva!");
			return false;
		}
		if(core == null)
		{
			System.out.println("A b�ny�szat nem siker�lt, mert az aszteroid�ban nincs nyersanyag!");
			return false;
		}
		ship.addMaterial(core);
		removeCore();
		System.out.println("A b�ny�szat siker�lt!");
		return true;
	}
	
	public boolean teleport(Ship ship)
	{
		System.out.println("A teleport�l�s nem siker�lt, mert a telepes egy aszteroid�n van!");
		return false;
	}
	
	public boolean pickedUpBy(Ship ship)
	{
		System.out.println("Aszteroid�t nem lehet felvenni! xD");
		return false;
	}
}
