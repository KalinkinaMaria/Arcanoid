/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

/**
 * Игрок
 * 
 * @author Мария
 */
public class Player {
    /** Счет */
    private int score;
    /** Кол-во жизней */
    private int lifes;
    
    /**
     *  Конструктор
     */
    public Player () {
        score = 0;
        lifes = 0;
    }
    
    /**
     * Конструктор
     * @param lifes кол-во жизней
     */
    public Player (int lifes) {
        score = 0;
        this.lifes = lifes;
    }
    
    /**
     * Задать количество жизней
     * @param countLives кол-во жизней
     */
    public void setLives(int countLives) {
        this.lifes = countLives;
    }
    
    /**
     * Получить кол-во жизней
     * @return кол-во жизней
     */
    public int lifes() {
        return lifes;
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
