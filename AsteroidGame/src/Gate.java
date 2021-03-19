import java.util.ArrayList;

public class Gate extends Field
{
	private boolean working;
	private Gate pair;
	
	public Gate(int number, Game game)
	{
		super(number, game);
		working = false;
	}
	
	public boolean getWorking()
	{
		return working;
	}
	
	public boolean pickedUpBy(Ship ship)
	{
		ship.addGate(this);
		return true;
	}
	
	public boolean teleport(Ship ship)
	{
		if(pair.getWorking())
		{
			removeFlyingObject(ship);
			pair.acceptFlyingObject(ship);
			System.out.println("A teleport�l�s siker�lt!");
			return true;
		}
		System.out.println("A teleport�l�s nem siker�lt, mert a kapu p�rja nem m�k�dik!");
		return false;
	}
	
	public static ArrayList<Material> craftGateReq()
	{
		ArrayList<Material> gateReq = new ArrayList<Material>();
		Iron ir1 = new Iron();
		Iron ir2 = new Iron();
		Ice ic = new Ice();
		Uranium u = new Uranium();
		gateReq.add(ir1);
		gateReq.add(ir2);
		gateReq.add(ic);
		gateReq.add(u);
		return gateReq;
	}
	
	public void setWorking(boolean working)
	{
		this.working = working;
	}
	
	public Gate getPair()
	{
		return pair;
	}
	
	public void setPair(Gate pair)
	{
		this.pair = pair;
	}
	
	public boolean onDrill()
	{
		System.out.println("A f�r�s sikertelen, mert egy kapun nem lehet f�rni!");
		return false;
	}
	
	public void onSolarStorm()
	{
		for(int i = 0; i < onSurface.size(); i++)
			onSurface.get(i).onSolarStormCase();
	}
	
	public boolean onMine(Ship ship)
	{
		System.out.println("A b�ny�szat nem siker�lt, mert a telepes egy kapun, nem egy aszteroid�n van, kapun pedig nem lehet b�ny�szni!");
		return false;
	}
	
	public boolean fillBy(Ship ship)
	{
		System.out.println("Kapuba nem lehet visszatenni nyersanyagot!");
		return false;
	}
}
