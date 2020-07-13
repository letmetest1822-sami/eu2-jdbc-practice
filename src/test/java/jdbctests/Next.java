package jdbctests;

import java.sql.*;

public class Next {
    public static void main(String[] args) throws SQLException {
        String dbUrl ="jdbc:oracle:thin:@100.25.34.235:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";
//create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //move pointer to first row
        while(resultSet.next()) {

            System.out.println(resultSet.getString("first_name"));

        }


        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
