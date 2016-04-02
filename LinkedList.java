
public class LinkedList {
	
	private LLNode head;
	private LLNode tail;
	private int size;
	public LinkedList()
	{
		size=0;
		head=new LLNode();
		tail=head;
		head.next=head;
	}
	
	public String[] Traverse()
	{
		LLNode position=head;

		String[] out=new String[size];
		for (int i=0; i<size;i++)
		{
			out[i]=position.data;
			position=position.next;
		}
  System.out.println(out);
		return out;
			
	}
	
	public void append(String in)
	{
		if (size==0)
		{head.data=in;
		head.next=new LLNode();
		tail=head.next;}
		else
		{
			tail.data=in;
			tail.next=new LLNode();
			tail=tail.next;
		}
	
		size++;
	}

	public void append(String[] in)
	{
		for (int i=0; i<in.length; i++)
			append(in[i]);
	}
	
	public void remove(String arg0)
	{
	  if (size==0) return;
	  if (head.data.equals(arg0)) 
	  {head=head.next; size--; return;	  }
	  LLNode pos=head.next;
	  LLNode prev=head;
	  for(int i=0; i<size;i++)
		  if (pos.data.equals(arg0)) 
	 { if (pos.next==null) {prev.next=null; tail=prev;}
		  else prev.next=pos.next; 
	  size--;}
	  }
}




