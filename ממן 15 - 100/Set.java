/**
 * The class Set - represents a data structure of nodes that contain odd natural numbers.
 * @author Ori Nave
 * @version 06/06/2022
 */
public class Set
{
    private IntNode _head;
    private int _elementsCounter;

    /**
     * Set class constructor
     * time complexity of O(1), space complexity of O(1)
     */
    public Set ()
    {
        _head = null;
        _elementsCounter = 0;
    }

    /**
     * checks if a set is empty.
     * @return true if the set is empty, false if not.
     * time complexity of O(1), space complexity of O(1)
     */
    public boolean isEmpty ()
    {
        return _head == null;
    }

    /**
     * checks if a number exists in the set.
     * @param num a number
     * @return true if the number exists in the set, false if not
     * time complexity of O(n), space complexity of O(1)
     */
    public boolean isMember (int num)
    {
        if (num <= 0 || num%2 == 0 || this.isEmpty())
            return false;

        IntNode temp = _head;
        while (temp != null)
        {
            if (temp.getValue() == num)
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * checks if two sets are equal
     * @param other other set
     * @return true if equal, false if not
     * time complexity of O(n), space complexity of O(1)
     */
    public boolean equals (Set other)
    {
        if (this.isEmpty() && other.isEmpty())
            return true;

        if (this.isEmpty() && !other.isEmpty())
            return false;

        if (!this.isEmpty() && other.isEmpty())
            return false;

        IntNode temp = _head;
        IntNode otherTemp = other._head;
        while (temp != null && otherTemp != null)
        {
            if (temp.getValue() != otherTemp.getValue())
                return false;
            temp = temp.getNext();
            otherTemp = otherTemp.getNext();
        }
        return true;
    }

    /**
     * returns how many numbers the set contains
     * @return how many numbers the set contains
     * time complexity of O(1), space complexity of O(1)
     */
    public int numOfElements ()
    {
        return _elementsCounter;
    }

    /**
     * checks if other set is a subset of the current set
     * @param other other set
     * @return true if the other set is a subset of the current set, false if not
     * time complexity of O(n), space complexity of O(1)
     */
    public boolean subSet (Set other)
    {
        if (other.isEmpty())
            return true;

        if (this.isEmpty() && other.isEmpty())
            return true;

        if (this.isEmpty() && !other.isEmpty())
            return false;

        if (this.numOfElements() < other.numOfElements())
            return false;

        int counter = 0;
        IntNode temp = _head;
        IntNode otherTemp = other._head;
        while (temp != null && otherTemp != null)
        {
            if (temp.getValue() > otherTemp.getValue())
                otherTemp = otherTemp.getNext();
            else if(temp.getValue() < otherTemp.getValue())
                temp = temp.getNext();
            else
            {
                counter++;
                temp = temp.getNext();
                otherTemp = otherTemp.getNext();
            }
        }
        return counter == other.numOfElements();
    }

    /**
     * adding a number to the set(the number should be positive and odd)
     * @param x a number
     * time complexity of O(n), space complexity of O(1)
     */
    public void addToSet (int x)
    {
        if (x <= 0 || x%2 == 0 || this.isMember(x))
            return;

        if (this.isEmpty())
        {
            _head = new IntNode(x, null);
        }
        else if(x < _head.getValue())
        {
            _head = new IntNode(x, _head);
        }
        else
        {
            IntNode temp = _head;
            while (temp.getNext() != null && x > temp.getNext().getValue())
            {
                temp = temp.getNext();
            }
            IntNode newNode = new IntNode(x, null);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
        _elementsCounter++;
    }

    /**
     * removing a number from the set
     * @param x a number
     * time complexity of O(n), space complexity of O(1)
     */
    public void removeFromSet (int x)
    {
        if (this.isEmpty() || !this.isMember(x))
            return;

        if (_head.getValue() == x)
        {
            _head = _head.getNext();
        }
        else
        {
            IntNode temp = _head;
            while (temp.getNext().getValue() != x) {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        }
        _elementsCounter--;
    }

    /**
     * a String representation of a set
     * @return a String representation of a set
     * time complexity of O(n), space complexity of O(1)
     */
    public String toString()
    {
        if (this.isEmpty())
        {
            return "{}";
        }
        String str = "{";
        IntNode temp = _head;
        while (temp.getNext() != null)
        {
            str += temp.getValue() + ",";
            temp = temp.getNext();
        }
        str += temp.getValue() + "}";
        return str;
    }

    /**
     * a method that creates an intersected set that composed of the current set and the other set
     * @param other other set
     * @return new set that is an intersected set of the current set and the other set
     * time complexity of O(n), space complexity of O(n)
     */
    public Set intersection (Set other)
    {
        if (this.isEmpty() && other.isEmpty())
            return new Set();

        Set interSet = new Set();
        IntNode temp = _head;
        IntNode otherTemp = other._head;
        while (temp != null && otherTemp != null)
        {
            if (temp.getValue() > otherTemp.getValue())
                otherTemp = otherTemp.getNext();
            else if (temp.getValue() < otherTemp.getValue())
                temp = temp.getNext();
            else
            {
                interSet.addToSet(temp.getValue());
                temp = temp.getNext();
                otherTemp = otherTemp.getNext();
            }
        }
        return interSet;
    }

    /**
     * a method that creates a unified set that composed of the current set and the other set
     * @param other other set
     * @return new set that is a unified set of the current set and the other set
     * time complexity of O(n), space complexity of O(n)
     */
    public Set union (Set other)
    {
        if (this.isEmpty() && other.isEmpty())
            return new Set();

        Set unionSet = new Set();
        IntNode temp = _head;
        IntNode otherTemp = other._head;
        while (temp != null)
        {
            unionSet.addToSet(temp.getValue());
            temp = temp.getNext();
        }

        while (otherTemp != null)
        {
            if (unionSet.isMember(otherTemp.getValue()))
                otherTemp = otherTemp.getNext();
            else
            {
                unionSet.addToSet(otherTemp.getValue());
                otherTemp = otherTemp.getNext();
            }
        }
        return unionSet;
    }

    /**
     * a method that creates a set that is the difference of the current set on the other set
     * @param other other set
     * @return new set that is the difference of the current set on the other set
     * time complexity of O(n), space complexity of O(n)
     */
    public Set difference (Set other)
    {
        if (this.isEmpty())
            return new Set();

        if (this.isEmpty() && other.isEmpty())
            return new Set();

        if (other.isEmpty())
        {
            Set thisSet = new Set();
            IntNode temp = _head;
            while (temp != null)
            {
                thisSet.addToSet(temp.getValue());
                temp = temp.getNext();
            }
            return thisSet;
        }

        Set diffSet = new Set();
        IntNode temp = _head;
        IntNode otherTemp = other._head;
        while (temp != null && otherTemp != null)
        {
            if (temp.getValue() > otherTemp.getValue())
                otherTemp = otherTemp.getNext();
            else if (temp.getValue() < otherTemp.getValue())
            {
                diffSet.addToSet(temp.getValue());
                temp = temp.getNext();
            }
            else
            {
                temp = temp.getNext();
                otherTemp = otherTemp.getNext();
            }
        }
        return diffSet;
    }
}
