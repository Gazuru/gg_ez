import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends FlyingObject {
	private ArrayList<Gate> gates;

	public Ship() {
		gates = new ArrayList<Gate>();
	}

	public boolean mine() {
		Skeleton.printFunc();
		boolean completed = location.onMine(this);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
		return completed;
	}

	public boolean craftGatePair() {
		Skeleton.printFunc();

		System.out.println("Kapuk sz�ma >0? y/n");
		String ans = Skeleton.getUserInput();

		if (ans.contains("n")) {
			ArrayList<Material> needed = Gate.craftGateReq();
			BillOfMaterial newBOM = new BillOfMaterial(needed);

			for (Material m : materials) {
				if (newBOM.newMaterial(m)) {
					for (Material m2 : newBOM.getFound()) {
						removeMaterial(m2);
					}
					Gate g1 = new Gate();
					Gate g2 = new Gate();
					g1.setPair(g2);
					g2.setPair(g1);
					addGate(g1);
					addGate(g2);
					Skeleton.printFuncRet("true");
					return true;
				}
			}
		}
		Skeleton.printFuncRet("false");
		return false;
	}

	public boolean placeGate() {
		Skeleton.printFunc();
		System.out.println("gates.size>0? y/n");
		String ans = Skeleton.getUserInput();

		if (ans.contains("y")) {
			ArrayList<Field> neighbour_fields = location.getNeighbours();
			for (Field f : neighbour_fields) {
				f.addNeighbour(gates.get(0));
				gates.get(0).addNeighbour(f);
			}
			gates.get(0).setWorking(true);
			location.addNeighbour(gates.get(0));
			gates.get(0).addNeighbour(location);
			this.removeGate(gates.get(0));
			Skeleton.printFuncRet("true");
			return true;
		} else {
			Skeleton.printFuncRet("false");
			return false;
		}
	}

	public boolean pickUpGate() {
		Skeleton.printFunc();
		System.out.println("2>gates? y/n");
		String ans = Skeleton.getUserInput();

		if (ans.contains("y")) {
			boolean ok = location.pickedUpBy(this);
			if (ok) {
				ArrayList<Field> neighbours = new ArrayList<Field>();
				neighbours = location.getNeighbours();
				for (int i = 0; i < neighbours.size(); i++)
					neighbours.get(i).removeNeighbour(location);
				move();
				if (gates.size() == 1) {
					for (int i = 0; i < gates.get(0).getNeighbours().size(); i++)
						gates.get(0).getNeighbours().remove(i);
					gates.get(0).setWorking(false);
				}
				if (gates.size() == 2) {
					for (int i = 0; i < gates.get(1).getNeighbours().size(); i++)
						gates.get(1).getNeighbours().remove(i);
					gates.get(1).setWorking(false);
				}
				System.out.println("A kapu felv�tele siker�lt!");
				return true;

			}
			System.out.println("A kapu felv�tele nem siker�lt, mert m�r van a telepesn�l 2 kapu!");
			return false;
		}
		return true;
	}

	public boolean buildRobot() {
		ArrayList<Material> needed = Robot.craftRobotReq();
		BillOfMaterial newBOM = new BillOfMaterial(needed);

		for (Material m : materials) {
			if (newBOM.newMaterial(m)) {
				for (Material m2 : newBOM.getFound()) {
					removeMaterial(m2);
				}
				Robot r = new Robot();
				r.setLocation(location);
				Skeleton.printFuncRet("true");
				return true;
			}
		}
		Skeleton.printFuncRet("false");
		return false;
	}

	public boolean buildBase() {
		ArrayList<Material> needed = Game.craftBaseReq();
		BillOfMaterial newBOM = new BillOfMaterial(needed);

		for (FlyingObject s : location.getOnSurface()) {
			for (Material m : materials) {
				if (newBOM.newMaterial(m)) {
					Skeleton.printFuncRet("true");
					Game.setEnd(true);
					return true;
				}
			}
		}
		Skeleton.printFuncRet("false");
		return false;
	}

	public boolean putMaterial(Material m) {
		Skeleton.printFunc();
		if (location.fillBy(this, m)) {
			Skeleton.printFuncRet("true");
			return true;
		}
		Skeleton.printFuncRet("false");
		return false;
	}

	public void removeMaterial(Material material) {
		Skeleton.printFunc();
		Skeleton.printFuncRet("");
	}

	public boolean addMaterial(Material material) {
		Skeleton.printFunc();

		System.out.println("kevesebb, mint 10 dolog van n�la inventoryban? y/n");
		String ans2 = Skeleton.getUserInput();

		if (ans2.contains("n")) {
			Skeleton.printFuncRet("false");
			return false;
		}

		Skeleton.printFuncRet("true");
		materials.add(material);
		return true;

	}

	public boolean move() {
		Skeleton.printFunc();
		Field neighbour_asteroid = location.getNeighbours().get(0);
		System.out.println("van szomszed amire tud menni? y/n");
		String ans3 = Skeleton.getUserInput();
		if (ans3.contains("y")) {
			location.removeFlyingObject(this);
			neighbour_asteroid.acceptFlyingObject(this);
			Skeleton.printFuncRet("true");
			return true;
		}
		Skeleton.printFuncRet("false");
		return false;
	}

	public void die() {
		Skeleton.printFunc();

		location.removeFlyingObject(this);
		Game.removeGameObject(this);
		Game.decreaseNumShips();

		Skeleton.printFuncRet("");
	}

	public void onExplode() {
		Skeleton.printFunc();
		die();
		Skeleton.printFuncRet("");
	}

	public void addGate(Gate newGate) {
		Skeleton.printFunc();
		// gates.add(newGate);
		Skeleton.printFuncRet("");
	}

	public void removeGate(Gate gate) {
		Skeleton.printFunc();
		gates.remove(gate);
		Skeleton.printFuncRet("");
	}

	public void step() {
		boolean done = false;
		while (!done) {
			System.out.println("Mit szeretn�l csin�lni?");
			System.out.println(
					"1:Move 2:Drill 3:Mine 4:Teleport 5:BuildBase 6:BuildRobot 7:BuildGate 8:PickUpGate 9:PutMaterial");
			Scanner s = new Scanner(System.in);
			int choose = s.nextInt();
			switch (choose) {
			case 1:
				done = move();
				break;
			case 2:
				done = drill();
				break;
			case 3:
				done = mine();
				break;
			case 4:
				done = useGate();
				break;
			case 5:
				done = buildBase();
				break;
			case 6:
				done = buildRobot();
				break;
			case 7:
				done = craftGatePair();
				break;
			case 8:
				done = pickUpGate();
				break;
			// case 9: done = putMaterial(); break;
			default:
				System.out.println("�rv�nytelen!");
			}
		}
		System.out.println();
	}

	public ArrayList<Material> getMaterials() {
		return materials;
	}
}
