package algo.recursive;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 斐波那契兔子问题
 *
 * 意大利数学家斐波那契提出的一个问题:由一对兔子开始。一年后可以繁殖成多少对兔子?假定每对大兔每月能生产一对小兔，而每对小兔生长两个月就成为大兔.
 * 这个问题导致了著名的数列:1,1,2,3,5,8,13,21,34,55,89,144,233,·…它是一个线性递归数列，这个数列一般称为斐波那契数列，它的每一项称为斐波那契数
 * 1代表1对，不是1只
 *
 * @author Jun
 * 创建时间： 2020/2/12
 */
public class RabbitRecursive {


    public static void main(String[] args) throws InterruptedException {

    }


    /**
     * 利用迭代 数组来计算, 比用递归快很多
     * @param n
     * @return 第n年的兔子
     */
    public static BigInteger rabbitArray(int n){
        if(n == 1 || n == 2){
           return BigInteger.valueOf(1);
        }

        BigInteger[] arr = new BigInteger[n];
        arr[0] = BigInteger.valueOf(1);
        arr[1] = BigInteger.valueOf(1);

        for(int i = 2; i < arr.length; i++){
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }
        return arr[arr.length -1];
    }

    /**
     * 利用递归来计算
     * 差不多48时超出int范围
     * @param n
     * @return 第n年的兔子
     */
    public static int countRabbit(int n){
        if(n == 1 || n == 2){
            return 1;
        }
        return countRabbit(n-1) + countRabbit(n - 2);
    }

    /**
     * 利用递归计算阶乘
     */
    public static BigInteger factorial(int n){
        if(n == 1){
            return BigInteger.valueOf(1);
        }
        BigInteger b = BigInteger.valueOf(n);
        return factorial(n - 1).multiply(b);
    }


}
