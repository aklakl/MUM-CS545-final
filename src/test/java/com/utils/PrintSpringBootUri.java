package com.utils;


import edu.mum.coffee.CoffeShopApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Mingli on 2017/05/24.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoffeShopApplication.class)
public class PrintSpringBootUri {


    @Test
    public void annotationScan() throws IllegalAccessException{
 
        Integer i = 0, j = 0;
        ArrayList<String> uriList = new ArrayList<>();
        Field field;

        try{
        	System.out.println("***************************************************start PrintSpringBootUri*****************************************************************");
            // get already loading class
            field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);
            Vector<Class> classes=(Vector<Class>) field.get(this.getClass().getClassLoader());
            List<Class> cl=new ArrayList<>(classes);

            // get RequestMapping value
            for (Class c : cl) {
            	try {
	                if (c.getAnnotation(RequestMapping.class)!=null) {
	                    //System.out.println(c.getName());
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
		                        // assemble RequestMapping
		                        String uri = bath[0] + bath2[0];
		                        uriList.add("CLASS:"+c.getName()+"|URI:"+uri);
		                        i++;
	                    }
	                }
            	}catch(Exception e){
                	//System.out.println("error"+i+":"+e.toString());
                	//e.printStackTrace();
                	continue;
                }
                //System.out.println(j+"|name:: "+c.getName() + "2uriList.size(): "+uriList.size());
                j++;
            }
            System.out.println("detect "+i+" uri");
            if (uriList.size()!=0) {
                uriList.forEach(System.out::println);
            }
            System.out.println("sum of the already loading class is "+j);
        //}catch(SQLException | ClassNotFoundException | NoSuchFieldException se){
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try{
            	//
            }catch(Exception se){
                se.printStackTrace();
            }
        }
       	System.out.println("***************************************************end PrintSpringBootUri*****************************************************************");
        
    }
}