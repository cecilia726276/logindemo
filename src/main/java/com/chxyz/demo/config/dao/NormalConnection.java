package com.chxyz.demo.config.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NormalConnection {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            String url = "jdbc:postgresql://rm-uf6vxrx64o9a7zsh57o.pg.rds.aliyuncs.com:3432/web_demo"; //PostgreSQL数据库实例所在的ip地址，并设置端口
            // web_demo 为数据库名
            String user = "cecilia726276";
            String password = "13_mouses";
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(url, user, password);
            System.out.println("是否成功连接pg数据库"+connection);
            String sql = "select * from users";
            statement = connection.createStatement();

            /**
             * 关于ResultSet的理解：Java程序中数据库查询结果的展现形式，或者说得到了一个结果集的表
             * 在文档的开始部分有详细的讲解该接口中应该注意的问题，请阅读JDK
             * */
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                //取出列值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println(id+","+name+",");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally{
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

        }
    }

}