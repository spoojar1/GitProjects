class Node{
    int pos, lvl;
    Node(int pos,int lvl){
        this.pos = pos;
        this.lvl = lvl;
    }
}
public class Solution {
    /*Logic:
    Apply simple bfs. Termination condition are that the next cell is less than what it could be next
    */
    public void wallsAndGates(int[][] rooms) {
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j]==0)
                    bfs(i,j,rooms);
            }
        }
    }
    public void bfs(int i,int j,int[][] rooms){
        int n = rooms.length, m = rooms[0].length;
        Queue<Node> q = new LinkedList<Node>();
        
        q.add(new Node(i*m+j,0));
        
        while(!q.isEmpty()){
            Node node = q.remove();
            i = node.pos/m;
            j = node.pos%m;
            int p_lvl = node.lvl;
            
            if(i-1>=0 && rooms[i-1][j]!=0){
                if(rooms[i-1][j]>p_lvl+1){
                    rooms[i-1][j]=p_lvl+1;
                    q.add(new Node((i-1)*m+j,p_lvl+1));
                }
            }
            if(i+1<n && rooms[i+1][j]!=0){
                if(rooms[i+1][j]>p_lvl+1){
                    rooms[i+1][j]=p_lvl+1;
                    q.add(new Node((i+1)*m+j,p_lvl+1));
                }
            }
            if(j-1>=0 && rooms[i][j-1]!=0){
                if(rooms[i][j-1]>p_lvl+1){
                    rooms[i][j-1]=p_lvl+1;
                    q.add(new Node(i*m+(j-1),p_lvl+1));
                }
            }
            if(j+1<m && rooms[i][j+1]!=0){
                if(rooms[i][j+1]>p_lvl+1){
                    rooms[i][j+1]=p_lvl+1;
                    q.add(new Node(i*m+(j+1),p_lvl+1));
                }
            }
        }
    }
}