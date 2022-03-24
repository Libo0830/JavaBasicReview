package com.pool;

import java.io.*;
import java.util.concurrent.*;

/**
 * 使用线程池复制文件
 */
public class ThreadPoolToCopyFileExample {

//    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 15, 200, TimeUnit.MILLISECONDS,
//            new ArrayBlockingQueue<Runnable>(10));

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final String targetUrl = "D://testTarget";
    private static final String resourceUrl = "D://testResources";

    public static void main(String[] args) {
        dealFile(resourceUrl, targetUrl);
        executorService.shutdown();
//        executor.shutdown();
    }
    private static boolean dealFile(String resourceFilePath, String targetFilePath){
        File file = new File(resourceFilePath);
        boolean result = file.isDirectory();
        if (result){
            createTargetFileDic(targetFilePath);
            String[] fileNames = file.list();
            if (fileNames != null && fileNames.length > 0){
                String resource = "";
                String target = "";
                for (String fileName : fileNames) {
                    resource = resourceFilePath + "//" + fileName;
                    target = targetFilePath + "//" + fileName;
                    System.out.println(resource + "---" + target);
                    boolean flag = dealFile(resource, target);
                    if (!flag){
                        downFile(resource, target);
                    }
                }
            }
        }else{
            downFile(resourceFilePath, targetFilePath);
        }
        return result;
    }

    private static void createTargetFileDic(String targetFilePath) {
        File targetFile = new File(targetFilePath);
        boolean existis = targetFile.exists();
        if (!existis) {
            targetFile.mkdir();
        }
    }

    private static void downFile(String resourceFilePath, String targetFilePath) {
        executorService.submit(new MyTask(resourceFilePath, targetFilePath));
//        executor.submit(new MyTask(resourceFilePath, targetFilePath));
//        System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
//                executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
    }

}

class MyTask implements Runnable{

    private String resourceUrl;
    private String targetUrl;

    public MyTask(String resourceUrl, String targetUrl){
        this.resourceUrl = resourceUrl;
        this.targetUrl = targetUrl;
    }

    @Override
    public void run() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(resourceUrl);
            fos = new FileOutputStream(targetUrl);
            byte[] bytes = new byte[1024];
            int len = 0 ;
            while ((len=fis.read(bytes)) != -1){
                fos.write(bytes, 0 , len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null){
                    fis.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
