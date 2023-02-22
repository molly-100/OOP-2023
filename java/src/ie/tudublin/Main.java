package ie.tudublin;

public class Main
{
	public static void loops()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Loops());
    }

	
	public static void main(String[] args)
	{
		System.out.println("Hello world");

		loops();
	}
	
}