package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelAPClientMaster {
    public static void main (String [] args){
        System.setProperty("sys.table", "AP_Client_Master");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sClientID" +
                            ", a.sAddrssID" +
                            ", a.sContctID" +
                            ", a.sCategrCd" +
                            ", a.dCltSince" +
                            ", a.dBegDatex" +
                            ", a.nBegBalxx" +
                            ", a.sTermIDxx" +
                            ", a.nDiscount" +
                            ", a.nCredLimt" +
                            ", a.nABalance" +
                            ", a.nOBalance" +
                            ", a.nLedgerNo" +
                            ", a.cVatablex" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sCompnyNm xClientNm" +
                            ", TRIM(CONCAT(c.sHouseNox, ' ', c.sAddressx, ', ', g.sBrgyName, ' ', h.sTownName, ', ', i.sProvName)) xAddressx" +
                            ", d.sCPerson1 xCPerson1" +
                            ", d.sCPPosit1 xCPPosit1" +
                            ", e.sDescript xCategrNm" +
                            ", f.sDescript xTermName" +
                        " FROM " + System.getProperty("sys.table") + " a" + 
                            " LEFT JOIN Client_Master b ON a.sClientID = b.sClientID" +
                            " LEFT JOIN Client_Address c" + 
                                " LEFT JOIN Barangay  g ON c.sBrgyIDxx = g.sBrgyIDxx" +
                                " LEFT JOIN TownCity h ON c.sTownIDxx = h.sTownIDxx" +
                                " LEFT JOIN Province i ON h.sProvIDxx = i.sProvIDxx" +
                            " ON a.sAddrssID = c.sAddrssID" +
                            " LEFT JOIN Client_Institution_Contact_Person d ON a.sContctID = d.sContctID" +
                            " LEFT JOIN Category e ON a.sCategrCd = e.sCategrCd" +
                            " LEFT JOIN Term f ON a.sTermIDxx = f.sTermCode" +
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
