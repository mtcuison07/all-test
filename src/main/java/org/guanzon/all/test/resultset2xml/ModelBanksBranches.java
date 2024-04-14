package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelBanksBranches {
    public static void main (String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_Banks_Branches.xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sBrBankID" +
                            ", a.sBrBankNm" +
                            ", a.sBrBankCD" +
                            ", a.sBankIDxx" +
                            ", a.sContactP" +
                            ", a.sAddressx" +
                            ", a.sTownIDxx" +
                            ", a.sTelNoxxx" +
                            ", a.sFaxNoxxx" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sBankName xBankName" +
                            ", b.sBankCode xBankCode" +
                            ", c.sTownName xTownName" +
                    " FROM Banks_Branches a" +
                            " LEFT JOIN Banks b ON a.sBankIDxx = b.sBankIDxx" +
                            " LEFT JOIN TownCity c ON a.sTownIDxx = c.sTownIDxx" +
                    " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance, loRS, System.getProperty("sys.default.path.metadata"), "Banks_Branches", "")){
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
