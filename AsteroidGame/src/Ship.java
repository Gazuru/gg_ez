import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ship extends FlyingObject {
    private ArrayList<Gate> gates = new ArrayList<Gate>();
    private ArrayList<Material> materials = new ArrayList<Material>();


    public Ship() {
        ArrayList<Field> locations = Game.getInstance().getFields();
        if (!locations.isEmpty()) {
            locations.get(new Random().nextInt(locations.size())).acceptFlyingObject(this);
        } else {
            System.out.println("Nincs aszteroida a jatekban!");
        }
        Game.getInstance().incrNumShips();
    }

    public boolean mine() {
        Skeleton.printFunc();
        //System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns " + completed);
        return location.onMine(this);
    }

    public boolean craftGatePair() {
        Skeleton.printFunc();

        /*System.out.println("Kapuk sz�ma >0? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("n")) {
            ArrayList<Material> needed = Gate.craftGateReq();
            BillOfMaterial newBOM = new BillOfMaterial(needed);

            for (Material m : materials) {
                if (newBOM.newMaterial(m)) {
                    for (Material m2 : newBOM.getFound()) {
                        removeMaterial(m2);
                    }
                    Gate g1 = new Gate();
                    Gate g2 = new Gate();
                    g1.setPair(g2);
                    g2.setPair(g1);
                    addGate(g1);
                    addGate(g2);
                    Skeleton.printFuncRet("true");
                    return true;
                }
            }
        }
        Skeleton.printFuncRet("false");
        return false;*/

        if (gates.size() < 2) {
            ArrayList<Material> needed = Gate.craftGateReq();
            BillOfMaterial bom = new BillOfMaterial(needed);

            for (Material m : materials) {
                if (bom.newMaterial(m)) {
                    for (Material m2 : bom.getFound()) {
                        removeMaterial(m2);
                    }
                    /*Gate g1 = new Gate();
                    Gate g2 = new Gate();
                    g1.setPair(g2);
                    g2.setPair(g1);
                    addGate(g1);
                    addGate(g2);*/
                    return true;
                }
            }
        }
        return false;
    }

    public boolean placeGate() {
        /*Skeleton.printFunc();
        System.out.println("gates.size>0? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("y")) {
            ArrayList<Field> neighbourFields = location.getNeighbours();
            for (Field f : neighbourFields) {
                f.addNeighbour(gates.get(0));
                gates.get(0).addNeighbour(f);
            }
            gates.get(0).setWorking(true);
            location.addNeighbour(gates.get(0));
            gates.get(0).addNeighbour(location);
            this.removeGate(gates.get(0));
            Skeleton.printFuncRet("true");
            return true;
        } else {
            Skeleton.printFuncRet("false");
            return false;
        }*/

        if (gates.size() > 0) {
            ArrayList<Field> neigboursFields = location.getNeighbours();
            for (Field f : neigboursFields) {
                f.addNeighbour(gates.get(0));
            }
            gates.get(0).setWorking(true);
            gates.get(0).addNeighbour(location);
            removeGate(gates.get(0));
            return true;
        }
        return false;
    }

    public boolean pickUpGate() {
        /*Skeleton.printFunc();
        System.out.println("2>gates? y/n");
        String ans = Skeleton.getUserInput();

        if (ans.contains("y")) {
            boolean ok = location.pickedUpBy(this);
            if (ok) {

                if (move()) {

                    ArrayList<Field> neighbours_tmp = location.getNeighbours();
                    for (Field f : neighbours_tmp) {
                        f.removeNeighbour(location);
                    }


                    System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns true");
                    return true;
                }
            }
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns false");
            return false;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns true");
        return true;*/
        if (gates.size() < 3) {
            return location.pickedUpBy(this);
        }
        return false;
    }

    public boolean buildRobot() {
        ArrayList<Material> needed = Robot.craftRobotReq();
        BillOfMaterial newBOM = new BillOfMaterial(needed);

        for (Material m : materials) {
            if (newBOM.newMaterial(m)) {
                for (Material m2 : newBOM.getFound()) {
                    removeMaterial(m2);
                }
                /*Robot r = new Robot();
                location.acceptFlyingObject(r);*/
                Skeleton.printFuncRet("true");
                return true;
            }
        }
        Skeleton.printFuncRet("false");
        return false;
    }

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

    public boolean putMaterial(Material m) {
        Skeleton.printFunc();
        boolean to_return = location.fillBy(this, m);
        Skeleton.printFuncRet(Boolean.toString(to_return));
        return to_return;
    }

    public ArrayList<Material> getMaterial() {
        return materials;
    }

    public void removeMaterial(Material material) {
        Skeleton.printFunc();
        materials.remove(material);
        Skeleton.printFuncRet("");
    }

    public boolean addMaterial(Material material) {
        Skeleton.printFunc();

        /*System.out.println("kevesebb, mint 10 dolog van n�la inventoryban? y/n");
        String ans2 = Skeleton.getUserInput();

        if (ans2.contains("n")) {
            Skeleton.printFuncRet("false");
            return false;
        }*/

        Skeleton.printFuncRet("true");
        if (materials.size() < 10) {
            materials.add(material);
            return true;
        }
        return false;

    }

    public boolean move() {
        Skeleton.printFunc();
        Field neighbour_asteroid = location.getNeighbours().get(0);
        /*System.out.println("van szomszed amire tud menni? y/n");
        String ans3 = Skeleton.getUserInput();
        if (ans3.contains("y")) {
            location.removeFlyingObject(this);
            neighbour_asteroid.acceptFlyingObject(this);
            Skeleton.printFuncRet("true");
            return true;
        }
        Skeleton.printFuncRet("false");
        return false;*/
        location.removeFlyingObject(this);
        neighbour_asteroid.acceptFlyingObject(this);
        return true;

    }

    public void die() {
        Skeleton.printFunc();
        location.removeFlyingObject(this);
        Game.getInstance().removeGameObject(this);
        Game.getInstance().decreaseNumShips();
        Skeleton.printFuncRet("");
    }

    public void onExplode() {
        Skeleton.printFunc();
        die();
        Skeleton.printFuncRet("");
    }

    public void onSolarStormCase() {
        die();
    }

    public void addGate(Gate newGate) {
        Skeleton.printFunc();
        gates.add(newGate);
        Skeleton.printFuncRet("");
    }

    public void removeGate(Gate gate) {
        Skeleton.printFunc();
        gates.remove(gate);
        Skeleton.printFuncRet("");
    }

    public void step() {
        boolean done = false;
        while (!done) {
            System.out.println("Mit szeretn�l csin�lni?");
            System.out.println(
                    "1:Move 2:Drill 3:Mine 4:Teleport 5:BuildBase 6:BuildRobot 7:BuildGate 8:PickUpGate 9:PutMaterial");
            Scanner s = new Scanner(System.in);
            int choose = s.nextInt();
            // case 9: done = putMaterial(); break;
            switch (choose) {
                case 1:
                    done = move();
                    break;
                case 2:
                    done = drill();
                    break;
                case 3:
                    done = mine();
                    break;
                case 4:
                    done = useGate();
                    break;
                case 5:
                    done = buildBase();
                    break;
                case 6:
                    done = buildRobot();
                    break;
                case 7:
                    done = craftGatePair();
                    break;
                case 8:
                    done = pickUpGate();
                    break;
                default:
                    System.out.println("�rv�nytelen!");
                    break;
            }
        }
        System.out.println();
    }

    public boolean useGate() {
        return location.teleport(this);
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }
}
