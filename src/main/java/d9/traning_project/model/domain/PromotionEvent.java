package d9.traning_project.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PromotionEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;
    private String event_name;
    private Date start_date;
    private Date expired_date;
    @Column(unique = true)
    private boolean status;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "promotionEvents")
    private List<Order> orders = new ArrayList<>();

}
