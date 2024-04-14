package org.guanzon.all.test.resultset2xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class ModelSysColumn {
    public static void main (String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/Model_SysColumn.xml");
        
        GRider instance = MiscUtil.Connect();
        
        String lsSQL = "SELECT" +
                            "  sColumnID" +
                            ", sTableNme" +
                            ", sColumnNm" +
                            ", sColLabel" +
                            ", sColumnDs" +
                            ", sRemarksx" +
                            ", nPosition" +
                            ", nColumnTp" +
                            ", cIsNullxx" +
                            ", nLengthxx" +
                            ", cFixedLen" +
                            ", nPrecisnx" +
                            ", nScalexxx" +
                            ", sFormatxx" +
                            ", sRegTypex" +
                            ", sValueFrm" +
                            ", sValueThr" +
                            ", sValueLst" +
                            ", cRecdStat" +
                            ", sModified" +
                            ", dModified" +
                        " FROM xxxSysColumn" + 
                        " WHERE 0=1";
        
        
        ResultSet loRS = instance.executeQuery(lsSQL);
        try {
            if (MiscUtil.resultSet2XML(loRS, System.getProperty("sys.default.path.metadata"))){
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
