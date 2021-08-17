package com.yd.test.sdk

import com.yd.scala.hello.sdk.http.IGithubHttpSdkService
import com.yd.scala.hello.sdk.{SdkContext, SdkManager}
import javax.annotation.Resource
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[StartApp]))
class SdkTest {
  @Resource
  var githubHttpSdkService: IGithubHttpSdkService = _

  @Before
  def before() {
    val ctx: SdkContext = new SdkContext()
    ctx.setHost("https://api.github.com")
    SdkManager.setContext(ctx)
  }

  @Test
  def TestSdk() {
    println(githubHttpSdkService)
    println("request--->" + githubHttpSdkService.getRequest)
    println("request2--->" + githubHttpSdkService.userInfo)
  }
}
