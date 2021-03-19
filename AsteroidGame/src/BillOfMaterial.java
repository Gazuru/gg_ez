import java.util.ArrayList;

public class BillOfMaterial
{
	private ArrayList<Material> needed = new ArrayList<Material>();
	private ArrayList<Material> found = new ArrayList<Material>();
	
	public BillOfMaterial(ArrayList<Material> requires)
	{
		needed = requires;
	}
	
	public boolean newMaterial(Material m)
	{
		boolean compatible = false;
		for(int i = 0; i < needed.size(); i++)
		{
			compatible = m.compatibleWith(needed.get(i));
			if(compatible)
			{
				needed.remove(i);
				found.add(m);
				break;
			}
		}
		if(needed.size() == 0)
			return true;
		else
			return false;
	}
	
	public ArrayList<Material> getFound()
	{
		return found;
	}
	
	public void removeNeeded(Material removeable)
	{
		needed.remove(removeable);
	}
	
	public void addFound(Material founded)
	{
		found.add(founded);
	}
}
