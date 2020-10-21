package com.example.shadowisles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueueEligibility {
    private boolean eligible;
    private int queueId;
    private List<LobbyResponseEntity.EligibilityRestriction> restrictions;
}
