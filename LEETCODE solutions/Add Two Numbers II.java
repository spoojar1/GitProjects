/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
            
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
    
        int sum = 0, carry = 0;
        ListNode result = null, head = null, prev = null;
        while(l1!=null && l2!=null){
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            
            result = new ListNode(sum);
            if(head==null){
                head = result;
                prev = head;
            }
            else{
                prev.next = result;
                prev = prev.next;
            }
            
            l1 = l1.next;
            l2 = l2.next;
        }
        
        if(l1==null && l2==null){
            if(carry>0){
                result = new ListNode(carry);
                prev.next = result;
            }
            
            return reverseLL(head);
        }
        
        ListNode temp = null;
        if(l1==null)
            temp = l2;
        if(l2==null)
            temp = l1;
        
        while(temp!=null){
            //System.out.println(temp.val+ " " + carry);
            sum = temp.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            
            result = new ListNode(sum);
            prev.next = result;
            prev = prev.next;
            
            temp = temp.next;
        }    
        
        if(carry>0){
            result = new ListNode(carry);
            prev.next = result;
        }
        
        return reverseLL(head);
    }
    public ListNode reverseLL(ListNode head){
         if(head==null || head.next==null)
            return head;
        
        ListNode p1 = null, p2 = head, p3 = head.next;
            
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