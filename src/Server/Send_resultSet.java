
package Server;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Send_resultSet implements Serializable{
    public String[] arr = null;
    
    public Vector vt2 = new Vector();

    public Send_resultSet(ResultSet rs) {
        arr = get_ColumnName(rs);
        vt2 = get_ColumnInfor(rs);
    }

    public String[] get_ColumnName(ResultSet rs){
        try {    
            ResultSetMetaData rsMT = rs.getMetaData();
            int colnumber = rsMT.getColumnCount();
            arr = new String[colnumber];
            for(int i=0; i<colnumber;i++){
                arr[i] = rsMT.getColumnName(i+1);
            }            
            return arr;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Vector get_ColumnInfor(ResultSet rs){
            try {    
            ResultSetMetaData rsMT = rs.getMetaData();
            int colnumber = rsMT.getColumnCount();
            while(rs.next()){      
                Vector vt1 = new Vector();
                for(int i=0;i<colnumber;i++){
                    vt1.add(rs.getString(i+1));
                } 
                vt2.add(vt1);
            }
            return vt2;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
