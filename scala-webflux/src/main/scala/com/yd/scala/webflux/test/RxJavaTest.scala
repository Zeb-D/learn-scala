package com.yd.scala.webflux.test

import io.reactivex.disposables.Disposable
import io.reactivex.functions.{Action, Consumer}
import io.reactivex.schedulers.Schedulers
import io.reactivex.{Observable, ObservableEmitter, ObservableOnSubscribe, Observer}
import org.slf4j.LoggerFactory

/**
  * @author created by Zeb-D on 2019-06-09 16:04
  */
object RxJavaTest {
  private val Log = LoggerFactory.getLogger(RxJavaTest.getClass)

  def main(args: Array[String]) {
    val onSubscribe = new ObservableOnSubscribe[String]() { //ObservableEmitter有三种发射的方法，分别是void onNext(T value)、void onError(Throwable error)、void onComplete()，
      // onNext方法可以无限调用，Observer（观察者）所有的都能接收到，onError和onComplete是互斥的，Observer（观察者）只能接收到一个，
      // OnComplete可以重复调用，但是Observer（观察者）只会接收一次，而onError不可以重复调用，第二次调用就会报异常。
      @throws[Exception]
      override def subscribe(emitter: ObservableEmitter[String]): Unit = {
        emitter.onNext("连载1")
        emitter.onNext("连载2")
        emitter.onNext("连载3")
        emitter.onComplete()
      }
    }
    //被观察者
    val novel = Observable.create(onSubscribe)
    //观察者
    val reader = new Observer[String]() {
      private[webflux] var mDisposable: Disposable = null

      override def onSubscribe(d: Disposable): Unit = {
        mDisposable = d
        Log.info("onSubscribe")
      }

      override def onNext(value: String): Unit = {
        if ("2" == value) {
          mDisposable.dispose()
          return
        }
        Log.info("onNext:" + value)
      }

      override def onError(e: Throwable): Unit = {
        Log.info("onError=" + e.getMessage)
      }

      override def onComplete(): Unit = {
        Log.info("onComplete()")
      }
    }
    //建立订阅关系
    novel.subscribe(reader)

    //订阅其它线程、其它观察者
    novel.subscribeOn(Schedulers.io).subscribe(new Observer[String]() {
      override def onSubscribe(d: Disposable): Unit = {
        Log.info("onSubscribe")
      }

      override def onNext(value: String): Unit = {
        Log.info("onNext:" + value)
      }

      override def onError(e: Throwable): Unit = {
        Log.info("onError=" + e.getMessage)
      }

      override def onComplete(): Unit = {
        Log.info("onComplete()")
      }
    })

    novel.subscribe(new Consumer[String]() {
      @throws[Exception]
      override def accept(integer: String): Unit = {
        Log.info(integer + "")
      }
    }, new Consumer[Throwable]() {
      @throws[Exception]
      override def accept(throwable: Throwable): Unit = {
      }
    }, new Action() {
      @throws[Exception]
      override def run(): Unit = {
      }
    })

  }
}
