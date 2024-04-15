package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelDepartment {
    public static void main (String [] args){
        System.setProperty("sys.table", "Department");
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_" + System.getProperty("sys.table") + ".xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  a.sDeptIDxx" +
                            ", a.sDeptName" +
                            ", a.sDeptHead" +
                            ", a.sMobileNo" +
                            ", a.sEMailAdd" +
                            ", a.sDeptCode" +
                            ", a.sHAssgnID" +
                            ", a.sSAssgnID" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sCompnyNm xDeptHead" +
                            ", c.sCompnyNm xHAssgnNm" +
                            ", d.sCompnyNm xSAssgnNm" +
                        " FROM " + System.getProperty("sys.table") + " a" +
                            " LEFT JOIN Client_Master b ON a.sDeptHead = b.sClientID" +
                            " LEFT JOIN Client_Master c ON a.sHAssgnID = c.sClientID" +
                            " LEFT JOIN Client_Master d ON a.sSAssgnID = d.sClientID" +
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(instance
                                        , loRS
                                        , System.getProperty("sys.default.path.metadata")
                                        , System.getProperty("sys.table")
                                        , "xDeptHead»xHAssgnNm»xSAssgnNm")){
                
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
