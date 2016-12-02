public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        
        if(matrix.length==0)
            return res;
        
        int m = matrix.length-1, n = matrix[0].length-1;
        int start = 0, i=0, j=0;
        
        while(m>=0 && n>=0 && start<=m && start<=n){
            i = start;
            for(j=i;j<n;j++)
                res.add(matrix[i][j]);
            
            j = n;
            for(i=start;i<=m;i++)
                res.add(matrix[i][j]);
                
            i = m;
            if(i!=start)
                for(j=n-1;j>=start;j--)
                    res.add(matrix[i][j]);
            
            j = start;
            if(j!=n)
                for(i=m-1;i>start;i--)
                    res.add(matrix[i][j]);
                
            m--;
            n--;
            start++;
            
        }
        
        return res;
    }
}