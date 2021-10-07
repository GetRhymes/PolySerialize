package com.poly.sockets;

import com.poly.models.Message;
import com.poly.models.MessageWithContent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MessageWriter {

    private final DataOutputStream outputStream;

    public MessageWriter(OutputStream outputStream) {
        this.outputStream = new DataOutputStream(outputStream);
    }

    private void writeMessage(Message message) {
        String strMessage = message.toTransferString();
        System.out.println("TRANS SIZE " + strMessage.length());
        try {
            outputStream.write(strMessage.getBytes().length << 24);
            outputStream.write(strMessage.getBytes().length << 16);
            outputStream.write(strMessage.getBytes().length << 8);
            outputStream.write(strMessage.getBytes().length);
            outputStream.write(strMessage.getBytes());
            printBA(strMessage.getBytes());
            System.out.println(strMessage);
            System.out.println(strMessage.getBytes().length);
            System.out.println(strMessage.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(byte[] file) throws IOException {
        outputStream.write(file);
        outputStream.flush();
    }

    public void close() throws IOException {
        outputStream.close();
    }

    public void write(MessageWithContent messageWithContent) throws IOException {
        Message message = messageWithContent.getMessage();
        writeMessage(message);
        if(message.getFileSize() != null && message.getFileSize() > 0
            && message.getFileName() != null && !message.getFileName().isEmpty()) {
            writeFile(messageWithContent.getContent());
        }
    }

    public static void printBA(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + ",");
            System.out.println();
        }
    }
}
