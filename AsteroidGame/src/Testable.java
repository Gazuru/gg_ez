public class Testable<X, Integer> {

    public X obj;
    public Integer num;

    public Testable(X x, Integer y) {
        obj = x;
        num = y;
    }

    public Class getObj(){
        return obj.getClass();
    }


}
