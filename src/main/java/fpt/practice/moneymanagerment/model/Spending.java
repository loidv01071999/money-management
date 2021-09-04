package fpt.practice.moneymanagerment.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "spending")
public class Spending {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_spending_id", referencedColumnName = "id")
    @NotNull
    private SubSpendingType subSpendingType;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @NotNull
    private Unit unit;

    @Column(name = "date")
    private Date date;

}
