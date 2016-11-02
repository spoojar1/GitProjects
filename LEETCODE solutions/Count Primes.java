public class Solution {
    public int countPrimes(int n) {
        /*Logic
        IMP: Edges cases 0,1,2 return 0;
        Sieve Of Eratosthenes:
        for 15, array would be 3,4,5,6,7,8,9,10,11,12,13,14. Primes would be 3,5,7,11,13
        Trick is to take a number from 2 to sqrt(n) and keep on cancelling its multiples. So after 2, the array is 3,5,7,9,11,13
        After cancelling 3, the array is 3,5,7,11,13. Since ceiling sqrt(15) is 3.
        */
        
        if(n==0 || n==1 || n==2)
            return 0;
        
        int num[] = new int[n];
        for(int i=2;i<n;i++)
            num[i]=i;
        
        for(int i=2;i<=(int)(Math.sqrt(n));i++){
            if(num[i]!=0){
                int j=2*i;
                while(j<n){
                    num[j]=0;
                    j=j+i;
                }
            }
        }
        
        int count=0;
        for(int i=2;i<n;i++)
            if(num[i]!=0)
                count++;
        return count;
    }   
}