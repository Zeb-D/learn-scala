package com.yd.test.validate

import com.yd.scala.hello.validate.{CommodityAddReq, ValidateService, ValidationInterceptor}
import javax.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[StartApp]))
class ValidateTest {

  @Resource
  var validateService: ValidateService = _
  @Resource
  var validationInterceptor: ValidationInterceptor = _

  @Resource
  var testBean: TestBean = _

  @Test
  def TestPublish() {
    println(validateService)
    println(validateService.delete(11231L))
    try {
      //校验报错
      validateService.delete(1123L)
    } catch {
      case ex: Exception => {
        println(ex)
      }
    }

    //两个参数
    println(validateService.delete(11231L, "123"))
    try {
      //校验报错
      validateService.delete(11231L, "11111111121212")
    } catch {
      case ex: Exception => {
        println(ex)
      }
    }
  }

  @Test
  def TestValidate(): Unit = {
    val req = new CommodityAddReq
    validationInterceptor.doValidation(req)
  }

  @Test
  def TestBeanIOC(): Unit = {
    println(testBean)
  }
}
