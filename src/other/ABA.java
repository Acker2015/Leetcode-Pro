package other;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {
	private static AtomicInteger atomicInt = new AtomicInteger(100);
	private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<Integer>(100, 0);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}