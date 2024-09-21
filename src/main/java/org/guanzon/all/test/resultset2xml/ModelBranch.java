package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelBranch {
    public static void main (String [] args){
        System.setProperty("sys.table", "Branch");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  sBranchCd" +
                            ", sBranchNm" +
                            ", sDescript" +
                            ", sCompnyID" +
                            ", sAddressx" +
                            ", sTownIDxx" +
                            ", sManagerx" +
                            ", sSellCode" +
                            ", cWareHous" +
                            ", sTelNumbr" +
                            ", cRecdStat" +
                            ", sContactx" +
                            ", dExportxx" +
                            ", cSrvcCntr" +
                            ", cAutomate" +
                            ", cMainOffc" +
                            ", sModified" +
                            ", dModified" +
                        " FROM " + System.getProperty("sys.table") +
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
