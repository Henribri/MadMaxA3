package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Model {

    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    private PreparedStatement ps;

    public Model() {
        connection = getConnection();
    }

    public void getAll() throws SQLException {
        Connection conn = this.connection;
        statement = conn.createStatement();
        rs = statement.executeQuery("SELECT * FROM tb_personne");

        // ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            String columnValue = rs.getString("name");
            System.out.print(columnValue + "\n");
        }

        rs.close();
        statement.close();
    }

    private Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/seminaire?serverTimezone=UTC", "root", "");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;
        }
    }

    public boolean checkIfUserExist(String uname, String pass) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM `tb_personne` WHERE `uname` =? AND `pass` =?";
        ps = getConnection().prepareStatement(query);

        ps.setString(1, uname);
        ps.setString(2, getMD5Hash(pass));
        rs = ps.executeQuery();

        return rs.next();
    }

    private static String getMD5Hash(String s) throws NoSuchAlgorithmException {

        String result = s;
        if (s != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
            md.update(s.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) { // 40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }
}
