import java.util.ArrayList;

public abstract class Field {
    protected ArrayList<Field> neighbours;
    protected ArrayList<FlyingObject> onSurface;
    protected int number;
    protected Game game;

    public Field() {
        neighbours = new ArrayList<Field>();
        onSurface = new ArrayList<FlyingObject>();
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Field> getNeighbours() {
        Skeleton.printFunc();

        Skeleton.printFuncRet("neighbours");
        return neighbours;
    }

    public void removeFlyingObject(FlyingObject fo) {
        Skeleton.printFunc();
        this.onSurface.remove(fo);
        Skeleton.printFuncRet("");
    }

    public void acceptFlyingObject(FlyingObject fo) {
        Skeleton.printFunc();
        this.onSurface.add(fo);
        Skeleton.printFuncRet("");
    }

    public void addNeighbour(Field n) {
        Skeleton.printFunc();
        neighbours.add(n);
        Skeleton.printFuncRet("");
    }

    public void removeNeighbour(Field oldNeighbour) {
        neighbours.remove(oldNeighbour);
    }

    public ArrayList<FlyingObject> getOnSurface() {
        return onSurface;
    }

    public abstract boolean onMine(Ship ship);

    public abstract boolean onDrill();

    public abstract void onSolarStorm();

    public boolean teleport(FlyingObject f) {
        Skeleton.printFunc();

        Skeleton.printFuncRet("false");
        return false;
    }

    public abstract boolean pickedUpBy(Ship ship);

    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
}
