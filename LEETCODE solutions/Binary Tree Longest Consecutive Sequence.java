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
    int max_len = 1;
    public int longestConsecutive(TreeNode root){
        if(root==null)
            return 0;
            
        DFS(root.left, 1, root.val);
        DFS(root.right, 1, root.val);
        
        return max_len;
    }
    public void DFS(TreeNode root, int len, int val){
        if(root==null)
            return;
        
        if(root.val==val+1){ //update max_len
            max_len = Math.max(max_len, len+1);
            DFS(root.left, len+1, root.val);
            DFS(root.right, len+1, root.val);
        }else{  //start counter again
            DFS(root.left, 1, root.val);
            DFS(root.right, 1, root.val);
        }
    }
}