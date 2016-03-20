/*Given a number n, calculate the number of co-primes with n between 1 to n.
Sample Input
5
1
4
7
9
12
Sample Output
1
2
6
6
4

Explanation (Reference: http://www.doc.ic.ac.uk/~mrh/330tutor/ch05s02.html)
Floating point solution
1) Initialize : result = n
2) Run a loop from 'p' = 2 to sqrt(n), do following for every 'p'.
     a) If p divides n, then 
           Set: result = result  * (1.0 - (1.0 / (float) p));
           Divide all occurrences of p in n.
3) Return result

Non-Floating point solution
1) Initialize result as n
2) Consider every number 'p' (where 'p' varies from 2 to √n). 
   If p divides n, then do following
   a) Subtract all multiples of p from 1 to n [all multiples of p
      will have gcd more than 1 (at least p) with n]
   b) Update n by repeatedly dividing it by p.
3) If the reduced n is more than 1, then remove all multiples
   of n from result.
   
*/

import java.util.*;

public class timepass {

	static int[] coprimeCount(int[] A) {
		int n = A.length;
		int res[] = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = helpMe(A[i]);
		}
		return res;
	}

	static int helpMe(int n) {
		int count = n;
		for (int i=2; i<= Math.sqrt(n);i++) {
			if (n%i==0) {
				while(n%i==0)
					n/=i;
				count-=count/i;
			}
		}
		if (n > 1)
			count-=count/n;
		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] res;

		int _A_size = Integer.parseInt(in.nextLine());
		int[] _A = new int[_A_size];
		int _A_item;
		for (int _A_i = 0; _A_i < _A_size; _A_i++) {
			_A_item = Integer.parseInt(in.nextLine());
			_A[_A_i] = _A_item;
		}

		res = coprimeCount(_A);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(res[res_i]);
		}
	}
}