package com.htxx.service;

import com.htxx.entity.InvoiceUploadResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public interface DanJuDaoRuService {

    InvoiceUploadResult uploadFile(MultipartFile file, HttpSession session) throws IOException;
}
