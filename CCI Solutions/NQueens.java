import java.util.*;

public class NQueens {
	static int size=8;	//Replace this by Console input code
	static int count=0;
	static void NQueens(int row,int col[]){
		if(row==size){
			display(col);
			return;
		}
		for(int i=0;i<size;i++){
			if(checkValid(row,i,col)){
				col[row]=i;
				NQueens(row+1,col);
			}
		}
	}
	
	static void display(int col[]){
		System.out.println("Pattern "+count++);
		for(int j=0;j<size;j++){
			for(int i=0;i<col[j];i++)
				System.out.print(". ");
			System.out.print("Q ");
			for(int i=col[j]+1;i<size;i++)
				System.out.print(". ");
			System.out.println();
		}
		System.out.println();
	}
	static boolean checkValid(int row1,int col1,int col[]){
		for(int row2=0;row2<row1;row2++){
			if(col1==col[row2])
				return false;
			if((row1-row2)==Math.abs(col1-col[row2]))
				return false;
		}
		return true;
	}
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		NQueens(0,new int[size]);
    }
}