package org.guanzon.all.test.controllers;

import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;

public class testBanks {
    public static void main(String [] args){
        System.setProperty("sys.default.path.metadata", "D:/GGC_Maven_Systems/config/metadata/");
        
        GRider instance = MiscUtil.Connect();
    }
}
