import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

public class mini extends JFrame {
    String username;
    mini(String username){
        this.username = username;
        ArrayList<String[]> dataList = new ArrayList<>();
        String column[] = {"Transaction","Amount","Balance","Date"};
        try {
            

            connectionsql c = new connectionsql();
            ResultSet rs = c.s.executeQuery("select type,amount,balance,date from acc"+username+"");

            while(rs.next()){
                String type = rs.getString("type");
                String amount = Integer.toString(rs.getInt("amount"));
                String balance = Integer.toString(rs.getInt("balance"));
                String date = rs.getString("date"); 
                
                String[] row = {type, amount, balance, date};
                dataList.add(row);
    

            }

            
        } catch (Exception e) {
            
        }

       
       String data[][] =dataList.toArray(new String[0][]); 
        JTable t1 = new JTable(data,column);
        t1.setBounds(30,40,250,300);
        JScrollPane sp =new JScrollPane(t1); 
        add(sp);


        setSize(500, 300);
        setLocation(290, 60);
        setVisible(true);
    }
    public static void main(String[] args) {
        new mini("");
    }
    
}
