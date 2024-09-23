package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelInvLocation {
    public static void main (String [] args){
        System.setProperty("sys.table", "Inv_Location");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sLocatnID" +
                            ", a.sDescript" +
                            ", a.sWHouseID" +
                            ", a.sSectnIDx" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sWHouseNm xWHouseNm" +
                            ", c.sSectnNme xSectnNme" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Warehouse b ON a.sWHouseID = b.sWHouseID" +
                            " LEFT JOIN Section c ON a.sSectnIDx = c.sSectnIDx" +
                        " WHERE 0=1";
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
