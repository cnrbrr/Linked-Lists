package uk.ac.standrews.cs.cs2001.practical06;

import java.util.Comparator;

/**
 * ADT providing operations on linked lists.
 * 
 * @author graham
 */
public interface IListManipulator {

    /**
     * Returns the size of a list.
     * 
     * @param head the head of the list
     * @return the size of the list
     */
    int size(ListNode head);

    /**
     * Counts the number of occurrences of a given element in a list.
     * 
     * @param head the head of the list
     * @param element the element to be matched
     * @return the number of elements in the list that are equal to the given element
     */
    int count(ListNode head, Object element);

    /**
     * Returns a string representation of a list.
     * 
     * @param head the head of the list
     * @return a string representation comprising the string representations of the list elements, separated by commas
     */
    String convertToString(ListNode head);

    /**
     * Accesses an element of a list, counting from the head of the list.
     * 
     * @param head the head of the list
     * @param n the position of the required element, with zero interpreted as the head
     * @return the element at the specified position
     * @throws InvalidIndexException if the position is not valid
     */
    Object getFromFront(ListNode head, int n) throws InvalidIndexException;

    /**
     * Accesses an element of a list, counting back from the tail of the list.
     * 
     * @param head the head of the list
     * @param n the position of the required element, with zero interpreted as the tail
     * @return the element at the specified position
     * @throws InvalidIndexException if the position is not valid
     */
    Object getFromBack(ListNode head, int n) throws InvalidIndexException;

    /**
     * Checks for deep equality of two lists.
     * 
     * @param head1 the head of the first list
     * @param head2 the head of the second list
     * @return true if the lists have equal length and the corresponding elements at each position are equal
     */
    boolean deepEquals(ListNode head1, ListNode head2);

    /**
     * Creates a deep copy of a list, without affecting the input list.
     * 
     * @param head the head of the list
     * @return a new list containing the same elements as the input list, but not sharing any list nodes with the input list
     */
    ListNode deepCopy(ListNode head);

    /**
     * Checks whether a list contains an element equal to the given element.
     * 
     * @param head the head of the list
     * @param element the element to be matched
     * @return true if the list contains an element equal to the given element
     */
    boolean contains(ListNode head, Object element);

    /**
     * Checks whether a list contains duplicate elements.
     * 
     * @param head the head of the list
     * @return true if the list contains two or more elements that are equal to one another
     */
    boolean containsDuplicates(ListNode head);

    /**
     * Appends one list to the end of another. The resulting list may include parts of the input lists.
     * 
     * @param head1 the head of the first list
     * @param head2 the head of the second list
     * @return a list containing the elements of the first list followed by the elements of the second list
     */
    ListNode append(ListNode head1, ListNode head2);

    /**
     * Converts a list of lists into a single list.
     * 
     * @param head the head of a list, each element of which is itself the head of a list
     * @return the result of combining all the sublists into a single list, in which the elements of the first sublist are followed by
     * the elements of each successive sublist, and the ordering within sublists is preserved
     */
    ListNode flatten(ListNode head);

    /**
     * Checks whether a list is circular.
     * 
     * @param head the head of the list
     * @return true if last node of the list points to the head
     */
    boolean isCircular(ListNode head);

    /**
     * Checks whether a list contains one or more cycles.
     * 
     * @param head the head of the list
     * @return true if last node of the list points to some node in the list
     */
    boolean containsCycles(ListNode head);

    /**
     * Creates a new list containing the results of applying the given transformation to all elements of the input list,
     * without affecting the input list.
     * 
     * @param head the head of the list
     * @param transformation the transformation to be applied to each element
     * @return a list containing the transformed elements
     */
    ListNode map(ListNode head, ITransformation transformation);

    /**
     * Returns the result of combining the given initial value and all the elements of the input list using the given associative operator.
     * For example, applying an 'add' operator to a list of integers, using the initial value zero, would produce the sum of the list elements.
     * 
     * @param head the head of the list
     * @param operator the operator used to combine elements
     * @param initial the initial value to be combined with the first element
     * @return the result of combining the initial value and all the elements
     */
    Object reduce(ListNode head, IOperator operator, Object initial);

    /**
     * Sorts the elements of a list according to a given ordering.
     * 
     * @param head the head of the list
     * @param comparator the ordering to be used
     * @return a sorted list, which may share some or all nodes of the input list
     */
    ListNode sort(ListNode head, Comparator comparator);
}
