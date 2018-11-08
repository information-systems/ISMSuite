package org.architecturemining.ismodeler.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MultiSet<T> implements Collection<T> {

	private class LinkedItem {
		private T object;
		private int count = 1;
		
		private LinkedItem next;
		
		public LinkedItem next() {
			return next;
		}
		
		public void setNext(LinkedItem next) {
			this.next = next;
		}
		
		public boolean hasNext() {
			return (next != null);
		}
		
		public LinkedItem(T object) {
			this.object = object;
			count = 1;
		}
		
		public int count() {
			return count;
		}
		
		public int push() {
			this.count++;
			return count;
		}
		
		public boolean pop() {
			if (count <= 1) {
				return false;
			}
			count--;
			return true;
		}
		
		@Override
		public int hashCode() {
			return object.hashCode();
		}
		
		public boolean equals(Object o) {
			return object.equals(o);
		}
	}
	
	private LinkedItem root = null;
	private int size = 0;
	private int uniquecount = 0;
	
	@Override
	public boolean add(T e) {
		if (root == null) {
			root = new LinkedItem(e);
			size++;
			uniquecount++;
			return true;
		}
		LinkedItem node = root;
		LinkedItem last = null;
		while( node != null) {
			if (node.equals(e)) {
				node.push();
				size++;
				return true;
			}
			last = node;
			node = node.next();
		}
	
		// node is the last one, and not found!
		LinkedItem n = new LinkedItem(e);
		last.setNext(n);
		size++;
		uniquecount++;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for(T o: c) {
			add(o);
		}
		return true;
	}

	@Override
	public void clear() {
		root = null;		
	}

	@Override
	public boolean contains(Object o) {
		LinkedItem n = root;
		while(n.hasNext()) {
			if (n.equals(o)) {
				return true;
			}
			n = n.next();
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o: c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
		
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		LinkedItem node = root;
		LinkedItem cur = null;
		while(node != null) {
			if (node.equals(o)) {
				if (!node.pop()) {
					// we need to remove it from the linked list...
					cur.setNext(node.next);
					node = cur;
					size--;
					uniquecount--;
					return true;
				} else {
					size--;
					return true;
				}
			}
			node = node.next();
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for(Object o: c) {
			changed = remove(o);
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return size;
	}
	
	public int uniqueSize() {
		return uniquecount;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
