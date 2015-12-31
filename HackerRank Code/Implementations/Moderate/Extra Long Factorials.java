import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static BigInteger fact(BigInteger n)
	{
		if(n.equals(new BigInteger("1")) || n.equals(new BigInteger("0")))
			return new BigInteger("1");
		else
			return n.multiply(fact(n.subtract(new BigInteger("1"))));
	}
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		System.out.println(fact(new BigInteger(String.valueOf(n))));
	}
}