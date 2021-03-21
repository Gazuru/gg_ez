public class Ice extends NonRadioactiveMaterial {
    public void onDrillSpecial(Asteroid a) {
        Skeleton.printFunc();

        System.out.println("Asteroid sun proximate? y/n");
        String ans = Skeleton.getUserInput();
        if (ans.contains("y")) {
            a.removeCore();
        }

        Skeleton.printFuncRet("");
    }
}
