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
    Stack<TreeNode> stk;
    List<Integer> result;
    public List<Integer> inorderTraversal(TreeNode root) {
        stk = new Stack<>();
        result = new ArrayList<>();
        inorder(root);
        return result;
    }

    public void insertLeft(TreeNode node){
        while(node!=null){
            stk.push(node);
            node = node.left;
        }
    }

    public void inorder(TreeNode root){
        insertLeft(root);     

        while(!stk.empty()){
            TreeNode node = stk.pop();
            result.add(node.val);
            insertLeft(node.right);
        }
    }
}