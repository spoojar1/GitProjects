/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // The reason to check s if it's null is we will call s.left & s.right in the next line
        if (s == null) return false;
        return equal(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean equal(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && equal(s.left, t.left) && equal(s.right, t.right);
    }
}