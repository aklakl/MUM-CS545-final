package com.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import edu.mum.coffee.CoffeShopApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Mingli on 2017/05/24.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoffeShopApplication.class)
public class SaveUriToDB {

    // JDBC 驱动名及数据库 URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver" ;
    private static final String DB_URL = "jdbc:mysql://*.*.*.*/****?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true&allowMultiQueries=true";

    // 数据库的用户名与密码
    private static final String USER = "******";
    private static final String PASS = "******";

    @Test
    public void annotationScan() throws IllegalAccessException{
        Connection conn = null;
        Statement stmt = null;
        Integer i = 0, j = 0;
        ArrayList<String> uriList = new ArrayList<>();
        Field field;


        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

            // 创建资源
            stmt = (Statement) conn.createStatement();

            // 获取所有已经加载类
            field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);
            Vector<Class> classes=(Vector<Class>) field.get(this.getClass().getClassLoader());
            List<Class> cl=new ArrayList<>(classes);

            // 获取RequestMapping值
            for (Class c : cl) {
                if (c.getAnnotation(RequestMapping.class)!=null) {
                    System.out.println(c.getName());
                    RequestMapping req = (RequestMapping) c.getAnnotation(RequestMapping.class);
                    String[] bath = req.path().length > 0 ? req.path() : req.value();
                    if (bath.length == 0) {
                        continue;
                    }
                    Method[] ms = c.getDeclaredMethods();
                    for (Method m : ms) {
                        String[] bath2;
                        if (m.getAnnotation(RequestMapping.class) != null) {
                            RequestMapping rm = m.getAnnotation(RequestMapping.class);
                            bath2 = rm.path().length > 0 ? rm.path() : rm.value();
                            bath2[0] = bath2[0].startsWith("/")? bath2[0] : "/"+bath2[0];
                        } else if (m.getAnnotation(GetMapping.class) != null) {
                            GetMapping rm = m.getAnnotation(GetMapping.class);
                            bath2 = rm.path().length > 0 ? rm.path() : rm.value();
                            bath2[0] = bath2[0].startsWith("/")? bath2[0] : "/"+bath2[0];
                        } else if (m.getAnnotation(PostMapping.class) != null){
                            PostMapping rm = m.getAnnotation(PostMapping.class);
                            bath2 = rm.path().length > 0 ? rm.path() : rm.value();
                            bath2[0] = bath2[0].startsWith("/")? bath2[0] : "/"+bath2[0];
                        } else if (m.getAnnotation(PutMapping.class) != null){
                            PutMapping rm = m.getAnnotation(PutMapping.class);
                            bath2 = rm.path().length > 0 ? rm.path() : rm.value();
                            bath2[0] = bath2[0].startsWith("/")? bath2[0] : "/"+bath2[0];
                        } else if (m.getAnnotation(DeleteMapping.class) != null){
                            DeleteMapping rm = m.getAnnotation(DeleteMapping.class);
                            bath2 = rm.path().length > 0 ? rm.path() : rm.value();
                            bath2[0] = bath2[0].startsWith("/")? bath2[0] : "/"+bath2[0];
                        } else if (m.getAnnotation(PatchMapping.class) != null){
                            PatchMapping rm = m.getAnnotation(PatchMapping.class);
                            bath2 = rm.path().length > 0 ? rm.path() : rm.value();
                            bath2[0] = bath2[0].startsWith("/")? bath2[0] : "/"+bath2[0];
                        } else
                            continue;
                        if (bath2.length == 0) {
                            continue;
                        }

                        // 组装RequestMapping
                        String uri = bath[0] + bath2[0];
                        			
                        // uri保存到数据库
                        String sql;
                        sql = "SELECT * FROM zx_management_operation WHERE interface_uri='" + uri + "'";
                        ResultSet rs = stmt.executeQuery(sql);
                        if (!rs.next()) {
                            sql = "insert into zx_management_operation (interface_uri)" +
                                    "    values ('" + uri + "')";
                            stmt.execute(sql);
                            uriList.add(uri);
                            j++;
                        }
                        System.out.println(uri);
                        i++;
                    }
                }
            }
            System.out.println("共检测出"+i+"个uri");
            if (uriList.size()!=0) {
                uriList.forEach(System.out::println);
            }
            System.out.println("共新增加"+j+"个uri");
            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException | NoSuchFieldException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        } finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException ignored){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}