package com.example.shadowisles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LobbyMatchmakingStateEntity {
    private List<Error> errors;
    private LowPriorityData lowPriorityData;
    private SearchState searchState;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private String errorType;
        private int id;
        private String message;
        private int penalizedSummonerId;
        private int penaltyTimeRemaining;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LowPriorityData {
        private String bustedLeaverAccessToken;
        private List<Integer> penalizedSummonerIds;
        private int penaltyTime;
        private int penaltyTimeRemaining;
        private String reason;
    }

    public enum SearchState {
        Invalid("Not in Queue"),
        AbandonedLowPriorityQueue("Low Priority Queue"),
        Canceled("Queue canceled"),
        Searching("In Queue"),
        Found("MATCH FOUND!"),
        Error("Error"),
        ServiceError("Service Error"),
        ServiceShutdown("Service Shutdown");

        private String message;

        SearchState(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
