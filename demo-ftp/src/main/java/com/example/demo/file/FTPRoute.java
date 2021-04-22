package com.example.demo.file;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FTPRoute extends RouteBuilder {

    @Value("${ftp.server}")
    private String ftpServer;
    @Value("${ftp.local.path}")
    private String localPath;

    @Resource
    private FileProcessor fileProcessor;

    @Override
    public void configure() throws Exception {
        //from(ftpServer).to(localPath).log(LoggingLevel.INFO, log, "Downloaded file ${file:name} complete.");
        from(ftpServer).process(fileProcessor).to(localPath).log(LoggingLevel.INFO, log, "trans  file ${file:name} complete.");

    }
}
