/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return null;
            
        ListNode slow = head;
        ListNode fast = head.next.next;
        
        //loop detected
        while(slow!=fast){
            if(fast.next==null || fast.next.next == null)
                return null;
            else
                fast = fast.next.next;
            
            slow = slow.next;
        }
        
        //count loop length
        int count = 0;
        do{
            fast = fast.next.next;
            slow = slow.next;
            count++;
        }while(slow!=fast);
        
        //move fast pointer count positions ahead of slow
        slow = head;
        fast = head;
        while(count>0){
            fast = fast.next;
            count--;
        }
        
        //move bot pointer by 1 step and you will reach the entry point of the loop
        while(fast!=slow){
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}