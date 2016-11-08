/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int mid = 0, start = 1, end = n;
        while(true){
            mid = (int)(((long)start + end)/2);     //sum may go beyond int so need to typecast
            int res = guess(mid);
            if(res==0)
                return mid;
            
            if(res<0)
                end = mid - 1;
            else
                start = mid + 1;
        }
    }
}