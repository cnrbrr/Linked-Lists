package uk.ac.standrews.cs.cs2001.practical06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class ListManipulator implements IListManipulator {

	@Override
	public int size(ListNode head) {
		int sizeOfList = 1; //set the initial size of the list, will be at least one unless = null

		if (head == null){ // if null list is empty
			return 0;
		}
		for(ListNode i = head; i.next != null; i = i.next){
			sizeOfList++; //until the list runs out increase the size
		}

		return sizeOfList;
	}

	@Override
	public int count(ListNode head, Object element) {
		int occurrences = 0; //can be 0 occurrences of an element

		for(ListNode i = head; i != null; i = i.next){ 
			if(i.element.equals(element)){
				occurrences++; //if the element if found increase the count
			}
		}

		return occurrences;
	}

	@Override
	public String convertToString(ListNode head) {
		String current = ""; 

		if(head == null){ //if the list is null return an empty string
			return "";
		}

		for(ListNode i = head; i != null; i = i.next){
			current = current + i.element.toString(); //update the string
			if(i.next != null){
				current = current + ","; //if not the last element then add a comma
			}
		}

		return current;
	}

	@Override
	public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
		int element = 0;
		if(head == null){ //if null throw exception
			throw new InvalidIndexException();
		}

		ListNode current = head;
		for(int i = 0; i <= n; i++){ //start loop from front
			if(i == n){ //if the value of n = i then set element and return
				element = current.element.hashCode();
				return element;
			}
			current = current.next; //or move onto the next value of current
			if(current == null){ //if null throw exception
				throw new InvalidIndexException();
			}
		}
		return element;
	}

	@Override
	public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
		int element = 0;
		Stack<String> holder = new Stack<String>(); //a stack for the list to be read into
		int full = size(head); //set an int to be the size of the list
		String[] tempHolder = new String[full]; //have an array the same size as the list
		if(head == null){ //if null throw exception
			throw new InvalidIndexException();
		}
		ListNode current = head; //set a new list node

		while(current != null){ 
			holder.push(current.element.toString()); //add the value to the stack
			if(current.next == null){
				break;
			} else {
				current = current.next; //move pointer
			}
		}

		for(int j = 0; j < holder.size(); j++){
			tempHolder[j] = holder.get(j).toString(); //move the values from the stack to the array
		}

		if(n == full - 1){ //if n is at the max point
			element = Integer.parseInt(tempHolder[0]);	//element is equal to the first value in the array	
			return element;
		}

		try{
			element = Integer.parseInt(tempHolder[n + 1]); //if not set it at this value
		} catch (ArrayIndexOutOfBoundsException e){
			throw new InvalidIndexException(); //if out of bounds throw exception
		}
		return element;
	}

	@Override
	public boolean deepEquals(ListNode head1, ListNode head2){
		while(head1 != null && head2 !=null){
			if(!head1.element.equals(head2.element)){ //if they don't equal one another return false
				return false;
			}

			head1 = head1.next; //updates pointers
			head2 = head2.next;
		}

		if(head1 != null && head2 == null){
			return false;
		}
		if(head1 == null && head2 != null){;
		return false;
		} //if all checks pass return true
		return true;
	}

	@Override
	public ListNode deepCopy(ListNode head) {
		ArrayList<Object> tempHolder = new ArrayList<Object>(); //temporary holder for objects
		if(head == null){ //if head = null return null
			return null;
		}

		ListNode current = head; //new list node, set
		while(current != null){
			tempHolder.add(current.element); //add to the arraylist
			if(current.next == null){
				break;
			}
			current = current.next; //move pointer
		}
		
		ListNode newList = new ListNode(tempHolder.get(tempHolder.size() - 1)); //new list node

		for(int i = tempHolder.size() - 2; i >= 0; i--){ //move backwards through the arraylist
			newList = new ListNode(tempHolder.get(i), newList);	
			if(i == 0){
				break;
			}
		}
		return newList;
	}

	@Override
	public boolean contains(ListNode head, Object element) {
		if(head == null){
			return false;
		}
		ListNode contains = head; //new list node
		while(contains != null){ //loop
			if(contains.element.equals(element)){ //if it is contained then return true
				return true;
			}

			if(contains.next == null){
				break;
			}
			contains = contains.next;
		}

		return false;
	}

	@Override
	public boolean containsDuplicates(ListNode head) {
		ArrayList<Object> alreadySeen = new ArrayList<Object>(); //all values already seen
		if(head == null){
			return false;
		}
		ListNode current = head;
		while(current != null){ //loop
			if(alreadySeen.contains(current.element)){ //if it exits in the array list return true
				return true;
			}

			if(current.next == null){
				break;
			}
			alreadySeen.add(current.element); //otherwise add it to the array list
			current = current.next; //move pointer
		}
		return false; //if loop breaks then return false
	}

	@Override
	public ListNode append(ListNode head1, ListNode head2) {
		if(head1 == null && head2 == null){ //if both are null return null
			return null;
		}
		ListNode current1 = head1; //2 new list nodes
		ListNode current2 = head2;

		if(head2 == null && head1 != null){ //if head2 is null return head 1
			return current1;
		}

		if(head1 == null && head2 != null){ //if head1 is null return head 2
			return current2;
		}
		while(current1.next != null){ //move to the end of the current1
			current1 = current1.next;
		}

		current1.next = head2; //the space after current 1 = start of head2

		return head1;
	}

	@Override
	public ListNode flatten(ListNode head) {
		ArrayList<ListNode> lists = new ArrayList<ListNode>(); //array list of list nodes 
		if(head == null){
			return null;
		}

		while(head != null){
			lists.add((ListNode) head.element); //add all the lists to lists
			if(head.next == null){
				break;
			}
			head = head.next; //move pointer
		}

		ListNode fullList = null;
		for(int i = 0; i <= lists.size() - 1; i++){ //loops through the array list
			if(fullList == null){ //if the new list node is empty populate it
				fullList = lists.get(i);
			} else {
				append(fullList, lists.get(i)); //add all lists onto the end
			}
		}
		return fullList;
	}

	@Override
	public boolean isCircular(ListNode head) {
		HashSet<Integer> nodes = new HashSet<Integer>(); //to store the hash values of the list

		if(head == null){
			return false;
		}

		int goal = head.hashCode(); //goal = first node in list

		ListNode first = head; //new list node

		while(first != null){
			if(first.next == null){
				break;
			}
			first = first.next; //move pointer, before first check as first = goal so first + 1
			if(first.hashCode() == goal){
				return true;
			}

			if(nodes.contains(first.hashCode())){ //if it finds another value then stop looking
				return false;
			}
			nodes.add(first.hashCode()); //if not found add to list
		}
		return false;
	}

	@Override
	public boolean containsCycles(ListNode head) { //similar to above
		HashSet<Integer> nodes = new HashSet<Integer>();
		if(head == null){
			return false;
		}
		ListNode last = head;

		while(last != null){
			if(last.next == null){
				break;
			}
			last = last.next;
			//difference is that it checks all values that have come before
			if(nodes.contains(last.hashCode())){ 
				return true;
			}
			nodes.add(last.hashCode());
		}
		return false;
	}

	@Override
	public ListNode map(ListNode head, ITransformation transformation) {
		ArrayList<Object> tempHolder = new ArrayList<Object>();
		if(head == null){
			return null;
		}

		ListNode current = head;
		while(current != null){
			//adds the transformed values of the list
			tempHolder.add(transformation.transform((current.element)));
			if(current.next == null){
				break;
			}
			current = current.next;
		}
		ListNode newList = new ListNode(tempHolder.get(tempHolder.size() - 1));
		//add all the values from the arraylist to the new listnode
		for(int i = tempHolder.size() - 2; i >= 0; i--){
			newList = new ListNode(tempHolder.get(i), newList);	
			if(i == 0){
				break;
			}
		}
		return newList;
	}

	@Override
	public Object reduce(ListNode head, IOperator operator, Object initial) {
		Object total = initial;
		ListNode current = head;
		while(current != null){
			total = operator.operate(total, current.element); //updates total
			if(current.next == null){
				break;
			}
			current = current.next;
		}
		return total;
	}

	@Override
	public ListNode sort(ListNode head, Comparator comparator) {
		ListNode finalReturnable = null; 
		if(head == null){
			return null;
		}
		if(head.next == null){ // if the next value is null return what i have
			return head;
		}

		Object first = null;
		Object second = null;

		ListNode returnable = deepCopy(head);
		ArrayList<Object> sorter = new ArrayList<Object>();

		while(returnable != null){ //adds all values to the array list
			sorter.add(returnable.element);
			returnable = returnable.next;
		}

		int leftToSort = sorter.size() - 1;
		for(int j = 0; j < sorter.size(); j++){ //double loop means all values are checked
			for(int i = 0; i < leftToSort; i++){
				first = sorter.get(i); //first = first value to be checked
				second = sorter.get(i + 1); //second = second value to be checked against
				if(comparator.compare(first, second) > 0){ //if first - second > 0
					Object temp = first; //hold firsts value
					sorter.add(i, second);// add second to where first was
					sorter.remove(i + 1); //delete original first
					sorter.add(i + 1, temp);//add temp to where second was
					sorter.remove(i + 2);//delete original second
				}
			}
			leftToSort--; //takes one off the number of items to be checked in the array list
		}

		for(int k = 0; k < sorter.size(); k++){
			if(finalReturnable == null){		//if null populate
				finalReturnable = new ListNode(sorter.get(k));
			} else {
				ListNode secondNode = new ListNode(sorter.get(k)); //temp list node
				append(finalReturnable, secondNode); //add it to the end
			}			
		}
		return finalReturnable;
	}

}
