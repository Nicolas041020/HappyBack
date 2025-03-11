package happy.paws.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @Column()
    private Integer petId;
    @Column()
    private Date date;
    @Column()
    private String vaccine;
    @Column()
    private int dosis;
    @Column()
    private double cuantity;


    public History(Integer id, Integer petId, Date date, String vaccine, int dosis, double cuantity) {
        this.id = id;
        this.petId = petId;
        this.date = date;
        this.vaccine = vaccine;
        this.dosis = dosis;
        this.cuantity = cuantity;
    }

    public History(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public double getCuantity() {
        return cuantity;
    }

    public void setCuantity(double cuantity) {
        this.cuantity = cuantity;
    }

    

}
