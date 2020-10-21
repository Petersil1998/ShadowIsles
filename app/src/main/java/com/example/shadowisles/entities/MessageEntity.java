package com.example.shadowisles.entities;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    private String body;
    private String fromId;
    private String fromPid;
    private long fromSummonerId;
    private String id;
    private boolean isHistorical;
    private String timestamp;
    private Type type;

    public enum Type {
        @SerializedName("chat")
        CHAT,
        @SerializedName("system")
        SYSTEM,
    }
}
