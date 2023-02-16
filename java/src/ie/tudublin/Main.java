package ie.tudublin;

public class Main
{

	// public static void helloProcessing()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new HelloProcessing());
    // }

	// public static void loops()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new Loops());
    // }

	// public static void bugZap()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new BugZap());
    // }

	// public static void starMap()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new StarMap());
    // }
	
	public static void main(String[] args)
	{
		System.out.println("Hello world");
		
		// Dog penny = new Dog(); // creating an instance
		// penny.setName("Penny");
		// penny.speak();

		Cat ginger = new Cat();

		ginger.setLives();

		while(ginger.getNumLives() >= 0)
        {
            ginger.kill();
        }

		// starMap();
	}

}