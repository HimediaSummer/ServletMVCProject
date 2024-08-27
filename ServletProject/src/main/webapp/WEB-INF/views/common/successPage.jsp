<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <script>
    (function() {
      const successCode = '${ requestScope.successCode }';

      let successMessage = '';
      let movePath = '';

      switch(successCode) {
        case 'insertEmp' :
          successMessage = '신규 직원 등록 성공!';
          movePath = '${pageContext.servletContext.contextPath}/employee/list';
          break;
        case 'updateEmp' :
          successMessage = '직원 정보 수정 성공!';
          movePath = '${pageContext.servletContext.contextPath}/employee/list';
          break;
        case 'deleteEmp' :
          successMessage = '직원 정보 삭제 성공!';
          movePath = '${pageContext.servletContext.contextPath}/employee/list';
          break;
      }

      alert(successMessage);
      location.href = movePath;

    })();
  </script>
</body>
</html>
