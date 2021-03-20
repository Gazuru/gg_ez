public abstract class Material
{
	public void onDrillSpecial(Asteroid a){
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	public boolean compatibleWith(Material other)
	{
		return this.getClass() == other.getClass();
	}
}
