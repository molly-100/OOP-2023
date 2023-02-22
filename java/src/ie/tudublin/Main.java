package ie.tudublin;

public class Main
{	
	public static void main(String[] args)
	{
		System.out.println("Hello world");

		Cat ginger = new Cat();

		ginger.setLives();

		while(ginger.getNumLives() >= 0)
        {
            ginger.kill();
        }
	}

}