package com.krekapps.indycarstats.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ekk on 25-Jun-17.
 */

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private int id;
}
