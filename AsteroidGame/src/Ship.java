import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends FlyingObject
{
	private ArrayList<Gate> gates;
	
	public Ship()
	{
		gates = new ArrayList<Gate>();
	}
	
	public boolean mine()
	{
		return location.onMine(this);
	}
	
	public boolean craftGatePair()
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
			newGate1.setPair(newGate2);
			newGate2.setPair(newGate1);
			System.out.println("A kapup�r �p�t�se siker�lt!");
			return true;
		}
		else
		{
			System.out.println("A kapup�r �p�t�se nem siker�lt, mert nincs a telepesnek hozz� el�g nyersanyaga!");
			return false;
		}
	}
	
	public boolean placeGate()
	{
		if(gates.size() == 0)
		{
			System.out.println("A kapu lerak�sa nem siker�lt, mert a telepesn�l nincs kapu!");
			return false;
		}
		ArrayList<Field> neighbours = new ArrayList<Field>();
		neighbours = location.getNeighbours();
		if(gates.size() == 1)
		{
			for(int i = 0; i < neighbours.size(); i++)
				neighbours.get(i).addNeighbour(gates.get(0));
			gates.get(0).setWorking(true);
			location.addNeighbour(gates.get(0));
			removeGate(gates.get(0));
			System.out.println("A kapu lerak�sa siker�lt, a telepesn�l nincs t�bb kapu!");
			return true;
		}
		System.out.println("Melyik kaput szeretn�d lerakni?");
		Scanner s = new Scanner(System.in);
		int choose = s.nextInt();
		for(int i = 0; i < neighbours.size(); i++)
			neighbours.get(i).addNeighbour(gates.get(choose));
		gates.get(choose).setWorking(true);
		location.addNeighbour(gates.get(choose));
		removeGate(gates.get(choose));
		System.out.println("A kapu lerak�sa siker�lt, a telepesn�l m�g van egy kapu!");
		return true;
	}
	
	public boolean useGate()
	{
		return location.teleport(this);
	}
	
	public boolean pickUpGate()
	{
		if(gates.size() < 2)
		{
			boolean ok = location.pickedUpBy(this);
			if(ok)
			{
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
				System.out.println("A kapu felv�tele siker�lt!");
				return true;
			}
			System.out.println("A kapu felv�tele nem siker�lt!");
			return false;
		}
		System.out.println("A kapu felv�tele nem siker�lt, mert m�r van a telepesn�l 2 kapu!");
		return false;
	}
	
	public boolean buildRobot()
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
			Robot newRobot = new Robot();
			System.out.println("A robot �p�t�se siker�lt!");
			return true;
		}
		System.out.println("A robot �p�t�se nem siker�lt, mert nincs a telepesn�l elegend� nyersanyag!");
		return false;
	}
	
	public boolean buildBase()
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
			if(buildable)
				break;
		}
		if(buildable)
		{
			System.out.println("A b�zis �pe�t�se siker�lt, a telepesek megnyert�k a j�t�kot!");
			game.setEnd(true);
			return true;
		}
		System.out.println("A b�zis �p�t�se nem siker�lt, mert nincs a telepesn�l elegend� nyersanyag!");
		return false;
	}
	
	public boolean putMaterial()
	{
		if(materials != null)
			location.fillBy(this);
		System.out.println("A nyersanyag lerak�sa nem siker�lt, mert a telepesn�l nincs nyersanyag!");
		return false;
	}
	
	public void removeMaterial(Material material)
	{
		materials.remove(material);
	}
	
	public void addMaterial(Material material)
	{
		materials.add(material);
	}
	
	public boolean move()
	{
		ArrayList<Field> moveable = new ArrayList<Field>();
		moveable = location.getNeighbours();
		Field newLocation;
		if(moveable.size() != 0)
		{
			System.out.println("Hova szeretn�l menni? 0: " + moveable.get(0).getNumber() + " 1: " + moveable.get(1).getNumber());
			Scanner s = new Scanner(System.in);
			int choose = s.nextInt();
			newLocation = moveable.get(choose);
			location.removeFlyingObject(this);
			newLocation.acceptFlyingObject(this);
			location = newLocation;
			System.out.println("A mozg�s sikeres!");
			return true;
		}
		System.out.println("A mozg�s sikertelen, mert nincs szomsz�d!");
		return false;
	}
	
	public void die()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		location.removeFlyingObject(this);
		Game.removeGameObject(this);
		Game.decreaseNumShips();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void onExplode()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		die();
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
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
		boolean done = false;
		while(!done)
		{
			System.out.println("Mit szeretn�l csin�lni?");
			System.out.println("1:Move 2:Drill 3:Mine 4:Teleport 5:BuildBase 6:BuildRobot 7:BuildGate 8:PickUpGate 9:PutMaterial");
			Scanner s = new Scanner(System.in);
			int choose = s.nextInt();
			switch(choose)
			{
				case 1: done = move(); break;
				case 2: done = drill(); break;
				case 3: done = mine(); break;
				case 4: done = useGate(); break;
				case 5: done = buildBase(); break;
				case 6: done = buildRobot(); break;
				case 7: done = craftGatePair(); break;
				case 8: done = pickUpGate(); break;
				case 9: done = putMaterial(); break;
				default: System.out.println("�rv�nytelen!");
			}
		}
		System.out.println();
	}
	
	public ArrayList<Material> getMaterials()
	{
		return materials;
	}
}
