package org.example.model.commodity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Size(max = 255)
    @Column(nullable = false)
    private String name;
    @Size(max = 4096)
    @Column(nullable = true)
    private String description = null;
    @Min(0L)
    @Column(nullable = false)
    private Long price = 0L;
    @Column(nullable = false, name = "available")
    private boolean isAvailable = false;

    public Commodity(CommodityDto ref) {
        if (ref.getId() != null) this.id = ref.getId();
        if (ref.getName() != null) this.name = ref.getName();
        if (ref.getDescription() != null) this.description = ref.getDescription();
        if (ref.getPrice() != null) this.price = ref.getPrice();
        if (ref.getIsAvailable() != null) this.isAvailable = ref.getIsAvailable();
    }

    public Commodity(Commodity ref, long id) {
        this.id = id;
        this.name = ref.getName();
        this.description = ref.getDescription();
        this.price = ref.getPrice();
        this.isAvailable = ref.isAvailable();
    }
}
