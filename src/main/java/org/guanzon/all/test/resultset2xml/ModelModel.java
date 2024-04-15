package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelModel {
    public static void main (String [] args){
        System.setProperty("sys.table", "Model");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sModelCde" +
                            ", a.sCategrCd" +
                            ", a.sModelNme" +
                            ", a.sDescript" +
                            ", a.sBriefDsc" +
                            ", a.sBrandCde" +
                            ", a.cEndOfLfe" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sDescript xBrandNme" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Brand b ON a.sBrandCde = b.sBrandCde" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xBrandNme")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
