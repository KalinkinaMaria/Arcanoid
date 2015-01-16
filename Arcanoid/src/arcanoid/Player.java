/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.service.SpeedVector;

/**
 * Игрок
 * 
 * @author Мария
 */
public class Player {
    /** Счет */
    private int score;
    /** Кол-во жизней */
    private int lives;
    
    /**
     *  Конструктор
     */
    public Player () {
        score = 0;
        lives = 0;
    }
    
    /**
     * Конструктор
     * @param lives кол-во жизней
     */
    public Player (int lives) {
        score = 0;
        this.lives = lives;
    }
    
    /**
     * Задать количество жизней
     * @param countLives кол-во жизней
     */
    public void setLives(int countLives) {
        this.lives = countLives;
    }
    
    /**
     * Получить кол-во жизней
     * @return кол-во жизней
     */
    public int lives() {
        return lives;
    }
    
    /**
     * Получить счет
     * @return счет
     */
    public int score() {
        return score;
    }
    
    /**
     * Задать счет
     * @param score счет 
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    
}
