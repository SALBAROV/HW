package com.company;

import java.util.Random;

public class Main {


    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 270, 240};
    public static int[] heroesDamage = {20, 25, 15, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) { // !false = true / !true = false
            round();
        }
    }

    public static void round() {
        if (bossHealth > 0) {
            chooseBossDefence();
            bossHits();
        }
        heroesHit();
        heal();
        printStatistics();
    }



    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose defence: " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(8) + 2; // 2, 3, 4, 5, 6, 7, 8, 9
                    bossHealth = checkHealth(bossHealth - heroesDamage[i] * coeff);
                    System.out.println(heroesAttackType[i] + " hits boss critically " + heroesDamage[i] * coeff);
                } else {
                    bossHealth = checkHealth(bossHealth - heroesDamage[i]);
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = checkHealth(heroesHealth[i] - bossDamage);
            }
        }
    }

    public static int checkHealth(int health) {
        if (health < 0) {
            return 0;
        } else {
            return health;
        }
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("________________");
    }

    public static void heal() {
//        for (int i = 0; i < heroesHealth.length; i++) {
//            if (heroesHealth[i] < 100) {
//                heroesHealth[i] += 25;
//                break;
//            }
//
//        }
        if (heroesHealth[3] > 0){
            for (int i = 0; i <heroesHealth.length ; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0 ){
                    int ability = 25;
                    heroesHealth[i] = heroesHealth[i] + ability;
                    System.out.println("Медик спас" + heroesAttackType);
                    break;


                }

            }
        }
    }
}