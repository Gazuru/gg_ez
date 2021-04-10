import java.util.ArrayList;

public class BillOfMaterial {
    private ArrayList<Material> needed = new ArrayList<Material>();
    private ArrayList<Material> found = new ArrayList<Material>();

    public BillOfMaterial(ArrayList<Material> requires) {
    	Skeleton.printFunc();
        needed = requires;
        Skeleton.printFuncRet("");
    }

    public boolean newMaterial(Material m) {
        Skeleton.printFunc();

        for (Material m2 : needed) {
            if (m.compatibleWith(m2)) {
                removeNeeded(m2);
                addFound(m);
                break;
            }
        }
        if (needed.isEmpty()) {
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    public ArrayList<Material> getFound() {
    	Skeleton.printFunc();
    	Skeleton.printFuncRet("");
        return found;
    }

    public void removeNeeded(Material r) {
        Skeleton.printFunc();
        needed.remove(r);
        Skeleton.printFuncRet("");
    }

    public void addFound(Material f) {
        Skeleton.printFunc();
        found.add(f);
        Skeleton.printFuncRet("");
    }
}
