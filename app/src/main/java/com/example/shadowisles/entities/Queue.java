package com.example.shadowisles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Queue {
    private int queueId;
    private String map;
    private String description;
    private String notes;

    @Override
    public String toString() {
        return description/* + " ("+map+")"*/;
    }
}
