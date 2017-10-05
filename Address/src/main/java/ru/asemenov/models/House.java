package ru.asemenov.models;

/**
 * Дом.
 */
public class House {
    /**
     * Id дома.
     */
    private int id;
    /**
     * Номер дома.
     */
    private String name;
    /**
     * Улица на которой находится дом.
     */
    private Street street;

    /**
     * Конструктор.
     */
    public House() {
    }

    /**
     * Конструктор.
     * @param id дома.
     */
    public House(int id) {
        this.id = id;
    }

    /**
     * Получить id дома.
     * @return int id дома.
     */
    public int getId() {
        return id;
    }

    /**
     * Установить id для дома.
     * @param id дома.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить номер дома.
     * @return string номер дома.
     */
    public String getName() {
        return name;
    }

    /**
     * Установить номер для дома.
     * @param name имя дома.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить улицу на которой расположен дом.
     * @return street улица.
     */
    public Street getStreet() {
        return street;
    }

    /**
     * Устанвоить улицу для дома.
     * @param street улица.
     */
    public void setStreet(Street street) {
        this.street = street;
    }

    /**
     * Переопределение метода toString.
     * @return String.
     */
    @Override
    public String toString() {
        return "House{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", street=" + street
                + '}';
    }
}
