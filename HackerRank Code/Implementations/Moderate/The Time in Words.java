import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int hr=s.nextInt();
        int min=s.nextInt();
        String s1[]={"zero","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
        String s2[]={"null","ten","twenty","thirty","forty","fifty"};
        int cat=0;
        if(min==0)
            cat=0;
        else
        	if(min<10)
        		cat=1;
        	else
        		if(min==15)
            		cat=6;
            	else
            		if(min<30)
            			cat=2;
            		else
            			if(min==30)
            				cat=3;
            			else
            				if(min==45)
            					cat=4;
            				else
            					if(min>30)
            						cat=5;
        
        String min_word="";
        if(min>30)
        	min=60-min;
        if(min<20)
        	min_word=s1[min];
        else
        	min_word=s2[min/10]+" "+s1[min%10];
        
        switch(cat){
            case 0: System.out.println(s1[hr]+" o' clock");
            		break;
            case 1: System.out.println(s1[min]+" minute past "+s1[hr]);
    				break;
            case 2: System.out.println(min_word+" minutes past "+s1[hr]);
    				break;
    		case 3: System.out.println("half past "+s1[hr]);
    				break;
    		case 4: System.out.println("quarter to "+s1[hr+1]);
    				break;
    		case 5: System.out.println(min_word+" minutes to "+s1[hr+1]);
    				break;
    		case 6: System.out.println("quarter past "+s1[hr]);
					break;
    		default:System.out.println("default");
        }
    }
}