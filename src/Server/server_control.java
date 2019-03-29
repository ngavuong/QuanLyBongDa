
package Server;

import Client.CauThu;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class server_control {
    
    mySQL mysql;
    ServerSocket ss;
    public int port;
    public Socket s;

//constructor
    public server_control() {
        port = 1998;
        mysql = new mySQL();
        openSocket();  //mở kết nối tới cổng 1998
        while(true){
            try {
                s = ss.accept();
                int x = receiveOption();
                
                if(x==1){                   
                    sendResult("OK");
                    addCauThu_exe();
                }
                else if(x==2){                   
                    sendResult("OK");
                    updateCauThu_exe();
                }
                else if(x==3){
                    getData_exe();
                }
                else if(x==4){
                    sendResult("OK");
                    String ma = receiveMa();
                    find_exe(ma);                   
                }
                else if(x==5){
                    sendResult("OK");
                    String ma = receiveMa();
                    delete_exe(ma);                   
                }
                               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void openSocket(){
        try {
            ss = new ServerSocket(port);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void sendResult(String res){
         try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public CauThu receiveCauThu(){
        CauThu c = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            c= (CauThu)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
  //server gửi về option để điều khiển action  
    public int receiveOption(){
        int x = -1;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            x= (int)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    
 // nhận mã cầu thủ cần tìm kiếm về để lấy thông tin cầu thủ đó
     public String receiveMa(){
         String ma =null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ma= (String)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }
// kiểm tra thêm cầu thủ vào database có thành công không 
    public void addCauThu_exe(){
        CauThu c = receiveCauThu();
        if(mysql.addData_cauthu(c)){
            sendResult("OK");
            System.out.println("thành công!");
        }           
        else{
            sendResult("failed!");
            System.out.println("thất bại!");
        }
    }
    
    // kiểm tra thêm cầu thủ vào database có thành công không 
    public void updateCauThu_exe(){
        CauThu c = receiveCauThu();
        if(mysql.updateData(c)){
            sendResult("OK");
            System.out.println("Sửa thành công!");
        }           
        else{
            sendResult("failed!");
            System.out.println("Sửa thất bại!");
        }
    }
//Trả về cầu thủ cần tìm từ database
    public void find_exe(String ma){
        ResultSet rs = mysql.getData_ma(ma);
        send_tableInfo(rs);
    }
 //xóa cầu thủ từ database
    public void delete_exe(String ma){
        if(mysql.delete_cauthu(ma)){
            sendResult("OK");
        }
        else
            sendResult("failed!");
    }

// lấy dữ liệu thông tin cầu thủ từ database rồi gửi cho client
    public void getData_exe(){
        ResultSet rs = mysql.getData();
        send_tableInfo(rs);
    }
     
//server gửi kết quả ResultSet rs cho  client để in thông tin ra bảng
    public void send_tableInfo(ResultSet rs){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(new Send_resultSet(rs));   //chuyển rs về đối tượng Send_resultSet        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    

}

 
