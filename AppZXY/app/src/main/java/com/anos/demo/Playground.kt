package com.anos.demo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun main() = runBlocking {

    println("Done!")
}

private fun doLongRunningTaskOne(): Flow<String> {
    return flow {
        delay(5000L)
        emit("One")
    }
}

private fun doLongRunningTaskTwo(): Flow<String> {
    return flow {
        delay(5000L)
        emit("Two")
    }
}

fun removeDuplicates(nums: IntArray): Int {
    var k = 0
    for (i in 1 until nums.size) {
        if (nums[i] == nums[i - 1]) {
            var j = i
            while (j < nums.size - 1) {
                nums[j] = nums[j + 1]
                j++
            }
        } else {
            k++
        }
    }
    println((k+1).toString())
    for (i in 0 until nums.size) print("${nums[i]}\t")
    return k+1
}

fun longestCommonPrefix(strs: Array<String>): String {
    if (strs.isEmpty()) return ""
    var prefix = strs[0]
    for (i in 0 until strs.size) {
        while (strs[i].startsWith(prefix).not()) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) return ""
        }
    }
    return prefix
}


fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..n/2) {
        if (n % i == 0) return false
    }
    return true
}

suspend fun cpuTest(dispatcher: CoroutineDispatcher) {
    val time = measureTimeMillis {
        coroutineScope {
            repeat(4) { index ->
                launch(dispatcher) {
                    val primes = (1..200_000).filter { isPrime(it) }
                    println("Task $index found ${primes.size} primes on ${Thread.currentThread().name}")
                }
            }
        }
    }
    println("CPU test with $dispatcher took $time ms\n")
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val indexMap = HashMap<Int, Int>()
    for (i in 0 until nums.size) {
        val current = nums[i]
        val remaining = target - current
        if (indexMap.contains(remaining)) {
            return intArrayOf(i, indexMap[remaining]!!)
        }
        indexMap[current] = i
    }
    nums.forEachIndexed { index, item ->
        indexMap[target - item]?.let { exist ->
            return intArrayOf(index, exist)
        }
        indexMap[item] = index
    }
    return intArrayOf()
}

suspend fun ioTest(dispatcher: CoroutineDispatcher) {
    val time = measureTimeMillis {
        coroutineScope {
            (0..100).forEachIndexed { index, i ->  }
            repeat(100) { index ->
                launch(dispatcher) {
                    Thread.sleep(100) // simulate blocking I/O
                    println("Task $index done on ${Thread.currentThread().name}")
                }
            }
        }
    }
    println("IO test with $dispatcher took $time ms\n")
}




fun deleteAtIndex(head: ListNode?, index: Int): ListNode? {
    if (head == null || index < 0) return head
    if (index == 0) return head.next
    var current = head
    var i = 0
    while (i < index - 1 && current != null) {
        current = current.next
        i++
    }
    current?.next = current.next?.next
    return head
}

fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    if (head == null) return null
    var newHead = head
    while (newHead != null && newHead.`val` == `val`) {
        newHead = newHead.next
    }
    var current = newHead
    while (current?.next != null) {
        if (current.next?.`val` == `val`) {
            current.next = current.next?.next
        } else {
            current = current.next
        }
    }
    return newHead
}

fun removeElements1(head: ListNode?, `val`: Int): ListNode? {
    if (head == null) return null
    val dummy = ListNode(0)
    dummy.next = head
    var current = dummy
    while (current.next != null) {
        if (current.next?.`val` == `val`) {
            current.next = current.next?.next
        } else {
            current = current.next!!
        }
    }
    return dummy.next
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val dummy = ListNode(0)
    var node = dummy
    var l1 = list1
    var l2 = list2

    while (l1 != null && l2 != null) {
        if (l1.`val` < l2.`val`) {
            node.next = l1
            l1 = l1.next
        } else {
            node.next = l2
            l2 = l2.next
        }
        node = node.next!!
    }
    node.next = l1 ?: l2
    return dummy.next
}

// 1,2,3,4,5
// 5,4,3,2,1
fun reverseList1(head: ListNode?): ListNode? {
    if (head == null) return null
    var prev: ListNode? = null
    var current = head
    while (current != null) {
        val tmpNext = current.next
        current.next = prev
        prev = current
        current = tmpNext
    }
    return prev
}

/**
 * Linked list data structure
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        var str = ""
        var current: ListNode? = this
        while (current != null) {
            str += "${current.`val`}"
            current = current.next
            if (current != null) str += " -> "
        }
        return str
    }
}

fun deleteDuplicates(head: ListNode?): ListNode? {
    var current = head
    while (current?.next != null) {
        if (current.`val` == current.next?.`val`) {
            current.next = current.next?.next
        } else {
            current = current.next
        }
    }
    return head
}

// insert new node
fun insert(head: ListNode?, node: ListNode): ListNode? {
    // check if head = empty
    if (head == null) return node

    // if node is smallest
    if (head.`val` > node.`val`) {
        node.next = head
        return node
    }

    // loop to find the position to insert
    var current = head
    while (current?.next != null && current.next!!.`val` < node.`val`) {
        current = current.next
    }
    node.next = current?.next
    current?.next = node
    return head
}

//fun reverseList(head: ListNode?): ListNode? {
//    var prev: ListNode? = null
//    var current = head
//    while (current != null) {
//        val next = current.next
//        current.next = prev
//        prev = current
//        current = next
//    }
//    return prev
//}