import java.util.*;

public class Solution {
	static void almostSorted(int a[][]){
		int b[]=new int[a.length];
		for(int i=0;i<a.length;i++)
			b[i]=a[i][0];
		
		java.util.Arrays.sort(a, new java.util.Comparator<int[]>() {
		    public int compare(int[] a1, int[] a2) {
		        return Integer.compare(a1[0], a2[0]);
		    }
		});
		
		int k=0;
		while(k<a.length){
			if(a[k][0]!=b[k])
				break;
			k++;
		}
		if(k==a.length){
			System.out.println("yes");
			return;
		}
		
		int pos1=-1,pos2=-1,count=0;;
		for(int i=0;i<a.length;i++){
			if(a[i][1]!=i){
				count++;
				if(count>2)
					break;
				else{
					if(pos1==-1)
						pos1=i;
					else
						pos2=i;
				}
			}
		}
		if(count==2){
			System.out.println("yes\nswap "+(pos1+1)+" "+(pos2+1));
			return;
		}
		
		int pos0=-1;
		pos1=-1;
		pos2=-1;
		count=0;
		boolean flag=false;
		int i=1;
		while(i<b.length){
			if(count<1){
				if(!flag){
					if(b[i]<b[i-1]){
						if(pos0!=-1){
							if(b[i]<b[pos0]){
								System.out.println("no");
								return;
							}
						}
						flag=true;
						pos1=i-1;
					}
					else
						pos0=i-1;
				}else{
					if(b[i]<b[i-1]){
						if(pos0!=-1){
							if(b[i]<b[pos0]){
								System.out.println("no");
								return;
							}else
								pos2=i;
						}else
							pos2=i;
					}else{
						if(b[i]<b[pos1]){
							System.out.println("no");
							return;
						}else
							count++;
					}
				}
			}else{
				if(b[i]<b[i-1]){
					System.out.println("no");
					return;
				}
			}
			i++;
		}
		System.out.println("yes\nreverse "+(pos1+1)+" "+(pos2+1));
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int a[][]=new int[n][2];
    	for(int i=0;i<n;i++){
    		a[i][0]=s.nextInt();
    		a[i][1]=i;
    	}
    	almostSorted(a);
    }
}