package org.guanzon.all.test.models;

import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.cas.model.parameters.Model_Banks;
import org.json.simple.JSONObject;

public class testModelBanks {
    public static void main(String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/");
        
        GRider instance = MiscUtil.Connect();
        
        JSONObject json;
        
        Model_Banks model = new Model_Banks(instance);
        json = model.newRecord();
        
        if (!"success".equals((String) json.get("result"))){
            System.err.println((String) json.get("message"));
            System.exit(1);
        }
        
        json = model.setBankName("Banco De Oro");
        if ("error".equals((String) json.get("result"))){
            System.err.println((String) json.get("message"));
            System.exit(1);
        }
        
        json = model.setBankCode("Banco De Oro");
        if ("error".equals((String) json.get("result"))){
            System.err.println((String) json.get("message"));
            System.exit(1);
        }
        
        model.setModifiedBy(instance.getUserID());
        model.setModifiedDate(instance.getServerDate());
        json = model.saveRecord();
        
        if (!"success".equals((String) json.get("result"))){
            System.err.println((String) json.get("message"));
            System.exit(1);
        } else {
            System.out.println((String) json.get("message"));
            System.exit(0);
        }
//        json = model.openRecord("M00124001");
//        if (!"success".equals((String) json.get("result"))){
//            System.err.println((String) json.get("message"));
//            System.exit(1);
//        } else {
//            model.setBankName("Banco de Oro");
//            model.setBankCode("BDO");
//            
//            json = model.saveRecord();
//            if (!"success".equals((String) json.get("result"))){
//                System.err.println((String) json.get("message"));
//                System.exit(1);
//            } else {
//                System.out.println((String) json.get("message"));
//                System.exit(0);
//            }
//        }
    }
}
