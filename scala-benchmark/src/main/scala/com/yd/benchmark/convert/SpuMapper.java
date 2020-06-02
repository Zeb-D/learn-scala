package com.yd.benchmark.convert;

import com.yd.benchmark.domain.Spu;
import com.yd.benchmark.domain.SpuRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author created by Zeb灬D on 2020-04-23 15:22
 */
@Mapper(config = ConverterConfig.class)
public interface SpuMapper {
    SpuMapper INSTANCE = Mappers.getMapper(SpuMapper.class);

    SpuRes toSpuRes(Spu s);
}
