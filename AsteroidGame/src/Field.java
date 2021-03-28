import java.util.ArrayList;

public abstract class Field {
    protected ArrayList<Field> neighbours;
    protected ArrayList<FlyingObject> onSurface;

    public Field() {
        neighbours = new ArrayList<Field>();
        onSurface = new ArrayList<FlyingObject>();
    }

    public ArrayList<Field> getNeighbours() {
        Skeleton.printFunc();

        Skeleton.printFuncRet("neighbours");
        return neighbours;
    }

    public void removeFlyingObject(FlyingObject fo) {
        Skeleton.printFunc();
        onSurface.remove(fo);
        fo.setLocation(null);
        Skeleton.printFuncRet("");
    }

    public void acceptFlyingObject(FlyingObject fo) {
        Skeleton.printFunc();
        onSurface.add(fo);
        fo.setLocation(this);
        Skeleton.printFuncRet("");
    }

    public void addNeighbour(Field n) {
        Skeleton.printFunc();
        neighbours.add(n);
        Skeleton.printFuncRet("");
    }

    public void removeNeighbour(Field oldNeighbour) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        neighbours.remove(oldNeighbour);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
    }

    public ArrayList<FlyingObject> getOnSurface() {
        return onSurface;
    }

    public boolean onMine(Ship ship){
        return false;
    }

    public boolean onDrill(){
        return false;
    }

    public abstract void onSolarStorm();

    public boolean teleport(FlyingObject f) {
        Skeleton.printFunc();

        Skeleton.printFuncRet("false");
        return false;
    }

    public boolean pickedUpBy(Ship ship) {
        return false;
    }

    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
}
