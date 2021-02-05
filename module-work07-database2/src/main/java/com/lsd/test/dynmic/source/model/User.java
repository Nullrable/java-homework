package com.lsd.test.dynmic.source.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class User implements Serializable{


    private static final long serialVersionUID = -4476347882559530852L;

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String name;

}
