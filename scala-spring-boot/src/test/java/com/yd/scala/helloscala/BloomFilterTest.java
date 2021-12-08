package com.yd.scala.helloscala;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @author created by Zeb灬D on 2021-12-08 10:24
 */
public class BloomFilterTest {
    long expectedInsertions = 10000000;
    double fpp = 0.00001;
    BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);

    @Test
    public void testFilter() {
        bloomFilter.put("aaa");
        bloomFilter.put("bbb");
        boolean containsString = bloomFilter.mightContain("aaa");
        System.out.println(containsString);

        BloomFilter<Email> emailBloomFilter = BloomFilter
                .create((Funnel<Email>) (from, into) -> into.putString(from.getDomain(), Charset.defaultCharset()),
                        expectedInsertions, fpp);

        emailBloomFilter.put(new Email("sage.wang", "quanr.com"));
        boolean containsEmail = emailBloomFilter.mightContain(new Email("sage.wangaaa", "quanr.com"));
        System.out.println(containsEmail);
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    public static class Email {
        private String userName;
        private String domain;
    }
}
