package com.spring.lemon.controller;

import com.spring.lemon.entity.bean.LemonEmployee;
import com.spring.lemon.mapper.LemonEmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
public class LemonEmployeeController
{
    //日志的使用
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LemonEmployeeMapper lemonEmployeeMapper;

    @ResponseBody
    @RequestMapping("listLemonEmployee")
    @Transactional
    public LemonEmployee listLemonEmployee() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        throw new RuntimeException();
//        return lemonEmployeeMapper.selectByPrimaryKey(1);
    }

    private void insert() {
        LemonEmployee lemonEmployee = new LemonEmployee();
        lemonEmployee.setEmployeeId(1);
        lemonEmployee.setCreateUname("喵喵");
        lemonEmployeeMapper.updateByPrimaryKeySelective(lemonEmployee);
    }

    /**
     * postman不能加content-type
     *
     * @param request
     * @return
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile[] fileList) throws IOException {
        for (MultipartFile file : fileList)
        {
            if (!file.isEmpty())
            {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                logger.info("上传的文件名为：" + fileName);
                // 获取文件的后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                logger.info("上传的后缀名为：" + suffixName);
                // 文件上传后的路径
                String filePath = "D://test//";
                // 解决中文问题，liunx下中文路径，图片显示问题
                // fileName = UUID.randomUUID() + suffixName;
                File dest = new File(filePath + fileName);
                // 检测是否存在目录
                if (!dest.getParentFile().exists())
                {
                    dest.getParentFile().mkdirs();
                }
                try
                {
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    return "success";
                }
                catch (IllegalStateException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return "上传失败";
            }
        }
        return "fail";
    }

    /**
     * 文件下载
     *
     * @param request
     * @param res
     */
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse res) {
        String fileName = "fdgtdferer.JPG";
        StringBuffer requestURL = request.getRequestURL();
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try
        {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("d://test//"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1)
            {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @RequestMapping("showPic")
    public String showPic(HttpServletRequest request, HttpServletResponse res) {
        String fileName = "DSC09996.JPG";
        StringBuffer requestURL = request.getRequestURL();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try
        {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("d://test//"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1)
            {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
        return "success";
    }
}
