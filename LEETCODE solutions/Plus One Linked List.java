/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode plusOne(ListNode head) {
        if(head==null)
            return head;
            
        int carry = helper(head);
        if(carry==1){
            ListNode start = new ListNode(carry);
            start.next = head;
            head = start;
        }
        return head;
    }
    
    public int helper(ListNode head){
        if(head==null)
            return 1;
        
        head.val += helper(head.next);
        
        if(head.val>=10){
            int carry = head.val/10;
            head.val %= 10;
            return carry;
        }else
            return 0;    
        
    }
}