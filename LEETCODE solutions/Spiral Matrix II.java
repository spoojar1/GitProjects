class Solution {
    public int[][] generateMatrix(int n) {
        
        int result[][] = new int[n][n];
        int num = 1;
        for(int i=0; i<n/2;i++){
            for(int j=i;j<n-i-1;j++){
                result[i][j] = num;
                num++;
            }
            for(int j=i;j<n-i-1;j++){
                result[j][n-i-1] = num;
                num++;
            }
            for(int j=n-i-1;j>i;j--){
                result[n-i-1][j] = num;
                num++;
            }
            for(int j=n-i-1;j>i;j--){
                result[j][i] = num;
                num++;
            }
        }
        
        if(n%2 == 1){
            result[n/2][n/2]=n*n;
        }
        
        return result;
    }
}