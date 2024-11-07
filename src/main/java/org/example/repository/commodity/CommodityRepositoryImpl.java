package org.example.repository.commodity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.example.model.commodity.Commodity;
import org.example.model.commodity.CommodityDto;
import org.example.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class CommodityRepositoryImpl implements CommodityRepository  {
    private long id = 0;
    private final HashMap<Long, Commodity> commodityDb = new HashMap<>();

    public long save(Commodity commodity) {
        if (has(commodity.getId())) {
            commodityDb.put(commodity.getId(), commodity);
            return commodity.getId();
        }
        return saveNew(commodity);
    }

    public long save(CommodityDto commodity) {
        return saveNew(commodity);
    }

    private long saveNew(Commodity commodity) {
        var newCommodity = new Commodity(commodity, ++id);
        commodityDb.put(id, newCommodity);
        return id;
    }

    private long saveNew(CommodityDto commodity) {
        var newCommodity = new Commodity(commodity, ++id);
        commodityDb.put(id, newCommodity);
        return id;
    }

    public boolean has(Long id) {
        return commodityDb.containsKey(id);
    }
    public Collection<Commodity> findAll() {
        return commodityDb.values();
    }

    public Optional<Commodity> findById(Long commodityId) {
        return Optional.ofNullable(commodityDb.get(commodityId));
    }

    public List<Commodity> findById(Collection<Long> commodityIds) {
        var commodities = new ArrayList<Commodity>();

        return commodityIds.stream()
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<Commodity> findByConstraint(Predicate<Commodity> constraint) {
        return commodityDb.values().stream()
                .filter(constraint)
                .toList();
    }

    public void delete(Long commodityId) {
        if (!commodityDb.containsKey(commodityId)) {
            throw new EntityNotFoundException(Commodity.class, commodityId);
        }
        commodityDb.remove(commodityId);
    }


}
