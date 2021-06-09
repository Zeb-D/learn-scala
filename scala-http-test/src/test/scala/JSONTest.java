import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by Zeb灬D on 2021-01-25 15:04
 */
public class JSONTest {

    @Test
    public void testJson() {
        List<user> users = new ArrayList<>();
        user u = new user();
        u.setAge(1);
        u.setName("aaaaa");
        users.add(u);
        users.add(u);
        System.out.println(JSONObject.toJSONString(users, SerializerFeature.DisableCircularReferenceDetect));
    }
}
