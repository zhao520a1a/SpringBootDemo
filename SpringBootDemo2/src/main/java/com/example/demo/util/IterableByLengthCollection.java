package com.example.demo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IterableByLengthCollection<T> implements Iterable<Collection<T>>{

	private Collection<T> collection;
	private int stepSize;
	private Class<?> myClass;
	private int index = 0;
	
	public IterableByLengthCollection() {
		super();
		this.index = 0;
	}

	public IterableByLengthCollection(Collection<T> collection) {
		super();
		this.collection = collection;
		this.stepSize = 1;
		this.index = 0;
	}
	
	public IterableByLengthCollection(Collection<T> collection,int stepSize,Class<?> myClass) {
		super();
		this.collection = collection;
		this.stepSize = stepSize;
		this.myClass = myClass;
		this.index = 0;
	}

	@Override
	public Iterator<Collection<T>> iterator() {
		return new Iterator<Collection<T>>() {
			
			@Override
			public boolean hasNext() {
				//System.out.println("index is " + index);
				return collection.size() > index;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Collection<T> next() {
				Collection<T> temp = null;
				try {
					temp = (Collection<T>) myClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				int count = -1;
				//System.out.println("count is " + count);
				Iterator<T> iterator = collection.iterator();
				
				while(iterator.hasNext()){
					if(count != -1){
						count++;
						iterator.next();
					}else{
						count++;
						//temp.add(iterator.next());
					}
					//System.out.print("next is " + iterator.next());
					
					while( (count >= index) && (count < (index + stepSize) ) ){
						//System.out.println("count:" + count + ",index:" + index + ",size is " + collection.size());
						if(iterator.hasNext()){
							T t = iterator.next();
							temp.add(t);
							count++;
						}else{
							//System.out.println("break");
							index = collection.size();
							break;
						}
					}
					if(count >= (index + stepSize) ){
						index += stepSize;
						break;
					}
				}
				/*for(int i = index;i < index + stepSize;i++){
					if(collection.iterator().hasNext()){
						temp.add(collection.iterator().next());
					}
				}
				index += stepSize;*/
				return temp;
			}
		};
	}

	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}

	public int getStepSize() {
		return stepSize;
	}

	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}
	
	public Class<?> getMyClass() {
		return myClass;
	}

	public void setMyClass(Class<?> myClass) {
		this.myClass = myClass;
	}
	
	@Override
	public String toString() {
		return "IterableByLengthCollection [collection=" + collection
				+ ", stepSize=" + stepSize + ", myClass=" + myClass + "]";
	}

	public static void main(String[] args) {
		List<Integer> ls = new ArrayList<>();
		for(int i = 0;i < 3;i++){
			ls.add(i);
		}


		System.out.println("ls is " + ls);
		IterableByLengthCollection<Integer> iblc = new IterableByLengthCollection<>(ls, 5,ArrayList.class);
		for(Collection<Integer> c : iblc){
			System.out.println(c);
			Iterator<Integer> iterator = c.iterator();
			while(iterator.hasNext()){
				System.out.print( iterator.next() + ",");
			}
			System.out.println();
			System.out.println("----------------------------------");
		}
	}

}
