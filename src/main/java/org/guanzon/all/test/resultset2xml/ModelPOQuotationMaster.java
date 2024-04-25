package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelPOQuotationMaster {
    public static void main (String [] args){
        System.setProperty("sys.table", "PO_Quotation_Master");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sTransNox" + 
                            ", a.sReferNox" + 
                            ", a.sSupplier" + 
                            ", a.sAddrssID" + 
                            ", a.sContctID" + 
                            ", a.dTransact" + 
                            ", a.dReferDte" + 
                            ", a.sTermCode" + 
                            ", a.dValidity" + 
                            ", a.nGrossAmt" + 
                            ", a.nDiscount" + 
                            ", a.nAddDiscx" + 
                            ", a.nVatRatex" + 
                            ", a.nVatAmtxx" + 
                            ", a.cVATAdded" + 
                            ", a.nTWithHld" + 
                            ", a.nFreightx" + 
                            ", a.nTranTotl" + 
                            ", a.sRemarksx" + 
                            ", a.nEntryNox" + 
                            ", a.sCategrCd" + 
                            ", a.cTranStat" + 
                            ", a.sPrepared" + 
                            ", a.dPrepared" + 
                            ", a.sModified" + 
                            ", a.dModified" +
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
