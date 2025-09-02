package com.demo.sftpServer;

import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;

@Configuration
public class SftpServerConfig {

    @Value("${sftp.server.port}")
    private int port;
    @Value("${sftp.server.user}")
    private String user;
    @Value("${sftp.server.password}")
    private String password;
    @Value("${sftp.server.root.dir}")
    private String rootDir;

    private SshServer sshd;

    @PostConstruct
    public void startServer() throws IOException {
        System.out.println("Starting SFTP server...");
        sshd = SshServer.setUpDefaultServer();
        sshd.setPort(port);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("hostkey.ser")));

        // Create root directories
        new File(rootDir + "/uploads").mkdirs();
        new File(rootDir + "/control/ready").mkdirs();

        // Set up SFTP subsystem - corrected approach
        sshd.setSubsystemFactories(Collections.singletonList(new SftpSubsystemFactory()));
        
        // Set up file system factory to restrict access to root directory
        sshd.setFileSystemFactory(new VirtualFileSystemFactory(Paths.get(rootDir)));

        // Set up password authentication
        sshd.setPasswordAuthenticator((username, pass, session) ->
                username.equals(user) && pass.equals(password));

        sshd.start();
        System.out.println("SFTP server started on port " + port);
        System.out.println("Root directory: " + new File(rootDir).getAbsolutePath());
        System.out.println("Login with user '" + user + "' and password '" + password + "'");
    }

    @PreDestroy
    public void stopServer() throws IOException {
        if (sshd != null) {
            System.out.println("Stopping SFTP server...");
            sshd.stop(true);
            System.out.println("SFTP server stopped.");
        }
    }
}