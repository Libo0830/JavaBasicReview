package com.basic.use;

import java.io.*;

public class ThreadCopyFileExample {

    public static void main(String[] args) {
        File resourcesFile = new File("D:\\testResources\\券影城查询接口修改功能节点.txt");
        File targetFile = new File("D:\\testResources\\券影城查询接口修改功能节点1.txt");
        Thread t1 = new Thread(new CopyFile(resourcesFile, targetFile, "线程1"));
        Thread t2 = new Thread(new CopyFile(resourcesFile, targetFile, "线程2"));
        Thread t3 = new Thread(new CopyFile(resourcesFile, targetFile, "线程3"));
        Thread t4 = new Thread(new CopyFile(resourcesFile, targetFile, "线程4"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}

class CopyFile extends Thread{

    private File resourcesFile;
    private File targetFile;
    private String name;

    public CopyFile(File resourcesFile, File targetFile, String name){
        this.resourcesFile = resourcesFile;
        this.targetFile = targetFile;
        this.name = name;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(resourcesFile)));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            String nextTxt = null;
            while ((nextTxt = br.readLine()) != null){
                System.out.println(Thread.currentThread().getName()+"--线程名称："+name+"--读取内容："+nextTxt);
                bw.write(nextTxt);
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null){
                    br.close();
                }
                if (bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
