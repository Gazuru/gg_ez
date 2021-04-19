public class Testable<X, Integer> {

    public X obj;
    public Integer num;

    /**
     * A tesztel�shez sz�ks�ges elem tuple-be rendez�s�t oldja meg, az x param�ter b�rmilyen objektum, m�g az y az ID-je
     * @param x tesztel�shez hozz�adni k�v�nt objektum
     * @param y az objektum azonos�t�si sz�ma
     */
    public Testable(X x, Integer y) {
        obj = x;
        num = y;
    }

    /**
     * Visszaadja a t�rolt objektum oszt�ly�t
     * @return obj objektum Class �rt�ke
     */
    public Class getObj(){
        return obj.getClass();
    }


}
