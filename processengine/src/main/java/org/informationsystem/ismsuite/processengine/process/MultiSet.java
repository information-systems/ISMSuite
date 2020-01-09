package org.informationsystem.ismsuite.processengine.process;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class MultiSet<T> implements Set<T> {
	
	private class MultiSetItem {
		private T object;
		private int count;
		
		@SuppressWarnings("unused")
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
			@SuppressWarnings("unchecked")
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
		
		@SuppressWarnings("unused")
		public void increase() {
			increase(1);
		}
		
		public void increase(int count) {
			if (count > 0) this.count += count;
		}
	}
	
	private HashMap<T, MultiSetItem> items = new HashMap<>();
	private int totalCount = 0;

	@Override
	public boolean add(T obj) {
		return add(obj, 1);
	}
	
	public boolean add(T obj, int count) {
		if (obj == null || count < 1) {
			return false;
		}
		this.totalCount += count;
		
		if (items.containsKey(obj)) {
			items.get(obj).increase(count);
			return true;
		}
		else {
			items.put(obj, new MultiSetItem(obj, count));
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
	
	public Set<T> getUnique() {
		return items.keySet();
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
		MultiSetItem item = items.get(o);
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

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder b = new StringBuilder();
		
		for(MultiSetItem it: items.values()) {
			b.append(", ");
			b.append(it.getObject().toString());
			b.append(": ");
			b.append(it.getCount());
		}
		
		return "[" + b.substring(1) + " ]";
	}
}
