package com.yd.scala.elasticsearch.es

import java.io.IOException
import java.util
import java.util.Collections

import lombok.extern.slf4j.Slf4j
import org.apache.http.HttpHost
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.{MultiSearchRequest, MultiSearchResponse}
import org.elasticsearch.action.update.UpdateRequest
import org.elasticsearch.client.{RequestOptions, RestClient, RestHighLevelClient}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
  * @author created by Zeb灬D on 2020-06-20 10:35
  */
@Component
@Slf4j
class HighLevelElasticClient extends InitializingBean {
  private val logger: Logger = LoggerFactory.getLogger(classOf[HighLevelElasticClient])
  /**
    * ES集群地址,多地址用英文逗号分隔
    */
  @Value("${es.host:172.16.248.134,172.16.248.135}") var host: String = _
  /**
    * ES集群端口
    */
  @Value("${es.port.restfull:9210}") var port: Int = _

  protected var client: RestHighLevelClient = null

  /**
    * 文档id不存在则以mapAll为准
    * 存在则以mapModified来做更新
    * {@link update}.
    *
    * @param index
    * @param id
    * @param mapAll      总的字段
    * @param mapModified 更新的字段
    * @return
    */
  def addAndUpdate(index: String, id: String, mapAll: util.Map[String, AnyRef], mapModified: util.Map[String, AnyRef]): Boolean = {
    try {
      val updateRequest = new UpdateRequest(index, id).retryOnConflict(3).doc(mapModified).upsert(mapAll)
      val response = client.update(updateRequest, RequestOptions.DEFAULT)
      return response.getId.equals(id)
    } catch {
      case e: IOException =>
        logger.error("es add method occur an exception. index:{},type:{},id:{},map:{},e:{}", index, id, mapAll, e)
    }
    return false
  }

  def add(index: String, id: String, mapAll: util.Map[String, AnyRef]): String = {
    try {
      val request = new IndexRequest(index).id(id).source(mapAll)
      val response = client.index(request, RequestOptions.DEFAULT)
      return response.getId
    } catch {
      case e: IOException =>
        logger.error("es add method occur an exception. index:{},type:{},id:{},map:{},e:{}", index, id, mapAll, e)
    }
    return null
  }

  def update(index: String, id: String, mapModified: util.Map[String, AnyRef]): Boolean = {
    try {
      val updateRequest = new UpdateRequest(index, id).retryOnConflict(3).doc(mapModified)
      val response = client.update(updateRequest, RequestOptions.DEFAULT)
      return response.getId.equals(id)
    } catch {
      case e: IOException =>
        logger.error("es add method occur an exception. index:{},type:{},id:{},map:{},e:{}", index, id, mapModified, e)
    }
    return false
  }

  def get(index: String, id: String): util.Map[String, AnyRef] = {
    try {
      val request = new GetRequest(index, id)
      val startTime = System.currentTimeMillis
      val response = client.get(request, RequestOptions.DEFAULT)

      val spendTime = System.currentTimeMillis - startTime
      if (spendTime > 500) logger.warn("es-get,spend:{} ms,ids:{}", spendTime, id)
      else if (spendTime > 200) logger.info("es-get,spend:{} ms,ids:{}", spendTime, id)

      return response.getSourceAsMap
    } catch {
      case e: IOException =>
        logger.error("es add method occur an exception. index:{},id:{},e:{}", index, id, e)
    }
    return Collections.emptyMap()
  }

  def search(multiSearchRequest: MultiSearchRequest): MultiSearchResponse = {
    try {
      return client.msearch(multiSearchRequest, RequestOptions.DEFAULT)
    } catch {
      case e: Exception =>
        logger.error("es search method occur an exception.multiSearchRequest:{}, multiSearchRequest:{},e:{}", multiSearchRequest, multiSearchRequest, e)
    }
    return null
  }

  override def afterPropertiesSet() = {
    val ips: Array[String] = host.split(",")
    val hosts = ips.map { ip => new HttpHost(ip, port, "http") }

    client = new RestHighLevelClient(RestClient.builder(hosts: _*))
  }
}
