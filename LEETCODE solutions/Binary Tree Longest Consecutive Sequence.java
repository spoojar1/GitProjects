/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int max_len=1;
    public int longestConsecutive(TreeNode root) {
        if(root==null)
            return 0;
        int temp = helpLongestConsecutive(root);
        return max_len;
    }
    public int helpLongestConsecutive(TreeNode root){
        int left_len=0, right_len=0;
        
        //single node always has a path of length 1
        if(root.left==null && root.right==null)
            return 1;
            
        if(root.left!=null)
            left_len = helpLongestConsecutive(root.left);
        if(root.right!=null)
            right_len = helpLongestConsecutive(root.right);
            
        if(left_len>=right_len){
            if(left_len>0 && root.val==root.left.val-1){
                if(left_len + 1 > max_len)
                    max_len = left_len + 1;
                return left_len + 1;
            }
        }
        
        if(right_len>0 && root.val==root.right.val-1){
            if(right_len + 1 > max_len)
                max_len = right_len + 1;           
            return right_len + 1;
        }
        
        //max len does not increase from both substrees, hence back to single node value  
        return 1;  
    }
}