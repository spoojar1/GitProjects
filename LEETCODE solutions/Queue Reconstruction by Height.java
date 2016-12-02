public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        /*LOGIC:
        Sorting order: if we sort by ascending order of numbers, we wont be able to predict how many values are higher than that number. Hence, we sort by descending order. Now if we encounter similar numbers, obviously number with lower k first.
        Keep adding elements to result as far as they satisfy the condition. If the number of elements is > k, then we need to remove some elements and put it in the stack which will maintain the order of removal. If number of elements is < k, then pop elements from stack and include it in the result till the result satisfies the condition.
        */
        
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                if(p1[0] != p2[0])
                    return p2[0] - p1[0];
                else
                    return p1[1] - p2[1];
            }        
        });
        
        LinkedList<int[]> result = new LinkedList<int[]>();
        LinkedList<int[]> stack = new LinkedList<int[]>();
        
        for(int i=0;i<people.length;i++){
            int p[] = people[i];
            while(p[1]>result.size())
                result.push(stack.pop());
            while(p[1]<result.size())
                stack.push(result.pop());
            
            result.push(p);
        }
        
        while(stack.size()>0)
            result.push(stack.pop());
        
        int res[][] = new int[people.length][2];
        for(int i=0;i<people.length;i++)
            res[i] = result.pollLast();
            
        return res;
    }
}