package com.bsfit.suaf.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.bsfit.suaf.pojo.User;
import com.bsfit.suaf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterAbortException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 20:47
 * @verson
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Value("${upload.dir}")
    private String realPath;
    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<User> all = userService.findAll();
        model.addAttribute("users",all);
        return "index";
    }

    @RequestMapping("/import")
    public String importFile(MultipartFile excelFile) throws Exception {
        log.info("File:[{}]====================>upload start",excelFile.getOriginalFilename());
        /**excel导入*/
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(2);
        List<User> list = ExcelImportUtil.importExcel(excelFile.getInputStream(), User.class, params);
        list.forEach(System.out::println);
        userService.addUser(list);
        /**上传完重定向*/
        return "redirect:/user/findAll";
    }
    @RequestMapping("/export")
    public void importFile(HttpServletResponse response) throws IOException {
        /**excel导出*/
        List<User> users = userService.findAll();
        users.forEach(user -> user.setPhoto(realPath+"/"+user.getPhoto()));
        log.info("File:[{}],数据量:{}====================>pull start","用户信息表.xls",users.size());

        ExportParams params = new ExportParams("用户信息", "用户信息表");
        Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, users);

        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("用户信息表.xls","UTF-8"));
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        ops.close();
        workbook.close();

    }
}
