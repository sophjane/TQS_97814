package homework.covidincidence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cache {

	private final int ttl;

	private final HashMap<String, CacheObject> cacheMap;

	protected class CacheObject {
		public long creationTime = System.currentTimeMillis();
		public String value;
 
		protected CacheObject(String value) {
			this.value = value;
		}
	}

	public Cache(int ttl, int maxSize) {
		cacheMap = new HashMap<>(maxSize);
		this.ttl = ttl;

		if(ttl > 0) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(ttl/2);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
						cacheCleanup();
					}
				}
			});
			t.setDaemon(true);
			t.start();
		}
	}

	public String get(String key) {
		synchronized(cacheMap) {
			CacheObject c;
			c = (CacheObject) cacheMap.get(key);
			if(c != null) 
				return c.value;
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
 
	public int size() {
		synchronized (cacheMap) {
			return cacheMap.size();
		}
	}


	public void cacheCleanup() {
		long now = System.currentTimeMillis();
		ArrayList<String> keysToDelete = new ArrayList<String>((cacheMap.size() / 2) + 1);


		synchronized(cacheMap) {
			Iterator it = cacheMap.entrySet().iterator();
			CacheObject c = null;

	
			while(it.hasNext()) {
				Map.Entry me = (Map.Entry)it.next();
				String key = (String)me.getKey();
				c = (CacheObject)me.getValue();
				
	
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