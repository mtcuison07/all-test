package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelProvince {
    public static void main (String [] args){
        System.setProperty("sys.table", "Province");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sProvIDxx" +                      
                            ", a.sProvName" +                      	
                            ", a.sRegionID" +                      	
                            ", a.cRecdStat" +                      
                            ", a.sModified" +                      	
                            ", a.dModified" +     
                            ", b.sRegionNm xRegionNm" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Region b ON a.sRegionID = b.sRegionID" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xRegionNm")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
