public abstract class RadioactiveMaterial extends Material {
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        if (a.getInSunProximity()) {
            a.explode();
        }
        Skeleton.printFuncRet("");
    }
}
