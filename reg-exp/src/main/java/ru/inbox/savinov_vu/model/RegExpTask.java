package ru.inbox.savinov_vu.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class RegExpTask {

    public int id;

    public List<String> matchStrings;

    public List<String> excludedStrings;

    public List<String> requiredSymbols;

}
