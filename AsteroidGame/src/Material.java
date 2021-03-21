public abstract class Material {
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
    }

    public boolean compatibleWith(Material other) {
        Skeleton.printFunc();

        if (this.getClass() == other.getClass()) {
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;

    }
}
