package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelBarangay {
    public static void main (String [] args){
        System.setProperty("sys.table", "Barangay");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sBrgyIDxx" +
                            ", a.sBrgyName" +
                            ", a.sTownIDxx" +
                            ", a.cHasRoute" +
                            ", a.cBlackLst" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" + 
                            ", b.sTownName xTownName" +
                        " FROM Barangay a" + 
                            " LEFT JOIN TownCity b ON a.sTownIDxx = b.sTownIDxx" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xTownName")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
