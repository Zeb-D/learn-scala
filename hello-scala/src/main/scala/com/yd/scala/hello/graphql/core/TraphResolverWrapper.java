package com.yd.scala.hello.graphql.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author created by Zeb灬D on 2021-09-15 17:33
 */
@Data
@NoArgsConstructor
public class TraphResolverWrapper {
    public List<ResolverRecipient> resolvers;
}
