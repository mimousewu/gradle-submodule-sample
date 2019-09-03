package com.thoughtworks.persistence;

import lombok.ToString;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@ToString(callSuper = true)
public abstract class ValueObject extends Identifiable {
}
