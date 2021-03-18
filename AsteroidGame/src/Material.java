public abstract class Material
{
	public void onDrillSpecial(Asteroid a){}
	public boolean compatibleWith(Material other)
	{
		return this.getClass() == other.getClass();
	}
}
