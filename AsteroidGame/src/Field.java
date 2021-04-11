import java.util.ArrayList;

public abstract class Field{
    protected ArrayList<Field> neighbours = new ArrayList<>();
    protected ArrayList<FlyingObject> onSurface = new ArrayList<>();


    public Field() {
        Game.getInstance().addField(this);
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
        n.neighbours.add(this);
        Skeleton.printFuncRet("");
    }

    public void removeNeighbour(Field oldNeighbour) {
    	Skeleton.printFunc();
        neighbours.remove(oldNeighbour);
        oldNeighbour.neighbours.remove(this);
        Skeleton.printFuncRet("");
    }

    public ArrayList<FlyingObject> getOnSurface() {
    	Skeleton.printFunc();
    	Skeleton.printFuncRet("");
        return onSurface;
    }

    public boolean onMine(FlyingObject f){
    	Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }

    public boolean onDrill(){
    	Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }

    public abstract void onSolarStorm();

    public boolean teleport(FlyingObject f) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }

    public boolean pickedUpBy(Ship ship) {
    	 Skeleton.printFunc();
         Skeleton.printFuncRet("false");
        return false;
    }

    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
    }
}
