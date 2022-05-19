package com.euprava.izradadokumenata.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private boolean removed;

    private LocalDateTime createdOn;
}
