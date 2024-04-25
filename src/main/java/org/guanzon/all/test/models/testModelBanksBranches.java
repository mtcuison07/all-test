package org.guanzon.all.test.models;

import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.cas.model.parameters.Model_Banks_Branches;
import org.json.simple.JSONObject;

public class testModelBanksBranches {
    public static void main(String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/");
        
        GRider instance = MiscUtil.Connect();
        
        JSONObject json;
        
        Model_Banks_Branches model = new Model_Banks_Branches(instance);
//        json = model.newRecord();
//        
//        if (!"success".equals((String) json.get("result"))){
//            System.err.println((String) json.get("message"));
//            System.exit(1);
//        }
//
//        model.setBankBranchName("BDO Dagupan - Mayombo");
//        model.setBankBranchCode("858");
//        model.setBankID("M00124001");
//        model.setBankName("Banco De Oro");
//        model.setBankCode("BDO");
//        model.setContactPerson("Michael T. Cuison");
//        model.setContactPersonAddress("027 Pogo grande");
//        model.setContactPersonAddressTownID("0314");        
//        model.setActive(true);
//        model.setModifiedBy(instance.getUserID());
//        model.setModifiedDate(instance.getServerDate());
//        json = model.saveRecord();
//        
//        if (!"success".equals((String) json.get("result"))){
//            System.err.println((String) json.get("message"));
//            System.exit(1);
//        } else {
//            System.out.println((String) json.get("message"));
//            System.exit(0);
//        }

        json = model.openRecord("M00100001");
        if (!"success".equals((String) json.get("result"))){
            System.err.println((String) json.get("message"));
            System.exit(1);
        } else {
//            model.setBankBranchLandine("0755220202");
//            model.setBankBranchFaxNo("0755150202");
            
            json = model.saveRecord();
            if (!"success".equals((String) json.get("result"))){
                System.err.println((String) json.get("message"));
                System.exit(1);
            } else {
                System.out.println((String) json.get("message"));
                System.exit(0);
            }
        }
    }
}
