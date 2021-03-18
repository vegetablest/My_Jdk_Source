import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/16 20:19
 * @verson
 */


public class RedisTest {

    public static void main(String[] args) {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("name","zhangsan");
        map.add("name","lisi");
        map.add("name","wangwu");
        System.out.println(map.get("name").get(2));

        List<String> list = new ArrayList<>();
        list.add(getUuid().toString().replaceAll("-","").substring(0,6));
        list.add(getUuid().toString().replaceAll("-","").substring(0,6));
        list.add(getUuid().toString().replaceAll("-","").substring(0,6));
        list.add(getUuid().toString().replaceAll("-","").substring(0,6));

        map.put("id",list);
        System.out.println(map.get("id").get(2));

        Set<String> strings = map.keySet();

        for (String string : strings) {
            List<String> values = map.get(string);
            for (String value : values) {
                System.out.println(value);
            }
        }
    }

    private static UUID getUuid() {
        return UUID.randomUUID();
    }
}
