package com.thoughtworks.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@ToString
@Getter
@Setter
public abstract class Identifiable {
    @Id
    @GeneratedValue(generator = "entity_id")
    @GenericGenerator(name = "entity_id", strategy = "uuid")
    private String id;
}
