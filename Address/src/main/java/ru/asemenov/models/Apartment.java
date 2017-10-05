package ru.asemenov.models;

/**
 * Квартира.
 */
public class Apartment {
    /**
     * Id квартиры.
     */
    private int id;
    /**
     * Номер квартиры.
     */
    private String name;
    /**
     * Дом к которому привязана квартира.
     */
    private House house;

    /**
     * Конструктор.
     */
    public Apartment() {
    }

    /**
     * Конструктор.
     * @param id квартиры.
     */
    public Apartment(int id) {
        this.id = id;
    }

    /**
     * Получить id квартиры.
     * @return id квартиры.
     */
    public int getId() {
        return id;
    }

    /**
     * Установить id квартиры.
     * @param id квартиры.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить номер квартиры.
     * @return string номер квартиры.
     */
    public String getName() {
        return name;
    }

    /**
     * Установить номер квартиры.
     * @param name квартиры.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить дом в котором находится квартира.
     * @return дом.
     */
    public House getHouse() {
        return house;
    }

    /**
     * Установить дом в котором будет находиться квартира.
     * @param house номер дома.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Переопределение метода toString.
     * @return String.
     */
    @Override
    public String toString() {
        return "Apartment{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", house=" + house
                + '}';
    }
}
