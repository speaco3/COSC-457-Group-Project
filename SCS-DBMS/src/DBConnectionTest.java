import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class DBConnectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        final String ID = "speaco3";
        final String PW = "COSC*zooge";
        final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/" + ID + "db?useSSL=false";
        ResultSet rs;
        int id, ssn, student, staff;
        String fname, minit, lname;

        try {
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM speaco3db.Person");
            while (rs.next()) {
                System.out.println(rs.getString("ID") + "; " + rs.getString("SSN") + "; " + rs.getString("FName") + "; " + rs.getString("Minit") + "; " + rs.getString("LName") + "; " + rs.getString("Student") + "; " + rs.getString("Staff"));
            }
            System.out.println();

            PreparedStatement insertTest = con.prepareStatement("INSERT INTO speaco3db.Person VALUES (?, ?, ?, ?, ?, ?, ?)");
            id = 100;
            ssn = 888888888;
            fname = "Sam";
            minit = "F";
            lname = "Peacock";
            student = 1;
            staff = 1;
            insertTest.setInt(1 , id);
            insertTest.setInt(2, ssn);
            insertTest.setString(3, fname);
            insertTest.setString(4, minit);
            insertTest.setString(5, lname);
            insertTest.setInt(6, student);
            insertTest.setInt(7, staff);
            insertTest.executeUpdate();

            rs = stmt.executeQuery("SELECT * FROM speaco3db.Person");
            while (rs.next()) {
                System.out.println(rs.getString("ID") + "; " + rs.getString("SSN") + "; " + rs.getString("FName") + "; " + rs.getString("Minit") + "; " + rs.getString("LName") + "; " + rs.getString("Student") + "; " + rs.getString("Staff"));
            }
            System.out.println();

            PreparedStatement updateTest = con.prepareStatement("UPDATE speaco3db.Person SET LName = ? WHERE ID = ?");
            lname = "Kroon";
            id = 100;
            updateTest.setString(1, lname);
            updateTest.setInt(2, id);
            updateTest.executeUpdate();

            rs = stmt.executeQuery("SELECT * FROM speaco3db.Person");
            while (rs.next()) {
                System.out.println(rs.getString("ID") + "; " + rs.getString("SSN") + "; " + rs.getString("FName") + "; " + rs.getString("Minit") + "; " + rs.getString("LName") + "; " + rs.getString("Student") + "; " + rs.getString("Staff"));
            }
            System.out.println();

            PreparedStatement deleteTest = con.prepareStatement("DELETE FROM speaco3db.Person WHERE ID = ?");
            deleteTest.setInt(1, id);
            deleteTest.executeUpdate();

            rs = stmt.executeQuery("SELECT * FROM speaco3db.Person");
            while (rs.next()) {
                System.out.println(rs.getString("ID") + "; " + rs.getString("SSN") + "; " + rs.getString("FName") + "; " + rs.getString("Minit") + "; " + rs.getString("LName") + "; " + rs.getString("Student") + "; " + rs.getString("Staff"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }// Main

}
