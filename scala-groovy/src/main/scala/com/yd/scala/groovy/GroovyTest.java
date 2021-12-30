package com.yd.scala.groovy;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author created by Zeb灬D on 2020-06-25 13:08
 */
public class GroovyTest {
    protected static Logger logger = LoggerFactory.getLogger(GroovyTest.class);
    public static void main(String[] args) throws IOException {
        GroovyScript groovyScript = new GroovyScript(StringUtils.EMPTY);
        String template = IOUtils.toString(GroovyTest.class.getClassLoader().getResourceAsStream("templates/customTemplate"), Charset.forName("utf-8"));

        String condition = "StringUtils.isEmpty(name);return name+System.currentTimeMillis()";
        groovyScript.addParam("name", "aa");

        Object object = groovyScript.build(String.format(template, condition), ScriptType.TEXT).run();
        System.out.println(object);

        logger.info("operateUid:{},groupUserVOList:{}", "1213");
        System.out.println("TGT-9WO3IorF32au1komLxEovZrjm3ZBqD2gT7ZDf591APIjM3A75G8DED17".length());



    }
}
