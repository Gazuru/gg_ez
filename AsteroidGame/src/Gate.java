import java.util.ArrayList;

public class Gate extends Field
{
	private boolean working;
	private Gate pair;
	
	public boolean getWorking()
	{
		return working;
	}
	
	public void pickedUpBy(Ship ship)
	{
		ship.addGate(this);
	}
	
	public void teleport(Ship ship)
	{
		if(pair.getWorking())
		{
			removeFlyingObject(ship);
			pair.acceptFlyingObject(ship);
		}
		else
			System.out.println("A teleportálás nem sikerült, mert a kapu párja nem mûködik!");
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
		System.out.println("A fúrás sikertelen, mert egy kapun nem lehet fúrni!");
		return false;
	}
	
	public void onSolarStorm()
	{
		for(int i = 0; i < onSurface.size(); i++)
			onSurface.get(i).onSolarStormCase();
	}
	
	public void onMine(Ship ship)
	{
		System.out.println("A bányászat nem sikerült, mert a telepes egy kapun van!");
	}
	
	public void fillBy(Ship ship)
	{
		System.out.println("Kapuba nem lehet visszatenni nyersanyagot!");
	}
}
