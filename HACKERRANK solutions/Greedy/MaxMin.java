import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// The part of the program involving reading from STDIN and writing to STDOUT has been provided by us.

public class MaxMin {
    
   public static void main(String[] args) throws NumberFormatException, IOException {

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(in.readLine());
      int K = Integer.parseInt(in.readLine());
      int[] list = new int[N];

      for(int i = 0; i < N; i ++)
         list[i] = Integer.parseInt(in.readLine());
      
      int unfairness = Integer.MAX_VALUE;
      
      /*
       * Write your code here, to process numPackets N, numKids K, and the packets of candies
       * Compute the ideal value for unfairness over here
      */
      Arrays.sort(list);
      int j;
      for(int i=0;i<N-K+1;i++){
    	  j=i+K-1;
    	  if(list[j]-list[i]<unfairness)
    		  unfairness=list[j]-list[i];
      }
      System.out.println(unfairness);
   }
}
