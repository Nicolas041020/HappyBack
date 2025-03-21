package happy.paws.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @Column()
    private Date date;
    @Column()
    private String vaccine;
    @Column()
    private int dosis;
    @Column()
    private double cuantity;
    @ManyToOne()
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column()
    private String comments;
    @Column()
    private String reason;


    public History(Date date, String vaccine, int dosis, double cuantity, String comments, String reason) {
        this.date = date;
        this.vaccine = vaccine;
        this.dosis = dosis;
        this.cuantity = cuantity;
        this.comments = comments;
        this.reason = reason;
    }

    public History(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    

}
