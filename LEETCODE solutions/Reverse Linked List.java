/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode p1, p2, p3;
        
        if(head==null || head.next==null)
            return head;
        
        p1 = null;
        p2 = head;
        p3 = head.next;
        
        while(p2!=null){
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            if(p3!=null)
                p3 = p3.next;
        }
        
        return p1;
    }
}