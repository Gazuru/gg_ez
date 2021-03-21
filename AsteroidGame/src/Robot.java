import java.util.ArrayList;
import java.util.Random;

public class Robot extends FlyingObject {
	public Robot() {
		location.acceptFlyingObject(this);
		game.addGameObject(this);
	}

	public void step() {
		boolean done = false;
		done = drill();
		if (!done)
			move();
	}

	public boolean move() {
		ArrayList<Field> moveable = new ArrayList<Field>();
		moveable = location.getNeighbours();
		Field newLocation;
		if (moveable.size() != 0) {
			Random random = new Random();
			int newLocationInt = random.nextInt(moveable.size());
			newLocation = moveable.get(newLocationInt);
			location.removeFlyingObject(this);
			newLocation.acceptFlyingObject(this);
			System.out.println("A mozgás sikeres!");
			return true;
		}
		System.out.println("A mozgás sikertelen, mert nincs szomszéd!");
		return false;
	}

	public void die() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		location.removeFlyingObject(this);
		Game.removeGameObject(this);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public void onExplode() {
		move();
	}

	public static ArrayList<Material> craftRobotReq() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		ArrayList<Material> robotReq = new ArrayList<Material>();
		robotReq.add(new Iron());
		robotReq.add(new Coal());
		robotReq.add(new Uranium());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
		return robotReq;
	}
}
