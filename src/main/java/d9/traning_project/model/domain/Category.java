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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private boolean status;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "categoryList")
    private List<Product> products = new ArrayList<>();

}