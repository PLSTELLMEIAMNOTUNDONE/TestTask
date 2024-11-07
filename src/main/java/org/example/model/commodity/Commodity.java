package org.example.model.commodity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Commodity {
    @NotNull
    private final Long id;
    @Size(max = 255)
    @NotNull
    private String name;
    @Size(max = 4096)
    private String description = null;
    @Min(0L)
    @NotNull
    private Long price = 0L;
    private boolean isAvailable = false;

    public Commodity(CommodityDto ref, long id) {
        this.id = id;
        if(ref.getName() != null) this.name = ref.getName();
        if(ref.getDescription() != null) this.description = ref.getDescription();
        if(ref.getPrice() != null) this.price = ref.getPrice();
        if(ref.getIsAvailable() != null) this.isAvailable = ref.getIsAvailable();
    }

    public Commodity(Commodity ref, long id) {
        this.id = id;
        this.name = ref.getName();
        this.description = ref.getDescription();
        this.price = ref.getPrice();
        this.isAvailable = ref.isAvailable();
    }
}
