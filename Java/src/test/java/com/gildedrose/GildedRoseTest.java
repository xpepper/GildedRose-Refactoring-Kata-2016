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

        assertEquals(1, firstItem().sellIn);
        assertEquals(2, firstItem().quality);

        app.updateInventory();

        assertEquals(1 - 1, firstItem().sellIn);
        assertEquals(2 - 1, firstItem().quality);
    }

    @Test
    public void the_quality_of_an_item_is_never_negative() {
        app.add(aGenericItemWith().quality(0).sellIn(1).build());

        app.updateInventory();

        assertEquals(0, firstItem().quality);
    }

    @Test
    public void once_the_sell_by_date_has_passed_quality_degrades_twice_as_fast() {
        app.add(aGenericItemWith().quality(2).sellIn(0).build());

        app.updateInventory();

        assertEquals(0, firstItem().quality);
    }

    @Test
    public void AgedBrie_increases_in_Quality_the_older_it_gets() throws Exception {
        Item agedBrie = new ItemBuilder("Aged Brie").quality(2).sellIn(1).build();
        app.add(agedBrie);

        app.updateInventory();

        assertEquals(2 + 1, firstItem().quality);
    }

    @Test
    public void AgedBrie_quality_is_never_more_than_50() throws Exception {
        Item agedBrie = new ItemBuilder("Aged Brie").quality(50).sellIn(1).build();
        app.add(agedBrie);

        app.updateInventory();

        assertEquals(50, firstItem().quality);
    }

    @Test
    public void Sulfuras_never_has_to_be_sold_or_decreases_in_Quality() throws Exception {
        Item sulfuras = new ItemBuilder("Sulfuras, Hand of Ragnaros").quality(80).sellIn(100).build();
        app.add(sulfuras);

        app.updateInventory();

        assertEquals(80, firstItem().quality);
        assertEquals(100, firstItem().sellIn);
    }

    private Item firstItem() {
        return app.item(0);
    }

    private ItemBuilder aGenericItemWith() {
        return new ItemBuilder("a generic item");
    }

}
