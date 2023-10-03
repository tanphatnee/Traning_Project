package d9.traning_project.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receiverName;
    private String address;
    private String phone;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users users;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "order")
    @JsonIgnore
    private List<OrderDetail> orderDetails= new ArrayList<>();
    private double totalAmount;
    @ManyToOne
    @JoinColumn(name = "orderStatusName_id")
    private OrderStatusName orderStatusNames;
    @ManyToMany
    @JoinColumn(name = "discount")
    @JsonIgnore
    private List<Discount> discounts = new ArrayList<>();
    @ManyToMany
    @JoinColumn(name = "promotionevent")
    @JsonIgnore
    private List<PromotionEvent> promotionEvents = new ArrayList<>();

}