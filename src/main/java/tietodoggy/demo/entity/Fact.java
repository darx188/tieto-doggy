package tietodoggy.demo.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "facts")
public class Fact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private Long id;
    //private UUID factId;

    private String description;
    @Column(name="isDeleted")
    private boolean isDeleted;

    /*
    public UUID getFactId() {
        return factId;
    }
    public void setFactId(UUID factId) {
        this.factId = factId;
    }

     */
    public Fact(){

    }
    public Fact(Long id, String description, boolean isDeleted) {
        super();
        this.description = description;
        this.id = id;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
