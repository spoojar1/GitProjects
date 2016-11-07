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
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode node = root;
        
        //find the node to be deleted
        while(node!=null && node.val!=key){
            if(key < node.val){
                parent = node;
                node = node.left;
            }
            else{
                parent = node;
                node = node.right;
            }
        }
        
        if(node==null)  //if node not found return root
            return root;
        else{
            if(node.left==null && node.right==null){    //if node to be deleted is a leaf node
            
                //if root is the node to be deleted, then parent will be null
                if(root.val==key)
                    return null;
                
                if(parent.val < key)
                    parent.right = null;
                else
                    parent.left = null;
                
                return root;
            }
            if(node.left==null){
                //if root is the node to be deleted and it has no left child
                if(root.val==key)
                    root = node.right;
                else{
                    if(parent.val < node.val)
                        parent.right = node.right;
                    else
                        parent.left = node.right;
                }
                
                return root;
            }
            if(node.right==null){
                //if root is the node to be deleted and it has no right child
                if(root.val==key)
                    root = node.left;
                else{
                    if(parent.val < node.val)
                        parent.right = node.left;
                    else
                        parent.left = node.left;
                }
                return root;
            }
            
            //if root is the node to be deleted and it has both children
            if(root.val==key)
                root = node.right;
            else{
                if(parent.val < node.val)
                    parent.right = node.right;
                else
                    parent.left = node.right;
            }
            
            TreeNode new_node = findNewNode(node.right);
            new_node.left = node.left;
        }
        
        return root;
    }
    
    public TreeNode findNewNode(TreeNode node){
        
        while(node.left!=null)
            node = node.left;
        return node;
    }
}