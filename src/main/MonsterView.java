package main;

import main.file.FileLoader;

public class MonsterView {
    public static void main(String[] args) {
        System.out.println(FileLoader.load("crs.txt"));
        System.out.println(FileLoader.load("ARS.txt"));
        System.out.println(FileLoader.load("braveM_ascii.txt"));
        System.out.println(FileLoader.load("ChienDeGarde.txt"));
        System.out.println(FileLoader.load("GeraldGardemain.txt"));

        System.out.println(FileLoader.load("casseur.txt"));
        System.out.println(FileLoader.load("chaossos.txt"));
        System.out.println(FileLoader.load("jl_larnarchon.txt"));
        System.out.println(FileLoader.load("revolutionary.txt"));
        System.out.println(FileLoader.load("robe_jaune.txt"));

        System.out.println(FileLoader.load("araignee_biblio.txt"));
        System.out.println(FileLoader.load("JeanMerlinBlancker.txt"));
        System.out.println(FileLoader.load("magic_book.txt"));
        System.out.println(FileLoader.load("prof_eps.txt"));
        System.out.println(FileLoader.load("professeur_magie.txt"));

        System.out.println(FileLoader.load("golem.txt"));
        System.out.println(FileLoader.load("marlenne_lilithia.txt"));
        System.out.println(FileLoader.load("playgirl.txt"));
        System.out.println(FileLoader.load("succubus.txt"));
        System.out.println(FileLoader.load("vampirette.txt"));

        System.out.println(FileLoader.load("archi_roi_demon_immortel_emmanuel_jupiter_macrongue.txt"));

    }
}