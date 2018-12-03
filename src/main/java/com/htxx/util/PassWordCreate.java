package com.htxx.util;

import java.util.Random;

public class PassWordCreate {
    /**
     * 获得密码
     *
     * @param len 密码长度
     * @return
     */
    public static String createPassWord(int len) {
        int random = createRandomInt();
        return createPassWord(random, len);
    }

    public static String createPassWord(int random, int len) {
        Random rd = new Random(random);
        final int maxNum = 62;
        StringBuffer sb = new StringBuffer();
        int rdGet;//取得随机数  
        char[] str = {'0', '1', '2', '3'};

        //char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int count = 0;
        while (count < len) {
            rdGet = Math.abs(rd.nextInt(maxNum));//生成的数最大为62-1
            if (rdGet >= 0 && rdGet < str.length) {
                sb.append(str[rdGet]);
                count++;
            }
        }
        return sb.toString();
    }

    public static int createRandomInt() {
        //得到0.0到1.0之间的数字，并扩大100000倍  
        double temp = Math.random() * 100000;
        //如果数据等于100000，则减少1  
        if (temp >= 100000) {
            temp = 99999;
        }
        int tempint = (int) Math.ceil(temp);
        return tempint;
    }

    public static void main(String[] args) {
        PassWordCreate pwc = new PassWordCreate();
        System.out.println(pwc.createPassWord(8));
    }
}
