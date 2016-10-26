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
    TreeNode prev = null, next = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        
        inorderSuccessorHelp(root, p);
        return next;
    }
    public void inorderSuccessorHelp(TreeNode root, TreeNode p) {
        if(root.left!=null)
            inorderSuccessorHelp(root.left, p);
        
        //termination condition
        if(next!=null)
            return;

        if(prev!=null && prev.val==p.val){
            next = root;
            return;
        }else
             prev = root;
        
        if(root.right!=null)
            inorderSuccessorHelp(root.right, p);
    }
}

