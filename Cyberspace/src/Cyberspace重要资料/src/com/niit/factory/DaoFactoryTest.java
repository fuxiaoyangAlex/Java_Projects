//package com.niit.factory;
//
//import com.niit.domain.Emp;
//import org.junit.Test;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.junit.Assert.*;
//
//public class DaoFactoryTest {
//    @Test
//    public void test() throws Exception{
//        String time = "2010-7-12";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//
//        Date hireDate = sdf.parse(time);
//        Emp emp = new Emp("Linda","Worker_manager",hireDate,4000,5000);
//
//        int i = DaoFactory.getEmpDaoInstance().insert(emp);
//        if(i == 1){
//            System.out.println("Insert into the table successfully!");
//        }
//
//    }
//
//}