package happy.paws.model;

import lombok.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VET")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_id")
    private Integer vet_id;
    @Column()
    private String name;
    @Column()
    private String identification;
    @Column()
    private String email;
    @Column()
    private String phoneNumber;
    @Column()
    private String passw;
    @Column()
    private String speciality;

    public Vet(){}
    

    public Vet(String name, String identification, String email, String phoneNumber, String passw, String speciality) {
        this.name = name;
        this.identification = identification;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passw = passw;
        this.speciality = speciality;
    }


    public Integer getVet_id() {
        return vet_id;
    }

    public void setVet_id(Integer vet_id) {
        this.vet_id = vet_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    public String getPassw() {
        return passw;
    }


    public void setPassw(String passw) {
        this.passw = passw;
    }

    
    



}
