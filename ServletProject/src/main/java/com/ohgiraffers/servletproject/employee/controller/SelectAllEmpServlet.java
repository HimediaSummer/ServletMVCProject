package com.ohgiraffers.servletproject.employee.controller;

import com.ohgiraffers.servletproject.employee.model.dto.EmployeeDTO;
import com.ohgiraffers.servletproject.employee.model.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/employee/list")
public class SelectAllEmpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeService empService = new EmployeeService();
        List<EmployeeDTO> empList =  empService.selectAllEmp();

        for(EmployeeDTO emp : empList) {
            System.out.println(emp);
        }

        /* 조회 결과 성공 여부에 따른 뷰 결정 */
        String path = "";
        if(empList != null) {
            path = "/WEB-INF/views/employee/employeeList.jsp";
            request.setAttribute("empList", empList);
        } else {
            path = "/WEB-INF/common/errorPage.jsp";
            request.setAttribute("message", "직원 목록 조회 실패!");
        }

        request.getRequestDispatcher(path).forward(request, response);

    }
}
