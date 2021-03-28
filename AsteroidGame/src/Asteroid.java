import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Field {
    private int layer;
    private Material core;
    private boolean inSunProximity;

    public Asteroid() {

    }

    public boolean getInSunProximity() {
        return inSunProximity;
    }

    public void setInSunProximity(Boolean inSunProximity) {
        this.inSunProximity = inSunProximity;
    }

    public boolean onDrill() {
        Skeleton.printFunc();

        /*System.out.println("layer>1? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("y")) {
            this.decreaseLayer();
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }*/

        if(layer>1){
            decreaseLayer();
            return true;
        }else if(layer == 1){
            core.onDrillSpecial(this);
            return true;
        }else{
            return false;
        }

    }

    public void acceptCore(Material newCore) {
        Skeleton.printFunc();
        core = newCore;
        Skeleton.printFuncRet("");
    }

    /*public void setCore(Material newCore) {
        core = newCore;
    }*/


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
        for(int i = 0; i < onSurface.size(); i++){

        }

        Skeleton.printFuncRet("");
    }

	public void onSolarStorm() {
		Skeleton.printFunc();

		System.out.println("el tud bujni? y/n");
		String ans = Skeleton.getUserInput();

		if (ans.contains("n")) {
			for (int i = 0; i < onSurface.size(); i++) {
				onSurface.get(i).onSolarStormCase();
			}
        }
        Skeleton.printFuncRet("");
    }

    public boolean fillBy(Ship ship, Material m) {
        Skeleton.printFunc();

        System.out.println("layer==0 és üres jelenleg? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("y")) {
            acceptCore(m);
            ship.removeMaterial(m);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    public boolean onMine(Ship ship) {
        Skeleton.printFunc();

        System.out.println("át van fúrva és van benne nyersanyag? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("n")) {
            Skeleton.printFuncRet("false");
            return false;
        }
        if (!ship.addMaterial(core)) {
            Skeleton.printFuncRet("false");
            return false;
        } else {
            removeCore();

            Skeleton.printFuncRet("true");
            return true;
        }
    }
}
