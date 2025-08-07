package com.anos.demo

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
