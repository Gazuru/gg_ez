public class Ice extends NonRadioactiveMaterial
{
	public void onDrillSpecial(Asteroid a)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("Asteroid sun proximate? y/n");
		String ans=Skeleton.getUserInput();
		if(ans.contains("y")) {
			a.removeCore();
			}
		else {
			
			}
		
		
			
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
}
