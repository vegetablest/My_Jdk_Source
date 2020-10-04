package FastJsonTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test02 {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"employees\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Bill\",\n" +
                "            \"lastName\": \"Gates\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"George\",\n" +
                "            \"lastName\": \"Bush\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Thomas\",\n" +
                "            \"lastName\": \"Carter\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);
        List<Object> list = new ArrayList<Object>();
        String string = jsonObject.toJSONString();
        System.out.println(string);
        boolean firstName = jsonObject.containsKey("employees");
        System.out.println(firstName);
        for (Map.Entry<String,Object> entry:jsonObject.entrySet()){
            System.out.println(entry.getKey()+entry.getValue());
        }
        Student student = new Student("zhangsan",23);
        list.add(student);
        String string1 = JSONObject.toJSONString(student);
        System.out.println(string1);
        for (Map.Entry<String,Object> entry:jsonObject.entrySet()){
            System.out.println(entry.getKey()+entry.getValue());
        }
        JSONArray jsonArray = JSONArray.parseArray(list.toString());
        System.out.println(jsonArray);
//        System.out.println(json.);
    }
    public static Boolean legalPerson(Object o1, Object o2, Object o3) {

        if (o3 == null || o1 == null) {
            return false;
        } else if (("13401").equals(o1.toString())) {
            return true;
        } else if ((o2.toString()).equals(o3.toString())) {
            return true;
        } else {
            return false;
        }
    }
    //##############################################################################//


}
