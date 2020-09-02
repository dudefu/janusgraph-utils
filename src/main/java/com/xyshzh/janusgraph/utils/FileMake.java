package com.xyshzh.janusgraph.utils;

import java.io.*;
import java.util.Random;

public class FileMake {

    private static final int NUM = 100 ;
    public static void main(String[] args) throws IOException {
        getVertexFile();
        getEdgeFile();
    }

    public static void getVertexFile() throws IOException {
        File file = new File("data/Vertex.txt");

            FileOutputStream fos = new FileOutputStream(file,false);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 1; i <= NUM; i++) {
                bw.write(getVertexLineData(i));
                bw.newLine();
            }
            bw.close();
    }

    /**
     * {"label":"Person","uid":"1","name":"bob"}
     */
    public static String getVertexLineData(int strUid){
        String label = "Person";
        String uid = String.valueOf(strUid);
        String name = ChineseNameMake.getChineseName();
        return new StringBuilder("{\"label\"").append(":\""+label+"\",").append("\"uid\":").
                append("\""+uid+"\",").append("\"name\":").append("\""+name+"\"}").toString();
    }


    public static void getEdgeFile() throws IOException {
        File file = new File("data/Edge.txt");

        FileOutputStream fos = new FileOutputStream(file,false);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (int i = 1; i <= NUM; i++) {
            bw.write(getEdgeLineData(1,NUM));
            bw.newLine();
        }
        bw.close();
    }

    /**
     * {"uid1":1,"uid2":2,"label":"OWN"}
     * @return
     */
    public static String getEdgeLineData(int start,int end){
        String uid1 = String.valueOf(getRandomInt(start,100));
        String uid2 = String.valueOf(getRandomInt(start,end));
        String label = "OWN";
        String str ;
        if(uid1.equals(uid2)){
            str = new StringBuilder("{\"uid1\"").append(":\""+uid1+"\",").append("\"uid2\":").
                    append("\""+(uid2+1)+"\",").append("\"label\":").append("\""+label+"\"}").toString();
        }else{
            str = new StringBuilder("{\"uid1\"").append(":\""+uid1+"\",").append("\"uid2\":").
                    append("\""+uid2+"\",").append("\"label\":").append("\""+label+"\"}").toString();
        }
        return str;
    }

    /**
     * [x,y]
     * new Reandom().nextInt(y + 1 - x) + x
     * @param start
     * @param end
     * @return
     */
    public static Integer getRandomInt(int start,int end){
       return new Random().nextInt(end+1-start)+start;
    }
}
