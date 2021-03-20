public abstract class RadioactiveMaterial extends Material
{
	public void onDrillSpecial(Asteroid a)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("Asteroid sun proximate? y/n");
		String ans=Skeleton.getUserInput();
		if(ans.contains("y")) {
			a.explode();
			}
		else {
			
			}
		
		
			
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
}
