public class Solution {
    /*Logic:
    Based on the floodfill logic. Use a checkfill method to check if floodfill can be applied. Recursively return false if not. If yes, floodfill the area with 'X's, else leave it.
    This will fail as it is dfs. Better solution will be to use bfs for floodfill
    
    Note: Make sure you keep track of already visited cells, or else you'll get stack or memory overflow issues. 
    */
    
    /*public void solve(char[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='O'){
                    if(checkfill(i,j,board))
                        board[i][j]='X';
                    else
                        board[i][j]='#';
                }
            }
        }
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='#')
                    board[i][j]='O';
            }
        }
    }
    
    public boolean checkfill(int i,int j,char[][] board){
        if(i>=0 && i<board.length && j>=0 && j<board[0].length){
            if(board[i][j]=='O'){
                board[i][j]='*';
                if(checkfill(i-1,j,board) && checkfill(i+1,j,board) && checkfill(i,j+1,board) && checkfill(i,j-1,board)){
                    board[i][j]='X';
                    return true;
                }else{
                    board[i][j]='#';
                    return false;
                }
            }else
                return true;
        }else
            return false;
    }*/
    
    public void solve(char[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='O')
                    checkfill(i,j,board);
            }
        }
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='#')
                    board[i][j]='X';
                else if(board[i][j]=='1')
                        board[i][j]='O';
            }
        }
    }
    
    public void checkfill(int i,int j,char[][] board){
        int n = board.length, m = board[0].length;
        Queue<Integer> s = new LinkedList<Integer>();
        
        s.add(i*m+j);
        
        while(!s.isEmpty()){
            int cryptic_pos = s.remove();
            i = cryptic_pos/m;
            j = cryptic_pos%m;
            boolean flag = false;
            
            if(board[i][j]=='X' || board[i][j]=='#')
                continue;
            if(board[i][j]=='O'){
                board[i][j]='#';
                
                if(i-1>=0)
                    s.add((i-1)*m+j);
                else
                    flag = true;
                if(i+1<n)
                    s.add((i+1)*m+j);
                else
                    flag = true;
                if(j-1>=0)
                    s.add(i*m+(j-1));
                else
                    flag = true;
                if(j+1<m)
                    s.add(i*m+(j+1));
                else
                    flag = true;
            }
            if(flag){
                s.clear();
                floodfill(i, j, board); 
            }
        }
    }
    
    public void floodfill(int child_i,int child_j, char[][] board){
        int n = board.length, m = board[0].length;
        Queue<Integer> s = new LinkedList<Integer>();
        s.add(child_i*m + child_j);
        
        while(!s.isEmpty()){
            int cryptic_pos = s.remove();
            int i = cryptic_pos/m, j = cryptic_pos%m;
            if(i>=0 && i<n && j>=0 && j<m)
            {
                if(board[i][j]=='O' || board[i][j]=='#'){
                    board[i][j]='1';
                    s.add((i-1)*m+j);
                    s.add((i+1)*m+j);
                    s.add(i*m+(j-1));
                    s.add(i*m+(j+1));
                }
            }
        }
    }
}