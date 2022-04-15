package com.yd.test.graphql

import java.util

import com.alibaba.fastjson.JSON
import com.yd.test.graphql.YamlCommonVO.SdkComService
import org.junit.Test
import org.yaml.snakeyaml.Yaml

/**
  * @author created by Zeb灬D on 2021-09-15 12:03
  */
class GraphqlYamlTest {

  @Test
  def Test() = {
    val yaml = new Yaml()
    val input = classOf[GraphqlYamlTest].getClassLoader
      .getResourceAsStream("graphql/yamlService.yml")
    val map: util.Map[Any, Any] = yaml.load(input)
    System.out.println(JSON.toJSON(map).toString)

    val yamlCommonVO: SdkComService = JSON.parseObject[SdkComService](JSON.toJSON(map).toString, classOf[SdkComService])
    System.out.println(yamlCommonVO.toString)
  }

}
