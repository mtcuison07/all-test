package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelLaborCategory {
    public static void main (String [] args){
        System.setProperty("sys.table", "Labor_Category");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sLaborIDx" +
                            ", a.sCategrCd" +
                            ", a.nAmountxx" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sDescript xCategrNm" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Category b ON a.sCategrCd = b.sCategrCd" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xCategrNm")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
