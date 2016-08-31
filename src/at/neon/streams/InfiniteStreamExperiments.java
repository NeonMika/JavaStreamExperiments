package at.neon.streams;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import at.neon.stream.util.PrintOnConstruction;

public class InfiniteStreamExperiments {

	public static void main(String[] args) {
		Iterator<PrintOnConstruction> iterator = PrintOnConstruction.getInfiniteIterator();
		Iterable<PrintOnConstruction> iterable = () -> iterator;
		Stream<PrintOnConstruction> stream = StreamSupport.stream(iterable.spliterator(), false);

		stream.filter(o -> {
			System.out.println(String.format("Filter %d", o.val));
			System.out.flush();
			return o.val > 10;
		}).map(o -> {
			System.out.println(String.format("Map %d to %d", o.val, o.val * 2));
			System.out.flush();
			return o.val * 2;
		}).limit(10).sorted((a, b) -> {
			System.out.println(String.format("Sorted2 %d %d", a, b));
			System.out.flush();
			return a.compareTo(b);
		}).forEach(i -> System.out.println(String.format("Final %d", i)));
	}
}
