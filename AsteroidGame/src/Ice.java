public class Ice extends NonRadioactiveMaterial {
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        if (a.getInSunProximity()) {
            a.removeCore();
        }
        Skeleton.printFuncRet("");
    }
}
