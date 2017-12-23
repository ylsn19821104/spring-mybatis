package com.shihui.test.cache;

import com.shihui.test.util.NonSpotUtil;
import org.junit.Test;

/**
 * Created by hongxp on 2017/11/11.
 */
public class LocalCacheTest {
    @Test
    public void testLocalCache() {
        String name = NonSpotUtil.getSupplierNameFromCache(100001555L);
        System.err.println("name:" + name);

        NonSpotUtil.SUPPLIER_NAME_CACHE.put(100001556L, "test");
    }
}
