import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends FlyingObject
{
	private ArrayList<Gate> gates;
	
	public void mine()
	{
		location.onMine(this);
	}
	
	public void craftGatePair()
	{
		ArrayList<Material> needed = Gate.craftGateReq();
		BillOfMaterial newBOM = new BillOfMaterial(needed);
		boolean buildable = false;
		for(int i = 0; i < materials.size(); i++)
		{
			buildable = newBOM.newMaterial(materials.get(i));
			if(buildable)
				break;
		}
		if(buildable)
		{
			materials.removeAll(newBOM.getFound());
			Gate newGate1 = new Gate();
			Gate newGate2 = new Gate();
			addGate(newGate1);
			addGate(newGate2);
		}
	}
	
	public void placeGate()
	{
		ArrayList<Field> neighbours = new ArrayList<Field>();
		neighbours = location.getNeighbours();
		for(int i = 0; i < neighbours.size(); i++)
			neighbours.get(i).addNeighbour(gates.get(0));
		gates.get(0).setWorking(true);
		location.addNeighbour(gates.get(0));
		removeGate(gates.get(0));
	}
	
	public void useGate()
	{
		location.teleport(this);
	}
	
	public void pickUpGate()
	{
		if(gates.size() < 2)
		{
			location.pickedUpBy(this);
			ArrayList<Field> neighbours = new ArrayList<Field>();
			neighbours = location.getNeighbours();
			for(int i = 0; i < neighbours.size(); i++)
				neighbours.get(i).removeNeighbour(location);
			move();
			if(gates.size() == 1)
			{
				for(int i = 0; i < gates.get(0).getNeighbours().size(); i++)
					gates.get(0).getNeighbours().remove(i);
				gates.get(0).setWorking(false);
			}
			if(gates.size() == 2)
			{
				for(int i = 0; i < gates.get(1).getNeighbours().size(); i++)
					gates.get(1).getNeighbours().remove(i);
				gates.get(1).setWorking(false);
			}
		}
	}
	
	public void buildRobot()
	{
		ArrayList<Material> needed = Robot.craftRobotReq();
		BillOfMaterial newBOM = new BillOfMaterial(needed);
		boolean buildable = false;
		for(int i = 0; i < materials.size(); i++)
		{
			buildable = newBOM.newMaterial(materials.get(i));
			if(buildable)
				break;
		}
		if(buildable)
		{
			materials.removeAll(newBOM.getFound());
			Robot newRobot = new Robot(location);
		}
	}
	
	public void buildBase()
	{
		ArrayList<Material> needed = Game.craftBaseReq();
		BillOfMaterial newBOM = new BillOfMaterial(needed);
		boolean buildable = false;
		ArrayList<FlyingObject> fo = new ArrayList<FlyingObject>();
		fo = location.getOnSurface();
		for(int i = 0; i < fo.size(); i++)
		{
			for(int j = 0; j < fo.get(i).materials.size(); i++)
			{
				buildable = newBOM.newMaterial(fo.get(i).materials.get(j));
				if(buildable)
					break;
			}
		}
		if(buildable)
		{
			//END
		}
	}
	
	public void putMaterial(Material material)
	{
		location.fillBy(this);
	}
	
	public void removeMaterial(Material material)
	{
		materials.remove(material);
	}
	
	public void addMaterial(Material material)
	{
		materials.add(material);
	}
	
	public void move()
	{
		ArrayList<Field> moveable = new ArrayList<Field>();
		moveable = location.getNeighbours();
		Field newLocation;
		if(moveable.size() != 0)
		{
			//amúgy user-input alapján választás, ez most csak egy próba
			newLocation = moveable.get(0);
			location.removeFlyingObject(this);
			newLocation.acceptFlyingObject(this);
		}
		else
			System.out.println("A mozgás sikertelen, mert nincs szomszéd!");
	}
	
	public void die()
	{
		location.removeFlyingObject(this);
		game.removeGameObject(this);
		game.decreaseNumShips();
	}
	
	public void onExplode()
	{
		die();
	}
	
	public void onSolarStormCase()
	{
		die();
	}
	
	public void addGate(Gate newGate)
	{
		gates.add(newGate);
	}
	
	public void removeGate(Gate gate)
	{
		gates.remove(gate);
	}
	
	public void step()
	{
		System.out.println("Mit szeretnél csinálni?");
		System.out.println("1:Move 2:Drill 3:Mine 4:Teleport 5:BuildBase 6:BuildRobot 7:BuildGate 8:PickUpGate 9:PutMaterial");
		Scanner s = new Scanner(System.in);
		int choose = s.nextInt();
		s.close();
		switch(choose)
		{
			case 1: move(); break;
			case 2: drill(); break;
			case 3: mine(); break;
			case 4: useGate(); break;
			case 5: buildBase(); break;
			case 6: buildRobot(); break;
			case 7: craftGatePair(); break;
			case 8: pickUpGate(); break;
			case 9: putMaterial(getMaterials().get(0)); break;
			default: System.out.println("Érvénytelen!");
		}
	}
	
	public ArrayList<Material> getMaterials()
	{
		return materials;
	}
}
