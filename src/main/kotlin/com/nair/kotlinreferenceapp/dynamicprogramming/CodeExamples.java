package com.nair.kotlinreferenceapp.dynamicprogramming;

import java.util.ArrayList;

public class CodeExamples {
    public int recursiveSum(int n) {
        int result = 0;
        if (n == 0) {
            return result;
        } else {
            return n + recursiveSum(n - 1);
        }
    }

    public int tailRecursiveSum(int n, int result) {
        if (n == 0) {
            return result;
        } else {
            return tailRecursiveSum(n - 1, result + n);
        }
    }

    public ArrayList<Integer> fibonacci(int n,  ArrayList<Integer> fibs) {
        if (fibs.isEmpty()) {
            fibs.add(0);
            fibs.add(1);
            fibs.add(1);
        }

        if (fibs.size() >= n ) {
            return fibs;
        }else{
            int currentOffset  = fibs.size() - 1;
            fibs.add(fibs.get(currentOffset) +fibs.get(currentOffset - 1));
            return fibonacci(n, fibs);
        }
    }
    public static void main(String... args) {
        CodeExamples obj = new CodeExamples();

        System.out.println("Recursive sum::" + obj.recursiveSum(5));
        System.out.println("Recursive sum::" + obj.tailRecursiveSum(5, 0));

        ArrayList result = obj.fibonacci(100,new ArrayList<>());
        System.out.println("result" + result);
    }
}
