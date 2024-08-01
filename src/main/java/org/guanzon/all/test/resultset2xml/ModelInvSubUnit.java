package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelInvSubUnit {
    public static void main (String [] args){
        System.setProperty("sys.table", "Inventory_Sub_Unit");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sStockIDx" +
                            ", a.nEntryNox" +
                            ", a.sItmSubID" +
                            ", a.nQuantity" +
                            ", a.dModified" +
                            ", b.sBarCodex xBarCodex" +
                            ", b.sDescript xDescript" +
                            ", c.sBarCodex xBarCodeU" +
                            ", c.sDescript xDescripU" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Inventory b ON a.sStockIDx = b.sStockIDx" +
                            " LEFT JOIN Inventory c ON a.sItmSubID = c.sStockIDx" +
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
