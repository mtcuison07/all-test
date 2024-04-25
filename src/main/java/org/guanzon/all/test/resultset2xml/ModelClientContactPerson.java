package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelClientContactPerson {
    public static void main (String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_Client_Institution_Contact.xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            " sContctID" +
                            ", sClientID" +
                            ", sCPerson1" +
                            ", sCPPosit1" +
                            ", sMobileNo" +
                            ", sTelNoxxx" +
                            ", sFaxNoxxx" +
                            ", sEMailAdd" +
                            ", sAccount1" +
                            ", sAccount2" +
                            ", sAccount3" +
                            ", sRemarksx" +
                            ", cPrimaryx" +
                            ", cRecdStat" +
                            ", dModified" +
                        " FROM Client_Institution_Contact_Person" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance, loRS, System.getProperty("sys.default.path.metadata"), "Client_Institution_Contact_Person", "")){
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
