package org.informationsystem.ismodeler.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MultiSet<T> implements Set<T> {
	
	private class MultiSetItem<T> {
		private T object;
		private int count;
		
		public MultiSetItem(T object) {
			this.object = object;
			this.count = 1;
		}
		
		public MultiSetItem(T object, int count) {
			this.object = object;
			this.count = count;
		}
		
		public int getCount() {
			return count;
		}
		
		public T getObject() {
			return object;
		}
		
		public boolean isEmpty() {
			return (count == 0);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(object);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MultiSetItem other = (MultiSetItem) obj;
			return Objects.equals(object, other.object);
		}

		/**
		 * Tries to perform a decrease. Returns true if 
		 * the decrease made the item empty;
		 * @return true if count > 0
		 */
		public boolean decrease() {
			if (!isEmpty()) {
				this.count--;
			}
			return (this.isEmpty());
		}
		
		public void increase() {
			this.count++;
		}
	}
	
	private HashMap<T, MultiSetItem<T>> items = new HashMap<>();
	private int totalCount = 0;

	@Override
	public boolean add(T obj) {
		if (obj == null) {
			return false;
		}
		this.totalCount++;
		
		if (items.containsKey(obj)) {
			items.get(obj).increase();
			return true;
		}
		else {
			items.put(obj, new MultiSetItem<T>(obj));
			return true;
		}
		
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for(T item : c) {
			this.add(item);
		}
		return true;
	}

	@Override
	public void clear() {
		this.items.clear();
		this.totalCount = 0;
	}

	@Override
	public boolean contains(Object o) {
		return items.containsKey(o);
	}
	
	public int size(Object o) {
		if (!items.containsKey(o)) {
			return 0;
		} else {
			return items.get(o).getCount();
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object item: c) {
			if (!items.containsKey(item)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return items.keySet().iterator();
	}

	@Override
	public boolean remove(Object o) {
		if (!items.containsKey(o)) {
			return false;
		}
		MultiSetItem<T> item = items.get(o);
		if (item.decrease()) {
			items.remove(o);
		}
		this.totalCount--;
		
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object o: c) {
			remove(o);
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		for(T item: items.keySet()) {
			if (!collection.contains(item)) {
				this.totalCount -= items.get(item).getCount(); 
				this.items.remove(item);
			}
		}
		return true;
	}

	@Override
	public int size() {
		return totalCount;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[totalCount];
		int next = 0;
		for(T item: items.keySet()) {
			for(int i = 0 ; i < items.get(item).getCount(); i++) {
				result[next] = item;
				next++;
			}
		}
		return result;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	
}
