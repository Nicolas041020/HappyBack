package happy.paws.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer petId;
    @Column()
    private String name;
    @Column()
    private String race;
    @Column()
    private int amount_of_walks;
    @Column()
    private int amount_of_food;
    @Column()
    private String food;
    @Column()
    private int weight;
    @Column()
    private String description;
    @Column()
    private double age;
    @ManyToOne
    @JoinColumn(name = "user_id")
    //@JsonIgnore
    //@JsonBackReference
    @JsonIgnoreProperties("pets")
    private User user;
    @OneToMany(mappedBy = "pet",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Consulta> consultas;
    @OneToMany(mappedBy = "pet",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<History> histories;
    @OneToMany(mappedBy = "pet",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Recordatory> recordatories;



    

    public Pet(Integer petId, String name, String race, int amount_of_walks, int amount_of_food, String food,
            int weight, String description, double age) {
        this.petId = petId;
        this.name = name;
        this.race = race;
        this.amount_of_walks = amount_of_walks;
        this.amount_of_food = amount_of_food;
        this.food = food;
        this.weight = weight;
        this.description = description;
        this.age = age;
    }

    public Pet(){}

    public Integer getId() {
        return petId;
    }

    public void setId(Integer petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAmount_of_walks() {
        return amount_of_walks;
    }

    public void setAmount_of_walks(int amount_of_walks) {
        this.amount_of_walks = amount_of_walks;
    }

    public int getAmount_of_food() {
        return amount_of_food;
    }

    public void setAmount_of_food(int amount_of_food) {
        this.amount_of_food = amount_of_food;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public List<Recordatory> getRecordatories() {
        return recordatories;
    }

    public void setRecordatories(List<Recordatory> recordatories) {
        this.recordatories = recordatories;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    

}
