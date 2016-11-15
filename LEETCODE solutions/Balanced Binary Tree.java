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
    public boolean isBalanced(TreeNode root) {
        return helper(root)!=-1;
    }
    
    public int helper(TreeNode root){
        if(root==null)
            return 0;
        
        int left_h = helper(root.left);
        int right_h = helper(root.right);
        
        if(left_h==-1 || right_h==-1)
            return -1;
            
        if(Math.abs(left_h-right_h)>1)
            return -1;
        else
            return Math.max(left_h,right_h) + 1;
    }
}