package org.example.controllers.commodity;


import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.commodity.Commodity;
import org.example.model.commodity.CommodityDto;
import org.example.service.commodity.CommodityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/commodity")
@RequiredArgsConstructor
@Slf4j
public class CommodityController {

    private final CommodityService service;

    public static final String APPLICATION_JSON = "application/json";

    @GetMapping(value = "/", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public List<Commodity> getAll() {
        return service.getAllCommodities().stream().toList();
    }

    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Commodity get(
            @PathVariable("id") Long id
    ) {
        log.info("searching for commodity: {}", id);
        return service.getById(id);
    }

    @GetMapping(value = "/search", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public List<Commodity> searchForCommodity(
            @RequestBody CommodityDto dto
    ) {
        log.info("searching for commodity: {}", dto);
        return service.getCommoditiesSuch(dto);
    }


    @PutMapping(value = "/{id}/update", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Long updateCommodity(
            @PathVariable("id") Long id,
            @RequestBody CommodityDto dto
    ) {
        dto.setId(id);
        log.info("updating commodity: {}", dto);
        return service.update(dto);
    }

    @PostMapping(value = "/create", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Long createCommodity(
            @RequestBody CommodityDto dto
    ) {
        log.info("creating commodity: {}", dto);
        return service.createCommodity(dto);
    }

    @DeleteMapping(value = "/{id}/delete", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public void deleteCommodity(
            @PathVariable("id") Long id
    ) {
        log.info("deleting commodity: {}", id);
        service.deleteById(id);
    }

}
