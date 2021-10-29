import java.util.Scanner;

public class Try
{
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		double d = reader.nextDouble();
		double l = 0.0;
		double u = 1.0;
		int count = 0;
		boolean big = false;


		while (d>1)
		{
			big = true;
			d = d/4;
			count++;
		}

		while (d > 0 && d < 1 && u-l>=Math.pow(10,-10))
		{
			double m = (l+u)/2;
			if(d <= m*m)
				u = m;
			else
				l = m;
		}
		
		if (big == true)
			System.out.println ((l+u)/2 * Math.pow(2, count));

		System.out.println((l+u)/2); 

	}
}