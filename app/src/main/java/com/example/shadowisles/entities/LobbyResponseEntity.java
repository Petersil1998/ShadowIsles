package com.example.shadowisles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LobbyResponseEntity {
    private boolean canStartActivity;
    private String chatRoomId;
    private String chatRoomKey;
    private GameConfig gameConfig;
    private List<Invitation> invitations;
    private Member localMember;
    private List<Member> members;
    private String partyId;
    private String partyType;
    private List<EligibilityRestriction> restrictions;
    private List<EligibilityRestriction> warnings;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GameConfig {
        private List<Integer> allowablePremadeSizes;
        private String customLobbyName;
        private String customMutatorName;
        private List<String> customRewardsDisabledReasons;
        private LobbyRequestEntity.SpectatorPolicy customSpectatorPolicy;
        private List<Member> customSpectators;
        private List<Member> customTeam100;
        private List<Member> customTeam200;
        private String gameMode;
        private boolean isCustom;
        private boolean isLobbyFull;
        private boolean isTeamBuilderManaged;
        private int mapId;
        private int maxHumanPlayers;
        private int maxLobbySize;
        private int maxTeamSize;
        private String pickType;
        private boolean premadeSizeAllowed;
        private int queueId;
        private boolean showPositionSelector;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Member {
        private boolean allowedChangeActivity;
        private boolean allowedInviteOthers;
        private boolean allowedKickOthers;
        private boolean allowedStartActivity;
        private boolean allowedToggleInvite;
        private boolean autoFillEligible;
        private boolean autoFillProtectedForPromos;
        private boolean autoFillProtectedForSoloing;
        private boolean autoFillProtectedForStreaking;
        private int botChampionId;
        private LobbyRequestEntity.BotDifficulty botDifficulty;
        private String botId;
        private String firstPositionPreference;
        private boolean isBot;
        private boolean isLeader;
        private boolean isSpectator;
        private String puuid;
        private boolean ready;
        private String secondPositionPreference;
        private boolean showGhostedBanner;
        private int summonerIconId;
        private int summonerId;
        private String summonerInternalName;
        private int summonerLevel;
        private String summonerName;
        private int teamId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Invitation {
        private String invitationId;
        private InvitationState state;
        private String timestamp;
        private int toSummonerId;
        private String toSummonerName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EligibilityRestriction {
        private String expiredTimestamp;
        private Map<String, String> restrictionArgs;
        private EligibilityRestrictionCode restrictionCode;
        private List<Integer> summonerIds;
        private String summonerIdsString;
    }

    public enum InvitationState {
        Requested("Requested"),
        Pending("Pending"),
        Accepted("Accepted"),
        Joined("Joined"),
        Declined("Declined"),
        Kicked("Kicked"),
        OnHold("OnHold"),
        Error("Error");

        String name;

        InvitationState(String name){
            this.name = name;
        }

        @androidx.annotation.NonNull
        @Override
        public String toString() {
            return name;
        }
    }

    public enum EligibilityRestrictionCode {
        QueueDisabled,
        QueueUnsupported,
        PlayerLevelRestriction,
        PlayerTimedRestriction,
        PlayerBannedRestriction,
        PlayerBingeRestriction,
        PlayerDodgeRestriction,
        PlayerInGameRestriction,
        PlayerLeaverBustedRestriction,
        PlayerLeaverTaintedWarningRestriction,
        PlayerMaxLevelRestriction,
        PlayerMinLevelRestriction,
        PlayerMinorRestriction,
        PlayerTimePlayedRestriction,
        PlayerRankedSuspensionRestriction,
        TeamHighMMRMaxSizeRestriction,
        TeamSizeRestriction,
        PrerequisiteQueuesNotPlayedRestriction,
        GameVersionMismatch,
        GameVersionMissing,
        GameVersionNotSupported,
        QueueEntryNotEntitledRestriction,
        UnknownRestriction,
        BanInfoNotAvailable,
        MinorInfoNotAvailable,
        SummonerInfoNotAvailable,
        LeaguesInfoNotAvailable,
        InventoryChampsInfoNotAvailable,
        InventoryQueuesInfoNotAvailable,
        MmrStandardDeviationTooLarge
    }
}


