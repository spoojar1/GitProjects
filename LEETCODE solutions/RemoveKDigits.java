public class RemoveKDigits {
    public static String left_trim(String num){
		String temp="";
		int i=0;
		while(i<num.length()){
			if(num.charAt(i)!='0')
				break;
			i++;
		}
		return num.substring(i).equals("")?"0":num.substring(i);
	}
	
    public static String buildLowestNumberRec(String str, int k, String res) {
        
    	int len = str.length();
        //System.out.println(res+" "+len+" "+k);
        if(k == 0){
            res += str;
            return left_trim(res);
        }
        
        if (len <= k)
        	return left_trim(res);
        //System.out.println(res+" "+len+" "+k);
        int minIndex = 0;
        for (int i = 1; i<=k ; i++)
            if (str.charAt(i) < str.charAt(minIndex))
                minIndex = i;
     
        // Append the smallest character to result
        res += String.valueOf(str.charAt(minIndex));
        
        String new_str = str.substring(minIndex+1);
        //System.out.println("Hi"+minIndex+" "+new_str);
        return buildLowestNumberRec(new_str, k-minIndex, res);
    }
    public String removeKdigits(String num, int k) {
        String res = "";
    	 
        // Note that result is passed by reference
        res = buildLowestNumberRec(num, k, res);
     
        return res;
    }
}