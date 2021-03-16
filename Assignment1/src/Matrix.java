import java.math.BigInteger;

public class Matrix 
{
	private BigInteger a,b,c,d;
	
	public Matrix(BigInteger a, BigInteger b, BigInteger c, BigInteger d)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public BigInteger getFirst()
	{
		return this.a;
	}
	
	public void Multiply(BigInteger a, BigInteger b, BigInteger c, BigInteger d)
	{
		this.a = a.multiply(a).add(c.multiply(b));
		this.b = a.multiply(b).add(b.multiply(d));
		this.c = a.multiply(c).add(d.multiply(c));
		this.d = c.multiply(b).add(d.multiply(d));
	}
	
	public void MultiplyOdd(Matrix other, BigInteger a, BigInteger b, BigInteger c, BigInteger d)
	{
		this.a = a.multiply(other.a).add(c.multiply(other.b));
		this.b = a.multiply(other.b).add(b.multiply(other.d));
		this.c = a.multiply(other.c).add(d.multiply(other.c));
		this.d = c.multiply(other.b).add(d.multiply(other.d));
	}
	
	public void matrixPower(int n)
	{
		Matrix odd = new Matrix(BigInteger.valueOf(1),BigInteger.valueOf(1),BigInteger.valueOf(1),BigInteger.valueOf(0));
		if(n>1)
		{
			this.matrixPower(n/2);
			this.Multiply(this.a,this.b,this.c,this.d);
			if((n%2) != 0)
			{
				this.MultiplyOdd(odd,this.a,this.b,this.c,this.d);
			}
		}
	}
	
	
}
