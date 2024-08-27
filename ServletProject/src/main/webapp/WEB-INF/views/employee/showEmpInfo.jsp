<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="../common/header.jsp"/>

    사원번호 : ${ requestScope.selectedEmp.empId }<br>
    사원명 : ${ requestScope.selectedEmp.empName }<br>
    부서코드 : ${ requestScope.selectedEmp.deptCode }<br>
    직급코드 : ${ requestScope.selectedEmp.jobCode }<br>
    급여 : ${ requestScope.selectedEmp.salary }<br>
</body>
</html>
