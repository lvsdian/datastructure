package cn.andios.linkedlist;

/**
 * @Author: lsd
 * @Description: linkedlist_demo
 * @Date: 2019/12/24 下午7:38
 */
public class LinkedList {
    int data;
    LinkedList next;

    public LinkedList() {
    }

    public LinkedList(int data) {
        this.data = data;
    }

    /**
     * 头插法创建链表,每个新节点总是插入到head的后面
     *  head->null
     *  head->newNode1->null
     *  head->newNode2->newNode1->null
     *  head->newNode3->newNOde2->newNode1->null
     * @return
     */
    public LinkedList createHead(){
        LinkedList header = new LinkedList();
        LinkedList newNode = null;
        for (int i = 10; i < 15; i++) {
            newNode = new LinkedList(i);
            //核心
            //node1节点的next赋给node2的next,node2成为node1节点的next
            newNode.next = header.next;
            header.next = newNode;
        }
        return header;
    }

    /**
     * 尾插法创建链表
     * head(tail)->null
     * head->newNode1(tail)->null
     * head->newNode1->newNode2(tail)->null
     * head->newNode1->newNode2->newNode3(tail)->null
     * @return
     */
    public LinkedList createTail(){
        LinkedList head = new LinkedList();
        LinkedList tail = new LinkedList();
        tail = head;
        LinkedList newNode = null;
        for (int i = 10; i < 15; i++) {
           newNode = new LinkedList(i);
           //核心
            //node2成为node1的next,再下次的新节点node3又成为node2的next,
            //tail用来表示尾节点,每次的新节点都插到tail的后面
           tail.next = newNode;
           tail = newNode;
        }
        return head;
    }

    /**
     * 遍历链表（x）
     * @param linkedList
     */
    public void traverse1(LinkedList linkedList){
        while (linkedList.next !=null){
            //先赋值linkedList,再打印
            linkedList = linkedList.next;
            System.out.print(linkedList.data+"\t");
        }
    }

    /**
     * 遍历链表(√)
     * @param linkedList
     */
    public void traverse2(LinkedList linkedList){
        while (linkedList !=null){
            System.out.print(linkedList.data+"\t");
            linkedList = linkedList.next;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        LinkedList listHead = linkedList.createHead();
        LinkedList listTail = linkedList.createTail();

        System.out.println("头插法：");;
        linkedList.traverse2(listHead);
        System.out.println("\n尾插法：");
        linkedList.traverse2(listTail);
    }
}
