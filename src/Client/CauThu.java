
package Client;

import java.io.Serializable;

public class CauThu implements Serializable{ //đối tượng có thể được biểu diễn như là một dãy byte liên tục
                                             //mà bao gồm dữ liệu của đối tượng cũng như thông tin về kiểu đối tượng
                                             //và kiểu dữ liệu được lưu giữ trong đối tượng.
    
    int maCT, so;
    String hoten,vitri,maCLB,ngaysinh,diachi,maQG;
    
    public void setmaCT(int ma){
        this.maCT = ma;
    }
    
    public void setso(int numb){
        this.so = numb;
    }
    
    public void sethoten(String name){
        this.hoten = name;
    }
    
    public void setvitri(String pos){
        this.vitri = pos;
    }
    
     public void setdiachi(String address){
        this.diachi = address;
    }
     
      public void setmaCLB(String maclb){
        this.maCLB = maclb;
    }
      
      public void setngaysinh(String date){
        this.ngaysinh = date;
    }
      
      public void setmaQG(String maqg){
        this.maCLB = maqg;
    }
      
     //getter
      
      public int getmaCT(){
        return this.maCT;
    }
    
    public int getso(){
        return this.so;
    }
    
    public String gethoten(){
        return this.hoten;
    }
    
    public String getvitri(){
        return this.vitri;
    }
    
     public String getdiachi(){
        return this.diachi;
    }
     
      public String getmaCLB(){
        return this.maCLB;
    }
      
      public String getngaysinh(){
        return this.ngaysinh;
    }
      
      public String getmaQG(){
        return this.maCLB;
    }
      //constructor
      public CauThu(int mact,String name,String pos,String date,String address,String maclb,String maqg,int so){
          setdiachi(address);
          sethoten(name);
          setmaCLB(maclb);
          setmaCT(mact);
          setmaQG(maqg);
          setngaysinh(date);
          setso(so);
          setvitri(pos);
      }
}


