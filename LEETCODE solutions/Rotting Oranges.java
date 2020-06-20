class Solution {
    public static int orangesRotting(int[][] grid) {
        int gridCopy[][] = copyArray(grid);
        int m = grid.length;
        int n = grid[0].length;
        int mins = 0;
        while(true){
            if(isCompletelyRotten(grid)){
                return mins;
            }
            
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(gridCopy[i][j]==2){
                        if(i>0 && grid[i-1][j]!=0)
                            grid[i-1][j] = 2;
                        if(j>0 && grid[i][j-1]!=0)
                            grid[i][j-1] = 2;
                        if(j<n-1 && grid[i][j+1]!=0)
                            grid[i][j+1] = 2;
                        if(i<m-1 && grid[i+1][j]!=0)
                            grid[i+1][j] = 2;
                    }
                }
            }
            mins ++;
            
            if(areGridsSame(grid, gridCopy)){
                return -1;
            }

            gridCopy = copyArray(grid);
        }
    }

    public static boolean isCompletelyRotten(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1)
                    return false;
            }
        }
        return true;
    }

    public static boolean areGridsSame(int[][] grid, int[][] gridCopy){
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]!=gridCopy[i][j])
                    return false;
            }
        }
        return true;
    }

    public static int[][] copyArray(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int gridCopy[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                gridCopy[i][j] = grid[i][j];
            }
        }
        return gridCopy;
    }

}