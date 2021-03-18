public class Ice extends NonRadioactiveMaterial
{
	public void onDrillSpecial(Asteroid a)
	{
		if(a.getInSunProximity())
			a.removeCore();
	}
}
