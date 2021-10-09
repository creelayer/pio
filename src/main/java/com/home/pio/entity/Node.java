package com.home.pio.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Node {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    public UUID uuid;

    public String name;

    public Boolean current;

    public Boolean master;

    public Boolean alive;

}
