public abstract class Material {
	public void onDrillSpecial(Asteroid a) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns");
	}

	public boolean compatibleWith(Material other) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		if (this.getClass() == other.getClass()) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " returns false");
		return false;

	}
}
