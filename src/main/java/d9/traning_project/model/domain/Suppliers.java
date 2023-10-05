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

public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_id;
    private String supplier_name;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();


}
