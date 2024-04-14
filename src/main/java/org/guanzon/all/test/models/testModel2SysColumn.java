package org.guanzon.all.test.models;

import com.sun.rowset.CachedRowSetImpl;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.RowSetMetaDataImpl;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.cas.model.systables.Model_SysColumn;
import org.json.simple.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class testModel2SysColumn {
    public static void main(String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/");
        final String XML = System.getProperty("sys.default.path.metadata") + "Model_Banks_Branches.xml";
        final String TABLE = "Banks_Branches";
        
        GRider instance = MiscUtil.Connect();
        
        JSONObject json;
        
        Model_SysColumn model = new Model_SysColumn(instance);
        
        try {
            File file = new File(XML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("column");
            int lnRow = nodeList.getLength();
            
            instance.beginTrans();
            for (int i = 0; i < lnRow; i++) {
                Element element = (Element) nodeList.item(i);
                String columnName = element.getElementsByTagName("COLUMN_NAME").item(0).getTextContent();
                String columnLabel = element.getElementsByTagName("COLUMN_LABEL").item(0).getTextContent();
                String nullable = element.getElementsByTagName("NULLABLE").item(0).getTextContent();
                String dataType = element.getElementsByTagName("DATA_TYPE").item(0).getTextContent();
                String length = element.getElementsByTagName("LENGTH").item(0).getTextContent();
                String precision = element.getElementsByTagName("PRECISION").item(0).getTextContent();
                String scale = element.getElementsByTagName("SCALE").item(0).getTextContent();

                //check first if the table and column already exist.
                json = model.openRecord("sTableNme = " + SQLUtil.toSQL(TABLE) +
                                        " AND sColumnNm = " + SQLUtil.toSQL(columnName));
                
                //if the record doest not exist, create new record
                if (!"success".equals((String) json.get("result"))){
                    json = model.newRecord();
                    
                    if (!"success".equals((String) json.get("result"))){
                        System.err.println((String) json.get("message"));
                        instance.rollbackTrans();
                        System.exit(1);
                    }
                }
                
                model.setValue("sTableNme", TABLE);
                model.setValue("sColumnNm", columnName);    
                model.setValue("sColLabel", columnLabel);
                model.setValue("nPosition", i + 1);
                model.setValue("nColumnTp", dataType);
                model.setValue("cIsNullxx", nullable);
                model.setValue("nLengthxx", length);
                
                switch (Integer.parseInt(dataType)){
                    case java.sql.Types.CHAR:
                    case java.sql.Types.VARCHAR :
                    case java.sql.Types.LONGVARCHAR :
                    case java.sql.Types.NCHAR :
                    case java.sql.Types.NVARCHAR :
                    case java.sql.Types.LONGNVARCHAR :
                    case java.sql.Types.CLOB :
                    case java.sql.Types.NCLOB :
                        model.setValue("cFixedLen", Logical.YES);
                        break;
                    default:
                        model.setValue("cFixedLen", Logical.NO
                        );
                }
                
                model.setValue("nPrecisnx", precision);
                model.setValue("nScalexxx", scale);
                model.setValue("sModified", instance.getUserID());
                model.setValue("dModified", instance.getServerDate());
                json = model.saveRecord();

                if (!"success".equals((String) json.get("result"))){
                    System.err.println((String) json.get("message"));
                    instance.rollbackTrans();
                    System.exit(1);
                } else {
                    System.out.println((String) json.get("message"));
                }
            }
            instance.commitTrans();
            System.exit(0);
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();
        }
    }
}
