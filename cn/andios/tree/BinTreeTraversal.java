/*
 * @Author: lsd
 * @Date: 2020-02-19 16:20:31
 * @Software: vscode
 * @Description: 
 *      二叉树遍历分为  深度遍历、广度遍历
 *      深度遍历分为：前、中、后序遍历
 *      广度遍历分为：层次遍历(即一层一层的遍历)
 *  
 *      前中后序遍历的递归方法遍历见BinTree.java
 */
package cn.andios.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//节点类
class TreeNode{
    Integer val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//非递归实现前中后序遍历辅助类
class Command{
    //是否继续遍历下一个节点
    Boolean isGo;
    TreeNode treeNode;
    public Command(Boolean isGo,TreeNode treeNode){
        this.isGo = isGo;
        this.treeNode = treeNode;
    }
}


public class BinTreeTraversal{

    /**
     * 非递归实现前序遍历 法一
     * 中后序遍历即改变if块中的顺序
     */
    public List<Integer> preOrder1(TreeNode root){
        List<Integer> list = new ArrayList<>();
        //非空判断
        if(root == null){
            return list;
        }
        Stack<Command> stack = new Stack<>();
        //根节点添加到stack中，并且isGo为true，即向下遍历
        stack.push(new Command(true, root));
        while(!stack.isEmpty()){
            //找到栈顶元素
            Command command = stack.pop();
            //继续遍历，因为用栈结构，所以要反过来
            if(command.isGo == true){
                //右孩子放入栈中,isGo为true
                if(command.treeNode.right != null){
                    stack.push(new Command(true, command.treeNode.right));
                }
                //左孩子放入栈中，isGo为true
                if(command.treeNode.left != null){
                    stack.push(new Command(true, command.treeNode.left));
                }
                //当前节点放入栈中，isGo为false
                stack.push(new Command(false, command.treeNode));
            
            }else{//不继续遍历，就访问val
                list.add(command.treeNode.val);
            }
        }
        return list;
    }

    /**
     * 非递归实现前序遍历 法二
     */
    public List<Integer> preOrder2(TreeNode root){
        List<Integer> list = new ArrayList<>();
        //非空判断
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        //根节点加到stack中
        stack.push(root);
        while (!stack.isEmpty()) {
            //出栈一个元素(因为前序遍历从根节点开始，所以可以直接出栈)
            TreeNode node = stack.pop();
            list.add(node.val);
            //加入左节点
            if(node.left != null){
                stack.push(node.left);
            }
            //加入右节点
            if(node.right != null){
                stack.push(node.right);
            }
        }
        return list;
    }

    /**
     * 非递归实现中序遍历 法二
     */
    public List<Integer> inOrder2(TreeNode root){
        List<Integer> list = new ArrayList<>();
        //非空判断
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        //因为把所有左子树放入栈中了，必须保证所有左子树都被遍历，所以 !stack.isEmpty()
        while (cur != null || !stack.isEmpty()) {
            //所有左子树放入栈中
            while(cur != null){
                stack.push(cur);
                cur = root.left;
            }
            //没有左子树了
            cur = stack.pop();
            //当前值放入list中
            list.add(cur.val);
            //切换为右子树
            cur = cur.right;
        }
        return list;
    }

    /**
     * 非递归实现后序遍历 法二
     */
    public List<Integer> postOrder2(TreeNode root){
        List<Integer> list = new ArrayList<>();
        //非空判断
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        //根节点加到stack中
        stack.push(root);
        //记录上一次访问的节点，初始为null
        TreeNode lastNode = null;
        while (!stack.isEmpty()) {
           TreeNode node = stack.peek();
           // 1.左右节点都为空
           // 2.1上次访问的节点是左节点(此时右节点为空，不然上次访问节点一定是右节点，因为入栈时先入右节点，再入左节点)
           // 2.2上次访问的节点是右节点
           //满足以上两种情况，就访问当前节点
           if((node.left == null && node.right == null) || (lastNode != null && (node.left == lastNode || node.right == lastNode))){
                //让节点出栈
                stack.pop();
                list.add(node.val);
                lastNode = node;
           }else{
               //左右节点入栈，但由于是栈结构，要逆序放入
               if(node.right != null){
                    stack.push(node.right);
               }
               if(node.left != null){
                    stack.push(node.left);
                }
           }
        }
        return list;
    }

    /**
     * 非递归实现后序遍历 法三
     */
    public List<Integer> postOrder3(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
            list.add(0,node.val);
        }
        return list;
    }

    /**
     * 非递归层次遍历
     */
    public List<List<Integer>> levelOrder1(TreeNode root){  
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        //用对列存储
        Queue<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            //count表示每一层的节点数
            int count = linkedList.size();
            //listTmp装每一层的节点，每一层遍历完后就放入list中
            List<Integer> listTmp = new ArrayList<>();
            while(count > 0){
               TreeNode node = linkedList.poll();
               listTmp.add(node.val);
               if(node.left != null){
                    linkedList.add(node.left);
                }
               if(node.right != null){
                    linkedList.add(node.right);
                }
                //每遍历一个，当前层节点数减一
                count --;
            }
            list.add(listTmp);
        }

        return list;
    }

    /**
     * 递归层次遍历
     */
    public List<List<Integer>> levelOrder2(TreeNode root){  
        List<List<Integer>> list = new ArrayList<>();
        //结果集为list，从root开始遍历，初始层数为0
        helper(list,root,0);
        return list;
    }

    private void helper(List<List<Integer>> list, TreeNode root, int depth) {
        if(root == null){
            return;
        }
        
        if(list.size() == depth){
            list.add(new ArrayList<>());
        }
        list.get(depth).add(root.val);
        
        helper(list, root.left, depth + 1);
        helper(list, root.right, depth + 1);
    }

    /**
     * 递归根据前序、中序遍历结果重建二叉树
     */
    public TreeNode reConstructBinaryTree(int[] pre,int[] in){
        if(pre.length == 0 || in.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for(int i = 0;i < in.length;i ++){
            if(in[i] == pre[0]){
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
}