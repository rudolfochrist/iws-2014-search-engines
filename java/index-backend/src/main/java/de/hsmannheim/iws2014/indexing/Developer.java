package de.hsmannheim.iws2014.indexing;

/**
 * Basic Developer. Fancy struct. Supposed to indexed.
 */
public class Developer {

    public String firsname;
    public String surname;
    public String residence;
    public String personalAbstract;

    public Developer addFirstname(String firstname) {
        this.firsname = firstname;
        return this;
    }

    public Developer addSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Developer addResidence(String residence) {
        this.residence = residence;
        return this;
    }

    public Developer addPersonalAbstract(String personalAbstract) {
        this.personalAbstract = personalAbstract;
        return this;
    }
}
