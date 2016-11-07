public class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        /*
        LOGIC: for O(n), if update's start_index is 1 and end_index is 3, we know that we need to add 3 to indices 1 to 3 but not beyond 3. In case where we hold total updates at any point, we would need to deduct 3 going beyond 3, hence we write a -3 at 4th index
        */
        
        int result[] = new int[length];
        
        if(updates.length==0)
            return result;
        
        for(int i=0;i<updates.length;i++){
            result[updates[i][0]] +=  updates[i][2];
            if(updates[i][1]+1<length)
                result[updates[i][1]+1] -=  updates[i][2];
        }
        
        int update_sum = 0;
        for(int i=0;i<length;i++){
            update_sum += result[i];
            result[i] = update_sum;     
        }
        
        return result;
    }
}