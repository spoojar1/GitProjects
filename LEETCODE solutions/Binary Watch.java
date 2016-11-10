public class Solution {
    /*List<String> list = new ArrayList<String>();
    public List<String> readBinaryWatch(int num) {
        if(num==0){
            list.add("0:00");
            return list;
        }
            
        int binary[] = new int[10];
        helper(0, num, binary);
        
        return list;
    }
    
    public void helper(int pos, int num, int binary[]){
        if(pos + num > 10)
            return;
        
        if(num==0)
            convWatch(binary);
        
        int temp[] = Arrays.copyOf(binary,binary.length);    
        for(int i=pos;i<10;i++){
            temp[i] = 1;
            helper(i+1, num-1, temp);
            temp[i] = 0;
        }
    }
    
    public void convWatch(int binary[]){
        String result = "";
        for(int i=0;i<4;i++)
            result += String.valueOf(binary[i]);
        
        int hour = Integer.parseInt(result,2);
        if(hour>11)
            return;
            
        result = "";
        for(int i=4;i<10;i++)
            result += String.valueOf(binary[i]);
        
        int min = Integer.parseInt(result,2);
        if(min>59)
            return;
        
        result = String.valueOf(hour) + ":" + (String.valueOf(min).length()==2?String.valueOf(min):"0"+String.valueOf(min));
        list.add(result);    
    }*/
    
    /*Above solution is recursive solution. Below is a more elegant one*/
    
    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<String>();
        for(int i=0;i<12;i++){
            for(int j=0;j<60;j++){
                int count = count1s(i) + count1s(j);
                if(count == num){
                    String result = String.valueOf(i)+":";
                    if(j<10)
                        result += "0"+String.valueOf(j);
                    else
                        result += String.valueOf(j);
                        
                    list.add(result);    
                }
            }
        }
        
        return list;
    }
    
    public int count1s(int num){
        int mask = 1;
        int count=0;
        while(num!=0){
            if((mask&num)==1)
                count++;
            num = num>>1;
        }
        
        return count;
    }
}