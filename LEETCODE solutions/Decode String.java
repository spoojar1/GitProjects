public class Solution {
    public String decodeString(String s) {
        /*Logic:
        Traverse the string. Keep track of the first independent subproblem. This can be done by tracking the first opening bracket and finding its corresponding closing bracket and then recursively solving the substring between these brackets. Once this is done, the rest of the substring remaining is just another problem.
        */
        
        if(s.length()==1)
            return s;
            
        String result="", repeat_count="";
        int count=-1, brac_count=0, start=0, end=0, count_start=0;
        for(int i=0; i<s.length(); i++){
            char c=s.charAt(i);
            
            //track repeat_counts
            if((int)c>=48 && (int)c<=57){
                if(count==-1){
                    count=c-'0';
                    count_start=i;
                    repeat_count=String.valueOf(count);
                }else{
                    if(count_start==i-1){   //detect continuous numbers like 5 after 1 in the sequence 15[a]
                        count=c-'0';
                        count_start=i;
                        repeat_count += String.valueOf(count);  //for repeat counts more than a single digits like 15 or 100
                    }
                }
            }
            else{
                //first number not encountered yet
                if(count==-1){
                    result += String.valueOf(c);
                    continue;
                }
                    
                if(c=='['){
                    brac_count++;
                    if(brac_count==1)   //track first opening bracket
                        start=i;
                }
                if(c==']'){
                    brac_count--;
                    if(brac_count==0){  //match opening bracket with its corresponding closing brakcet and recursively solve the subproblem
                        end=i;
                        result += repeatString(decodeString(s.substring(start+1,end)),Integer.parseInt(repeat_count));
                    
                        //resetting the counters for the next subproblem
                        count=-1;
                        brac_count=0;
                        start=0;
                        end=0;
                        repeat_count="";
                        count_start=0;
                    }
                }
            }
        }
        
        return result;
    }
    
    //repeat string s "num" number of times
    public String repeatString(String s, int num){
        String result="";
        while(num>=1){
            result += s;
            num--;
        }
        return result;
    }
}