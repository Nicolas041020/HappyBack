package happy.paws.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="RECORRIDO")
public class Recorrido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "pet_id")
    private Pet pet_id;

    private Double lat;

    private Double lon;

    public Recorrido(Pet pet_id, Double lat, Double lon) {
        this.pet_id = pet_id;
        this.lat = lat;
        this.lon = lon;
    }

    public Recorrido(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pet getPet_id() {
        return pet_id;
    }

    public void setPet_id(Pet pet_id) {
        this.pet_id = pet_id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }


    

}
