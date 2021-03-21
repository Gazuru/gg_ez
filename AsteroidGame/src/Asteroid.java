import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Asteroid extends Field
{
	private int layer;
	private Material core;
	private boolean inSunProximity;
	
	public Asteroid()
	{
		
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setNumber(int x)
	{
		number = x;
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public boolean getInSunProximity()
	{
		return inSunProximity;
	}
	public void setInSunProximity(Boolean inSunProximity)
	{
		this.inSunProximity=inSunProximity;
	}
	
	public boolean onDrill()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("layer>1? y/n");
		String ans=Skeleton.getUserInput();
		
		if(ans.contains("y")) {
			this.decreaseLayer();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
			
			return true;
		} else {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
			return false;
		}
	}
	
	public void acceptCore(Material newCore)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void setCore(Material newCore)
	{
		core = newCore;
	}
	
	
	public void removeCore()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void decreaseLayer()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void explode()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		for(FlyingObject f:onSurface){
			f.onExplode();
		}
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public void onSolarStorm()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("el tud bujni? y/n");
		String ans=Skeleton.getUserInput();
		if(ans.contains("n")) {
			for(FlyingObject f:onSurface){
				f.onSolarStormCase();
			}
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns");
	}
	
	public boolean fillBy(Ship ship,Material m)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("layer==0 és üres jelenleg? y/n");
		String ans=Skeleton.getUserInput();
		
		if(ans.contains("y"))
		{
			acceptCore(m);
			ship.removeMaterial(m);
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
			return true;
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
		return false;
	}
	
	public boolean onMine(Ship ship)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		System.out.println("át van fúrva és van benne nyersanyag? y/n");
		String ans=Skeleton.getUserInput();
		
		if(ans.contains("n"))
		{
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
			return false;
		}
		if(!ship.addMaterial(core)) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
			return false;
		}else {
		removeCore();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns true");
		return true;
		}
	}
	
	public boolean teleport(Ship ship)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
		return false;
	}
	
	public boolean pickedUpBy(Ship ship)
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" returns false");
		return false;
	}
}
