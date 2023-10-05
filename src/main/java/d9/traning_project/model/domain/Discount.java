package d9.traning_project.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String discount_code;
    private double discount_percent;
    @Column(unique = true)
    private int stock;
    private boolean status;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "discounts")
    private List<Order> orders = new ArrayList<>();

}
