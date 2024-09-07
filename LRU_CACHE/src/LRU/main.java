package LRU;

public class main {

	public static void main(String[] args) {
		  LRU_PROGRAM cache = new LRU_PROGRAM(3);
	        cache.put(1, 10);
	        cache.put(2, 20);
	        cache.put(3, 30);
	        System.out.println("Value for key 1: " + cache.get(1));
	        cache.put(4, 40);
	        System.out.println("Value for key 2: " + cache.get(2));
	        cache.removeKey(1);
	        cache.clearCache();
	}

}
