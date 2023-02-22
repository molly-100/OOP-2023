package ie.tudublin;

public class Cat {

    String name;
    private int numLives;
	
	public void setLives()
	{
		this.numLives = 9;
	}

    public int getNumLives()
    {
        return numLives;
    }
	
	public Cat()
	{
        
	}
	
	public Cat(String name)
	{
        System.out.println(name);
	}

    public void kill()
    {
        if(numLives > 0)
        {
            System.out.println("Ouch");
        }
        else
        {
            System.out.println( "Dead");
        }
        numLives = numLives - 1;

    }

    
}



