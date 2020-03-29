package com.example.demo.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;

@Component
@Slf4j
public class FileProcessor implements Processor {

    @Value("${ftp.local.path}")
    private String localPath;

    public void process(Exchange exchange) throws Exception {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        String fileName = inFileMessage.getGenericFile().getFileName();//文件名
        String splitTag = File.separator;//系统文件分隔符
        System.out.println(localPath + splitTag + fileName);//文件的绝对路径
        System.out.println("业务代码处理");
    }
}
