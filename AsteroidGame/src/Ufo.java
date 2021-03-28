
public class Ufo extends FlyingObject{

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
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
    		return true;
    	}else{
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
