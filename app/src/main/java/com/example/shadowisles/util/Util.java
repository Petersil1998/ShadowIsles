package com.example.shadowisles.util;

import com.example.shadowisles.entities.FriendEntity;
import com.example.shadowisles.entities.Queue;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Queue> availableQueues = new ArrayList<>();

    public static Queue getQueue(List<Queue> queues, int queueId) {
        for (Queue queue : queues) {
            if(queue.getQueueId() == queueId){
                return queue;
            }
        }
        return null;
    }

    public static List<FriendEntity> getFriendsInFolder(List<FriendEntity> friends, int folderID) {
        List<FriendEntity> ret = new ArrayList<>();
        for (FriendEntity friend: friends) {
            if(friend.getDisplayGroupId() == folderID) {
                ret.add(friend);
            }
        }
        return ret;
    }
}
