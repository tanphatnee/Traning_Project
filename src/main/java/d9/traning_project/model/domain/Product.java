package d9.traning_project.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String productName;
    private double price;
    private int stock;
    private String description;
    private String imgUrl_main;
    private boolean product_status;

    @ManyToMany
    @JsonIgnore //để ngăn Jackson chuyển đổi nó thành JSON
//    @JoinColumn(name = "category_id")
    private List<Category> categoryList = new ArrayList<>();
    @ManyToOne
    @JsonIgnore //để ngăn Jackson chuyển đổi nó thành JSON
    private Suppliers supplier ;

}