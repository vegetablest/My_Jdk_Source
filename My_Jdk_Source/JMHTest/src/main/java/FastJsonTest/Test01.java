package FastJsonTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.StaticData;
import pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test01 {
//    public Object process(Object obj) {
//        JSONObject pre = JSONObject.parseObject("" + obj);
//        JSONObject preBody = JSONObject.parseObject("" + pre.get("body"));
//        List<Object> list = new ArrayList();
//        if ( preBody.containsKey("reason") && (preBody.size() == 9) ){
//            StaticData staticData = new StaticData();
//            if(!(""+preBody.get("handType")).equals("D")){  /*黑名单1*/
//                staticData.setIndentyno(""+preBody.get("indentyno"));
//                staticData.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//                staticData.setResult1(""+preBody.get("indentyno"));
//                list.add(staticData);
//            }else{
//                staticData.setIndentyno(""+preBody.get("indentyno"));
//                staticData.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//                staticData.setResult1("0");
//                list.add(staticData);
//            }
//        }
//        else if(preBody.containsKey("unitleader") && (preBody.size() == 16) ){  /*区域收入上线*/
//            StaticData staticData = new StaticData();
//            if(!(""+preBody.get("handType")).equals("D")){
//                staticData.setOrgName(""+preBody.get("orgName"));
//                staticData.setTradeName(""+preBody.get("tradeName"));
//                staticData.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//                staticData.setResult2(Double.parseDouble(""+preBody.get("reference")));
//                list.add(staticData);
//            }else{
//                staticData.setOrgName(""+preBody.get("orgName"));
//                staticData.setTradeName(""+preBody.get("tradeName"));
//                staticData.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//                staticData.setResult2(0D);
//                list.add(staticData);
//            }
//        }
//        else if (preBody.containsKey("spouseIdentityno") && (preBody.size() == 20)){   /*风险客户关系人名单&风险客户名单*/
//            StaticData staticData = new StaticData();
//            StaticData staticData2 = new StaticData();
//            if(!(""+preBody.get("handType")).equals("D")){
//                staticData.setIndentyno(""+preBody.get("identityno"));
//                staticData.setPhoneNumber(""+preBody.get("phoneNumber"));
//                staticData2.setSpouseIdentityno(""+preBody.get("spouseIdentityno"));
//                staticData2.setSpousePhone(""+preBody.get("spousePhone"));
//                staticData.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//                staticData2.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//
//                staticData.setResult3(""+preBody.get("identityno"));
//                staticData.setResult3_2(""+preBody.get("phoneNumber"));
//                staticData2.setResult4(""+preBody.get("spouseIdentityno"));
//                staticData2.setResult4_2(""+preBody.get("spousePhone"));
//                list.add(staticData);
//                list.add(staticData2);
//            }else{
//                staticData.setIndentyno(""+preBody.get("identityno"));
//                staticData.setPhoneNumber(""+preBody.get("phoneNumber"));
//                staticData2.setSpouseIdentityno(""+preBody.get("spouseIdentityno"));
//                staticData2.setSpousePhone(""+preBody.get("spousePhone"));
//                staticData.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//                staticData2.setTime(new Date(Long.parseLong(""+preBody.get("changeDate"))));
//
//                staticData.setResult3("0");
//                staticData.setResult3_2(""+preBody.get("0"));
//                staticData2.setResult4(""+preBody.get("0"));
//                staticData2.setResult4_2(""+preBody.get("0"));
//                list.add(staticData);
//                list.add(staticData2);
//            }
//        }
//        else if((preBody.size() == 8) ) {      /*银监局黑名单*/
//            StaticData staticData = new StaticData();
//            if (!("" + preBody.get("handType")).equals("D")) {
//                staticData.setIndentyno("" + preBody.get("indentyno"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData.setResult5("" + preBody.get("indentyno"));
//                list.add(staticData);
//            } else {
//                staticData.setIndentyno("" + preBody.get("indentyno"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData.setResult5("0");
//                list.add(staticData);
//            }
//        }else if(preBody.containsKey("socialcreditCode")) {   /*企业黑名单*/
//            StaticData staticData = new StaticData();
//            StaticData staticData2 = new StaticData();
//            StaticData staticData3 = new StaticData();
//            StaticData staticData4 = new StaticData();
//            if (!("" + preBody.get("handType")).equals("D")) {
//                staticData.setSocialcreditCode("" + preBody.get("socialcreditCode"));
//                staticData.setResult6("" + preBody.get("socialcreditCode"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData2.setOrganCode("" + preBody.get("organCode"));
//                staticData2.setResult6_2("" + preBody.get("organCode"));
//                staticData2.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData3.setRegisterNo("" + preBody.get("registerNo"));
//                staticData3.setResult6_3("" + preBody.get("registerNo"));
//                staticData3.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData4.setTaxregisterNo("" + preBody.get("taxregisterNo"));
//                staticData4.setResult6_4("" + preBody.get("taxregisterNo"));
//                staticData4.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                list.add(staticData);
//                list.add(staticData2);
//                list.add(staticData3);
//                list.add(staticData4);
//            } else {
//                staticData.setSocialcreditCode("" + preBody.get("socialcreditCode"));
//                staticData.setResult6("" + preBody.get("0"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData2.setOrganCode("" + preBody.get("organCode"));
//                staticData2.setResult6_2("" + preBody.get("0"));
//                staticData2.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData3.setRegisterNo("" + preBody.get("registerNo"));
//                staticData3.setResult6_3("" + preBody.get("0"));
//                staticData3.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData4.setTaxregisterNo("" + preBody.get("taxregisterNo"));
//                staticData4.setResult6_4("" + preBody.get("0"));
//                staticData4.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                list.add(staticData);
//                list.add(staticData2);
//                list.add(staticData3);
//                list.add(staticData4);
//            }
//        }else if((preBody.size() == 17) && preBody.containsKey("dealerNo")) {      /*经销商等级*/
//            StaticData staticData = new StaticData();
//            if (!("" + preBody.get("handType")).equals("D") && "1".equals(""+preBody.get("status"))) {
//                staticData.setDealerNo("" + preBody.get("dealerNo"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                if((""+preBody.get("level")).equals("880")){
//                    staticData.setResult7("A");
//                }else if((""+preBody.get("level")).equals("881")){
//                    staticData.setResult7("B");
//                }else if((""+preBody.get("level")).equals("882")){
//                    staticData.setResult7("C");
//                }else if((""+preBody.get("level")).equals("14697")){
//                    staticData.setResult7("A+");
//                }else if((""+preBody.get("level")).equals("14698")){
//                    staticData.setResult7("D");
//                }else {
//                    staticData.setResult7("0");
//                }
//
//                list.add(staticData);
//            } else if("1".equals(""+preBody.get("status"))){
//                staticData.setDealerNo("" + preBody.get("dealerNo"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                staticData.setResult7("0");
//                list.add(staticData);
//            }
//        }
//
//        else if((preBody.size() == 17) && preBody.containsKey("dealerName")) {      /*疑似欺诈经销商等级*/
//            StaticData staticData = new StaticData();
//            if (!("" + preBody.get("handType")).equals("D")) {
//                staticData.setDealerName("" + preBody.get("dealerName"));
//                staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                if((""+preBody.get("saleLevel")).equals("169771")){
//                    staticData.setResult8("1");
//                }else if((""+preBody.get("saleLevel")).equals("169781")){
//                    staticData.setResult8("2");
//                }else if((""+preBody.get("saleLevel")).equals("169791")){
//                    staticData.setResult8("3");
//                }else if((""+preBody.get("saleLevel")).equals("169801")){
//                    staticData.setResult8("4");
//                }else if((""+preBody.get("saleLevel")).equals("169811")){
//                    staticData.setResult8("5");
//                }else if((""+preBody.get("saleLevel")).equals("169821")){
//                    staticData.setResult8("6");
//                }else if((""+preBody.get("saleLevel")).equals("169831")){
//                    staticData.setResult8("7");
//                }else if((""+preBody.get("saleLevel")).equals("169841")) {
//                    staticData.setResult8("8");
//                } else{
//                        staticData.setResult8("0");
//                    }
//                    list.add(staticData);
//                } else {
//                    staticData.setDealerNo("" + preBody.get("dealerNo"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult8("0");
//                    list.add(staticData);
//                }
//            }
///*            else if((preBody.size() == 13) && preBody.containsKey("dealerNo")) {      *//**//*贷前经销商管理*//**//*
//                StaticData staticData = new StaticData();
//                if (!("" + preBody.get("handType")).equals("D")) {
//                    staticData.setSocialcreditCode("" + preBody.get("salesworkerid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult9("" + preBody.get("salelevel"));
//                    list.add(staticData);
//                } else {
//                    staticData.setSocialcreditCode("" + preBody.get("salesworkerid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult9("0");
//                    list.add(staticData);
//                }
//            }else if( preBody.containsKey("assureme")){      //公户管理
//            StaticData staticData = new StaticData();
//            if (!("" + preBody.get("handType")).equals("D") && (""+preBody.get("status")).equals("1")) {
//                JSONObject assureme = preBody.getJSONObject("assureme");
//                JSONObject client = preBody.getJSONObject("client");
//                JSONObject coopCarBrand = preBody.getJSONObject("coopCarBrand");
//                JSONObject coopCarSubBrand = preBody.getJSONObject("coopCarSubBrand");
//                JSONObject guaranty = preBody.getJSONObject("guaranty");
//                for (Map.Entry<String, Object> entry : assureme.entrySet()) {
//                }
//                list.add(staticData);
//            } else {
//
//                list.add(staticData);
//            }
//        }*/else if( preBody.containsKey("salesworkerid")){      /*疑似欺诈销售顾问*/
//                StaticData staticData = new StaticData();
//                if (!("" + preBody.get("handType")).equals("D")) {
//                    staticData.setSocialcreditCode("" + preBody.get("salesworkerid"));
//                    staticData.setDealerNO("" + preBody.get("dealerid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult11("" + preBody.get("salelevel"));
//                    list.add(staticData);
//                } else {
//                    staticData.setSocialcreditCode("" + preBody.get("salesworkerid"));
//                    staticData.setDealerNO("" + preBody.get("dealerid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult11("0");
//                    list.add(staticData);
//                }
//            } else if( preBody.containsKey("provinceid")) {      /*疑似欺诈城市*/
//                StaticData staticData = new StaticData();
//                if (!("" + preBody.get("handType")).equals("D") && "1".equals(""+preBody.get("status"))) {
//                    staticData.setProvinceid("" + preBody.get("provinceid"));
//                    staticData.setCityid("" + preBody.get("cityid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult12("" + preBody.get("cityid"));
//                    list.add(staticData);
//                } else {
//                    staticData.setProvinceid("" + preBody.get("provinceid"));
//                    staticData.setCityid("" + preBody.get("cityid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult12("0");
//                    list.add(staticData);
//                }
//            }
//            else if(preBody.containsKey("parentarea")) {      /*疑似欺诈区域*/
//                StaticData staticData = new StaticData();
//                if (!("" + preBody.get("handType")).equals("D") && "1".equals(""+preBody.get("status"))) {
//                    staticData.setParentarea("" + preBody.get("parentarea"));
//                    staticData.setChildarea("" + preBody.get("childarea"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult13("" + preBody.get("salelevel"));
//                    list.add(staticData);
//                } else {
//                    staticData.setParentarea("" + preBody.get("parentarea"));
//                    staticData.setChildarea("" + preBody.get("childarea"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult13("0");
//                    list.add(staticData);
//                }
//            }
//            else if(preBody.containsKey("companyname")) {      /*疑似欺诈法人*/
//                StaticData staticData = new StaticData();
//                StaticData staticData2 = new StaticData();
//                StaticData staticData3 = new StaticData();
//                if (!("" + preBody.get("handType")).equals("D")) {
//                    staticData.setCompanyid("" + preBody.get("companyid"));
//                    staticData.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData.setResult14("" + preBody.get("salelevel"));
//
//                    staticData2.setCompanyname("" + preBody.get("companyname"));
//                    staticData2.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData2.setResult14_2("" + preBody.get("salelevel"));
//
//                    staticData3.setOrgcreditcode("" + preBody.get("orgcreditcode"));
//                    staticData3.setTime(new Date(Long.parseLong("" + preBody.get("changeDate"))));
//                    staticData3.setResult14_3("" + preBody.get("salelevel"));
//                    list.add(staticData);
//                    list.add(staticData2);
//                    list.add(staticData3);
//                }
//            }
//            return list;
//
//        }
}
