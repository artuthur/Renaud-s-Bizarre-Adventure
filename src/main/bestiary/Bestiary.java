package main.bestiary;

import main.donjon.Theme;
import main.effect.Spell;

public enum Bestiary{
    CRS(Theme.INTERIEUR, "CRS", "crs.txt", 40, 40, Spell.COUP_DE_MATRAQUE),
    ARS(Theme.INTERIEUR, "ARS", "ARS.txt", 20, 5, Spell.FLASH_BALL),
    BRAVE_M(Theme.INTERIEUR, "BRAVE M", "braveM_ascii.txt", 50, 40, Spell.BAVURE),
    CHIEN_DE_GARDE(Theme.INTERIEUR, "Chien de gardes", "ChienDeGarde.txt", 30, 0, Spell.MORSURE),
    GERALD_GARDEMIN(Theme.INTERIEUR, "Gérald Gardemin", "GeraldGardemain.txt", 100, 30, Spell.NON_LIEU, Spell.DECENTE_DE_GARDE),
    PROFESSEUR_DE_MAGIE(Theme.EDUCATION, "Professeur de magie", "professeur_magie.txt", 40, 10, Spell.COUP_DE_REGLE_MAGIC),
    LIVRE_SCOLAIRE_MAGIC(Theme.EDUCATION, "Livre scolaire magic", "magic_book.txt", 20, 10, Spell.DEVOIR_MAISON),
    PROFESSEUR_DEPS(Theme.EDUCATION, "Professeur d'EPS", "prof_eps.txt", 100, 20, Spell.ESSOUFFLEMENT),
    ARAIGNEE_DE_BIBLIOTHEQUE(Theme.EDUCATION, "Araignée de bibliothèque", "araignee_biblio.txt", 50, 30, Spell.TOILE_ETHERIQUE),
    JEAN_MERLIN_BLANQUER(Theme.EDUCATION, "Jean_Merlin_Blanquer", "JeanMerlinBlancker.txt", 70, 20, Spell.DEGRADATION_MENTAL, Spell.BURNOUT),
    ROBE_JAUNE(Theme.OPPOSITION, "Robe Jaune", "robe_jaune.txt", 20, 50, Spell.EXPLOSION_MARCANIQUE),
    CASSEUR(Theme.OPPOSITION, "Casseur", "casseur.txt", 30, 30, Spell.PILLAGE),
    CHAOSSOS(Theme.OPPOSITION, "Chaossos", "chaossos.txt", 10, 99, Spell.HARCELEMENT_CIBLER),
    REVOLUTIONNAIR(Theme.OPPOSITION, "Révolutionnair", "revolutionary.txt", 20, 60, Spell.GUILLOTINE),
    JL_ANARCHON(Theme.OPPOSITION, "JL l'Anarchon", "jl_larnarchon.txt", 60, 80, Spell.BARRAGE, Spell.FRAPPE_INSOUMISE),
    SUCCUBUS(Theme.CULTURE, "Succubus <3", "succubus.txt", 169, 0, Spell.ENLACEMENT_EROTIQUE),
    PLAYGIRL(Theme.CULTURE, "PlayGirl", "playgirl.txt", 142, 15, Spell.DEUX_BON_ARGUMENT),
    VAMPIRETTE(Theme.CULTURE, "Vampirette", "vampirette.txt", 166, 0, Spell.DRAINAGE_SANGUIN),
    ESCLAVE_GOLEM(Theme.CULTURE, "Esclave Golem", "golem.txt", 666, 0, Spell.CHARGE_INERTE),
    MARLENNE_LILITHIA(Theme.CULTURE, "Marlène Lilithia", "marlenne_lilithia.txt", 250, 0, Spell.ASMODEUS, Spell.ETREINTE_VAMPIRIQUE),
    ARDIEJM(Theme.FINALE, "Archi roi démon immortel Emanuelle Jupiter Macrongue", "archi_roi_demon_immortel_emmanuel_jupiter_macrongue.txt", 1493, 49, Spell.MORNING_STAR, Spell.CONFINEMENT)

    ;

    private Theme theme;
    private String name;
    private String fileName;
    private int health;
    private int defense;
    private Spell firstSpell;
    private Spell secondSpell;

    private Bestiary(Theme theme, String name, String fileName, int health, int defense, Spell firstSpell, Spell secondSpell){
        this.theme = theme;
        this.name = name;
        this.fileName = fileName;
        this.health = health;
        this.defense = defense;
        this.firstSpell = firstSpell;
        this.secondSpell = secondSpell;
    }

    private Bestiary(Theme theme, String name, String fileName, int health, int defense, Spell firstSpell){
        this(theme, name, fileName, health, defense, firstSpell, null);
    }

    public Theme getTheme(){
        return theme;
    }

    public String getName(){
        return name;
    }

    public String getFileName(){
        return fileName;
    }
    
    public int getHealth(){
        return health;
    }

    public int getDefense(){
        return defense;
    }

    public Spell getFirstSpell(){
        return firstSpell;
    }

    public Spell getSecondSpell(){
        return secondSpell;
    }

    public boolean isBoss(){
        return secondSpell == null ? false : true;
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", fileName:" + fileName + ", health:" + health + ", defense:" + defense + ", firstSpell:" + firstSpell + ", secondSpell:" + secondSpell + "]";
    }
}