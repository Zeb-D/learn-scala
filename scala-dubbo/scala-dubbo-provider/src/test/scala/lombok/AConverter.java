package lombok;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(config = ConverterConfig.class)
public interface AConverter {
    AConverter INSTANCE = Mappers.getMapper(AConverter.class);

    @Mappings(
            {
//                    @Mapping(source = "res.amount", target = "res.amount", numberFormat = "0.00"),
//                    @Mapping(source = "res.amount1", target = "res.amount1", numberFormat = "0.00")
            }
    )
    ListARes toRes(ListAVO vo);

    @Mappings(
            {
                    @Mapping(source = "amount", target = "amount", numberFormat = "0.00"),
                    @Mapping(source = "amount1", target = "amount1", numberFormat = "0.00")
            }
    )
    Ares toRes(AVO a);
}