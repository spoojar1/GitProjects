import java.util.*;

public class CuttingBoards {
	static long boardCutting(long cx[],long cy[]){
		long cost=0;
		Arrays.sort(cx);
		Arrays.sort(cy);
		int i=cy.length-1,j=cx.length-1,h=1,v=1;
		while(i>-1 && j>-1){
			if(cy[i]>=cx[j]){
				if(cy[i]>cx[j]){
					cost=(long) ((cost+(cy[i]*v))%(Math.pow(10, 9)+7));
					h++;
					i--;
				}else{
					if(v<h){
						cost=(long) ((cost+(cy[i]*v))%(Math.pow(10, 9)+7));
						h++;
						i--;
					}else{
						cost=(long) ((cost+(cx[j]*h))%(Math.pow(10, 9)+7));
						v++;
						j--;
					}
				}
			}else{
				cost=(long) ((cost+(cx[j]*h))%(Math.pow(10, 9)+7));
				v++;
				j--;
			}
		}
		if(i==-1){
			while(j>-1){
				cost=(long) ((cost+(cx[j]*h))%(Math.pow(10, 9)+7));
				v++;
				j--;
			}
		}else{
			while(i>-1){
				cost=(long) ((cost+(cy[i]*v))%(Math.pow(10, 9)+7));
				h++;
				i--;
			}
		}
		return cost;
	}
   public static void main(String[] args){
	   Scanner s=new Scanner(System.in);
	   int m,n,t=s.nextInt();
	   long cx[],cy[];
	   for(int i=0;i<t;i++){
		   m=s.nextInt();
		   n=s.nextInt();
		   cy=new long[m];
		   cx=new long[n];
		   for(int j=0;j<m-1;j++)
			   cy[j]=s.nextInt();
		   for(int j=0;j<n-1;j++)
			   cx[j]=s.nextInt();
		   System.out.println(boardCutting(cx,cy));
	   }
   }
}
