package tietodoggy.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
import java.sql.Date;

@Entity
@Table(name = "transaction")
public class Facts {

    @Id
    private UUID factId;

    private String fact;

    private Date uploadDate;

    public Facts() {
        super();
        this.factId = UUID.randomUUID();
    }

    public UUID getFactId() {
        return factId;
    }

    public Facts(Date uploadDate, String fact) {
        super();
        this.uploadDate = uploadDate;
        this.fact = fact;
    }

    public void setFactId(UUID factId) {
        this.factId = factId;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
