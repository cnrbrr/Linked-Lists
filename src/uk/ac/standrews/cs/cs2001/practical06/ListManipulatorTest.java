package uk.ac.standrews.cs.cs2001.practical06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class ListManipulatorTest{
	
	public ListManipulatorTest(){
		
	}
	

    private final IListManipulator sut = new ListManipulator();

    private final int element1 = 3;
    private final int element2 = 75;
    private final int element3 = -31;
    private final int element4 = 7;

    private final ListNode list1 = new ListNode(element1);
    private final ListNode list2 = new ListNode(element2, list1);
    private final ListNode list3 = new ListNode(element1, list2);
    private final ListNode list4 = new ListNode(element3, list3);

    @Test
    public void size() {

        assertEquals(0, sut.size(null));
        assertEquals(3, sut.size(list3));
    }

    @Test
    public void count() {

        assertEquals(0, sut.count(list3, 1));
        assertEquals(1, sut.count(list3, element2));
        assertEquals(2, sut.count(list3, element1));
    }

    @Test
    public void convertToString() {

        assertEquals("", sut.convertToString(null));
        assertEquals(String.valueOf(element1), sut.convertToString(list1));
        assertEquals(element1 + "," + element2 + "," + element1, sut.convertToString(list3));
    }

    @Test
    public void getCountingForwards() throws InvalidIndexException {


        assertEquals(element3, sut.getFromFront(list4, 0));
        assertEquals(element1, sut.getFromFront(list4, 1));
        assertEquals(element2, sut.getFromFront(list4, 2));
        assertEquals(element1, sut.getFromFront(list4, 3));
    }

    @Test(expected = InvalidIndexException.class)
    public void getCountingForwardsIndexTooLarge() throws InvalidIndexException {

        sut.getFromFront(list3, 4);
    }

    @Test(expected = InvalidIndexException.class)
    public void getCountingForwardsEmptyList() throws InvalidIndexException {

        sut.getFromFront(null, 0);
    }

  @Test
    public void getCountingBackwards() throws InvalidIndexException {
	  
        assertEquals(element1, sut.getFromBack(list4, 0));
        assertEquals(element2, sut.getFromBack(list4, 1));
        assertEquals(element1, sut.getFromBack(list4, 2));
        assertEquals(element3, sut.getFromBack(list4, 3));
    }

    @Test(expected = InvalidIndexException.class)
    public void getCountingBackwardsIndexTooLarge() throws InvalidIndexException {

        sut.getFromBack(list4, 4);
    }

    @Test(expected = InvalidIndexException.class)
    public void getCountingBackwardsEmptyList() throws InvalidIndexException {

        sut.getFromBack(null, 0);
    }

    @Test
    public void deepEquals() {

        assertTrue(sut.deepEquals(null, null));
        assertFalse(sut.deepEquals(list1, null));
        assertFalse(sut.deepEquals(null, list1));
        assertTrue(sut.deepEquals(list1, list1));
        assertTrue(sut.deepEquals(list1, new ListNode(element1)));
        assertTrue(sut.deepEquals(list2, new ListNode(element2, new ListNode(element1))));
        assertFalse(sut.deepEquals(list1, list2));
    }

   @Test
    public void deepCopy() {

        assertNull(sut.deepCopy(null));
        ListNode copy1 = sut.deepCopy(list1);
        assertNotSame(list1, copy1);
        assertTrue(sut.deepEquals(list1, copy1));
        
        ListNode copy2 = sut.deepCopy(list4);
        assertNotSame(list4, copy2);
        assertNotSame(list4.next, copy2.next);
        assertNotSame(list4.next.next, copy2.next.next);
        assertTrue(sut.deepEquals(list4, copy2));
    }

 @Test
    public void append() {

        assertNull(sut.append(null, null));
        assertTrue(sut.deepEquals(list1, sut.append(list1, null)));
        assertTrue(sut.deepEquals(list1, sut.append(null, list1)));

        ListNode first = new ListNode(element1, new ListNode(element2));
        ListNode second = new ListNode(element3, new ListNode(element4));
        ListNode expected = new ListNode(element1, new ListNode(element2, new ListNode(element3, new ListNode(element4))));
        assertTrue(sut.deepEquals(expected, sut.append(first, second)));
    }

     @Test
    public void map() {

        assertNull(sut.map(null, addOne));

        ListNode expected = new ListNode(element3 + 1, new ListNode(element1 + 1, new ListNode(element2 + 1, new ListNode(element1 + 1))));

        assertTrue(sut.deepEquals(expected, sut.map(list4, addOne)));
    }

    @Test
    public void reduce() {

        assertEquals(0, sut.reduce(null, add, 0));
        assertEquals(element3 + element1 + element2 + element1, sut.reduce(list4, add, 0));
    }

    @Test
    public void flatten() {

        assertNull(sut.flatten(null));
        assertTrue(sut.deepEquals(list4, sut.flatten(new ListNode(list4))));

        ListNode first = new ListNode(element1, new ListNode(element2));
        ListNode second = new ListNode(element3, new ListNode(element4));
        ListNode input = new ListNode(first, new ListNode(second, null));
        ListNode expected = new ListNode(element1, new ListNode(element2, new ListNode(element3, new ListNode(element4))));
        assertTrue(sut.deepEquals(expected, sut.flatten(input)));
    }

    @Test
    public void contains() {

        assertFalse(sut.contains(null, element1));
        assertTrue(sut.contains(list1, element1));
        assertFalse(sut.contains(list1, element2));
        assertFalse(sut.contains(list3, element3));
        assertTrue(sut.contains(list4, element3));
    }

   @Test
    public void containsDuplicates() {
	   
        assertFalse(sut.containsDuplicates(null));
        assertFalse(sut.containsDuplicates(list1));
        assertFalse(sut.containsDuplicates(list2));
        assertTrue(sut.containsDuplicates(list3));
        assertTrue(sut.containsDuplicates(list4));
    }

    @Test
    public void isCircular() {

        assertFalse(sut.isCircular(null));
        assertFalse(sut.isCircular(list1));
        assertFalse(sut.isCircular(list4));

        ListNode cycle1 = new ListNode(element1);
        cycle1.next = cycle1;

        ListNode last2 = new ListNode(element3);
        ListNode cycle2 = new ListNode(element1, last2);
        last2.next = cycle2;

        ListNode last3 = new ListNode(element3);
        ListNode cycle3 = new ListNode(element1, new ListNode(element2, last3));
        last3.next = cycle3;

        ListNode cyclic = new ListNode(element1, cycle3);
        
        assertTrue(sut.isCircular(cycle1));
        assertTrue(sut.isCircular(cycle2));
        assertTrue(sut.isCircular(cycle3));
        assertFalse(sut.isCircular(cyclic));
    }

    @Test
    public void containsCycles() {
    	 
        assertFalse(sut.containsCycles(null));
        assertFalse(sut.containsCycles(list1));
        assertFalse(sut.containsCycles(list4));

        ListNode cycle1 = new ListNode(element1);
        cycle1.next = cycle1;

        ListNode last2 = new ListNode(element3);
        ListNode cycle2 = new ListNode(element1, new ListNode(element2, last2));
        last2.next = cycle2;

        ListNode cyclic = new ListNode(element1, cycle2);

        assertTrue(sut.containsCycles(cycle1));
        assertTrue(sut.containsCycles(cycle2));
        assertTrue(sut.containsCycles(cyclic));
    }

    @Test
    public void sort() {

        assertNull(sut.sort(null, int_comparator));
        ListNode list_a = new ListNode(3);
        ListNode list_b = new ListNode(3, new ListNode(-2));
        ListNode list_c = new ListNode(3, new ListNode(-2, new ListNode(-14)));
        assertTrue(sut.deepEquals(list_a, sut.sort(list_a, int_comparator)));
        assertFalse(sut.deepEquals(list_b, sut.sort(list_b, int_comparator)));
        assertTrue(sut.deepEquals(new ListNode(-2, new ListNode(3)), sut.sort(list_b, int_comparator)));
        assertTrue(sut.deepEquals(new ListNode(-14, new ListNode(-2, new ListNode(3))), sut.sort(list_c, int_comparator)));
    } 

    private final ITransformation addOne = new ITransformation() {

        @Override
        public Object transform(Object element) {

            return (Integer) element + 1;
        }
    };

    private final IOperator add = new IOperator() {

        @Override
        public Object operate(Object element1, Object element2) {

            return (Integer) element1 + (Integer) element2;
        }
    };

    private final Comparator int_comparator = new Comparator() {

        @Override
        public int compare(Object object1, Object object2) {

            return (Integer) object1 - (Integer) object2;
        }
    };
}
