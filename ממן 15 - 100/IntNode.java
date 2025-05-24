/**
 * The class IntNode - represents a node in a data structure
 * @author Ori Nave
 * @version 06/06/2022
 */
public class IntNode
{
    private int _value;
    private IntNode _next;

    /**
     * IntNode class constructor, composed of the current value in the node and a pointer to the next node
     * @param v value
     * @param n next node
     * time complexity of O(1), space complexity of O(1)
     */
    public IntNode(int v, IntNode n)
    {
        _value = v;
        _next = n;
    }

    /**
     * returns the node value
     * @return the node value
     * time complexity of O(1), space complexity of O(1)
     */
    public int getValue()
    {
        return _value;
    }

    /**
     * returns the next node
     * @return the next node
     * time complexity of O(1), space complexity of O(1)
     */
    public IntNode getNext()
    {
        return _next;
    }

    /**
     * setting value to the current node
     * @param v value
     * time complexity of O(1), space complexity of O(1)
     */
    public void setValue(int v)
    {
        _value = v;
    }

    /**
     * setting the next node of the current node
     * @param n next node
     * time complexity of O(1), space complexity of O(1)
     */
    public void setNext(IntNode n)
    {
        _next = n;
    }
}