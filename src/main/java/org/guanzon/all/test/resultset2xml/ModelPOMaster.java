package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelPOMaster {
    public static void main (String [] args){
        System.setProperty("sys.table", "PO_Master");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" + 
                            "  a.sTransNox" +
                            ", a.sBranchCd" +
                            ", a.dTransact" +
                            ", a.sCompnyID" +
                            ", a.sDestinat" +
                            ", a.sSupplier" +
                            ", a.sAddrssID" +
                            ", a.sContctID" +
                            ", a.sReferNox" +
                            ", a.sTermCode" +
                            ", a.nTranTotl" +
                            ", a.nVatRatex" +
                            ", a.nVatAmtxx" +
                            ", a.cVATAdded" +
                            ", a.nTWithHld" +
                            ", a.nDiscount" +
                            ", a.nAddDiscx" +
                            ", a.nAmtPaidx" +
                            ", a.nNetTotal" +
                            ", a.sRemarksx" +
                            ", a.sSourceCd" +
                            ", a.sSourceNo" +
                            ", a.cEmailSnt" +
                            ", a.nEmailSnt" +
                            ", a.nEntryNox" +
                            ", a.sCategrCd" +
                            ", a.cPOTypexx" +
                            ", a.cTranStat" +
                            ", a.sPrepared" +
                            ", a.dPrepared" +
                            ", a.sApproved" +
                            ", a.dApproved" +
                            ", a.sAprvCode" +
                            ", a.sPostedxx" +
                            ", a.dPostedxx" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", j.sBranchNm xBranchNm" +
                            ", l.sCompnyNm xCompnyNm" +
                            ", k.sBranchNm xDestinat" +
                            ", b.sCompnyNm xSupplier" + 
                            ", TRIM(CONCAT(IFNULL(c.sHouseNox, ''), ' ', IFNULL(c.sAddressx, ''), ', ', IFNULL(h.sBrgyName, ''), ' ', IFNULL(i.sTownName, ''))) xAddressx" +
                            ", d.sCPerson1 xCPerson1" +
                            ", d.sCPPosit1 xCPPosit1" +
                            ", d.sMobileNo xCPMobil1" +
                            ", e.sDescript xTermName" +
                            ", f.sDescript xCategrNm" +
                            ", g.sDescript xInvTypNm" +
                        " FROM " + System.getProperty("sys.table") + " a"+ 
                            " LEFT JOIN Client_Master b ON a.sSupplier = b.sClientID" +
                            " LEFT JOIN Client_Address c" +
                                    " LEFT JOIN Barangay h ON c.sBrgyIDxx = h.sBrgyIDxx" +
                                    " LEFT JOIN TownCity i ON c.sTownIDxx = i.sTownIDxx" +
                            " ON a.sAddrssID = c.sAddrssID" +
                            " LEFT JOIN Client_Institution_Contact_Person d ON a.sContctID = d.sContctID" +
                            " LEFT JOIN Term e ON a.sTermCode = e.sTermCode" +
                            " LEFT JOIN Category f" +
                                    " LEFT JOIN Inv_Type g ON f.sInvTypCd = g.sInvTypCd" +
                            " ON a.sCategrCd = f.sCategrCd" +
                            " LEFT JOIN Branch j ON a.sBranchCd = j.sBranchCd" +
                            " LEFT JOIN Branch k ON a.sDestinat = k.sBranchCd" +
                            " LEFT JOIN Company l ON a.sCompnyID = l.sCompnyID" +
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
