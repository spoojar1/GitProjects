public class Solution {
    
    public void gameOfLife(int[][] board) {
        
        if(board.length==0)
            return;
            
        int row = board.length;
        int col = board[0].length;
        
        //setting up helper arrays for lookup
        int x[] = {-1,-1,-1, 0,0, 1,1,1};
        int y[] = {-1, 0, 1,-1,1,-1,0,1};
        
        //temporary array to hold updated values
        int new_board[][]=new int[board.length][];
        for(int i=0; i<board.length; i++)
            new_board[i]=Arrays.copyOf(board[i], board[i].length);
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                
                int count=0;
                //count live neighbours 
                for(int k=0; k<8; k++){
                    int tmp_x = i + x[k];
                    int tmp_y = j + y[k];
                    
                    //boundary conditions
                    if(tmp_x>=0 && tmp_x<row && tmp_y>=0 && tmp_y<col){
                        if(board[tmp_x][tmp_y]==1)
                            count++;
                    }
                }
                
                //dies
                if(count < 2)
                    new_board[i][j] = 0;
                    
                //live cell lives, dead cell remains dead
                if(count==2 || count==3)
                    new_board[i][j] = board[i][j] & 1;
                    
                //dies
                if(count > 3)
                    new_board[i][j] = 0;
                
                //dead becomes live, live remains live
                if(count == 3)
                    new_board[i][j] = 1;
                    
            }
        }
        
        //copy changed array into original array
        for(int i=0;i<new_board.length;i++)
            board[i]=Arrays.copyOf(new_board[i], new_board[i].length);
    }
}