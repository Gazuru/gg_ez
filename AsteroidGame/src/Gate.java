import java.util.ArrayList;

public class Gate extends Field
{
	private boolean working;
	private Gate pair;
	
	public Gate()
	{
		working = false;
	}
	
	public boolean getWorking()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("working? y/n");
		String ans=Skeleton.getUserInput();
		if(ans.contains("y")) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns"+" true");
			return true;
		} else {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns"+" false");
			return false;
		}
		
		
	}
	
	public boolean pickedUpBy(Ship ship)
	{
		ship.addGate(this);
		return true;
	}
	
	public boolean teleport(FlyingObject f)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		if(pair.getWorking())
		{
			removeFlyingObject(f);
			pair.acceptFlyingObject(f);
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns"+" true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns"+" false");
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
