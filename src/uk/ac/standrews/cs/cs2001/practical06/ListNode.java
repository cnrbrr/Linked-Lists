package uk.ac.standrews.cs.cs2001.practical06;

public class ListNode {

    public Object element;
    public ListNode next;

    public ListNode(Object element) {

        this(element, null);
    }

    public ListNode(Object element, ListNode next) {

        this.element = element;
        this.next = next;
    }
}
