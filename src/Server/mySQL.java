
package Server;

import Client.CauThu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySQL {
 
    public Connection cn;
    public final String url="jdbc:mysql://localhost:3306/qlbongda";
    public final String userName = "root";
    public final String password = "ngadola"; 
    
    public mySQL(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url,userName,password);
            System.out.println("connected!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        }catch (SQLException e){
            System.out.println("Error connection!");
        }       
    }
    
    //Lấy thông tin của bảng cauthu để in danh sách sinh viên
    public ResultSet getData(){
       ResultSet rs = null;
        Statement st = null;
        String sql = "select * from cauthu ";
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("selected error!"+ ex.toString());
        }
        return rs;
    }
 //trả về thông tin cầu thủ có mã là mã đã nhập vào   
    public ResultSet getData_ma(String ma){
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "select * from cauthu where mact = ?";
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1,ma);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("selected error!"+ ex.toString());
        }
        return rs;
    }
       
    public boolean addData_cauthu(CauThu cauthu){
        
        String sql = "insert into cauthu values(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
             pst = cn.prepareStatement(sql);
             
             pst.setInt(1,cauthu.getmaCT());
             pst.setString(2,cauthu.gethoten());
             pst.setString(3,cauthu.getvitri());
             pst.setString(4,cauthu.getngaysinh());
             pst.setString(5,cauthu.getdiachi());
             pst.setString(6,cauthu.getmaCLB());
             pst.setString(7,cauthu.getmaQG());
             pst.setInt(8,cauthu.getso());
             
             pst.executeUpdate();
              System.out.println("Thanh cong!");
             return true; // thành công
            
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false; 
    }
    
//Xóa cầu thủ có maCT nhập từ bàn phím
    public boolean delete_cauthu(String ma){
        
        String sql = "delete from cauthu where mact = ?";
        PreparedStatement pst = null;
        try {
             pst = cn.prepareStatement(sql);             
             pst.setString(1,ma);
             pst.executeUpdate();
             System.out.println("Thanh cong!");
             return true; // thành công                 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false; 
    }
//update thông tin của cầu thủ
    public boolean updateData(CauThu cauthu){
        String sql = "update cauthu set maCT = ?, hoten = ?, vitri = ?, ngaysinh=?, diachi=?, maCLB=?, maQG=?, so=? where maCT = ?";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1,cauthu.getmaCT());
             pst.setString(2,cauthu.gethoten());
             pst.setString(3,cauthu.getvitri());
             pst.setString(4,cauthu.getngaysinh());
             pst.setString(5,cauthu.getdiachi());
             pst.setString(6,cauthu.getmaCLB());
             pst.setString(7,cauthu.getmaQG());
             pst.setInt(8,cauthu.getso());
             pst.setInt(9,cauthu.getmaCT());
             
             pst.executeUpdate();
             System.out.println("Thanh cong!");
             return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
