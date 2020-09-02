package com.xyshzh.janusgraph.utils;

import java.io.*;
import java.util.*;

public class FileMakeCardId {

    private static final int NUM = 1000 ;
    private static Map<Integer,String> cardids = new HashMap<Integer,String>();
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
     * {"label":"Person","cardid":"1","name":"bob"}
     */
    public static String getVertexLineData(int num){
        String label = "Person";
        String cardid = StringUtils.getIdNo(true);
        cardids.put(num,cardid);
        String name = ChineseNameMake.getChineseName();
        return new StringBuilder("{\"label\"").append(":\""+label+"\",").append("\"cardid\":").
                append("\""+cardid+"\",").append("\"name\":").append("\""+name+"\"}").toString();
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
     * {"cardidFrom":1,"cardidTo":2,"label":"OWN"}
     * @return
     */
    public static String getEdgeLineData(int start,int end){
        int randomFrom = getRandomInt(start,100);
        int randomTo = getRandomInt(start,100);
        String cardidFrom = cardids.get(randomFrom);
        String cardidTo = cardids.get(randomTo);
        String[] label = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20"};
        int randomLabelid = getRandomInt(0,6);
        String str ;
        if(randomFrom == randomTo){
            cardidTo = cardids.get(randomTo+1);
            str = new StringBuilder("{\"cardidFrom\"").append(":\""+cardidFrom+"\",").append("\"cardidTo\":").
                    append("\""+cardidTo+"\",").append("\"label\":").append("\""+label[randomLabelid]+"\"}").toString();
        }else{
            str = new StringBuilder("{\"cardidFrom\"").append(":\""+cardidFrom+"\",").append("\"cardidTo\":").
                    append("\""+cardidTo+"\",").append("\"label\":").append("\""+label[randomLabelid]+"\"}").toString();
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
