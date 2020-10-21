package com.example.shadowisles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LobbyRequestEntity {
    private CustomGameLobby customGameLobby;
    private Map<String, String> gameCustomization;
    private boolean isCustom;
    private int queueId;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomGameLobby {
        private Configuration configuration;
        private int gameId;
        private String lobbyName;
        private String lobbyPassword;
        private List<String> practiceGameRewardsDisabledReasons;
        private List<LobbyMember> spectators;
        private List<LobbyMember> teamOne;
        private List<LobbyMember> teamTwo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Configuration {
        private String gameMode;
        private String gameServerRegion;
        private GameTypeConfig gameTypeConfig;
        private int mapId;
        private int maxPlayerCount;
        private GameTypeConfig mutators;
        private SpectatorPolicy spectatorPolicy;
        private int teamSize;
        private String tournamentGameMode;
        private String tournamentPassbackDataPacket;
        private String tournamentPassbackUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GameTypeConfig {
        private boolean advancedLearningQuests;
        private boolean allowTrades;
        private String banMode;
        private int banTimerDuration;
        private boolean battleBoost;
        private boolean crossTeamChampionPool;
        private boolean deathMatch;
        private boolean doNotRemove;
        private boolean duplicatePick;
        private boolean exclusivePick;
        private String gameModeOverride;
        private int id;
        private boolean learningQuests;
        private int mainPickTimerDuration;
        private int maxAllowableBans;
        private String name;
        private int numPlayersPerTeamOverride;
        private boolean onboardCoopBeginner;
        private String pickMode;
        private int postPickTimerDuration;
        private boolean reroll;
        private boolean teamChampionPool;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LobbyMember {
        private boolean autoFillEligible;
        private boolean autoFillProtectedForPromos;
        private boolean autoFillProtectedForSoloing;
        private boolean autoFillProtectedForStreaking;
        private int botChampionId;
        private BotDifficulty botDifficulty;
        private boolean canInviteOthers;
        private String excludedPositionPreference;
        private int id;
        private boolean isBot;
        private boolean isOwner;
        private boolean isSpectator;
        private PositionPreferences positionPreferences;
        private boolean showPositionExcluder;
        private String summonerInternalName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositionPreferences {
        private String firstPreference;
        private String secondPreference;
    }

    public enum SpectatorPolicy {
        NotAllowed,
        LobbyAllowed,
        FriendsAllowed,
        AllAllowed
    }

    public enum BotDifficulty {
        NONE,
        EASY,
        MEDIUM,
        HARD,
        UBER,
        TUTORIAL,
        INTRO
    }
}