/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.jbaumgartner19;

import java.util.Objects;

/**
 *
 * @author jbaumgartner19
 */
public class Spieler {
    private String firstName;
    private String lastName;
    private int points;
    private String country;
    private int handicap;

    public Spieler(String firstName, String lastName, int points, String country, int handicap) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.country = country;
        this.handicap = handicap;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + this.points;
        hash = 67 * hash + Objects.hashCode(this.country);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Spieler other = (Spieler) obj;
        if (this.points != other.points) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }
    
    public int getNettopoints(){
        return points - (handicap);
    }
    
    @Override
    public String toString() {
        return country + "|" + firstName + "|" + lastName + "|" + points + "|" + "Handicap" + handicap;
    }
    
}
