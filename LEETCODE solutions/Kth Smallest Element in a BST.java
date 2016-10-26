import java.util.*;

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
    ArrayList<Integer> arr = new ArrayList<Integer>();
    public int kthSmallest(TreeNode root, int k) {
        helpKthSmallest(root, k);
        return arr.get(k-1);
    }
    
    public void helpKthSmallest(TreeNode root, int k) {
        
        if(root.left!=null)
            helpKthSmallest(root.left, k);
            
        if(arr.size()==k)
            return;
        else
            arr.add(root.val);
        
        if(root.right!=null)
            helpKthSmallest(root.right, k);
    }
}