import java.util.ArrayList;

public class Gate extends Field
{
	private boolean working;
	private Gate pair;
	
	public void pickedUpBy(Ship ship)
	{
		
	}
	
	public void teleport(Ship ship)
	{
		
	}
	
	public ArrayList<Material> craftGateReq()
	{
		ArrayList<Material> G = new ArrayList<Material>();
		return G;
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
	
	public void onDrill()
	{
		System.out.println("A fúrás sikertelen, mert egy kapun nem lehet fúrni!");
	}
	
	public void onSolarStorm()
	{
		
	}
}
