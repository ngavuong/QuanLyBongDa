
package Client;

import Server.Send_resultSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Vector;

public class client_control {
    String host;
    int port;
    Socket s;

    public client_control() {
        host = "localhost";
        port = 1998;
    }
    public Socket openSocket(){
        try {
            s = new Socket(host,port);
        } catch (IOException ex) {
            System.out.println("error!");
        }
        return s;
    }
// đóng socket
    public void CloseConnect(Socket s){
        try {
            s.close();
        } catch (IOException ex) {
            
        }
    }
 // Gửi cầu thủ c sang phía server
    public void sendCauThu(CauThu c){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(c); //Gửi cauthu c sang bên server
        } catch (IOException ex) {
            System.out.println("Can't send!");
        }
    }
//nhận kết quả phản hồi từ server
    public String getResult(){
        String res = "";
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            res = (String)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return res;
    }
    
    public void send_option(int x){
         try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(x); //Gửi option x sang bên server
        } catch (IOException ex) {
            System.out.println("Can't send!");
        }
    }
  // gửi mã cầu thủ cần tìm kiếm  
     public void send_Ma(String ma){
         try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(ma); 
        } catch (IOException ex) {
            System.out.println("Can't send!");
        }
    }

 // nhận kết quả danh sách cầu thủ từ server để in ra bảng   
    public Send_resultSet receive_tableInfo(){
        Send_resultSet srs = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            srs = (Send_resultSet)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srs;
    }
}
