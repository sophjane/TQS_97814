package homework.covidincidence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cache {

	private final int ttl;

	private final HashMap<String, CacheObject> cacheMap;

	private int hits ;
	private int misses;


	protected class CacheObject {
		private long creationTime = System.currentTimeMillis();
		private String value;
 
		protected CacheObject(String value) {
			this.value = value;
		}
	}

	public Cache(int ttl, int maxSize) {
		cacheMap = new HashMap<>(maxSize);
		this.ttl = ttl*1000;
		this.hits = 0;
		this.misses = 0;

		if(ttl > 0) {

			Runnable task = () -> {
				while (true) {
					if(ttl <= 0) {
						break;
					}

					try {
						Thread.sleep(ttl);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
						Thread.currentThread().interrupt();
					}
					cacheCleanup();
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.start();
		}
	}

	public String get(String key) {
		synchronized(cacheMap) {
			CacheObject c;
			c = cacheMap.get(key);
			if(c != null) {
				this.hits++;
				return c.value;
			}
			this.misses++;
			return null;
		}
	}

	public void put(String key, String value) {
		synchronized(cacheMap) {
			cacheMap.put(key, new CacheObject(value));
		}
	}

	public void remove(String key) {
		synchronized (cacheMap) {
			cacheMap.remove(key);
		}
	}
 
	public int getSize() {
		synchronized (cacheMap) {
			return cacheMap.size();
		}
	}

	public int getTtl() {
		return this.ttl;
	}

	public int getMisses() {
		return this.misses;
	}

	public int getHits() {
		return this.hits;
	}

	public String getCacheInfo() {
		return String.format("This cache contains %d elements with %d of TTL.\nNº hits: %d, nº misses: %d", getSize(), getTtl(), getHits(), getMisses());
	}


	public void cacheCleanup() {
		long now = System.currentTimeMillis();
		ArrayList<String> keysToDelete = new ArrayList<>((cacheMap.size() / 2) + 1);


		synchronized(cacheMap) {
			Iterator<Entry<String, CacheObject>> it = cacheMap.entrySet().iterator();
			CacheObject c = null;

	
			while(it.hasNext()) {
				Map.Entry<String, CacheObject> me = it.next();
				String key = me.getKey();
				c = me.getValue();
				
	
				if(c != null && (now > (ttl+c.creationTime))) {
					keysToDelete.add(key);
				}	
			}
		}

		for(String k : keysToDelete) {
			synchronized(cacheMap) {
				cacheMap.remove(k);
			}
			Thread.yield();
		}


	}

}