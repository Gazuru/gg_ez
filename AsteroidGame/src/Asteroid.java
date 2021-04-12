import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Field {
    private int layer;
    private Material core;
    private boolean inSunProximity;

    public Asteroid() {
        genCore();
        layer = new Random().nextInt(10) + 1;
        inSunProximity = new Random().nextBoolean();
    }

    public void genCore() {
        switch (new Random().nextInt(5)) {
            case 0:
                acceptCore(new Coal());
                break;
            case 1:
                acceptCore(new Iron());
                break;
            case 2:
                acceptCore(new Ice());
                break;
            case 3:
                acceptCore(new Uranium());
                break;
            case 4:
                core = null;
                break;
        }
    }

    public Material getCore() {
        return core;
    }

    public int getLayer() {
        return layer;
    }

    public boolean getInSunProximity() {
        Skeleton.printFunc();
        Skeleton.printFuncRet(Boolean.toString(inSunProximity));
        return inSunProximity;

    }

    public void setInSunProximity(Boolean inSunProximity) {
        Skeleton.printFunc();
        this.inSunProximity = inSunProximity;
        Skeleton.printFuncRet("");
    }

    public boolean onDrill() {
        Skeleton.printFunc();
        if (layer > 1) {
            decreaseLayer();
            Skeleton.printFuncRet("true");
            return true;
        } else if (layer == 1) {
            decreaseLayer();
            if(core!=null)
                core.onDrillSpecial(this);
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }
    }

    public void acceptCore(Material newCore) {
        Skeleton.printFunc();
        core = newCore;
        Skeleton.printFuncRet("");
    }

    public void removeCore() {
        Skeleton.printFunc();
        core = null;
        Skeleton.printFuncRet("");
    }

    public void decreaseLayer() {
        Skeleton.printFunc();
        layer--;
        Skeleton.printFuncRet("");
    }

    public void explode() {
        Skeleton.printFunc();
        for (int i = onSurface.size() - 1; i >= 0; i--) {
            onSurface.get(i).onExplode();
        }

        for (int i = neighbours.size() - 1; i >= 0; i--) {
            removeNeighbour(neighbours.get(i));
        }

        Game.getInstance().removeField(this);

        Skeleton.printFuncRet("");
    }

    public void onSolarStorm() {
        Skeleton.printFunc();
        if (layer != 0 || core != null) {
            for (int i = onSurface.size() - 1; i >= 0; i--) {
                onSurface.get(i).onSolarStormCase();
            }
        }
        Skeleton.printFuncRet("");
    }

    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();
        if (layer == 0 && core == null) {
            acceptCore(m);
            ship.removeMaterial(m);
            core.onFill(this);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    public boolean onMine(FlyingObject f) {
        Skeleton.printFunc();
        if (layer == 0 && core != null) {
            if (f.addMaterial(core)) {
                removeCore();
                return true;
            }
        }
        return false;
    }
}
