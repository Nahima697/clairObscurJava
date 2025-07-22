package main;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import utils.exception.PotionIndisponibleException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws PotionIndisponibleException {
        new GameManager().lancerJeu();
        }
    }
