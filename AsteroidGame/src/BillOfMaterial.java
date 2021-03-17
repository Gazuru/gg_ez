import java.util.ArrayList;

public class BillOfMaterial
{
	private ArrayList<Material> needed;
	private ArrayList<Material> found;
	
	public BillOfMaterial(ArrayList<Material> requires)
	{
		needed = requires;
	}
	
	public boolean newMaterial(Material m)
	{
		return true;
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
