package com.yd.scala.hello

import com.google.common.collect.{Range, TreeRangeMap}

/**
  * @author created by Zeb灬D on 2021-02-07 15:57
  */
object RangeMapTest {
  def main(args: Array[String]): Unit = {
    val rangeMap: TreeRangeMap[Integer, String] = TreeRangeMap.create[Integer, String]()
    rangeMap.put(Range.closed(1, 10), "aaa")
    System.out.println(rangeMap)//[[1‥10]=aaa]
    rangeMap.put(Range.open(3, 6), "bbb")
    System.out.println(rangeMap)//[[1‥3]=aaa, (3‥6)=bbb, [6‥10]=aaa]
    rangeMap.put(Range.openClosed(10, 20), "aaa")
    System.out.println(rangeMap)//[[1‥3]=aaa, (3‥6)=bbb, [6‥10]=aaa, (10‥20]=aaa]
    rangeMap.put(Range.closed(20, 20), "aaa")
    System.out.println(rangeMap)//[[1‥3]=aaa, (3‥6)=bbb, [6‥10]=aaa, (10‥20)=aaa, [20‥20]=aaa]
    rangeMap.remove(Range.closed(5, 11))
    System.out.println(rangeMap)//[[1‥3]=aaa, (3‥5)=bbb, (11‥20)=aaa, [20‥20]=aaa]
  }
}
