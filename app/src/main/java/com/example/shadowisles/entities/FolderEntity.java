package com.example.shadowisles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolderEntity {
    private boolean collapsed;
    private int id;
    private boolean isLocalized;
    private boolean isMetaGroup;
    private String name;
    private int priority;
}
