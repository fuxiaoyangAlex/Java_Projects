package blog.flowingsun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    List<Connection> cs = new ArrayList<Connection>();
    int size;
    public ConnectionPool(int size){
        this.size = size;
        init();
    }

    public void init(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            for(int i=0; i<size; i++){
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/myblog?characterEncoding=utf-8","root","admin");
                cs.add(c);
            }
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public synchronized Connection getConnection(){
        while(cs.isEmpty()){
            try{
                this.wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        Connection c = cs.remove(0);
        return c;
    }

    public synchronized void returnConnection(Connection c){
      if(c !=null){
        cs.add(c);
        this.notifyAll();
      }
    }
}
