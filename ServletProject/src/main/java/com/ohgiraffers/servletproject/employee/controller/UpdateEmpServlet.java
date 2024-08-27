package com.ohgiraffers.servletproject.employee.controller;

import com.ohgiraffers.servletproject.employee.model.dto.EmployeeDTO;
import com.ohgiraffers.servletproject.employee.model.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee/update")
public class UpdateEmpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 클라이언트로부터 empId와 entDate를 요청 파라미터로 받아옵니다.
        String empId = request.getParameter("empId");
        java.sql.Date entDate = java.sql.Date.valueOf(request.getParameter("entDate"));

        // 2. EmployeeDTO 객체를 생성하고, 클라이언트로부터 받은 값을 설정합니다.
        EmployeeDTO emp = new EmployeeDTO();
        emp.setEmpId(empId);
        emp.setEntDate(entDate);

        // 3. EmployeeService 클래스의 updateEmp 메서드를 호출하여 직원 정보를 업데이트합니다.
        int result = new EmployeeService().updateEmp(emp);

        // 4. 결과에 따라 적절한 JSP 페이지를 설정합니다.
        String path = "";
        if (result > 0) {
            // 업데이트가 성공한 경우
            path = "/WEB-INF/views/common/successPage.jsp";
            request.setAttribute("successCode", "updateEmp");
        } else {
            // 업데이트가 실패한 경우
            path = "/WEB-INF/views/common/errorPage.jsp";
            request.setAttribute("message", "회원 정보 수정 실패!");
        }

        // 5. 설정된 JSP 페이지로 포워딩하여 응답을 클라이언트에게 전달합니다.
        request.getRequestDispatcher(path).forward(request, response);
    }
}
