package ru.job4j.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cars")
public class Car {

    private int id;
    private String brand;
    private String model;
    private Body body;
    private short yearOfManufacture;
    private int mileage;
    private Transmission transmission;
    private Engine engine;
    private String description;
    private boolean sold;
    private User author;
    private byte[] foto;
    private Timestamp created;

    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "sold")
    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @ManyToOne
    @JoinColumn(name = "author")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @ManyToOne
    @JoinColumn(name = "body")
    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @ManyToOne
    @JoinColumn(name = "engine_id")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "yearofmanufacture")
    public short getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(short yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Column(name = "mileage")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column(name = "foto")
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Column(name = "create_date")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (yearOfManufacture != car.yearOfManufacture) return false;
        if (mileage != car.mileage) return false;
        if (sold != car.sold) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (body != null ? !body.equals(car.body) : car.body != null) return false;
        if (transmission != null ? !transmission.equals(car.transmission) : car.transmission != null) return false;
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) return false;
        if (description != null ? !description.equals(car.description) : car.description != null) return false;
        if (author != null ? !author.equals(car.author) : car.author != null) return false;
        if (!Arrays.equals(foto, car.foto)) return false;
        return created != null ? created.equals(car.created) : car.created == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (int) yearOfManufacture;
        result = 31 * result + mileage;
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sold ? 1 : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(foto);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
