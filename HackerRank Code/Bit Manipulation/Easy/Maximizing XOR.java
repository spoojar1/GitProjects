import java.util.*;

public class Solution {
	static int maxXOR(int l,int r){
		int max=-1,tmp;
		for(int i=l;i<r;i++){
			for(int j=l+1;j<=r;j++){
				tmp=i^j;
				if(tmp>max)
					max=tmp;
			}
		}
		return max;
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int l=s.nextInt();
    	int r=s.nextInt();
    	System.out.println(maxXOR(l,r));
    }
}