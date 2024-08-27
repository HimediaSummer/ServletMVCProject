package com.ohgiraffers.servletproject.common.jdbc;

import com.ohgiraffers.servletproject.common.config.ConfigLocation;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {

        Connection conn = null;

        Properties prop = new Properties();
        try {
            /* 설명. WEB-INF 하위에 config 폴더에 DB 접속 정보를 넣을 것이다.
             *  해당 경로를 모든 계층의 클래스에 사용하기 위해서는 모든 접속을 할 시 상수 필드로 저장하여 공유할 수 있도록 한다.
             *  이를 필터(ContextConfigFilter)에서 처리할 것이다.
             * */
            prop.load(new FileReader(ConfigLocation.CONNECTION_CONFIG_LOCATION));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            conn = DriverManager.getConnection(url, prop);

            /* 설명. autoCommit 설정 변경 : 트랜잭션 직접 제어 */
            conn.setAutoCommit(false);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

