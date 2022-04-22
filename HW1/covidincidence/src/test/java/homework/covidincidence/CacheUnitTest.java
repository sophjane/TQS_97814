package homework.covidincidence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.awaitility.Awaitility.await;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CacheUnitTest {

    private Cache cache;

    private final int TTL = 5;

    @BeforeEach
    public void init() {
        cache = new Cache(TTL, 6);
    }

    @AfterEach
    public void tearDown() {
        cache = null;
    }

    @Test
    @DisplayName("Cache has size 0 on construction")
    public void cacheIsEmptyTest() {
        assertEquals(0, cache.size());
    }

    @Test
    @DisplayName("Cache has correct TTL on construction")
    public void cacheHasCorrectTtlTest() {
        assertEquals(TTL*1000, cache.getTtl());
    }

    @Test
    @DisplayName("Cache has 0 hits and 0 misses on constrution") 
    public void cacheHasZeroHitsAndZeroMissesTest() {
        assertEquals(0, cache.getHits());
        assertEquals(0, cache.getMisses());
    }

    @Test
    @DisplayName("After putting n elements in an empty cache, the size of the cache is n")
    public void  cacheCorrectSizeAfterPutTest() {
        cache.put("one", "1");
        assertEquals(1, cache.size());
    }

    @Test
    @DisplayName("After putting an element in cache, it can be retrieved before ttl")
    public void cacheHasElementPutInCacheBeforeTtl() {
        cache.put("one", "1");
        assertEquals("1", cache.get("one"));
        assertEquals(1, cache.getHits());
        assertEquals(0, cache.getMisses());
    }

    @Test
    @DisplayName("After putting an element in cache, it cannot be retrieved after ttl")
    public void cacheDoesNotHaveElementPutInCacheAfterTtl() {
        cache.put("one", "1");
        await().until(() -> cache.size() == 0);
        assertEquals(null, cache.get("one"));
        assertEquals(0, cache.getHits());
        assertEquals(1, cache.getMisses());
    }

    @Test
    @DisplayName("After putting n elements in an empty cache, the cache will be with size 0 after ttl")
    public void cacheIsSizeZeroAfterTTLOfElements() {
        cache.put("one", "1");
        cache.put("two", "2");
        await().until(() -> cache.size() == 0);
        assertEquals(null, cache.get("one"));
        assertEquals(null, cache.get("two"));
    }
}
