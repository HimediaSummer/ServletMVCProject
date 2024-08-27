package com.ohgiraffers.servletproject.employee.controller;

import com.ohgiraffers.servletproject.employee.model.dto.EmployeeDTO;
import com.ohgiraffers.servletproject.employee.model.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee/select")
public class SelectOneEmpByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String empId = request.getParameter("empId");   //main.jsp에서 사용자가 입력한 번호 가져오기

        System.out.println("empId : " + empId);     //확인

        EmployeeService empService = new EmployeeService();
        EmployeeDTO selectedEmp = empService.selectOneEmpById(empId);   //empService에 일시키기

        System.out.println("selectedEmp : " + selectedEmp);     //DAO에서 가져온 값 확인

        String path = "";
        if(selectedEmp != null) {
            path = "/WEB-INF/views/employee/showEmpInfo.jsp";
            request.setAttribute("selectedEmp", selectedEmp);
        } else {
            path = "/WEB-INF/views/common/errorPage.jsp";
            request.setAttribute("message", "직원 정보 조회 실패");
        }

        request.getRequestDispatcher(path).forward(request, response);

    }
}
