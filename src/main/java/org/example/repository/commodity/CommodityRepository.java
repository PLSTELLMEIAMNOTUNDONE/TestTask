package org.example.repository.commodity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.example.model.commodity.Commodity;
import org.example.model.commodity.CommodityDto;

public interface CommodityRepository {

    Collection<Commodity> findAll();

    long save(Commodity commodity);

    Optional<Commodity> findById(Long commodityId);

    List<Commodity> findById(Collection<Long> commodityIds);

    long save(CommodityDto commodity);

    boolean has(Long id);

    List<Commodity> findByConstraint(Predicate<Commodity> constraint);

    void delete(Long commodityId);
}
