public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length * matrix[0].length;
        
        PriorityQueue<Integer> p = new PriorityQueue<Integer>(k+1, new Comparator<Integer>(){
            public int compare(Integer i, Integer j){
                return j.compareTo(i);
            }
        });
            
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                p.add(matrix[i][j]);
                if(p.size()==k+1)
                    p.poll();
            }
        }
        
        return p.peek();
    }
}