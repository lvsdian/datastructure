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
        List<Node> datas = new ArrayList<>();

        Integer arr [] = new Integer []{3,9,20,null,null,15,7};
        for (Integer i : arr) {
            if(i != null){
                datas.add(new Node(i));
            }else{
                datas.add(null);
            }
            
        }
        for (Integer parIndex = 0; parIndex < datas.size() / 2; parIndex ++) {
            if(datas.get(parIndex) == null){
                continue;
            }
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
        System.out.print("\n前序遍历：");
        binaryTree.preOrder();
        System.out.print("\n中序遍历：");
        binaryTree.inOrder();
        System.out.print("\n后序遍历：");
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
    private Integer value;
    private Node lNode;
    private Node rNode;

    //构造
    public Node(Integer value) {
        this.value = value;
    }
    //get set ...
    public Integer getValue(){
        return this.value;
    }
    public void setValue(Integer value){
        this.value = value;
    }
    public Node getLNode(){
        return this.lNode;
    }
    public void setLNode(Node lNode){
        this.lNode = lNode;
    }
    public Node getRNode(){
        return this.rNode;
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
        System.out.print(this.value+"\t");
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
        System.out.print(this.value+"\t");
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
        System.out.print(this.value+"\t");
    }
}