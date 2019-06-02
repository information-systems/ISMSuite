/**
 * 
 */
package test.org.informationsystem.ismodeler.process;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.informationsystem.ismodeler.process.MultiSet;
import org.junit.jupiter.api.Test;

/**
 * @author jmw
 *
 */
class MultiSetTester {

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#add(java.lang.Object)}.
	 */
	@Test
	void testAdd() {
		MultiSet<String> ms = new MultiSet<>();
		String str = new String("Hi, I am a test!");
		
		assertEquals(0, ms.size(str));		
		ms.add(str);
		assertEquals(1, ms.size(str));
		assertEquals(1, ms.size());
		
		ms.add(str);
		assertEquals(2, ms.size(str));
		assertEquals(2, ms.size());
		
		ms.add("I am another string!");
		assertEquals(2, ms.size(str));
		assertEquals(3, ms.size());
	}

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#addAll(java.util.Collection)}.
	 */
	@Test
	void testAddAll() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		
		assertTrue(ms.isEmpty());
		
		ms.addAll(list);
		
		assertEquals(5, ms.size());
		assertEquals(3, ms.size(str1));
		assertEquals(2, ms.size(str2));
	}

	@Test
	void testClear() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		
		assertEquals(5, ms.size());
		assertEquals(3, ms.size(str1));
		assertEquals(2, ms.size(str2));
		
		ms.clear();
		
		assertFalse(ms.contains(str1));
		assertFalse(ms.contains(str2));
		
		assertEquals(0, ms.size());
		
		assertTrue(ms.isEmpty());
		
		assertEquals(0, ms.size(str1));
		assertEquals(0, ms.size(str2));
	}

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#containsAll(java.util.Collection)}.
	 */
	@Test
	void testContainsAll() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		
		assertEquals(5, ms.size());
		
		assertTrue(ms.containsAll(list));
		
		list.add(new String("En een nieuwe!"));
	
		assertFalse(ms.containsAll(list));
	}

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#iterator()}.
	 */
	@Test
	void testIterator() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		
		Iterator<String> it = ms.iterator();
		
		int count = 0;
		while(it.hasNext()) {
			String obj = it.next();
			if (!(obj.equals(str1) || obj.equals(str2))) {
				fail("Expected obj to be str1 or str2!");
			}
			count++;
		}
		
		assertEquals(2, count);
	}

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#remove(java.lang.Object)}.
	 */
	@Test
	void testRemove() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		
		assertEquals(5, ms.size());
		
		for(int i = 2 ; i >= 0 ; i--) {
			assertTrue(ms.remove(str1));
			assertEquals(i, ms.size(str1));
			
			if (i > 0) assertTrue(ms.contains(str1));
			else assertFalse(ms.contains(str1));
		}
		
		assertFalse(ms.remove(str1));
		
	}

	@Test
	void testRemoveAll() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		ms.addAll(list);
		ms.add(str1);
		
		assertEquals(5, ms.size());
		assertEquals(3, ms.size(str1));
		assertEquals(2, ms.size(str2));
		
		ms.removeAll(list);
		
		assertEquals(3, ms.size());
		assertEquals(2, ms.size(str1)); 
		assertEquals(1, ms.size(str2));
		
		ms.removeAll(list);
		
		assertEquals(1, ms.size());
		assertEquals(1, ms.size(str1)); 
		assertEquals(0, ms.size(str2));
	}

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#retainAll(java.util.Collection)}.
	 */
	@Test
	void testRetainAll() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		
		assertEquals(5, ms.size());
		
		ArrayList<String> toKeep = new ArrayList<>();
		toKeep.add(str1);
		
		ms.retainAll(toKeep);
		
		assertEquals(3, ms.size());
		assertEquals(3, ms.size(str1));
		assertEquals(0, ms.size(str2));
	}

	/**
	 * Test method for {@link org.informationsystem.ismodeler.process.MultiSet#toArray()}.
	 */
	@Test
	void testToArray() {
		ArrayList<String> list = new ArrayList<>();
		String str1 = new String("hoi");
		String str2 = new String("boe!");
		
		list.add(str1);
		list.add(str2);
		list.add(str1);
		list.add(str2);
		list.add(str1);
		
		assertEquals(5, list.size());
		
		MultiSet<String> ms = new MultiSet<>();
		ms.addAll(list);
		
		assertEquals(5, ms.size());
		
		Object[] o = ms.toArray();
		
		assertEquals(5, o.length);
		
		int str1c = 0;
		int str2c = 0;
		for(int i = 0 ; i < o.length; i++) {
			if (o[i].equals(str1)) {
				str1c++;
			}else if (o[i].equals(str2)) {
				str2c++;
			}
		}
		
		assertEquals(3, str1c);
		assertEquals(2, str2c);
		
	}

}
