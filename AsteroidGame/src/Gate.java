import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Gate extends Field implements Steppable {
    private boolean working;
    private boolean wild;
    private Gate pair;

    /**
     * konstruktor
     * false-ra �llitja a working-et �s a wild-ot
     */
    public Gate() {
        working = false;
        wild = false;
    }

    /**
     * step fv ami minden k�r�be lefut
     * ha wild true meghivjuk a goingWild fv-�t
     */
    public void step() {
        Skeleton.printFunc();
        if (wild) {
            goingWild();
        }
        updateView();
        Skeleton.printFuncRet("");
    }

    private void updateView() {
        // TODO Auto-generated method stub

    }

    /**
     * �t�llitjuk a kapott param�terre a wild tagvaltozot
     *
     * @param w ezt adjuk �t param�terk�nt
     */
    public void setWild(boolean w) {
        this.wild = w;
    }

    public boolean getWild() {
        return wild;
    }

    /**
     * visszaterunk a working tagvaltozoval
     *
     * @return boolean working ezzel t�r�nk vissza
     */
    public boolean getWorking() {
        Skeleton.printFunc();

        Skeleton.printFuncRet(Boolean.toString(working));
        return working;
    }

    /**
     * ez hivodik meg ha felveszi egy parameterkent kapott ship
     * addoljuk a ship gates listajaba a kaput,
     * toroljuk a szomszedsagi viszonyokat,
     * majd mozgatjuk a shipet �s false-ra �llitjuk a kapu working tagvaltozojat
     *
     * @param ship ezt adjuk at parameterkent
     * @return boolean true ha sikeres a felv�tel
     * @return boolean false ha sikertelen
     */
    public boolean pickedUpBy(Ship ship) {
        Skeleton.printFunc();
        if (ship.getGates().size() < 3) {
            ship.addGate(this);
            if(!neighbours.get(0).getClass().equals(Gate.class))
                Vars.DESTINATION = neighbours.get(0);
            else
                Vars.DESTINATION = neighbours.get(1);
            ship.move();
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                neighbours.get(i).removeNeighbour(this);
            }
            Game.getInstance().removeField(this);
            setWorking(false);
            getPair().setWorking(false);
            Skeleton.printFuncRet("true");
            return true;
        } else {
            return false;
        }
    }

    /**
     * teleport-olas eseten ezt hivjuk meg a Gate-en,
     * ha mukodik a kapu, levesszuk rola a hajot �s a parjara atrakjuk
     *
     * @param f ezt adjuk at parameterkent
     * @return boolean true ha teljesul a feltetel ezzel terunk vissza
     * @return boolean false ha nem teljesul a feltetel
     */
    public boolean teleport(FlyingObject f) {
        Skeleton.printFunc();

        if (pair.getWorking()) {
            removeFlyingObject(f);
            pair.acceptFlyingObject(f);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    /**
     * visszaterunk a craftol�s�hoz szukseges anyagokkal
     * letrehzunk egy uj listat �s hozzaadjuk a szukseges anyagokat
     *
     * @return ArrayList<Material> needed ezzel terunk vissza
     */
    public static ArrayList<Material> craftGateReq() {
        Skeleton.printFunc();
        ArrayList<Material> needed = new ArrayList<>();
        needed.add(new Iron());
        needed.add(new Iron());
        needed.add(new Ice());
        needed.add(new Uranium());
        Skeleton.printFuncRet("needed");
        return needed;
    }

    /**
     * a working tagvaltozot a param�terben kapott ertekre allitjuk
     *
     * @param _working ezt adjuk at parameterkent
     */
    public void setWorking(boolean _working) {
        Skeleton.printFunc();
        working = _working;
        Skeleton.printFuncRet("");
    }

    /**
     * visszaterunk a Gate p�rj�val
     *
     * @return Gate pair ezzel t�r�nk vissza
     */
    public Gate getPair() {
        Skeleton.printFunc();
        Skeleton.printFuncRet("");
        return pair;
    }

    /**
     * a pair tagvaltozot a param�terben kapott ertekre allitjuk
     *
     * @param pair ezt adjuk at parameterkent
     */
    public void setPair(Gate pair) {
        Skeleton.printFunc();
        this.pair = pair;
        Skeleton.printFuncRet("");
    }

    /**
     * napvihar eset�n hivodik meg, a felszinen levo osszes hajora meghivja az onSolarStormCase-t,
     * majd a wild erteket a Gate-nek true-ra �llitja
     */
    public void onSolarStorm() {
        Skeleton.printFunc();

        for (int i = onSurface.size() - 1; i >= 0; i--)
            onSurface.get(i).onSolarStormCase();
        setWild(true);
        Skeleton.printFuncRet("");
    }

    /**
     * ez a fv felel a megvadult kapu mozgasaert
     * mozgatjuk a Gate-et az aszteroid�k k�zt�tt, �gy, hogy a szomsz�ds�gi viszonyokat mindig kezelj�k
     * az �jakat hozz�adjuk a r�gieket elt�volitjuk
     */
    public void goingWild() {
        Skeleton.printFunc();
        Field toMoveLocation = neighbours.get(0);

        for (int i = neighbours.size() - 1; i >= 0; i--) {
            neighbours.get(i).removeNeighbour(this);
        }
        ArrayList<Field> toMoveLocationNeighbours = toMoveLocation.getNeighbours();

        this.addNeighbour(toMoveLocation);

        for (int i = toMoveLocationNeighbours.size() - 1; i >= 0; i--) {
            toMoveLocationNeighbours.get(i).addNeighbour(this);
        }

        Skeleton.printFuncRet("");
    }


}
