/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        /*LOGIC:
        same as array mergesort but here we dont have indices, so we keep track of head and tail at every level. Then calculate mid at every level.
        Simplemerge: since we cannot use extra space, we follow the concept of merging 2 sorted lists here. Swap values and then reorder second list. eg: 2->3->5 and 0->1->6 becomes 0->3->5 and 2->1->6 (which then reorders into 1->-2>6). Here the second list head does not advance. When the first list head reaches its end i.e. null, the second list will be sorted already.
        */
        
        if(head==null || head.next==null)
            return head;
            
        ListNode tail = head;
        //calculate tail
        while(tail.next!=null)
            tail = tail.next;
            
        mergesort(head, tail);
        
        return head;
    }
    
    public void mergesort(ListNode head, ListNode tail){
        if(head!=tail){
            
            int count = 1;
            ListNode len_p = head;
            
            //calculate length of list
            while(len_p!=tail){
                len_p = len_p.next;
                count++;
            }
            
            count = count/2;
            ListNode mid = head, prev = null;
            
            //adjust mid to middle of list with the help of count
            while(count-->0){
                prev = mid;
                mid = mid.next;
            }
                
            mergesort(head, prev);
            mergesort(mid, tail);
            simpleMerge(head, mid, tail);
        }
    }
    
    //same as merge 2 sorted lists concept
    public void simpleMerge(ListNode head, ListNode mid, ListNode tail){
        while(head!=mid){
            if(head.val > mid.val){
                int t = head.val;
                head.val = mid.val;
                mid.val = t;
                
                ListNode temp = mid;
                while(temp!=tail && temp.val > temp.next.val){
                    t = temp.val;
                    temp.val = temp.next.val;
                    temp.next.val = t;
                    
                    temp = temp.next;
                }
            }
            
            head = head.next;
        }
    }
}