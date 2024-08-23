package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelInvStockRequestCancelMaster {
    public static void main (String [] args){
        System.setProperty("sys.table", "Inv_Stock_Req_Cancel_Master");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.sBranchCd" +
                            ", a.sCategrCd" +
                            ", a.dTransact" +
                            ", a.sOrderNox" +
                            ", a.sRemarksx" +
                            ", a.sApproved" +
                            ", a.dApproved" +
                            ", a.sAprvCode" +
                            ", a.nEntryNox" +
                            ", a.cTranStat" +
                            ", a.dStartEnc" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sBranchNm xBranchNm" +
                            ", c.sDescript xCategrNm" +
                        " FROM " + System.getProperty("sys.table") + " a" + 
                            " LEFT JOIN Branch b ON a.sBranchCd= b.sBranchCd" +
                            " LEFT JOIN Category c ON a.sCategrCd = c.sCategrCd" +
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
