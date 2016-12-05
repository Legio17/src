package ca.main.game.utilities;

public class LinearNode<T> {
	
	private T element;
	private LinearNode next;
	
	public LinearNode()
	{
		this.element=null;
		this.next=null;
	}
	public LinearNode(T element, LinearNode<T> next)
	{
		this.element=element;
		this.next=next;
	}
	
	public LinearNode(T element)
	{
		this.next=null;
		this.element=element;
	}
	
	public LinearNode<T> getNext()
	{
		return next;
	}
	
	public void setNext(LinearNode<T> node)
	{
		this.next=node;
	}
	
	public T getElement()
	{
		return element;
	}
	
	public void setElement(T element)
	{
		this.element=element;
	}

}
