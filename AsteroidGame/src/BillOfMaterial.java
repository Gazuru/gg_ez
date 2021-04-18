import java.util.ArrayList;

public class BillOfMaterial {
    private ArrayList<Material> needed = new ArrayList<Material>();
    private ArrayList<Material> found = new ArrayList<Material>();
    /**
    * konstruktor, beállitjuk a kapott listara listankat
    *
    * @param requires erre állitjuk be
    */
    public BillOfMaterial(ArrayList<Material> requires) {
    	Skeleton.printFunc();
        needed = requires;
        Skeleton.printFuncRet("");
    }
    /**
    * uj anyag talalasa eseten hivodik meg es ellenorizzuk vele hogy felhasznalhato-e
    * 
    * @param  m atadjuk parameterkent a talalt uj anyagot
    * 
    * @return boolean true ha a neededben levo valamelyik anyag kompatibilis(tipus egyezik) a parameterkent kapott anyaggal
    * vagy ha ures a needed lista
    * 
    * @return boolean false ha nem teljesul a feltetel
    * 
    */
    public boolean newMaterial(Material m) {
        Skeleton.printFunc();

        for (Material m2 : needed) {
            if (m.compatibleWith(m2)) {
                removeNeeded(m2);
                addFound(m);
                break;
            }
        }
        if (needed.isEmpty()) {
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }
    /**
    * lekerdezzuk vele a found listát
    *
    * @return ArrayList<Material> found ezzel térünk vissza
    * 
    */
    public ArrayList<Material> getFound() {
    	Skeleton.printFunc();
    	Skeleton.printFuncRet("");
        return found;
    }
    /**
    * eltávolitjuk a parameterkent kapott anyagot a needed listarol
    *
    * @param r ezt tavolitjuk el
    * 
    */
    public void removeNeeded(Material r) {
        Skeleton.printFunc();
        needed.remove(r);
        Skeleton.printFuncRet("");
    }
    /**
     * hozzaadjuk a parameterkent kapott anyagot a found listahoz
     *
     * @param f ezt adjuk hozza
     * 
     */
    public void addFound(Material f) {
        Skeleton.printFunc();
        found.add(f);
        Skeleton.printFuncRet("");
    }
}
