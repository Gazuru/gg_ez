import java.util.ArrayList;
import java.util.Random;

public class Ufo extends FlyingObject{
	private ArrayList<Material> materials;
	@Override
	public void step() {
		Skeleton.printFunc();
		boolean done = false;
        done = drill();
        if (!done) {
        	Random random = new Random();
        	int randomI = random.nextInt(2);
        	if(randomI==0) {
        		useGate();
        	}
        	if(randomI==1) {
        		move();
        	}
        }
        Skeleton.printFuncRet("");
	}

	@Override
	public boolean move() {
		 Skeleton.printFunc();
	        Field neighbour_asteroid = location.getNeighbours().get(0);
	        if (neighbour_asteroid!=null) {
	            location.removeFlyingObject(this);
	            neighbour_asteroid.acceptFlyingObject(this);
	            Skeleton.printFuncRet("true");
	            return true;
	        }
	        Skeleton.printFuncRet("false");
	        return false;
	}
	 public boolean mine() {
	        Skeleton.printFunc();
	        //System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
	        return location.onMine(this);
	    }

	@Override
	public void die() {
		Skeleton.printFunc();
        location.removeFlyingObject(this);
        Game.removeGameObject(this);
        Skeleton.printFuncRet("");
	}

	@Override
	public void onExplode() {
		Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
	}

	@Override
	public void onSolarStormCase() {
		Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
	}
	public boolean useGate() {
		if(location.teleport(this)) {
			Skeleton.printFuncRet("true");
    		return true;
    	}else{
    		Skeleton.printFuncRet("false");
    	return false;}
	}
	public boolean drill() {
		Skeleton.printFunc();
        Skeleton.printFuncRet("false");
        return false;
	}

	public boolean addMaterial(Material material) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("true");
        materials.add(material);
        return true;
    }
}
