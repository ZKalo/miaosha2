package com.kalo.entity.databaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sequence_info")
public class SequenceInfo {
    @Id
    private String name;

    @Column(name = "current_value")
    private Integer currentValue;

    private Integer step;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}