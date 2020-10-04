package FastJsonTest;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity   {
/*    private TextView tv;
    private Button bt;*/
    //    private String readyJson = "[{\"a\":\"123\",\"b\":\"456\",\"F\":{\"aa\":\"111\",\"bb\":\"222\"}},{\"a\":\"321\",\"b\":\"654\",\"F\":{\"aa\":\"333\",\"bb\":\"444\"}}]";
    //工资范围
    private List<String> salary_range = new ArrayList();
    //工资占比
    private List<String> salary_size = new ArrayList();
    //地点
    private List<String> addr = new ArrayList();
    //地点占比
    private List<String> addr_size = new ArrayList();
    //经验
    private List<String> exp = new ArrayList();
    //经验占比
    private List<String> exp_size = new ArrayList();
    //学历
    private List<String> edu = new ArrayList();
    //学历占比
    private List<String> edu_size = new ArrayList();
    private String readyJson = "{\n" +
            "    \"JobSalarys\": [\n" +
            "        {\n" +
            "            \"Salary\": \"10000-15000\",\n" +
            "            \"Location_L\": \"北京\",\n" +
            "            \"Location_P\": \"21.93%\",\n" +
            "            \"Education_L\": \"本科及以上\",\n" +
            "            \"Education_P\": \"66.31%\",\n" +
            "            \"Experience_L\": \"3年以上\",\n" +
            "            \"Experience_P\": \"33.69%\",\n" +
            "            \"SalaryPro\": 0.2\n" +
            "        },\n" +
            "        {\n" +
            "            \"Salary\": \"20000-30000\",\n" +
            "            \"Location_L\": \"北京\",\n" +
            "            \"Location_P\": \"41.95%\",\n" +
            "            \"Education_L\": \"本科及以上\",\n" +
            "            \"Education_P\": \"81.69%\",\n" +
            "            \"Experience_L\": \"3年以上\",\n" +
            "            \"Experience_P\": \"44.51%\",\n" +
            "            \"SalaryPro\": 0.19\n" +
            "        },\n" +
            "        {\n" +
            "            \"Salary\": \"3000-7000\",\n" +
            "            \"Location_L\": \"成都\",\n" +
            "            \"Location_P\": \"9.50%\",\n" +
            "            \"Education_L\": \"大专及以上\",\n" +
            "            \"Education_P\": \"70.25%\",\n" +
            "            \"Experience_L\": \"经验不限\",\n" +
            "            \"Experience_P\": \"81.90%\",\n" +
            "            \"SalaryPro\": 0.19\n" +
            "        },\n" +
            "        {\n" +
            "            \"Salary\": \"15000-20000\",\n" +
            "            \"Location_L\": \"北京\",\n" +
            "            \"Location_P\": \"26.73%\",\n" +
            "            \"Education_L\": \"本科及以上\",\n" +
            "            \"Education_P\": \"75.39%\",\n" +
            "            \"Experience_L\": \"3年以上\",\n" +
            "            \"Experience_P\": \"43.14%\",\n" +
            "            \"SalaryPro\": 0.15\n" +
            "        },\n" +
            "        {\n" +
            "            \"Salary\": \"7000-10000\",\n" +
            "            \"Location_L\": \"北京\",\n" +
            "            \"Location_P\": \"22.88%\",\n" +
            "            \"Education_L\": \"大专及以上\",\n" +
            "            \"Education_P\": \"48.28%\",\n" +
            "            \"Experience_L\": \"经验不限\",\n" +
            "            \"Experience_P\": \"54.55%\",\n" +
            "            \"SalaryPro\": 0.13\n" +
            "        },\n" +
            "        {\n" +
            "            \"Salary\": \"30000-100000\",\n" +
            "            \"Location_L\": \"北京\",\n" +
            "            \"Location_P\": \"40.87%\",\n" +
            "            \"Education_L\": \"本科及以上\",\n" +
            "            \"Education_P\": \"82.65%\",\n" +
            "            \"Experience_L\": \"5年以上\",\n" +
            "            \"Experience_P\": \"36.07%\",\n" +
            "            \"SalaryPro\": 0.09\n" +
            "        }\n" +
            "    ]}";
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.start);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseJsonWithJSONObject(readyJson);
            }
        });
    }*/

/*
    private void parseJsonWithJSONObject(String readyJson) {
        try {
            JSONArray jsonArray = new JSONArray(readyJson);
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("MainActivity",jsonObject.getString("a")+"+++++++++++++");
                tv.append(jsonObject.getString("a")+" ");
                Log.d("MainActivity",jsonObject.getString("b")+"-------------");
                tv.append(jsonObject.getString("b")+" ");

                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("F"));
                tv.append(jsonObject1.getString("aa")+" ");
                tv.append(jsonObject1.getString("bb")+" ");
//
            JSONObject jsonObject1 = new JSONObject(readyJson);
            System.out.println(jsonObject1.length()+"+++++++++++++");
            System.out.println(jsonObject1.getJSONArray("JobSalarys")+"+++++++++++++");

            JSONArray jsonArray = new JSONArray(jsonObject1.getJSONArray("JobSalarys").toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject0 = jsonArray.getJSONObject(i);
                salary_range.add(jsonObject0.getString("Salary"));
                addr.add(jsonObject0.getString("Location_L"));
                addr_size.add(jsonObject0.getString("Location_P"));
                edu.add(jsonObject0.getString("Education_L"));
                edu_size.add(jsonObject0.getString("Education_P"));
                exp.add(jsonObject0.getString("Experience_L"));
                exp_size.add(jsonObject0.getString("Experience_P"));
                salary_size.add(jsonObject0.getString("SalaryPro"));

                System.out.println(jsonObject0.getString("Salary")+"---------------");
                System.out.println(jsonObject0.getString("Location_L")+"---------------");
                System.out.println(jsonObject0.getString("Location_P")+"---------------");
                System.out.println(jsonObject0.getString("Education_L")+"---------------");
                System.out.println(jsonObject0.getString("Education_P")+"---------------");
                System.out.println(jsonObject0.getString("Experience_L")+"---------------");
                System.out.println(jsonObject0.getString("Experience_P")+"---------------");
                System.out.println(jsonObject0.getString("SalaryPro")+"---------------");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
