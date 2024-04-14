package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelClientMaster {
    public static void main (String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_Client_Master.xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sClientID" +
                            ", a.cClientTp" +
                            ", a.sLastName" +
                            ", a.sFrstName" +
                            ", a.sMiddName" +
                            ", a.sSuffixNm" +
                            ", a.sMaidenNm" +
                            ", a.sCompnyNm" +
                            ", a.cGenderCd" +
                            ", a.cCvilStat" +
                            ", a.sCitizenx" +
                            ", a.dBirthDte" +
                            ", a.sBirthPlc" +
                            ", a.sAddlInfo" +
                            ", a.sSpouseID" +
                            ", a.sTaxIDNox" +
                            ", a.sLTOIDxxx" +
                            ", a.sPHBNIDxx" +
                            ", a.cLRClient" +
                            ", a.cMCClient" +
                            ", a.cSCClient" +
                            ", a.cSPClient" +
                            ", a.cCPClient" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sTownName xBirthPlc" +
                            ", c.sCntryNme xCitizenx" +
                            ", d.sCompnyNm xSpouseNm" +
                        " FROM Client_Master a" +
                            " LEFT JOIN TownCity b ON a.sBirthPlc = b.sTownIDxx" +
                            " LEFT JOIN Country c ON a.sCitizenx = c.sCntryCde" +
                            " LEFT JOIN Client_Master d ON a.sSpouseID = d.sClientID" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance, loRS, System.getProperty("sys.default.path.metadata"), "Client_Master", "")){
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
