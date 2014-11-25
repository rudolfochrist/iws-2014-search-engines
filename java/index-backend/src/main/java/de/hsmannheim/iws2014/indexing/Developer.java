package de.hsmannheim.iws2014.indexing;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * Basic Developer. Fancy struct. Supposed to indexed.
 */
public class Developer {

    public String firstname = "<no-firstname>";
    public String surname = "<no-surname>";
    public String residence = "<no-residence>";
    public String personalAbstract = StringUtils.EMPTY;

    public Developer(JSONObject json) {
        firstname = json.optString("firstname", firstname);
        surname = json.optString("surname", surname);
        residence = json.optString("residence", residence);
        personalAbstract = json.optString("personalAbstract", personalAbstract);
    }

    public Developer() {};

    public Developer addFirstname(String firstname) {
        this.firstname = firstname;
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

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.element("firstname", firstname);
        j.element("surname", surname);
        j.element("residence", residence);
        j.element("personalAbstract", personalAbstract);
        return j;
    }
}
