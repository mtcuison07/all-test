package org.guanzon.all.test.appdriver;

import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class testConnect {
    public static void main(String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/");
        
        GRider instance = MiscUtil.Connect();
    }
}
