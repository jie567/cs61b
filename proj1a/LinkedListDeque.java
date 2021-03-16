// 双端链表
public class LinkedListDeque<T> {
    public class StuffNode{
        public T item;
        public StuffNode pre;
        public StuffNode next;
        public StuffNode(T i,StuffNode p, StuffNode n){
            item=i;
            pre=p;
            next=n;
        }
    }
    private StuffNode first;
    private StuffNode last;
    private StuffNode Recursion=first;
    private int size=0;
    public LinkedListDeque(){
        first = null;
        last = null;
    }
    public LinkedListDeque(T x){
        first =new StuffNode(x,last,last);
        last=null;
    }
    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return first ==null;
    }

    // Adds an item of type T to the front of the deque
    public void addFirst(T x){
        StuffNode newNode=new StuffNode(x,null,null);
        if(isEmpty()){
            last=newNode;  //  first 必须得指向 last  这里first 是有的 不过是一个空节点
        }
        newNode.next=first;
        first=newNode;
        size+=1;
    }
    // Adds an item of type T to the back of the deque
    public void addLast(T x){
        StuffNode newNode=new StuffNode(x,null,null);
        if(last==null||isEmpty()){
            addFirst(x);
            size-=1;
        }else{
            last.next=newNode;
            last=last.next;
        }
        size+=1;
    }

    /* must take constant time */
    public int size(){
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space
    public void printDeque(){
        StuffNode p= first;
        while(p!=null){
            System.out.print(p.item);
            System.out.print(' ');
            p=p.next;
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst(){
        size-=1;
        if(first==null){
            return null;
        }
        StuffNode temp= first.next;
        first =temp;
        return temp.item;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast(){
        size-=1;
        if(last==null){
            return null;
        }
        StuffNode temp= first;
        while(temp.next!=last){
            temp=temp.next;
        }
        temp.next=null;
        last=temp;
        return temp.item;
    }

    /* use iteration */
    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        if(index>size||index<0){
            return null;
        }
        StuffNode temp= first;
        while(index!=0){
            index-=1;
            temp=temp.next;
        }
        System.out.println(temp.item);
        return temp.item;
    }

    //Same as get, but uses recursion.

    public T getRecursive(int index){
        Recursion=first.next;
        if(index==0 && Recursion!=first){
            return Recursion.item;
        }
        else if(index!=0&& Recursion==first){
            return null;
        }
        return getRecursive(index-1);
    }
}
