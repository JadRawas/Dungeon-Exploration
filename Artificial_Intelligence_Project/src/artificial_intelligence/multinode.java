package artificial_intelligence;





import java.util.ArrayList;

public class multinode <T> {
    public T data;
    String action = "Initial";
    public int id=1;
    public multinode parent=null;
    public ArrayList<multinode> child;
    
    public multinode(T data,int id)
    {
        this.data = data;
        child = new ArrayList<multinode>();
        parent=null;
        this.id=id;
    }
    public multinode(T data,multinode p,int id)
    {
        this.data = data;
        child = new ArrayList<multinode>();
        parent=p;
        this.id=id;
    }
    public void setData(T data)
    {
        this.data = data;
    }
    public T getData()
    {
        return data;
    }
    public multinode getNode(int x)
    {
        if (x >= 0 && x < child.size()) {
            return child.get(x);

        } else {
            return null;

        }
    }
    public multinode getParent()
    {
        return parent;
    }
    public void insertChild(T data,int id,String action)
    {
        multinode n=new multinode(data,this,id);
        n.action = action;
        child.add(n);
    }
    boolean isEmpty()
    {
        return data==null;
    }
}

