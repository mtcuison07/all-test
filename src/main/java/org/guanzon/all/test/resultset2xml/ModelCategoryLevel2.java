package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelCategoryLevel2 {
    public static void main (String [] args){
        System.setProperty("sys.table", "Category_Level2");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sCategrCd" +
                            ", a.sDescript" +
                            ", a.sInvTypCd" +
                            ", a.sMainCatx" +
                            ", a.cClassify" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sDescript xInvTypNm" +
                            ", c.sDescript xMainCatx" +
                        " FROM " + System.getProperty("sys.table") + " a" + 
                            " LEFT JOIN Inv_Type b ON a.sInvTypCd = b.sInvTypCd" +
                            " LEFT JOIN Category c ON a.sCategrCd = c.sCategrCd" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xInvTypNm»xMainCatx")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
