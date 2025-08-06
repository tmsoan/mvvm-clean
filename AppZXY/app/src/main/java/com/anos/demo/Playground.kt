package com.anos.demo

import android.R.string




fun main() {
    println("------------")

    val nums = intArrayOf(1, 1, 2) // Input array
    val expectedNums = intArrayOf(1, 2) // The expected answer with correct length

    val k = removeDuplicates(nums) // Calls your implementation

    assert(k == expectedNums.size)
    for (i in 0 until k) {
        assert(nums[i] == expectedNums[i])
    }
}

fun removeDuplicates(nums: IntArray): Int {
    return 1
}


abstract class Xe(val number: Int = 0) {

}

 class Oto : Xe(number = 4), IMoving, ISample {

    override fun run() {
        TODO("Not yet implemented")
    }

    override fun run1() {
        TODO("Not yet implemented")
    }
}

interface IMoving {
    fun run()
}

interface ISample {
    fun run1()
}