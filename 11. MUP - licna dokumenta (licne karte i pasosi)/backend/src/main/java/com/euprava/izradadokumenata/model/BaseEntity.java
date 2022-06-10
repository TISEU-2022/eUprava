package com.euprava.izradadokumenata.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    private boolean removed;

    private LocalDateTime createdOn;
}
