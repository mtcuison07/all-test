package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelInvStockRequestCancelDetail {
    public static void main (String [] args){
        System.setProperty("sys.table", "Inv_Stock_Req_Cancel_Detail");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.nEntryNox" +
                            ", a.sOrderNox" +
                            ", a.sStockIDx" +
                            ", a.nQuantity" +
                            ", a.sNotesxxx" +
                            ", a.dModified" +
                            ", b.sBarCodex xBarCodex" +
                            ", b.sDescript xDescript" +
                            ", c.sDescript xCategr01" +
                            ", d.sDescript xCategr02" +
                            ", d.sDescript xInvTypNm" +
                        " FROM " + System.getProperty("sys.table") + " a" + 
                            " LEFT JOIN Inventory b ON a.sStockIDx = b.sStockIDx" +
                            " LEFT JOIN Category c ON b.sCategCd1 = c.sCategrCd" +
                            " LEFT JOIN Category_Level2 d ON b.sCategCd2 = d.sCategrCd" +
                            " LEFT JOIN Inv_Type e ON d.sInvTypCd = e.sInvTypCd" +
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
