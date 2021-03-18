public abstract class RadioactiveMaterial extends Material
{
	public void onDrillSpecial(Asteroid a)
	{
		if(a.getInSunProximity())
			a.explode();
	}
}
