class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m==0)
            return 0;
        int n = grid[0].length;
        int islands = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    findIsland(i, j, grid);
                    islands++;
                }
            }
        }
        return islands;
    }
    
    public void findIsland(int i, int j, char[][] grid){
        if(i<0 || j<0 || i==grid.length || j==grid[0].length)
            return;
        if(grid[i][j] == '0' || grid[i][j] == 'a')
            return;
        grid[i][j] = 'a';
        
            findIsland(i-1, j, grid);
            findIsland(i+1, j, grid);
            findIsland(i, j-1, grid);
            findIsland(i, j+1, grid);
    }
}