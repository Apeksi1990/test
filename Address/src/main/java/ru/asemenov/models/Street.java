package ru.asemenov.models;

/**
 * Улица.
 */
public class Street {
    /**
     * Id улицы.
     */
    private int id;
    /**
     * Название улицы.
     */
    private String name;

    /**
     * Конструктор.
     */
    public Street() {
    }

    /**
     * Конструктор.
     * @param id улицы.
     */
    public Street(int id) {
        this.id = id;
    }

    /**
     * Получить id улицы.
     * @return int id улицы.
     */
    public int getId() {
        return id;
    }

    /**
     * Установить id для дома.
     * @param id улицы.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить название улицы.
     * @return String название улицы.
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название улицы.
     * @param name название улицы.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Переопределение метода toString.
     * @return String.
     */
    @Override
    public String toString() {
        return "Street{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
