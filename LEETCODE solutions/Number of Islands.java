public class Solution {
    int count=0;
    char temp[][];
    public int numIslands(char[][] grid) {
        temp = grid;
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[0].length;j++){
                if(temp[i][j]=='1'){
                    floodfill(i,j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void floodfill(int i,int j){
        if(i>=0 && i<temp.length && j>=0 && j<temp[0].length){
            if(temp[i][j]!='1')
                return;
            temp[i][j]='0';
            floodfill(i-1,j);
            floodfill(i+1,j);
            floodfill(i,j-1);
            floodfill(i,j+1);
        }
    }
}