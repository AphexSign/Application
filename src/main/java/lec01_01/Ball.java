package lec01_01; /**
 * Это часть Lec01_01: Настройка среды и введение в Java.
 */

import java.awt.Color;

/**
 * Это простой объект, который имеет объем.
 */
// Вы не можете заставить lec01_01.Ball реализовать сопоставимый интерфейс.
public class Ball {

    private double volume;
    private Color color;

    /**
     * Конструктор, который создает новый объект ball с заданным объемом и цветом.
     * @param volume объем нового объекта ball
     * @param color цвет нового объекта ball
     */
    public Ball(double volume, Color color) {
        this.volume = volume;
        this.color = color;
    }

    /**
     * Конструктор, который создает новый объект ball с заданным объемом, заданным строкой.
     * @param volume Строка, представляющая объем нового объекта.
     */
    public Ball(String volume, Color color) {
        this(Double.parseDouble(volume), color);

    }

    /**
     * Возвращает объем шара.
     * @верните обьем мяча.
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     * Возвращает цвет шара.
     * @верните цвет шара.
     */
    public Color getColor() {
        return this.color;
    }

}