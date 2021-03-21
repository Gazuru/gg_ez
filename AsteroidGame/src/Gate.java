import java.io.IOException;
import java.util.ArrayList;

public class Gate extends Field {
	private boolean working;
	private Gate pair;

	public Gate() {
		working = false;
	}

	public boolean getWorking() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("working? y/n");
		String ans = Skeleton.getUserInput();
		if (ans.contains("y")) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " true");
			return true;
		} else {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " false");
			return false;
		}

	}

	public boolean pickedUpBy(Ship ship) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		ship.addGate(this);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " true");
		return true;
	}

	public boolean teleport(FlyingObject f) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (pair.getWorking()) {
			removeFlyingObject(f);
			pair.acceptFlyingObject(f);
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " false");
		return false;
	}

	public static ArrayList<Material> craftGateReq() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		ArrayList<Material> needed = new ArrayList<>();
		needed.add(new Iron());
		needed.add(new Iron());
		needed.add(new Ice());
		needed.add(new Uranium());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " needed");
		return needed;
	}

	public void setWorking(boolean working) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public Gate getPair() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
		return pair;
	}

	public void setPair(Gate pair) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		this.pair = pair;
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public boolean onDrill() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " false");
		return false;
	}

	public void onSolarStorm() {
		for (int i = 0; i < onSurface.size(); i++)
			onSurface.get(i).onSolarStormCase();
	}

	public boolean onMine(Ship ship) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " false");
		return false;
	}

	public boolean fillBy(Ship ship) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns" + " false");
		return false;
	}
}
