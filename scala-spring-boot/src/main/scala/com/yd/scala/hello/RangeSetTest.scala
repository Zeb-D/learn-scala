package com.yd.scala.hello

import com.google.common.collect.{Range, TreeRangeSet}

/**
  * RangeSet类是用来存储一些不为空的也不相交的范围的数据结构。
  * 假如需要向RangeSet的对象中加入一个新的范围，那么任何相交的部分都会被合并起来，所有的空范围都会被忽略。
  * @author created by Zeb灬D on 2021-02-08 20:52
  */
object RangeSetTest {
  def main(args: Array[String]): Unit = {
    val rangeSet = TreeRangeSet.create[Integer]
    rangeSet.add(Range.closed(1, 10))
    System.out.println(rangeSet)
    rangeSet.add(Range.closedOpen(11, 15))
    System.out.println(rangeSet)
    rangeSet.add(Range.open(15, 20))
    System.out.println(rangeSet)
    rangeSet.add(Range.openClosed(0, 0))
    System.out.println(rangeSet)
    rangeSet.remove(Range.open(5, 10))
    System.out.println(rangeSet)
  }

}
