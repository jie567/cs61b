public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;
    private final int Default_Size=8;
    private final int Refactor=2;
//头部插入元素时，head下标左移一位；头部删除元素时，head下标右移一位。
//尾部插入元素时，tail下标右移一位；尾部删除元素时，tail下标左移一位
    //内部数组被看成是一个环，下标移动到边界临界点时，通过取模运算来计算逻辑下标对应的真实下标
    public ArrayDeque() {
        items=(T[])new Object[Default_Size];
        head=0;
        tail=0;
        size=0;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void resize(){
        int currentLength=this.items.length;
        T[] temp=(T[])new Object[currentLength*Refactor];
        for(int i=head,j=0;i<currentLength;i++,j++){
            temp[j]=this.items[i];
        }
        for(int i=0,j=currentLength-head;i<head;j++,i++){
            temp[j]=this.items[i];
        }
        this.items=temp;
        this.head=0;
        this.tail=this.items.length;
    }

    public int getIndex(int logicIndex){
        int currentLength=this.items.length;
        if(logicIndex<0){
            logicIndex+=currentLength;
        }else if(logicIndex>=currentLength){
            logicIndex-=currentLength;
        }
        return logicIndex;
    }
    public void addFirst(T x){
        head=getIndex(head-1);
        items[head]=x;
        if(head==tail){
            resize();
        }
        size+=1;
    }

    public void addLast(T x){
        items[tail]=x;
        tail=getIndex(tail+1);
        if(head==tail){
            resize();
        }
        size+=1;
    }

    public T removeFirst(){
        T temp=items[head];
        items[head]=null;
        head=getIndex(head+1);
        size-=1;
        return temp;
    }

    public T removeLast(){
        tail=getIndex(tail-1);
        T temp=items[tail];
        items[tail]=null;
        size-=1;
        return temp;
    }

    public T get(int index){
        if(index>this.size||index<0){
            return null;
        }
        return items[getIndex(index)];
    }

    public void printDeque(){
        for(int j=head;j<this.items.length;j++){
            if(items[j]==null){
                break;
            }
            System.out.print(items[j]);
            System.out.print(' ');
        }
        for(int i=0;i<this.items.length-tail;i++){
            if(items[i]==null){
                break;
            }
            System.out.print(items[i]);
            System.out.print(' ');
        }
    }

    public int size(){
        return size;
    }
}
