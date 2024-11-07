package org.example.service.commodity;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.BadRequestException;
import org.example.model.commodity.Commodity;
import org.example.model.commodity.CommodityDto;
import org.example.repository.commodity.CommodityRepository;
import org.example.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommodityService {
    private final CommodityRepository commodityRepository;

    public Collection<Commodity> getAllCommodities() {
        return commodityRepository.findAll();
    }

    public long createCommodity(CommodityDto commodityDto) {
        if(commodityRepository.has(commodityDto.getId())) {
            throw new BadRequestException("такой товар уже существует");
        }
        return commodityRepository.save(commodityDto);
    }

    public List<Commodity> getCommoditiesSuch(CommodityDto commodityDto) {
        return commodityRepository.findByConstraint(commodityLikeDtoPredicate(commodityDto));
    }

    private Predicate<Commodity> commodityLikeDtoPredicate(CommodityDto commodityDto) {
        return commodity ->
                commodityDto.getIsAvailable() == null || commodity.isAvailable() == commodityDto.getIsAvailable()
                        &&
                        commodityDto.getName() == null || commodity.getName().equals(commodityDto.getName())
                        &&
                        commodityDto.getDescription() == null || commodity.getDescription().equals(commodityDto.getDescription())
                        &&
                        commodityDto.getPrice() == null || commodity.getPrice().equals(commodityDto.getPrice());
    }

    public Commodity getById(Long id) {
        var commodity = commodityRepository.findById(id);
        if (commodity.isEmpty()) {
            throw new EntityNotFoundException(Commodity.class, id);
        }
        return commodity.get();
    }

    public void deleteById(Long id) {
        commodityRepository.delete(id);
    }

    public long update(CommodityDto commodityDto) {
        var commodity = getById(commodityDto.getId());
        update(commodityDto, commodity);
        commodityRepository.save(commodity);
        return commodity.getId();
    }

    private void update(CommodityDto commodityDto, Commodity commodity) {
        if(commodityDto.getName() != null) {
            commodity.setName(commodityDto.getName());
        }
        if(commodityDto.getDescription() != null) {
            commodity.setDescription(commodityDto.getDescription());
        }
        if(commodityDto.getPrice() != null) {
            commodity.setPrice(commodityDto.getPrice());
        }
        if(commodityDto.getIsAvailable() != null) {
            commodity.setAvailable(commodityDto.getIsAvailable());
        }
    }


}
