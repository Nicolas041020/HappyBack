package happy.paws.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CONSULTA")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @Column()
    private Date fecha;
    @Column()
    private String motivo;
    @Column()
    private String estado;
    @Column()
    private String veterinario;
    @Column()
    private String resultado;
    @ManyToOne()
    @JoinColumn(name = "pet_id")
    private Pet pet;


    public Consulta (){}


    public Consulta(Integer id, Date fecha, String motivo, String estado, String veterinario, String resultado,
            Pet pet) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
        this.veterinario = veterinario;
        this.resultado = resultado;
        this.pet = pet;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getMotivo() {
        return motivo;
    }


    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getVeterinario() {
        return veterinario;
    }


    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }


    public String getResultado() {
        return resultado;
    }


    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    public Pet getPet() {
        return pet;
    }


    public void setPet(Pet pet) {
        this.pet = pet;
    }

    
}
