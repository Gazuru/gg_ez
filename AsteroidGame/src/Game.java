import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game implements Steppable
{
	private ArrayList<FlyingObject> gameObjects;
	private int numShips;
	private ArrayList<Field> fields;
	
	public void removeGameObject(FlyingObject fo)
	{
		gameObjects.remove(fo);
	}
	
	public void decreaseNumShips()
	{
		numShips--;
	}
	
	public void removeField(Field f)
	{
		fields.remove(f);
	}
	
	public void step()
	{
		for(int i = 0; i < gameObjects.size(); i++)
			gameObjects.get(i).step();
		if(solarStorm())
		{
			for(int i = 0; i < fields.size(); i++)
				fields.get(i).onSolarStorm();
		}
	}
	
	public boolean solarStorm()
	{
		Random random = new Random();
		int rand = random.nextInt(20);
		if(rand == 10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void startGame()
	{
		initGame();
		while(true) //END
			step();
	}
	
	public void initGame()
	{
		System.out.println("Mekkora legyen a pálya?");
		Scanner s = new Scanner(System.in);
		int choose = s.nextInt();
		s.close();
		while(fields.size() != choose)
		{
			Asteroid newAsteroid= new Asteroid();
			Random random = new Random();
			int rand = random.nextInt(4);
			switch(rand)
			{
				case 0:
					Coal c = new Coal();
					newAsteroid.acceptCore(c);
					break;
				case 1:
					Iron ir = new Iron();
					newAsteroid.acceptCore(ir);
					break;
				case 2:
					Ice i = new Ice();
					newAsteroid.acceptCore(i);
					break;
				case 3:
					Uranium u = new Uranium();
					newAsteroid.acceptCore(u);
					break;
			}
			addField(newAsteroid);
		}
		System.out.print("Játékosok száma: ");
		Scanner s2 = new Scanner(System.in);
		choose = s2.nextInt();
		s2.close();
		numShips = choose;
		while(gameObjects.size() != numShips)
		{
			Ship newShip = new Ship();
			Random r = new Random();
			int nr = r.nextInt(fields.size());
			newShip.setLocation(fields.get(nr));
			addGameObject(newShip);
		}
	}
	
	public void addGameObject(FlyingObject fo)
	{
		gameObjects.add(fo);
	}
	
	public void addField(Field f)
	{
		fields.add(f);
	}
	
	public static ArrayList<Material> craftBaseReq()
	{
		ArrayList<Material> baseReq = new ArrayList<Material>();
		Coal c1 = new Coal();
		Coal c2 = new Coal();
		Coal c3 = new Coal();
		Iron ir1 = new Iron();
		Iron ir2 = new Iron();
		Iron ir3 = new Iron();
		Ice i1 = new Ice();
		Ice i2 = new Ice();
		Ice i3 = new Ice();
		Uranium u1 = new Uranium();
		Uranium u2 = new Uranium();
		Uranium u3 = new Uranium();
		baseReq.add(c1);
		baseReq.add(c2);
		baseReq.add(c3);
		baseReq.add(ir1);
		baseReq.add(ir2);
		baseReq.add(ir3);
		baseReq.add(i1);
		baseReq.add(i2);
		baseReq.add(i3);
		baseReq.add(u1);
		baseReq.add(u2);
		baseReq.add(u3);
		return baseReq;
	}
}
