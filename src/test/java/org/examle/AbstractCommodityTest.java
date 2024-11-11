package org.examle;


import org.example.Main;
import org.example.model.commodity.Commodity;
import org.example.repository.commodity.CommodityRepository;
import org.example.service.commodity.CommodityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest(
        properties = {
                "spring.datasource.url=jdbc:h2:mem:testdb",
                "spring.jpa.hibernate.ddl-auto=create-drop"
        }
)
@ContextConfiguration(classes = Main.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AbstractCommodityTest {

    protected CommodityService commodityService;
    @Autowired
    protected CommodityRepository commodityRepository;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    protected void prepare() {

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

    @AfterEach
    protected void cleanUp() {
        commodityRepository.deleteAll();
    }


//    @Test
//    public void testInit() {
//
//    }
}
