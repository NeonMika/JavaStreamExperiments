package at.neon.stream.util;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.accessibility.internal.resources.accessibility;

public class PrintOnConstruction implements Comparable<PrintOnConstruction> {
	private static AtomicInteger i;
	
	public int val;
	
	static {
		i = new AtomicInteger(0);
	}
	
	public PrintOnConstruction() {
		val = i.getAndIncrement();
		System.out.println("Created " + val);
		System.out.flush();
	}
	
	public static Iterator<PrintOnConstruction> getInfiniteIterator() {
		return new Iterator<PrintOnConstruction>() {
			
			@Override
			public PrintOnConstruction next() {
				return new PrintOnConstruction();
			}
			
			@Override
			public boolean hasNext() {
				return true;
			}
		};
	}

	@Override
	public int compareTo(PrintOnConstruction other) {
		return this.val - other.val;
	}
}
