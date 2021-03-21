import java.util.ArrayList;

public abstract class FlyingObject implements Steppable {
	protected Field location;
	protected ArrayList<Material> materials;
	protected Game game;

	public FlyingObject() {

		materials = new ArrayList<Material>();

	}

	public Field getLocation() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
		return location;
	}

	public void setLocation(Field newLocation) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		location = newLocation;
		location.acceptFlyingObject(this);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public ArrayList<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> newMaterials) {
		materials = newMaterials;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game newGame) {
		game = newGame;
	}

	public boolean drill() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean completed = location.onDrill();
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
		return completed;
	}

	public boolean useGate() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean completed = location.teleport(this);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
		return completed;
	}

	public abstract boolean move();

	public abstract void die();

	public abstract void onExplode();

	public void onSolarStormCase() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		die();
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}
}
