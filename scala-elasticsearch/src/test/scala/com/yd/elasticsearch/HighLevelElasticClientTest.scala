package com.yd.elasticsearch

import java.util
import java.util.concurrent.TimeUnit

import com.yd.scala.elasticsearch.StartEsApp
import com.yd.scala.elasticsearch.es.HighLevelElasticClient
import org.elasticsearch.action.search.{MultiSearchRequest, MultiSearchResponse, SearchRequest, SearchType}
import org.elasticsearch.common.unit.TimeValue
import org.elasticsearch.index.query.{BoolQueryBuilder, QueryBuilders}
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.junit.Assert._
import org.junit._
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@Test
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringBootTest(classes = Array(classOf[StartEsApp]))
class HighLevelElasticClientTest {

  @Autowired() var elasticClient: HighLevelElasticClient = _

  @Test
  def testOK() = assertTrue(true)

  @Test
  def testKO() = {
    println("hello")
    println(elasticClient)
  }

  val TEST_USER_INFO = "test_user_info"

  @Test
  def testCrud() = {
    var userId = "1234567890"
    val mapAll: util.Map[String, Object] = new util.HashMap[String, Object](16)

    mapAll.put("name", "yd")
    mapAll.put("address", "广东省深圳市南山区")
    mapAll.put("desc", "帅哥，在那")
    val uid = elasticClient.add(TEST_USER_INFO, userId, mapAll)
    println(uid.equals(userId))

    var userInfo = elasticClient.get(TEST_USER_INFO, uid)
    println(userInfo)

    val mapModified: util.Map[String, AnyRef] = new util.HashMap[String, AnyRef](16)
    mapAll.put("age", new Integer(10))
    mapModified.put("desc", "帅哥，在附近") //这个字段
    var success = elasticClient.addAndUpdate(TEST_USER_INFO, uid, mapAll, mapModified)
    println(success)

    userInfo = elasticClient.get(TEST_USER_INFO, uid)
    println(userInfo)

    userId = "1234567890" + "test"
    success = elasticClient.addAndUpdate(TEST_USER_INFO, userId, mapAll, mapAll)
    println(success)

    userInfo = elasticClient.get(TEST_USER_INFO, userId)
    println(userInfo)
  }

  @Test
  def testQuery() {
    //中文分词
    val boolQueryBuilder: BoolQueryBuilder = QueryBuilders.boolQuery
    boolQueryBuilder.must(QueryBuilders.matchQuery("desc", "哥"))
    val searchSourceBuilder = new SearchSourceBuilder
    searchSourceBuilder.timeout(new TimeValue(6, TimeUnit.SECONDS))
    searchSourceBuilder.size(10)
    searchSourceBuilder.query(boolQueryBuilder)
    val searchRequest = new SearchRequest(TEST_USER_INFO)
    searchRequest.searchType(SearchType.QUERY_THEN_FETCH)
    searchRequest.source(searchSourceBuilder)
    val multiSearchRequest = new MultiSearchRequest
    multiSearchRequest.add(searchRequest)
    val multiSearchResponse = elasticClient.search(multiSearchRequest)
    println(multiSearchResponse)

    //id后缀查询
    //address 模糊查询


  }

}


