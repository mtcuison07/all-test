package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelSalesman {
    public static void main (String [] args){
        System.setProperty("sys.table", "Salesman");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sEmployID" +	
                            ", a.sBranchCd" +
                            ", a.sLastName" +	
                            ", a.sFrstName" +	
                            ", a.sMiddName" +	
                            ", a.cRecdStat" +
                            ", a.sModified" +	
                            ", a.dModified" +
                            ", b.sCompnyNm xClientNm" +
                            ", c.sBranchNm xBranchNm" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Client_Master b ON a.sEmployID = b.sClientID" +
                            " LEFT JOIN Branch c ON a.sBranchCd = c.sBranchCd" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xClientNm»xBranchNm")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
