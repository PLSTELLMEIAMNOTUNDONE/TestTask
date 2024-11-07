package org.examle;


import org.example.model.commodity.Commodity;
import org.example.repository.commodity.CommodityRepository;
import org.example.repository.commodity.CommodityRepositoryImpl;
import org.example.service.commodity.CommodityService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;



public class AbstractCommodityTest {

    protected CommodityService commodityService;

    protected CommodityRepository commodityRepository;

    @BeforeEach
    protected void prepare() {
        this.commodityRepository = new CommodityRepositoryImpl();

        this.commodityService = new CommodityService(commodityRepository);

        commodityRepository.save(
                new Commodity(
                        1L,
                        "com1",
                        "desc1",
                        100L,
                        false
                )
        );
        commodityRepository.save(
                new Commodity(
                        2L,
                        "com2",
                        "desc2",
                        200L,
                        true
                )
        );
    }
}
