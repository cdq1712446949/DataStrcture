package com.cdq.sparsearr;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;

/**
 * @author ：ヅてＤＱ
 * @date ：Created in 2020/5/18 15:08
 * @description：稀疏数组
 * @modified By：
 * @version: 1.0.1
 */
public class SparseArray {

    /**
     * 将记录旗子位置的二维数组转换成稀疏数组后存储在磁盘文件中
     * 然后读取磁盘文件并且恢复成原始二维数组
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //将期盼映射成一个二维数组
        int[][] chessArr = new int[11][11];
        //添加棋子位置
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][4] = 2;

        //输出二维数组
        System.out.println("原始二维数组:");
        for (int[] arr : chessArr) {
            for (int temp : arr) {
                System.out.printf("%d \t", temp);
            }
            System.out.println();
        }

        //将二维数组转换成稀疏数组
        int sum = 0;
        for (int[] arr : chessArr) {
            for (int temp : arr) {
                if (temp != 0) {
                    sum++;
                }
            }
        }
        System.out.println("原始数组有效数据数量：" + sum);

        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        //稀疏数组添加数据
        int count = 0;
        for (int i=0;i<chessArr.length;i++) {
            for (int j=0;j<chessArr[0].length;j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr[i][j];
                }
            }
        }

        System.out.println("稀疏数组：");
        for (int[] arr : sparseArr) {
            for (int temp : arr) {
                System.out.printf("%d \t", temp);
            }
            System.out.println();
        }

        //稀疏数组存储到文件中，并且读取该文件
        File file = new File("D:\\map.data");
        FileOutputStream out =new FileOutputStream(file);
        for (int[] arr : sparseArr) {
            for (int temp : arr) {
                out.write((String.valueOf(temp)+"\t").getBytes());
            }
            out.write("\n".getBytes());
        }
        out.close();

        FileInputStream in = new FileInputStream(file);
        byte[] tempBytes = new byte[1024];
        int result = in.read(tempBytes);
        System.out.println("result:"+result);
        String fileStr = new String(tempBytes,0,result);
        System.out.println("文件中读取的稀疏数组:\n"+fileStr);
        in.close();

        System.out.println("根据制表符分割后的字符串数组");
        String[] fielStrArr = fileStr.split("\n");
        for (String tempStr :fielStrArr){
            System.out.println(tempStr);
        }

        int[][] sparseArray = new int[fielStrArr.length][3];
        int count2= 0;
        for (String tempStr :fielStrArr){
            String[] strArr = tempStr.split("\t");
            int[] tempNumArr = new int[3];
            int i=0;
            for (String tempNumStr:strArr){
                int tempNum = Integer.valueOf(tempNumStr);
                tempNumArr[i] =tempNum;
                i++;
            }
            sparseArray[count2]=tempNumArr;
            count2++;
        }
        System.out.println("文件中读取的稀疏数组：");
        for (int[] arr : sparseArray) {
            for (int temp : arr) {
                System.out.printf("%d \t", temp);
            }
            System.out.println();
        }

        //稀疏数组还原成原始二维数组
        int[][] chessArray = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i=0;i<sum;i++){
            int j=i+1;
            int[] tempArr = sparseArr[j];
            chessArray[tempArr[0]][tempArr[1]]=tempArr[2];
        }
        System.out.println("还原后的原始二维数组:");
        for (int[] arr : chessArray) {
            for (int temp : arr) {
                System.out.printf("%d \t", temp);
            }
            System.out.println();
        }
    }

}
