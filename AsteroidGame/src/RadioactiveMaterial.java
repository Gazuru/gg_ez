public abstract class RadioactiveMaterial extends Material {
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();

        System.out.println("Asteroid sun proximate? y/n");
        String ans = Skeleton.getUserInput();
        if (ans.contains("y")) {
            a.explode();
        }

        Skeleton.printFuncRet("");
    }
}
