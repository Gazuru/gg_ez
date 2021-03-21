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
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		boolean completed= location.onMine(this);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns "+completed);
		return completed;
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
			System.out.println("A kapupár építése sikerült!");
			return true;
		}
		else
		{
			System.out.println("A kapupár építése nem sikerült, mert nincs a telepesnek hozzá elég nyersanyaga!");
			return false;
		}
	}
	
	public boolean placeGate()
	{
		if(gates.size() == 0)
		{
			System.out.println("A kapu lerakása nem sikerült, mert a telepesnél nincs kapu!");
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
			System.out.println("A kapu lerakása sikerült, a telepesnél nincs több kapu!");
			return true;
		}
		System.out.println("Melyik kaput szeretnéd lerakni?");
		Scanner s = new Scanner(System.in);
		int choose = s.nextInt();
		for(int i = 0; i < neighbours.size(); i++)
			neighbours.get(i).addNeighbour(gates.get(choose));
		gates.get(choose).setWorking(true);
		location.addNeighbour(gates.get(choose));
		removeGate(gates.get(choose));
		System.out.println("A kapu lerakása sikerült, a telepesnél még van egy kapu!");
		return true;
	}
	
	
	
	public boolean pickUpGate()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("2>gates? y/n");
		String ans=Skeleton.getUserInput();
		
		if(ans.contains("y"))
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
				System.out.println("A kapu felvétele sikerült!");
				return true;
			
		}
		System.out.println("A kapu felvétele nem sikerült, mert már van a telepesnél 2 kapu!");
		return false;
	}
		return true;
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
			System.out.println("A robot építése sikerült!");
			return true;
		}
		System.out.println("A robot építése nem sikerült, mert nincs a telepesnél elegendõ nyersanyag!");
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
			System.out.println("A bázis épeítése sikerült, a telepesek megnyerték a játékot!");
			game.setEnd(true);
			return true;
		}
		System.out.println("A bázis építése nem sikerült, mert nincs a telepesnél elegendõ nyersanyag!");
		return false;
	}
	
	public boolean putMaterial(Material m)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		if(location.fillBy(this,m)) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
		return false;
	}
	
	public void removeMaterial(Material material)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public boolean addMaterial(Material material)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("kevesebb mint 10 dolog van nála inventoryba? y/n");
		String ans2=Skeleton.getUserInput();
		
		if(ans2.contains("n")) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
			return false;
		}
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
		return true;
		
	}
	
	public boolean move()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Field neighbour_asteroid=location.getNeighbours().get(0);
		System.out.println("van szomszed amire tud menni? y/n");
		String ans3=Skeleton.getUserInput();
		if(ans3.contains("y"))
		{
			location.removeFlyingObject(this);
			neighbour_asteroid.acceptFlyingObject(this);
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
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
			System.out.println("Mit szeretnél csinálni?");
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
				//case 9: done = putMaterial(); break;
				default: System.out.println("Érvénytelen!");
			}
		}
		System.out.println();
	}
	
	public ArrayList<Material> getMaterials()
	{
		return materials;
	}
}
