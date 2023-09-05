package main.donjon;

import main.Mathf;

public enum Advice {
    CONSEIL_1("Je sais pas");

    private String help;

    private Advice(String advice){
        this.help = advice;
    }

    public String getHelp(){
        return help;
    }

    public static Advice getRandom(){
        return Mathf.random(values());
    }

    public String toString(){
        return getClass().getSimpleName() + "[help:" + help + "]";
    }
}
