package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private GildedRose app;

    @Before
    public void setup() {
        app = new GildedRose();
    }

    @Test
    public void at_the_end_of_each_day_the_system_lowers_both_values_for_every_item() {
        app.add(aGenericItemWith().quality(2).sellIn(1).build());

        assertEquals(1, app.item(0).sellIn);
        assertEquals(2, app.item(0).quality);

        app.updateInventory();

        assertEquals(1-1, app.item(0).sellIn);
        assertEquals(2-1, app.item(0).quality);
    }

    @Test
    public void the_quality_of_an_item_is_never_negative() {
        app.add(aGenericItemWith().quality(0).sellIn(1).build());

        app.updateInventory();

        assertEquals(0, app.item(0).quality);
    }

    @Test
    public void once_the_sell_by_date_has_passed_quality_degrades_twice_as_fast() {
        app.add(aGenericItemWith().quality(2).sellIn(0).build());

        app.updateInventory();

        assertEquals(0, app.item(0).quality);
    }

    private ItemBuilder aGenericItemWith() {
        return new ItemBuilder("a generic item");
    }

}
