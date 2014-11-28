package com.etop.weixin.servlet;

import com.etop.weixin.service.RequestService;
import com.etop.weixin.service.VerificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 处理微信请求的servlet@
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
//@WebServlet(name = "RequestServlet", urlPatterns = "/request")
public class RequestServlet extends HttpServlet {

    RequestService requestService = new RequestService();
    VerificationService verificationService = new VerificationService();
    public static final String TOKEN = "weixin";

    /**
     * 处理微信信息请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        requestService.responseMsg(request.getInputStream(),response.getOutputStream());*/
    }

    /**
     * 处理微信token码验证
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        Writer out = response.getWriter();
        out.write(verificationService.verify(request,TOKEN));
        out.flush();
        out.close();*/
    }

}
