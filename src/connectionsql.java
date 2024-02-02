import java.sql.*;
public class connectionsql {
    Connection c;
    static Statement s;

    public connectionsql(){
        try {
            c = DriverManager.getConnection("jdbc:mysql:///bankoverseeingsystem","root","Abhi123$");
            s = c.createStatement();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
