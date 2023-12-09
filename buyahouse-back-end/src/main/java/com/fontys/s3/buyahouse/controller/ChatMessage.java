package com.fontys.s3.buyahouse.controller;

import lombok.Data;

@Data
public class ChatMessage {
    private String id;
    private String propertyId;
    private String from;
    private String message;
}
