package org.example.repository.commodity;


import org.example.model.commodity.Commodity;
import org.example.model.commodity.CommodityDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    default long save(CommodityDto dto) {
        return save(new Commodity(dto)).getId();
    }
}
