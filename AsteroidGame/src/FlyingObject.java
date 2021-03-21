import java.util.ArrayList;

public abstract class FlyingObject implements Steppable {
    protected Field location;
    protected ArrayList<Material> materials;
    protected Game game;

    public FlyingObject() {

        materials = new ArrayList<Material>();

    }

    public Field getLocation() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
        return location;
    }

    public void setLocation(Field newLocation) {
        Skeleton.printFunc();
        location = newLocation;
        Skeleton.printFuncRet("");
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
        Skeleton.printFunc();
        boolean completed = location.onDrill();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
        return completed;
    }

    public boolean useGate() {
        Skeleton.printFunc();
        boolean completed = location.teleport(this);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
        return completed;
    }

    public abstract boolean move();

    public abstract void die();

    public abstract void onExplode();

    public void onSolarStormCase() {
        Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
    }
}
