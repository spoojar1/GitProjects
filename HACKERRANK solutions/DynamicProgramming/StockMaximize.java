import java.util.*;

public class StockMaximize {
	public static long calcProfit(int a[]) {
		long prof = 0, max = 0, stock_count = 0;
		int b[] = new int[a.length];
		for (int i = a.length - 1; i >= 0; i--) {
			if (a[i] <= max)
				b[i] = 1;
			else
				max = a[i];
			prof += max - a[i];
		}
		/*
		 * for(int i=0;i<b.length;i++){ if(b[i]==1){ stock_count++; prof-=a[i];
		 * }else{ prof+=(stock_count*a[i]); stock_count=0; } }
		 */
		return prof;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt(), n, a[];
		for (int i = 0; i < t; i++) {
			n = s.nextInt();
			a = new int[n];
			for (int j = 0; j < n; j++)
				a[j] = s.nextInt();
			System.out.println(calcProfit(a));
		}
	}
}