package com.nair.kotlinreferenceapp.dynamicprogramming


class Examples {

    fun recursiveSum(n: Int): Int {
        var result = 0

        if (n != 0) {
            result = n + recursiveSum(n - 1);
        }
        return result
    }


}

fun main(args: Array<String>) {
    println("Hello World")

    val examples = Examples()
    val result = examples.recursiveSum(10000)
    println("Result::" + result)
}