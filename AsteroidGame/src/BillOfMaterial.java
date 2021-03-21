import java.util.ArrayList;

public class BillOfMaterial {
	private ArrayList<Material> needed = new ArrayList<Material>();
	private ArrayList<Material> found = new ArrayList<Material>();

	public BillOfMaterial(ArrayList<Material> requires) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " BillOfMaterial constructor");
		needed = requires;
	}

	public boolean newMaterial(Material m) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		for (Material m2 : needed) {
			if (m.compatibleWith(m2)) {
				removeNeeded(m2);
				addFound(m);
			}
		}

		if (needed.isEmpty()) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns false");
		return false;

		/*
		 * boolean compatible = false; for(int i = 0; i < needed.size(); i++) {
		 * compatible = m.compatibleWith(needed.get(i)); if(compatible) {
		 * needed.remove(i); found.add(m); break; } } if(needed.size() == 0) return
		 * true; else return false;
		 */
	}

	public ArrayList<Material> getFound() {
		return found;
	}

	public void removeNeeded(Material removable) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public void addFound(Material f) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}
}
