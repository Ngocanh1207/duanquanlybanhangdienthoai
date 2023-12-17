package bandienthoai.model.BackEnd.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConnectionDB {

    private static int countConection = 0;
    private static int countQuery = 0;
    private static int countUpdate = 0;

    private Connection conn;
    private Statement stmt = null;
    private ResultSet rset = null;

    private String DB_Name;
    private String user_Name;
    private String pass;

    private String ipAddress;

    public ConnectionDB() {
        this.conn = null;
        this.DB_Name = null;
        this.user_Name = null;
        this.pass = null;
        this.ipAddress = "localhost:3306";
        checkDriver();
        DB_Name = "quanlysieuthidienthoai";
        user_Name = "root";
        pass = "";
        setupConnect();
    }

    public ConnectionDB(String DB_Name) {
        this.conn = null;
        this.DB_Name = null;
        this.user_Name = null;
        this.pass = null;
        this.ipAddress = "localhost:3306";
        checkDriver();
        this.DB_Name = DB_Name;
    }

    public ConnectionDB(String DB_Name, String user_Name, String pass) {
        this.conn = null;
        this.DB_Name = null;
        this.user_Name = null;
        this.pass = null;
        this.ipAddress = "localhost:3306";
        checkDriver();
        this.DB_Name = DB_Name;
        this.user_Name = user_Name;
        this.pass = pass;
        setupConnect();
    }

    // Kết nối tới DB
    private void setupConnect() {
        try {
            String url = "jdbc:mysql://" + getIpAddress() + "/" + getDB_Name() + "?useUnicode=true&characterEncoding=UTF-8";
            setConn(DriverManager.getConnection(url, getUser_Name(), getPass()));
            setStmt(getConn().createStatement());
            setCountConection(getCountConection() + 1);
            System.out.println("**\n" + getCountConection() + ": Success! Đã kết nối tới '" + getDB_Name() + "'");

        } catch (SQLException e) {
            System.err.println("-- ERROR! Không thể kết nối tới '" + getDB_Name() + "'");
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể kết nối tới '" + getDB_Name() + "'");
        }
    }

    // đăng nhập
    public void logIn(String user_Name, String pass) {
        this.setUser_Name(user_Name);
        this.setPass(pass);
        setupConnect();
    }

    // lấy data theo câu query
    public ResultSet sqlQuery(String qry) {
        if (checkConnect()) {
            try {
                setRset(getStmt().executeQuery(qry));
                setCountQuery(getCountQuery() + 1);
                System.out.println(getCountQuery() + ": Success Query! " + qry);
                return getRset();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể lấy dữ liệu từ " + getDB_Name() + "\n" + ex.getLocalizedMessage());
                return null;
            }
        }
        return null;
    }

    // ghi data theo câu update
    public Boolean sqlUpdate(String qry) {
        if (checkConnect()) {
            try {
                getStmt().executeUpdate(qry);
                setCountUpdate(getCountUpdate() + 1);
                System.out.println(getCountUpdate() + ": Success Update! " + qry);
                return true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể ghi dữ liệu xuống " + getDB_Name() + "\n" + ex.getLocalizedMessage());
                return false;
            }
        }
        return false;
    }

    // đóng connection
    public void closeConnect() {
        try {
            if (getConn() != null) {
                getConn().close();
            }
            if (getStmt() != null) {
                getStmt().close();
            }
            System.out.println("Success! Đóng kết nối tới '" + getDB_Name() + "' thành công.\n**");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể đóng kết nối tới " + getDB_Name() + "\n" + ex.getLocalizedMessage());
        }
    }

    // check logIn, connect
    public Boolean checkConnect() {
        if (getConn() == null || getStmt() == null) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Chưa thiết lập kết nối tới '" + getDB_Name() + "'. Vui lòng đăng nhập để thiết lập kết nối!");
            return false;
        }
        return true;
    }

    // check driver
    private void checkDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không tìm thấy Driver mySql");
        }
    }

    // lấy headers của table tên tableName trong db này - đã có cách khác, không dùng hàm này nữa
    public ArrayList<String> getHeaders(String tableName) {
        ArrayList<String> headers = new ArrayList<>();
        if (checkConnect()) {
            try {
                ResultSetMetaData rsMetaData = sqlQuery("SELECT * FROM " + tableName).getMetaData();
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    headers.add(rsMetaData.getColumnName(i));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể lấy headers của " + tableName + " trong " + getDB_Name());
            }
        }
        return headers;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the user_Name
     */
    public String getUser_Name() {
        return user_Name;
    }

    /**
     * @param user_Name the user_Name to set
     */
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    /**
     * @return the DB_Name
     */
    public String getDB_Name() {
        return DB_Name;
    }

    /**
     * @param DB_Name the DB_Name to set
     */
    public void setDB_Name(String DB_Name) {
        this.DB_Name = DB_Name;
    }

    /**
     * @return the rset
     */
    public ResultSet getRset() {
        return rset;
    }

    /**
     * @param rset the rset to set
     */
    public void setRset(ResultSet rset) {
        this.rset = rset;
    }

    /**
     * @return the stmt
     */
    public Statement getStmt() {
        return stmt;
    }

    /**
     * @param stmt the stmt to set
     */
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return the countUpdate
     */
    public static int getCountUpdate() {
        return countUpdate;
    }

    /**
     * @param aCountUpdate the countUpdate to set
     */
    public static void setCountUpdate(int aCountUpdate) {
        countUpdate = aCountUpdate;
    }

    /**
     * @return the countQuery
     */
    public static int getCountQuery() {
        return countQuery;
    }

    /**
     * @param aCountQuery the countQuery to set
     */
    public static void setCountQuery(int aCountQuery) {
        countQuery = aCountQuery;
    }

    /**
     * @return the countConection
     */
    public static int getCountConection() {
        return countConection;
    }

    /**
     * @param aCountConection the countConection to set
     */
    public static void setCountConection(int aCountConection) {
        countConection = aCountConection;
    }
}
