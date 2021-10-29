public class MyMath
{
	public static double sqrt(double d)
	{
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
			return (l+u)/2 * Math.pow(2, count);

		return (l+u)/2;
	}

	public static void main(String[] args)
	{
		double rand;
		double times = 10000000;

		double rstartTime = System.currentTimeMillis();
		for (int i=0; i<times; i++)
		{
			rand = 100 * Math.random();
		}
		double rcurrentTime = System.currentTimeMillis();
		double rtime = rcurrentTime - rstartTime;


		double mystartTime = System.currentTimeMillis();
		for (int i=0; i<times; i++)
		{
			rand = 100 * Math.random();
			MyMath.sqrt(rand);
		}
		double mycurrentTime = System.currentTimeMillis();
		double mytime = (mycurrentTime - mystartTime - rtime) / times;


		double startTime = System.currentTimeMillis();
		for (int i=0; i<times; i++)
		{
			rand = 100 * Math.random();
			Math.sqrt(rand);
		}
		double currentTime = System.currentTimeMillis();
		double time = (currentTime - startTime - rtime) / times;

		System.out.println("Average time for MyMath.sqrt is " + mytime);
		System.out.println("Average time for Math.sqrt is " + time);
	}

}