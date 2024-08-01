package org.guanzon.all.test.appdriver;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class testSFTP {
    public static void main(String [] args){
        int lnPort = 22;
        String lsHostname = "13.250.94.208";
        String lsUsername = "ph_guanzon";
        String lsPKeyPath = "d:/GGC_Maven_Systems/config/ph_guanzon.ppk";
        
        String lsUpload = "d:/GGC_Maven_Systems/temp/Bolttech/upload/";
        String lsSucces = "d:/GGC_Maven_Systems/temp/Bolttech/success/";
        String lsFailed = "d:/GGC_Maven_Systems/temp/Bolttech/failed/";
        String lsRemote = "/uat/sales/unprocessed/";
        String lsFilename = "GUAPHTCV_PH_20240730.csv";
        
        

        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            JSch jsch = new JSch();
            jsch.addIdentity(lsPKeyPath);
            session = jsch.getSession(lsUsername, lsHostname, lnPort);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            FileInputStream fis = new FileInputStream(lsUpload + lsFilename);
            channelSftp.put(fis, lsRemote + lsFilename);
            fis.close();
            
            System.out.println("File transferred successfully to host.");
            
            Path sourcePath = Paths.get(lsUpload + lsFilename);
            Path destinationPath = Paths.get(lsSucces + lsFilename);
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File transfer success.");
        } catch (JSchException | SftpException | IOException ex) {
            ex.printStackTrace();
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
