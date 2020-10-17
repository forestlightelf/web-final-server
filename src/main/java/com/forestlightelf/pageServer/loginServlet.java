package com.forestlightelf.pageServer;

import com.alibaba.fastjson.JSON;
import com.forestlightelf.mybatisMapper.mybatisUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.forestlightelf.mybatisMapper.*;
import java.io.IOException;
@WebServlet(value = "/s01")
public class loginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置跨域
        resp.setHeader("Access-Control-Allow-Origin","*");//允许所有域名访问
        resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        //设置请求的编码格式
        req.setCharacterEncoding("UTF-8");
        //设置响应的类型
        resp.setContentType("text/json; charset=utf-8");
        //获取普通表单项（文本框）
        String uName=req.getParameter("uName");
        String uPassword=req.getParameter("uPassword");
        System.out.println(uPassword);
        System.out.println(uName);
        System.out.println("请求参数列表："+req.getQueryString());
        //与数据库比对
        SqlSession sqlSession= mybatisUtil.getSqlSession();
        userInfoQuery query=sqlSession.getMapper(userInfoQuery.class);

        boolean match=uPassword.equals(query.getUserPassword(uName));
        //转换
        String json= JSON.toJSONString(match);

        //输出
        resp.getWriter().write(json);
    }
}
