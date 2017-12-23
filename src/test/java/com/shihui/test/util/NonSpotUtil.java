package com.shihui.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.shihui.commons.ApacheHttpClient;
import com.shihui.commons.utils.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongxp on 2017/6/6.
 */
public class NonSpotUtil {
    private static final String PARTNER_URL;
    private static final String GONGXIAO_SUPPLIERS_URL;

    private static final String NON_SPOT_GOODS_URL;//商品查询URL
    private static final String GOODS_ID_KEY = "g_id";
    private static final String PARAM_GOODS_IDS_KEY = "pids";
    private static final String GOODS_SPECS_KEY = "g_specs";
    private static final String API_RESULT_KEY = "result";
    private static final String API_STATUS_KEY = "apistatus";
    private static final String API_SUCCESS = "1";

    private static ApacheHttpClient httpClient = new ApacheHttpClient(30000, 30000);

    static {
        PARTNER_URL = "http://test.paas.api.intra.hiwemeet.com/api/common/channels";
        GONGXIAO_SUPPLIERS_URL = "http://test.gongxiao.api.intra.hiwemeet.com/api/unstock/suppliers/get_multi_by_ids";
        NON_SPOT_GOODS_URL = "http://test.v2.goods.17shihui.com/api/internal/supplier";
    }


    /**
     * 渠道名称缓存
     */
    private final static LoadingCache<Integer, String> PARTNER_CACHE = CacheBuilder.newBuilder()
            // 设置并发级别
            .concurrencyLevel(10)
            // 缓存24小时
            .expireAfterWrite(24 * 60, TimeUnit.MINUTES)
            // 初始化大小500
            .initialCapacity(500)
            // 最大缓存 5000
            .maximumSize(5000)
            .build(new CacheLoader<Integer, String>() {
                @Override
                public String load(Integer parter) throws Exception {
                    return NonSpotUtil.getPartnerName(parter);
                }
            });

    public static String getPartnerFromCache(Integer partner) {
        try {
            return PARTNER_CACHE.get(partner);
        } catch (ExecutionException e) {
            System.err.println("查询渠道名称错误  partner:{}, e:{}" + new Object[]{partner, e.getMessage()});
        }
        return "实惠";
    }

    /**
     * 获取渠道名称
     *
     * @param partner
     * @return
     */
    public static String getPartnerName(Integer partner) {
        try {
            String response = httpClient.buildGet(PARTNER_URL).withParam("ids", partner).executeAsyncString();
            System.err.println("查询渠道,url:" + PARTNER_URL + ",参数:" + partner + " 返回结果： " + response);
            JSONObject responseJson = JSON.parseObject(response);
            if (responseJson.getString("apistatus").equals("1")) {
                JSONArray pArray = responseJson.getJSONArray("result");
                if (pArray != null && pArray.size() == 1) {
                    return pArray.getJSONObject(0).getString("abbrev");
                }
            }
            return "实惠";
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 商户名称缓存
     */
    public final static LoadingCache<Long, String> SUPPLIER_NAME_CACHE = CacheBuilder.newBuilder()
            // 设置并发级别
            .concurrencyLevel(10)
            // 缓存24小时
            .expireAfterWrite(24 * 60, TimeUnit.MINUTES)
            // 初始化大小500
            .initialCapacity(500)
            // 最大缓存 5000
            .maximumSize(5000)
            .build(new CacheLoader<Long, String>() {
                @Override
                public String load(Long merchantId) throws Exception {
                    return getSupplierNameById(merchantId);
                }
            });


    /**
     * 从缓存换的商户名称
     *
     * @param merchantId
     * @return
     */
    public static String getSupplierNameFromCache(Long merchantId) {
        try {
            return SUPPLIER_NAME_CACHE.get(merchantId);
        } catch (ExecutionException e) {
            System.err.println("查询渠道名称错误  merchantId:{}, e:{}" + new Object[]{merchantId, e.getMessage()});
        }
        return "";
    }

    public static String getSupplierNameById(long merchantId) {
        try {
            String executeAsyncString = httpClient.buildPost(GONGXIAO_SUPPLIERS_URL).withParam("supplier_ids", merchantId).executeAsyncString();
            JSONObject object = JSONObject.parseObject(executeAsyncString);
            if (null == object) {
                throw new Exception("调用/api/unstock/suppliers/get_multi_by_ids,获取查询接口异常！");
            }
            if (0 == object.getIntValue("apistatus")) {
                System.err.println("调用/api/unstock/suppliers/get_multi_by_ids,merchantId:" + merchantId + ",errorMsg:" + object.getString("errorMsg"));
                return "";
            }

            JSONObject rtObj = object.getJSONObject("result");
            if (null == rtObj) {
                System.err.println("/api/unstock/suppliers/get_multi_by_ids,result:" + rtObj);
                throw new Exception("调用商户信息查询接口异常！response:" + rtObj);
            }

            JSONObject merchantObj = rtObj.getJSONObject("" + merchantId);
            String name = "";
            if (null != merchantObj)
                name = merchantObj.getString("supplier_name");
            return name;
        } catch (Exception e) {
            System.err.println("调用商户信息查询接口异常！merchantId:" + merchantId + ",error:" + e.getMessage());
            return "";
        }
    }

    /**
     * 批量查询商品规格
     */
    public static Map<Long, String> goodsSpecifications(List<Long> goodsIds) {
        Map<Long, String> goodsSpecifications = Maps.newHashMap();
        Map<String, Object> params = Maps.newHashMap();
        params.put(PARAM_GOODS_IDS_KEY, StringUtils.join(goodsIds, ","));
        String response = httpClient.buildGet(NON_SPOT_GOODS_URL).withParam(params).executeAsyncString();
        if (!StringUtils.isEmpty(response)) {
            JSONObject responseJson = null;
            try {
                responseJson = JSON.parseObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != responseJson && responseJson.getString(API_STATUS_KEY).equals(API_SUCCESS)) {
                JSONArray goodsArray = responseJson.getJSONArray(API_RESULT_KEY);
                for (int i = 0; i < goodsArray.size(); i++) {
                    Long goodsId = goodsArray.getJSONObject(i).getLong(GOODS_ID_KEY);
                    String specs = goodsArray.getJSONObject(i).getString(GOODS_SPECS_KEY);
                    goodsSpecifications.put(goodsId, specs);
                }
            }
        }

        return goodsSpecifications;
    }

    public static String joins(List<String> originList, String splitStr) {
        if (null == originList) {
            return "";
        }
        if ("".equals(splitStr)) {
            splitStr = "-";
        }
        StringBuffer sBuffer = new StringBuffer();
        int i = 0;
        for (String str : originList) {
            if (i > 0) {
                sBuffer.append("-");
            }
            if (null != str && !"".equals(str)) {
                sBuffer.append(str);
                i++;
            }
        }
        return sBuffer.toString();
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Map map) {
        return !NonSpotUtil.isEmpty(map);
    }

    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }

    public static boolean isNotEmpty(Collection coll) {
        return !NonSpotUtil.isEmpty(coll);
    }
}