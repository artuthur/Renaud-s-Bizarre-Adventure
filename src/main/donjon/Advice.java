package main.donjon;

import main.Mathf;

public enum Advice {
    CONSEIL_1("Les ennemies de l'étage des gardes ont une armure moyenne, Si vous infligez peu de dégât, essayer d'utiliser vos sorts!"),
    CONSEIL_2("Le chien de garde ne possède pas de DEF, essaye de l'attaquer sans vos sorts!"),
    CONSEIL_3("ARS inflige certes beaucoup de dégâ, mais est assez fragile, tuez-le vite!"),
    CONSEIL_4("La Brave M et l'ennemi le plus dangereux du cachot"),
    CONSEIL_5("Les ennemis de la bibliothèque sont assez fragiles, mais faite attention à leur attaque!"),
    CONSEIL_6("Le professeur d'EPS est très résistant, mais il n'inflige que peu de dégât, vous pouvez lancer en boucle vos sorts de soin pour vous soigner!"),
    CONSEIL_7("Le livre scolaire magic est l'ennemie qui inflige les plus de dégât de la bibliothèque, mais il est très fragile!"),
    CONSEIL_8("Les ennemies de la ville en feu sont extrêmement résistante aux attaques de base, utiliser vos sorts!"),
    CONSEIL_9("Les Chaossos ne prendront presque aucun dégât des attaques de base, attaqué l'avec vos sort!"),
    CONSEIL_10("Seulement un seul monstre dans l'étage de la luxure infernale possède de l'armure, attaquer les donc avec vos attaques de base!"),
    CONSEIL_11("La Playgirl est la seule à avoir de l'armure dans son étage!"),
    CONSEIL_12("Les attaques du Golem sont ridiculement faibles, mais il possède une quantité astronomique de HP!"),
    CONSEIL_13("Malgré le fait que Gardemin représente l'ordre, il ne suit que très peu les règlent."),
    CONSEIL_14("Même si le chien de garde est dangereux, il est aussi très câlin!"),
    CONSEIL_15("Jean Merlin à beau être le chef de la grande bibliothèque, il est assez idiot."),
    CONSEIL_16("Des rumeurs circulent sur le fait que le livre de magie soit en réalité un Hentai!"),
    CONSEIL_17("Personne ne sait ce que le Professeur d'EPS fait dans la bibliothèque."),
    CONSEIL_18("Les ficelles de l'arrenter de la bibliothèque permettent de fabriquer des habits très confortables!"),
    CONSEIL_19("Anarchon est énervé tout le temps car, il ne comprend pas pourquoi personne ne veut de lui en tant que chef de la tour, même si tout le monde sait qu'il ne saurait jamais comment diriger la tour."),
    CONSEIL_20("Les robe Jaune sont très redoutées par le chef de la tour, il a peur qu'il commence à venire pour lui."),
    CONSEIL_21("Les Chaossos ne possèdent aucune intelligence, tout ce qu'ils veulent c'est harcelé et suivre aveuglément des idiots."),
    CONSEIL_22("Lilithia ne possède aucun sens moral et aucune pudeur, elle serait même capable d'expliquer des connaissances impie et perverses à des enfants!"),
    CONSEIL_23("Les succubus sont aussi collants car elles n'ont pas eu de relation amoureuse depuis des millénaires, elle ne demande qu'un peu d'amour et d'attentions."),
    CONSEIL_24("Les Playgirl suivent Lilithia car elle a posé pour la couverture d'un de leur magasine!"),
    CONSEIL_25("Le Golem ne possède aucune intelligence il ne save que suivre et écouter aveuglément les femmes de l'étage infernal en échange d'attention de leur part."),
    CONSEIL_26("Jupiter n'est que le surnom que s'est donné l'archi roi démon, il n'est tellement imbu de lui-même qu'il pense être un dieu."),
    ;
    private String help;

    private Advice(String advice){
        this.help = advice;
    }

    public String getHelp(){
        return help;
    }

    public static Advice random(){
        return Mathf.random(values());
    }

    public String toString(){
        return getClass().getSimpleName() + "[help:" + help + "]";
    }
}
