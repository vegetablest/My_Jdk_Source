package com.bsfit.suaf.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.bsfit.suaf.pojo.Card;
import com.bsfit.suaf.pojo.Order;
import com.bsfit.suaf.pojo.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 17:43
 * @verson
 */
public class TestExport {
    /**
     * 模拟查询数据库，查询所有
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User().setName("小明" + i).
                    setAge(23 + i).
                    setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6)).
                    setBir(new Date());
            user.setStatus(String.valueOf(i%2));
            if (i%2 == 0){
                user.setHabbys(Arrays.asList("看书","学习","code"));
            }else {
                user.setHabbys(Arrays.asList("烫头","喝酒","吸烟"));
            }
            /*身份证*/
            Card card = new Card().setAddress("北京市").setNo("123456789");
            user.setIgnore(String.valueOf(i)).setCard(card);
            /*订单*/
//            user.setOrders(Arrays.asList(new Order("衣服","345678"),new Order("鞋子","345678")));
            user.setPhoto("D:\\Img\\286.jpg");
            users.add(user);
        }
        return users;
    }
    @Test
    public void testExport() throws Exception{
        List<User> users = getUsers();
        /**
         * 1.参数一，导出的配置对象，标题和sheet页名字
         * 2.参数二，导出的类型 class
         * 3.参数三，导出的对象
         * */
        ExportParams exportParams = new ExportParams("用户信息表", "用户信息");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);
        //大数据量转换，不建议这样，建议像分页一样来
//        Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, User.class, users);
        FileOutputStream fos = new FileOutputStream("D:\\bangsun\\gitspace\\EasyPoi-Study\\user.xls");
//        FileOutputStream fos = new FileOutputStream("user.xls");
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
