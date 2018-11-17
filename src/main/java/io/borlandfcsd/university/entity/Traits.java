package io.borlandfcsd.university.entity;

import java.util.ArrayList;
import java.util.List;

public enum Traits {
    ACTIVE ("Active"),
    ADAPTABLE("Adaptable"),
    CHARISMATIC ("Charismatic"),
    CHEERFUL ("Cheerful"),
    CLEVER ("Clever"),
    CREATIVE ( "Creative"),
    EDUCATED ("Educated"),
    HUMOROUS ( "Humorous"),
    SENSITIVE ( "Sensitive"),
    SPORTING ( "Sporting"),
    STRONG ( "Strong"),
    TOLERANT ("Tolerant");

    private String value;

    Traits(String value) {
        this.value = value;
    }

    public static List<String> getRandomTraits(){
        List<String> traits = new ArrayList<>();
        for (Traits trait : Traits.values()){
            if(Math.round(Math.random()) == 1){
                traits.add(trait.getValue());
            }
        }
        return traits;
    }

    public String getValue() {
        return value;
    }
}
