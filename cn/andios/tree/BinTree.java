/*
 * @Author: lsd
 * @Date: 2020-02-02 11:35:46
 * @Software: vscode
 * @Description: 二叉树的创建及前中后序遍历
 */

package cn.andios.tree;
import java.util.ArrayList;
import java.util.List;

public class BinTree {
    public static void main(String[] args) {
        //创建树
        BinaryTree binaryTree = new BinaryTree();
        //节点数据
        List<Node> datas = new ArrayList();

        int arr [] = new int []{0,1,2,3,4,5,6,7};
        for (int i : arr) {
            datas.add(new Node(arr[i]));
        }
        for (int parIndex = 0; parIndex < datas.size() / 2; parIndex ++) {
            //左孩子
            datas.get(parIndex).setLNode(datas.get(2 * parIndex + 1));
            //右孩子
            if(2 * parIndex + 2 < datas.size()){
                datas.get(parIndex).setRNode(datas.get(2 * parIndex + 2));
            }
        }
        
        //将节点挂到树上
        binaryTree.setRoot(datas.get(0));
        //遍历
        System.out.println("\n前序遍历：");
        binaryTree.preOrder();
        System.out.println("\n中序遍历：");
        binaryTree.inOrder();
        System.out.println("\n后序遍历：");
        binaryTree.postOrder();
    }
    
}
//树
class BinaryTree{
    private Node root;
    public void setRoot(Node root){
        this.root = root;
    }
    //前序遍历
    public void preOrder(){
        //assert(tree != null);
        if(this.root != null){
            this.root.preOrder();
        }
    }
    //中序遍历
    public void inOrder(){
        if(this.root != null){
            this.root.inOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }
    }
}
//节点
class Node{
    private int value;
    private Node lNode;
    private Node rNode;

    //构造
    public Node(final int value) {
        this.value = value;
    }
    //get set ...
    public int getValue(){
        return ths.value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public Node getLNode(){
        return ths.lNode;
    }
    public void setLNode(Node lNode){
        this.lNode = lNode;
    }
    public int getRNode(){
        return ths.rNode;
    }
    public void setRNode(Node rNode){
        this.rNode = rNode;
    }

    @Override
    public String toString(){
        return "Node[value="+value +  "]";
    }

    //前序遍历
    public void  preOrder(){
        //根
        System.out.print(this+"\t");
        //左
        if(this.lNode != null){
            this.lNode.preOrder();
        }
        //右
        if(this.rNode != null){
            this.rNode.preOrder();
        }
    }
    //中序遍历
    public void  inOrder(){
        //左
        if(this.lNode != null){
            this.lNode.inOrder();
        }
        //根
        System.out.print(this+"\t");
        //右
        if(this.rNode != null){
            this.rNode.inOrder();
        }
    }
    //后序遍历
    public void  postOrder(){
        //左
        if(this.lNode != null){
            this.lNode.postOrder();
        }
        //右
        if(this.rNode != null){
            this.rNode.postOrder();
        }
        //根
        System.out.print(this+"\t");
    }
}