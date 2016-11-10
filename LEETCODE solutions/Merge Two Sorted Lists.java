/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /*LOGIC:
        Keep first list intact and try to insert elements of list2 into list1.
        */
        
        ListNode head1 = null, prev1 = null, head2 = null, prev2 = null;
        
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
            
        //list 1 should always have first element smallest    
        if(l1.val < l2.val){
            head1 = l1;
            head2 = l2;
        }else{
            head1 = l2;
            head2 = l1;            
        }
        
        ListNode result = head1;    
        while(head1!=null && head2!=null){
            if(head1.val <= head2.val){         //=is for edgecase list1 [1] and list2[1]
                prev1 = head1;
                head1 = head1.next;
            }
            else{
                prev2= head2;
                head2 = head2.next;
                prev1.next = prev2;
                prev2.next = head1;
                prev1 = prev2;
            }
        }
        
        if(head1==null)     //happens when first list is traversed and still items present in list2
            prev1.next = head2;
        
        return result;
    }
}