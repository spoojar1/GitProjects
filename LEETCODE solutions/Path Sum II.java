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
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null)
            return result;
        
        List<Integer> list = new ArrayList<Integer>();
        helper(root,sum,list);

        return result;
    }
    
    public void helper(TreeNode root, int sum, List<Integer> list){
        if(root.left==null && root.right==null){
            if(sum==root.val){
                List<Integer> list2 = new ArrayList<Integer>();
                for(int i : list)
                    list2.add(i);
                    
                list2.add(root.val);    
                result.add(list2);
            }
        }else{
            List<Integer> list2 = new ArrayList<Integer>();
            for(int i : list)
                list2.add(i);
                
            list2.add(root.val);
            if(root.left!=null)
                helper(root.left, sum-root.val, list2);
            if(root.right!=null)
                helper(root.right, sum-root.val, list2);
        }
    }
}