package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelInvStockRequestMaster {
    public static void main (String [] args){
        System.setProperty("sys.table", "Inv_Stock_Request_Master");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.sBranchCd" +
                            ", a.sCategrCd" +
                            ", a.dTransact" +
                            ", a.sReferNox" +
                            ", a.sRemarksx" +
                            ", a.sIssNotes" +
                            ", a.nCurrInvx" +
                            ", a.nEstInvxx" +
                            ", a.sApproved" +
                            ", a.dApproved" +
                            ", a.sAprvCode" +
                            ", a.nEntryNox" +
                            ", a.sSourceCd" +
                            ", a.sSourceNo" +
                            ", a.cConfirmd" +
                            ", a.cTranStat" +
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
                                        , "")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
