/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null)
            return head;
        
        ListNode f1 = head, f2 = head.next, prev = null;
        
        head = f2;
        while(f2!=null){
            f1.next = f2.next;
            f2.next = f1;
            
            if(prev!=null)
                prev.next = f2;
            
            prev = f1;
            
            f1 = f1.next;
            if(f1!=null)
                f2 = f1.next;
            else
                f2 = null;
                
        }    
        
        return head;
    }
}