package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Indication implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private IndicationType indicationType;

    @NotNull
    private BigDecimal indication;

    @NotNull
    @Temporal(TemporalType.DATE)
    @PastOrPresent
    private Date date;


    public enum IndicationType {
        HEATING,
        HEAT_WATER,
        COLD_WATER,
        ELECTRICITY_DAY,
        ELECTRICITY_NIGHT
    }

    public Indication(IndicationType indicationType, BigDecimal indication, Date date) {
        this.indicationType = indicationType;
        this.indication = indication;
        this.date = date;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public Indication() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IndicationType getIndicationType() {
        return indicationType;
    }

    public void setIndicationType(IndicationType indicationType) {
        this.indicationType = indicationType;
    }

    public BigDecimal getIndication() {
        return indication;
    }

    public void setIndication(BigDecimal indication) {
        this.indication = indication;
    }

}
