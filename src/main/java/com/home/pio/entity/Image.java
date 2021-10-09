package com.home.pio.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(indexes = @Index(columnList = "filename"))
public class Image {

    @Id
//    @GeneratedValue
    @Type(type = "uuid-char")
    public UUID uuid;

    public String filename;

    public String prefix;

    @ManyToOne(optional = false)
    public Bucket bucket;

    public Image(){
        uuid = UUID.randomUUID();
    }
}
