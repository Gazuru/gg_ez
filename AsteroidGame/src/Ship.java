import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ship extends FlyingObject {
    private ArrayList<Gate> gates = new ArrayList<Gate>();
    private ArrayList<Material> materials = new ArrayList<Material>();

    /**
     * konstruktor,
     * ha nincs aszteroida amire mehet letrehoz egyet
     * hozzáaddolja magát a singleton Game gameObjects listajahoz
     * valamint az aszteroidat is
     */
    public Ship() {
        ArrayList<Field> locations = Game.getInstance().getFields();
        if (!locations.isEmpty()) {
            locations.get(new Random().nextInt(locations.size())).acceptFlyingObject(this);
        } else {
            System.out.println("Nincs aszteroida a jatekban!");
        }
        // Game.getInstance().incrNumShips();
    }
    /**
     * parameterul megkapja az adott anyagot es summazza hany ilyen tipusu van az urhajonal
     * majd visszater az ertekkel
     * 
     * @param m a nyersanyagtípus
     * @return sum, az összeg
     **/
    public int sumMaterial(Material m) {
        int sum = 0;
        for (int i = 0; i < this.materials.size(); i++) {
            if (m.getClass().equals(materials.get(i).getClass())) {
                sum++;
            }
        }
        return sum;
    }
    /**
     * summazza hany gate van az urhajonal
     * majd visszater az ertekkel
     * 
     * @return int
     * 
     **/
    public int sumGates() {
        if (gates.size() > 0) {
            return gates.size();
        } else {
            return 0;
        }
    }

    /**
     * banyaszatert felelos fv,
     * meghivja a locationon az onMine fv-t this parameterrel
     *
     * @return boolean true ha sikerrel zajlott a banyaszat
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean mine() {
        Skeleton.printFunc();
        return location.onMine(this);
    }

    /**
     * a gate kraftolasert felelos fv, ha kevesebb mint 2 gate van nála,
     * megnezi megvannak-e a megfelelo anyagok, ha igen hozzaadja a gates listahoz
     *
     * @return boolean true ha sikerrel zajlott a craft
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean craftGatePair() {
        Skeleton.printFunc();
        if (gates.size() < 2) {
            ArrayList<Material> needed = Gate.craftGateReq();
            BillOfMaterial bom = new BillOfMaterial(needed);

            for (Material m : materials) {
                if (bom.newMaterial(m)) {
                    for (Material m2 : bom.getFound()) {
                        removeMaterial(m2);
                    }
                    Gate gate1 = new Gate();
                    Gate gate2 = new Gate();
                    gate1.setPair(gate2);
                    gate2.setPair(gate1);
                    this.addGate(gate1);
                    this.addGate(gate2);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ez a fv felelos a gate lerakasaert,
     * ha több mint 0 gate-ünk van, hozzáadja a megfelelo szomszedsagi viszonyokat
     * workingre állitja a gatet,
     * majd kiveszi a ship gates listajabol
     *
     * @return boolean true ha sikerrel zajlott a place
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean placeGate() {
        if (gates.size() > 0) {
            ArrayList<Field> neigboursFields = location.getNeighbours();
            for (Field f : neigboursFields) {
                f.addNeighbour(gates.get(0));
            }
            if(!gates.get(0).getPair().neighbours.isEmpty()){
                gates.get(0).setWorking(true);
                gates.get(0).getPair().setWorking(true);
            }
            gates.get(0).addNeighbour(location);
            Game.getInstance().addField(gates.get(0));
            removeGate(gates.get(0));
            return true;
        }
        return false;
    }

    /**
     * ez a fv felelos a gate felvevesert,
     * ha kevesebb mint 3 gate-ünk van,
     * meghivja a location pickedUpBy fv-jét this paraméterezéssel
     *
     * @return boolean true ha sikerrel zajlott a pickup
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean pickUpGate() {
        if (gates.size() < 3) {
            return location.pickedUpBy(this);
        }
        return false;
    }

    /**
     * a robot kraftolasert felelos fv,
     * megnezi megvannak-e a megfelelo anyagok, ha igen lerakja a robotot
     *
     * @return boolean true ha sikerrel zajlott a craft
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean buildRobot() {
        ArrayList<Material> needed = Robot.craftRobotReq();
        BillOfMaterial newBOM = new BillOfMaterial(needed);

        for (Material m : materials) {
            if (newBOM.newMaterial(m)) {
                for (Material m2 : newBOM.getFound()) {
                    removeMaterial(m2);
                }
                Robot r = new Robot();
                location.acceptFlyingObject(r);
                Skeleton.printFuncRet("true");
                return true;
            }
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    /**
     * a base kraftolasert felelos fv,
     * megnezi megvannak-e a megfelelo anyagok, ha igen megnyertuk a jatekot
     *
     * @return boolean true ha sikerrel zajlott a craft
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean buildBase() {
        ArrayList<Material> needed = Game.getInstance().craftBaseReq();
        BillOfMaterial newBOM = new BillOfMaterial(needed);

        for (FlyingObject s : location.getOnSurface()) {
            for (Material m : s.getMaterial()) {
                if (newBOM.newMaterial(m)) {
                    Skeleton.printFuncRet("true");
                    Game.getInstance().setEnd(true);
                    return true;
                }
            }
        }
        Skeleton.printFuncRet("false");
        return false;
    }

    /**
     * az anyag visszahelyezésért felelos fv,
     * meghivja a location fillBy fv-ét this és Material m paraméterrel
     *
     * @param m ezt adjuk át paraméterként
     * @return boolean to_return ezzel térünk vissza
     */
    public boolean putMaterial(Material m) {
        return location.fillBy(this, m);
    }

    /**
     * anyag lekerdezes eseten ezt hivjuk meg a Ship-en, a materials listaval ter vissza
     *
     * @return ArrayList<Material> materials ezzel terunk vissza
     */
    public ArrayList<Material> getMaterial() {
        return materials;
    }

    /**
     * kapuk lekérdezése esetén hívódik meg
     *
     * @return ArrayList<Gate> a kapuk listájával tér vissza
     */
    public ArrayList<Gate> getGates() {
        return gates;
    }

    /**
     * anyag eltavolitas eseten ezt hivjuk meg a Ship-en,
     * a parameterkent kapott Material material objektumot eltavolitjuk a materials listabol
     *
     * @param material ezt adjuk át paraméterként
     */
    public void removeMaterial(Material material) {
        Skeleton.printFunc();
        materials.remove(material);
        Skeleton.printFuncRet("");
    }

    /**
     * anyag hozzaadas eseten ezt hivjuk meg a Ship-en,
     * a parameterkent kapott Material material objektumot hozzaadjuk a materials listahoz
     *
     * @param material ezt adjuk át paraméterként
     * @return boolean true ha kevesebb mint 10 anyag van nalunk ezért elfer
     * @return boolean false ha a lehetsegesnel tobb anyag van nalunk
     */
    public boolean addMaterial(Material material) {
        Skeleton.printFunc();

        Skeleton.printFuncRet("true");
        if (materials.size() < 10) {
            materials.add(material);
            return true;
        }
        return false;

    }

    /**
     * mozgasert felelos fv,
     * random kivalaszt egy a location aktualis szoszedjat, (kesobb ez player input lesz)
     * majd atallitja a locationjat arra, a megfelelo onSurface listakezelest pedig elvegzi
     *
     * @return boolean true ha sikerrel zajlott a mozgas
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean move() {
        Skeleton.printFunc();
        location.removeFlyingObject(this);
        Vars.DESTINATION.acceptFlyingObject(this);
        return true;

    }

    /**
     * meghalas eseten hivodik meg a fv,
     * eltavolitja a Game gameObjects listajarol es a location onSurface listajarol magat
     * es meghivja a decreaseNumShips fv-t
     */
    public void die() {
        Skeleton.printFunc();
        location.removeFlyingObject(this);
        Game.getInstance().removeGameObject(this);
        Game.getInstance().decreaseNumShips();
        Skeleton.printFuncRet("");
    }

    /**
     * felrobbanas eseten hivodik meg, meghivja a die fv-t
     */
    public void onExplode() {
        Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
    }

    /**
     * solar storm eseten hivodik meg, meghivja a die fv-t
     */
    public void onSolarStormCase() {
        die();
    }

    /**
     * kapu hozzaadas eseten ezt hivjuk meg a Ship-en,
     * a parameterkent kapott Gate newGate objektumot hozzaadjuk a gates listahoz
     *
     * @param newGate ezt adjuk át paraméterként
     */
    public void addGate(Gate newGate) {
        Skeleton.printFunc();
        gates.add(newGate);
        Skeleton.printFuncRet("");
    }

    /**
     * kapu eltavolitas eseten ezt hivjuk meg a Ship-en,
     * a parameterkent kapott Gate newGate objektumot kitoroljuk a gates listabol
     *
     * @param gate ezt adjuk át paraméterként
     */
    public void removeGate(Gate gate) {
        Skeleton.printFunc();
        gates.remove(gate);
        Skeleton.printFuncRet("");
    }

    /**
     * step fv ami minden körébe lefut
     * done lokalis valtozo false alapbol,
     * ha tud valamit csinalni akkor azt megcsinalja, és done true lesz,
     * ezeket switch casebol valaszthatjuk player inputtal ki
     * ha nem tud érvénytelen és visszatér
     */
    public void step() {
        updateView();
        SpaceGameView.refresh();
        Vars.TURN_DONE = false;

        while (!Vars.TURN_DONE) {

        }
        updateView();
    }


    public void updateView() {
        ArrayList<Field> listToDisplay = new ArrayList();
        listToDisplay.add(this.location);
        for (int i = 0; i < this.getLocation().neighbours.size(); i++) {
            listToDisplay.add(this.getLocation().neighbours.get(i));
        }
        SpaceGameView.addObjects(listToDisplay);
    }

    /**
     * hasznalja a kaput amin epp all
     * meghivja a location teleport fv-jét this paraméterrel
     *
     * @return boolean true ha sikeresen vegbe megy a teleport
     * @return boolean false ha a feltetel nem teljesul
     */
    public boolean useGate() {
        return location.teleport(this);
    }

    /**
     * anyag lekerdezes eseten ezt hivjuk meg a Ship-en,
     * lekerdezzuk a materials lista elemeit
     *
     * @return ArrayList<Material> materials ezzel terunk vissza
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }
}
