package org.examle.service;


import java.util.Comparator;
import java.util.List;

import org.examle.AbstractCommodityTest;
import org.example.model.commodity.Commodity;

import org.example.model.commodity.CommodityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CommodityServiceTest extends AbstractCommodityTest {
    @Test
    public void findAllTest() {
        var all = commodityService.getAllCommodities()
                .stream()
                .sorted(Comparator.comparing(Commodity::getId))
                .toList();
        Assertions.assertEquals(all.size(), 2);

        var expected = List.of(
                new Commodity(
                        1L,
                        "com1",
                        "desc1",
                        100L,
                        false
                ),
                new Commodity(
                        2L,
                        "com2",
                        "desc2",
                        200L,
                        true
                )
        );
        Assertions.assertEquals(expected, all);
    }

    @Test
    public void insertNewTest() {
        commodityService.createCommodity(
                new CommodityDto(
                        3L,
                        "com3",
                        "desc3",
                        300L,
                        false
                )
        );

        var all = commodityService.getAllCommodities()
                .stream()
                .sorted(Comparator.comparing(Commodity::getId))
                .toList();
        Assertions.assertEquals(all.size(), 3);

        var expected = List.of(
                new Commodity(
                        1L,
                        "com1",
                        "desc1",
                        100L,
                        false
                ),
                new Commodity(
                        2L,
                        "com2",
                        "desc2",
                        200L,
                        true
                ),
                new Commodity(
                        3L,
                        "com3",
                        "desc3",
                        300L,
                        false
                )
        );
        Assertions.assertEquals(expected, all);
    }

    @Test
    public void deleteOneTest() {
        commodityService.deleteById(2L);

        var all = commodityService.getAllCommodities()
                .stream()
                .sorted(Comparator.comparing(Commodity::getId))
                .toList();

        Assertions.assertEquals(all.size(), 1);

        var expected = List.of(
                new Commodity(
                        1L,
                        "com1",
                        "desc1",
                        100L,
                        false
                )
        );

        Assertions.assertEquals(expected, all);
    }

    @Test
    public void updateOneTest() {

        commodityService.update(
                new CommodityDto(
                        1L,
                        "new name",
                        "new desc",
                        101L,
                        true
                )
        );
        var all = commodityService.getAllCommodities()
                .stream()
                .sorted(Comparator.comparing(Commodity::getId))
                .toList();
        Assertions.assertEquals(all.size(), 2);

        var expected = List.of(
                new Commodity(
                        1L,
                        "new name",
                        "new desc",
                        101L,
                        true
                ),
                new Commodity(
                        2L,
                        "com2",
                        "desc2",
                        200L,
                        true
                )
        );
        Assertions.assertEquals(expected, all);
    }

    @Test
    public void getByIdTest() {

        var actual = commodityService.getById(2L);

        var expected =
                new Commodity(
                        2L,
                        "com2",
                        "desc2",
                        200L,
                        true
                );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchWithFilterTest() {

        var filter =
                new CommodityDto(
                        null,
                        null,
                        null,
                        null,
                        true
                );

        var all = commodityService.getCommoditiesSuch(filter)
                .stream()
                .sorted(Comparator.comparing(Commodity::getId))
                .toList();
        Assertions.assertEquals(all.size(), 1);

        var expected = List.of(
                new Commodity(
                        2L,
                        "com2",
                        "desc2",
                        200L,
                        true
                )
        );
        Assertions.assertEquals(expected, all);
    }

}
