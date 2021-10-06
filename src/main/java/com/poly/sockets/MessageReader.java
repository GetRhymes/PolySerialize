package com.poly.sockets;

import com.poly.models.Message;
import com.poly.models.MessageWithContent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MessageReader {


    private InputStream inputStream;

    public MessageReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private byte[] readFile(int size) throws IOException {
        byte[] file = new byte[size];
        for (int i = 0; i < size; i++) {
            file[i] = (byte) inputStream.read();
        }
        return file;
    }

    private Message readMessage() throws IOException {
        int size = 0;
        for (int i = 0; i < 4; i++) {
            size = size << 8;
            size += inputStream.read();
        }
        Message message = new Message();
        byte[] msg = new byte[size];
        for (int i = 0; i < size; i++) {
            msg[i] = (byte) inputStream.read();
        }
        message.parseToMessage(new String(msg, StandardCharsets.UTF_8));
        return message;
    }

    public boolean readyForMessageReading() throws IOException {
        return inputStream.available() > 0;
    }

    public void close() throws IOException {
        inputStream.close();
    }

    public MessageWithContent read() throws IOException {
        Message message = readMessage();
        byte[] content = null;
        if(message.getFileSize() != null && message.getFileSize() > 0
                && message.getFileName() != null && !message.getFileName().isEmpty()) {
            content = readFile(message.getFileSize());
        }
        return new MessageWithContent(message, content);
    }

}