import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigInteger;


public class Fibonacci 
{
	
	
	public static BigInteger algorithm1(int n)
	{
		if(n == 1) 
			return BigInteger.valueOf(1);
		if(n == 0)
			return BigInteger.valueOf(0);
		return (algorithm1(n-1).add(algorithm1(n-2)));

	}
	
	public static BigInteger algorithm2(int n)
	{
		BigInteger fn = BigInteger.valueOf(0);
		BigInteger fnMinus2 = BigInteger.valueOf(0);
		BigInteger fnMinus1 = BigInteger.valueOf(1);
		
		if(n == 1)
			return BigInteger.valueOf(1);
		
		if(n == 0)
			return BigInteger.valueOf(0);
		
		for(int i=2; i<=n; i++)
		{
			fn = fnMinus2.add(fnMinus1);
			fnMinus2 = fnMinus1;
			fnMinus1= fn;
			
						
		}
		
		return fn;
		
	}
	
	public static BigInteger algorithm3(int n)
	{
		if(n == 0)
			return BigInteger.valueOf(0);
		Matrix twoByTwo = new Matrix(BigInteger.valueOf(1),BigInteger.valueOf(1),BigInteger.valueOf(1),BigInteger.valueOf(0));
		twoByTwo.matrixPower(n-1);
		return (twoByTwo.getFirst());
	}
	
	public static void writeToFile1(long [] Write, int size)
	{
		try
		{
			PrintWriter A1 = new PrintWriter("Algorithm 1.txt");
			A1.println("test output");
			for(int i = 0; i<size; i++)
			{
				A1.println(Write[i]);
			}
			A1.close();
		}
		catch (IOException E)
		{
			System.out.println("IOException encountered, unable to write.");
		}
	}
	
	public static void writeToFile2(long [] Write, int size)
	{
		try
		{
			PrintWriter A2 = new PrintWriter("Algorithm 2.txt");
			A2.println("test output");
			for(int i = 0; i<size; i++)
			{
				A2.println(Write[i]);
			}
			A2.close();
		}
		catch (IOException E)
		{
			System.out.println("IOException encountered, unable to write.");
		}
	}
	
	public static void writeToFile3(long [] Write, int size)
	{
		try
		{
			PrintWriter A3 = new PrintWriter("Algorithm 1.txt");
			A3.println("test output");
			for(int i = 0; i<size; i++)
			{
				A3.println(Write[i]);
			}
			A3.close();
		}
		catch (IOException E)
		{
			System.out.println("IOException encountered, unable to write.");
		}
	}
	
	public static void main (String [] args)
	{
		long start1, finish1, duration1;
		long start2, finish2, duration2;
		long start3, finish3, duration3;
		int size = 40;
		long [] durationAlg1 = new long[size];
		long [] durationAlg2 = new long[size];
		long [] durationAlg3 = new long[size];
		BigInteger [] array1 = new BigInteger [size];
		BigInteger [] array2 = new BigInteger [size];
		BigInteger [] array3 = new BigInteger [size];
		
		
		
		
		
		System.out.println("The output and time of the first algorithm is:" );
		for(int i =0; i<size; i++)
		{
			start1 = System.nanoTime();
			array1[i] = algorithm1(i);
			
			finish1 = System.nanoTime();
			duration1 = finish1-start1;
			durationAlg1[i] = duration1;
			System.out.println(array1[i] + " " + "executed in " + duration1 + " ns");
			//System.out.println(duration1);
		}		
		
		System.out.println("\nThe output and time of the second algorithm is:" );
		for(int i =0; i<size; i++)
		{
			start2 = System.nanoTime();
			array2[i] = algorithm2(i);
			
			finish2 = System.nanoTime();
			duration2 = finish2-start2;
			durationAlg2[i] = duration2;
			System.out.println(array2[i] + " " + "executed in " + duration2 + " ns");
			//System.out.println(duration2);
		}
		
		System.out.println("\nThe output and time of the third algorithm is:" );
		for(int i =0; i<size; i++)
		{
			start3 = System.nanoTime();
			array3[i] = algorithm3(i);
			
			finish3 = System.nanoTime();
			duration3 = finish3-start3;
			durationAlg3[i] = duration3;
			System.out.println(array3[i] + " " + "executed in " + duration3 + " ns");
			//System.out.println(duration3);
		}
		
		writeToFile1(durationAlg1,size);
		writeToFile2(durationAlg2,size);
		writeToFile3(durationAlg3,size);
	}
	
}
