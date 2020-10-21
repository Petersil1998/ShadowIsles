package com.example.shadowisles.entities;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendEntity {
    private Availability availability;
    private int displayGroupId;
    private String displayGroupName;
    private String gameName;
    private String gameTag;
    private int groupId;
    private String groupName;
    private int icon;
    private String id;
    private boolean isP2PConversationMuted;
    private String lastSeenOnlineTimestamp;
    private HashMap<String,String> lol;
    private String name;
    private String note;
    private String patchline;
    private String pid;
    private String platformId;
    private String product;
    private String productName;
    private String puuid;
    private String statusMessage;
    private String summary;
    private String summonerId;
    private long time;

    public enum Availability {
        @SerializedName("offline")
        OFFLINE("Offline"),
        @SerializedName("away")
        AWAY("Away"),
        @SerializedName("dnd")
        DND("DnD"),
        @SerializedName("chat")
        ONLINE("Online"),
        @SerializedName("mobile")
        MOBILE("Mobile");

        String name;

        Availability(String name){
            this.name = name;
        }

        public String toString(){
            return name;
        }
    }
}
