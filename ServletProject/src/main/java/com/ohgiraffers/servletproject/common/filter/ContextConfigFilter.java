package com.ohgiraffers.servletproject.common.filter;

import com.ohgiraffers.servletproject.common.config.ConfigLocation;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class ContextConfigFilter implements Filter {

    public ContextConfigFilter() {
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        /* 설명.
         *  DB 접속 설정 정보 파일의 경로 정보가 비어있는 경우 초기화 해준다.
         *  (web.xml, ConfigLocation.java 참고 요망)
         * */
        if (ConfigLocation.CONNECTION_CONFIG_LOCATION == null) {
            String root = request.getServletContext().getRealPath("/");
            String connectionInfoPath = request.getServletContext().getInitParameter("connection-info");

            System.out.println("DB접속경로 설정 완료");
            System.out.println(root + "/" + connectionInfoPath);    // 제작된 DB 접속경로 확인
            ConfigLocation.CONNECTION_CONFIG_LOCATION = root + "/" + connectionInfoPath;
        }

        if (ConfigLocation.MAPPER_LOCATION == null) {
            String root = request.getServletContext().getRealPath("/");
            String mapperLocation = request.getServletContext().getInitParameter("mapper-location");

            System.out.println("매퍼 경로 설정 완료");
            System.out.println(root + "/" + mapperLocation);        // 제작된 매퍼 접속경로 확인
            ConfigLocation.MAPPER_LOCATION = root + "/" + mapperLocation;
        }

        /* 설명. 이렇게 경로를 static 영역에 저장하면 템플릿에서도 사용이 가능하다. */
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}

