/*
package com.htxx.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/down")
public class DownloadController {

    @RequestMapping("/file")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到要下载的文件名
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        fileName = new String(fileName.toString().getBytes("UTF-8"));
        //上传的文件都是保存在/upload目录下的子目录当中
        String fileSaveRootPath = ResourceUtils.getURL("classpath:").getPath() + "\\upload";
        //String localFile = "D:\\tempFile";
        //得到要下载的文件
        File file = new File(fileSaveRootPath + "\\" + fileName);
        //如果文件不存在
        if (!file.exists()) {
            return null;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_") + 1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));

        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            //循环将输入流中的内容读取到缓冲区当中
            while (i != -1) {
                //输出缓冲区的内容到浏览器，实现文件下载
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    */
/**
     * @param filename     要下载的文件名
     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     * @return 要下载的文件的存储目录
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     *//*

    public String findFileSavePathByFileName(String filename, String saveRootPath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if (!file.exists()) {
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
*/
