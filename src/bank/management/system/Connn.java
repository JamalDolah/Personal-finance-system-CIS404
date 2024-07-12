package bank.management.system;

import java.sql.*;

public class Connn {
    Connection connection;
    Statement statement;
    public Connn(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance_system",
                    "root","Majicmen64");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
