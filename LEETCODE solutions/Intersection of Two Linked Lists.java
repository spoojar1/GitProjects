/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        /*LOGIC:
        Put nodes of each list into stack.
        Keep popping until you see a different node
        */
            
        Stack<ListNode> s1 = new Stack<ListNode>();
        Stack<ListNode> s2 = new Stack<ListNode>();
        
        while(headA!=null){
            s1.push(headA);
            headA = headA.next;
        }
        
        while(headB!=null){
            s2.push(headB);
            headB = headB.next;
        }
        
        ListNode result = null;
        while(!s1.empty() && !s2.empty()){
            ListNode node = s1.pop();
            if(node!=s2.pop())
                return result;
            
            result = node;
        }
        
        return result;
    }
}