package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelPOQuotationRequestMaster {
    public static void main (String [] args){
        System.setProperty("sys.table", "PO_Quotation_Request_Master");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.sBranchCd" +
                            ", a.dTransact" +
                            ", a.sDestinat" +
                            ", a.sReferNox" +
                            ", a.sRemarksx" +
                            ", a.dExpPurch" +
                            ", a.nEntryNox" +
                            ", a.sCategrCd" +
                            ", a.cTranStat" +
                            ", a.sPrepared" +
                            ", a.dPrepared" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sBranchNm xBranchNm" +
                            ", c.sBranchNm xDestinat" +
                            ", d.sDescript xCategrNm" +
                            ", e.sdescript xInvTypNm" +
                        " FROM " + System.getProperty("sys.table") + " a"+ 
                            " LEFT JOIN Branch b ON a.sBranchCd = b.sBranchCd" +
                            " LEFT JOIN Branch c ON a.sDestinat = b.sBranchCd" +
                            " LEFT JOIN Category d ON a.sCategrCd = d.sCategrCd" +
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
