package lombok;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.math.BigDecimal;
import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CommonConverter {
    //
//    default String toString(BigDecimal decimal) {
//        System.out.println(decimal);
//        if (decimal == null) return null;
//        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//    }

    default Integer toInteger(Boolean booleanValue) {
        if (booleanValue) {
            return 1;
        } else {
            return 0;
        }
    }

    default Boolean toBoolean(Integer booleanValue) {
        if (booleanValue == null) {
            return Boolean.FALSE;
        }
        if (booleanValue == 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }


    default Instant toInstant(Long timestamps) {
        if (timestamps == null) {
            return null;
        }
        return Instant.ofEpochMilli(timestamps);
    }

    default Long toTimestamps(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.toEpochMilli();
    }

    default JSONObject toJSONObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    default String toJSONString(JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

}
