package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelSupplierAccreditation {
    public static void main (String [] args){
        System.setProperty("sys.table", "Supplier_Accreditation");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL ="SELECT" +
                            "  a.sTransNox" +
                            ", a.dTransact" +
                            ", a.sClientID" +
                            ", a.sContctID" +
                            ", a.sRemarksx" +
                            ", a.cTranType" +
                            ", a.sCategrCd" +
                            ", a.cTranStat" +
                            ", a.sApproved" +
                            ", a.dApproved" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sCompnyNm" +
                            ", c.sCPerson1" +
                            ", c.sCPPosit1" +
                            ", c.sMobileNo" +
                            ", c.sEMailAdd" +
                        " FROM " + System.getProperty("sys.table") + " a" + 
                            " LEFT JOIN Client_Master b ON a.sClientID = b.sClientID" +
                            " LEFT JOIN Client_Institution_Contact_Person c ON a.sContctID = c.sContctID" +
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
