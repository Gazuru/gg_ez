public class Testable<X, Integer> {

    public X obj;
    public Integer num;

    /**
     * A teszteléshez szükséges elem tuple-be rendezését oldja meg, az x paraméter bármilyen objektum, míg az y az ID-je
     * @param x teszteléshez hozzáadni kívánt objektum
     * @param y az objektum azonosítási száma
     */
    public Testable(X x, Integer y) {
        obj = x;
        num = y;
    }

    /**
     * Visszaadja a tárolt objektum osztályát
     * @return obj objektum Class értéke
     */
    public Class getObj(){
        return obj.getClass();
    }


}
