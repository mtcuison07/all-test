package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelInvSerialLedger {
    public static void main (String [] args){
        System.setProperty("sys.table", "Inv_Serial_Ledger");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            	"  a.sSerialID" +
                                ", a.sBranchCd" +
                                ", a.nLedgerNo" +
                                ", a.dTransact" +
                                ", a.sSourceCd" +
                                ", a.sSourceNo" +
                                ", a.cSoldStat" +
                                ", a.cLocation" +
                                ", a.dModified" +
                                ", c.sBarCodex xBarCodex" +
                                ", c.sDescript xDescript" +
                                ", b.sSerial01 xSerial01" +
                                ", b.sSerial02 xSerial02" +
                        " FROM " + System.getProperty("sys.table") + " a"+ 
                            " LEFT JOIN Inv_Serial b ON a.sSerialID = b.sSerialID" +
                            " LEFT JOIN Inventory c ON b.sStockIDx = c.sStockIDx" +
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
