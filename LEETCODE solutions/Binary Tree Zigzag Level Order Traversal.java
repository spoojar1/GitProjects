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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> curr = new ArrayDeque<TreeNode>(), next = null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root==null)
            return result;
            
        boolean flag = false;
        curr.add(root);
        
        List<Integer> list;
        
        while(!curr.isEmpty()){
            next = new ArrayDeque<TreeNode>(); 
            list = new ArrayList<Integer>();
            while(!curr.isEmpty()){
                TreeNode node = curr.removeLast();
                list.add(0, node.val);
                if(!flag){
                    if(node.right!=null)
                        next.add(node.right);
                    if(node.left!=null)
                        next.add(node.left);
                }else{
                    if(node.left!=null)
                        next.add(node.left);
                    if(node.right!=null)
                        next.add(node.right);
                }
            }
            
            result.add(list);
            flag = !flag;
            curr = next;
        }
        
        return result;
    }
}