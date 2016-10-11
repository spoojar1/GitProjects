//Definition for a binary tree node.
class TreeNode {
	 int val;
	 TreeNode left;
	 TreeNode right;
	 TreeNode(int x) { val = x; }
}
public class Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
            
        return checkSymmetry(root.left, root.right);
    }
    public boolean checkSymmetry(TreeNode leftNode, TreeNode rightNode) {
        if(leftNode == null && rightNode == null ){
            return true;
        }
         if(leftNode == null || rightNode == null ){
            return false;
        }
        if(leftNode.val == rightNode.val)
            return checkSymmetry(leftNode.left,rightNode.right) && checkSymmetry(leftNode.right,rightNode.left);
        else
            return false;
    }
}