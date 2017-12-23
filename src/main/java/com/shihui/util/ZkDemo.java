package com.shihui.util;

import org.apache.commons.io.FileUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.File;
import java.util.List;

/**
 * Created by hongxp on 2017/7/20.
 */
public class ZkDemo {

    public static void main(String[] args) {
        try {
            createOrUpdate("/zkDemo/haha", "hoho");
            read("/zkDemo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CuratorFramework CLIENT;

    private static CuratorFramework client() {
        if (CLIENT == null) {
            String zkHost = "localhost:2181";
            RetryPolicy rp = new ExponentialBackoffRetry(1000, 3);
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                    .connectString(zkHost)
                    .connectionTimeoutMs(5000)
                    .sessionTimeoutMs(5000)
                    .retryPolicy(rp);
            CuratorFramework zkClient = builder.build();
            CLIENT = zkClient;
            CLIENT.start();
        }

        return CLIENT;
    }

    public static void createOrUpdate(String path, String content) throws Exception {
        client().newNamespaceAwareEnsurePath(path).ensure(client().getZookeeperClient());
        client().setData().forPath(path, content.getBytes());
        System.out.println("添加成功！！！");
    }

    /**
     * 删除zk节点
     *
     * @param path 删除节点的路径
     **/
    public static void delete(String path) throws Exception {
        client().delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        System.out.println("删除成功!");
    }

    /**
     * 判断路径是否存在
     *
     * @param path
     **/
    public static void checkExist(String path) throws Exception {
        if (client().checkExists().forPath(path) == null) {
            System.out.println("路径不存在!");
        } else {
            System.out.println("路径已经存在!");
        }
    }

    /**
     * 读取的路径
     *
     * @param path
     **/
    public static void read(String path) throws Exception {
        String data = new String(client().getData().forPath(path), "utf-8");
        System.out.println("读取的数据:" + data);
    }

    /**
     * @param path 路径
     *             获取某个节点下的所有子文件
     */
    public static void getListChildren(String path) throws Exception {
        List<String> paths = client().getChildren().forPath(path);
        for (String p : paths) {
            System.out.println(p);
        }
    }

    /**
     * @param zkPath    zk上的路径
     * @param localpath 本地上的文件路径
     **/
    public static void upload(String zkPath, String localpath) throws Exception {
        createOrUpdate(zkPath, "");//创建路径
        byte[] bs = FileUtils.readFileToByteArray(new File(localpath));
        client().setData().forPath(zkPath, bs);
        System.out.println("上传文件成功！");
    }

}
