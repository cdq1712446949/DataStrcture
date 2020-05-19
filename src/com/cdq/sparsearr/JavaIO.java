package com.cdq.sparsearr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ：ヅてＤＱ
 * @date ：Created in 2020/5/18 16:09
 * @description：
 * @modified By：
 * @version: 1.0.1
 */
public class JavaIO {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\map.data");
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[50];
        int count = in.read(bytes);
        System.out.println(new String(bytes, 0, count));
    }

}
