package com.bysj.logistics.manage.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.io.FileUtil;
import com.bysj.logistics.manage.dto.FileVO;
import com.bysj.logistics.manage.pojo.Result;
import com.bysj.logistics.manage.pojo.User;
import com.bysj.logistics.manage.service.UserService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/2 23:20
 * @verson
 */
@Slf4j
@RestController
@Api(value="文件Controller",tags={"文件操作接口"})
@RequestMapping("/file")
public class FileExportController {

    @Autowired
    private UserService userService;

    @GetMapping("/export")
    @ApiOperation(value = "Excel文件导出",notes = "User data export")
    public void importFile(HttpServletResponse response) throws IOException {
        /**excel导出*/
        List<User> users = userService.findAll();
        ArrayList<User> users1 = new ArrayList<>();
        users1.addAll(users);
        log.info("File:[{}],数据量:{}====================>pull start","用户信息表.xls",users.size());

        ExportParams params = new ExportParams("用户信息", "用户信息表");
        Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, users1);

        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("用户信息表.xls","UTF-8"));
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        ops.close();
        workbook.close();
    }


    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "用户文件上传",notes = "User avatar upload")
    public Result<String> upload(MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/file/";
        String flag = System.currentTimeMillis() + "";
        String fileName = file.getOriginalFilename();
        try {
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
            System.out.println(fileName + "--上传成功");
            Thread.sleep(1L);
        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        return Result.succ(flag);
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @PostMapping("/upload/multiple")
    @ApiOperation(value = "多文件上传",notes = "User more file upload")
    public Result<List<FileVO>> multipleUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/file/";
        List<FileVO> fileVOS = Lists.newArrayList();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String flag = System.currentTimeMillis() + "";
            String fileName = file.getOriginalFilename();
            FileVO fileVO = new FileVO();
            fileVO.setFlag(flag);
            fileVO.setFileName(file.getOriginalFilename());
            fileVOS.add(fileVO);
            try {
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
            }

        }
        return Result.succ(fileVOS);
    }
}
