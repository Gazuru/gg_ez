import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Field {
    private int layer;
    private Material core;
    private boolean inSunProximity;

    public Asteroid() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int x) {
        number = x;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean getInSunProximity() {
        return inSunProximity;
    }

    public void setInSunProximity(Boolean inSunProximity) {
        this.inSunProximity = inSunProximity;
    }

    public boolean onDrill() {
        Skeleton.printFunc();

        System.out.println("layer>1? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("y")) {
            this.decreaseLayer();
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }
    }

    public void acceptCore(Material newCore) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }

    public void setCore(Material newCore) {
        core = newCore;
    }


    public void removeCore() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }

    public void decreaseLayer() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }

    public void explode() {
        Skeleton.printFunc();
        for (FlyingObject f : onSurface) {
            f.onExplode();
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

        System.out.println("layer==0 �s �res jelenleg? y/n");
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

        System.out.println("�t van f�rva �s van benne nyersanyag? y/n");
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
