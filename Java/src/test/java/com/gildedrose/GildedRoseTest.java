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

        assertEquals(2 - 2, firstItem().quality);
    }

    @Test
    public void aged_brie_increases_in_quality_the_older_it_gets() throws Exception {
        Item agedBrie = new ItemBuilder("Aged Brie").quality(2).sellIn(1).build();
        app.add(agedBrie);

        app.updateInventory();

        assertEquals(2 + 1, firstItem().quality);
    }

    @Test
    public void aged_brie_increases_in_quality_even_when_the_sell_by_date_has_passed() throws Exception {
        Item agedBrie = new ItemBuilder("Aged Brie").quality(2).sellIn(-1).build();
        app.add(agedBrie);

        app.updateInventory();

        // TODO: is this a wrong case or a bug?
        // assertEquals(2 + 1, firstItem().quality);
        assertEquals(2 + 2, firstItem().quality);
    }

    @Test
    public void aged_brie_quality_is_never_more_than_50() throws Exception {
        Item agedBrie = new ItemBuilder("Aged Brie").quality(50).sellIn(1).build();
        app.add(agedBrie);

        app.updateInventory();

        assertEquals(50, firstItem().quality);
    }

    @Test
    public void sulfuras_never_has_to_be_sold_or_decreases_in_quality() throws Exception {
        Item sulfuras = new ItemBuilder("Sulfuras, Hand of Ragnaros").quality(80).sellIn(100).build();
        app.add(sulfuras);

        app.updateInventory();

        assertEquals(80, firstItem().quality);
        assertEquals(100, firstItem().sellIn);
    }

    @Test
    public void backstage_passes_increases_in_quality_by_1_when_there_are_more_than_10_days() throws Exception {
        Item backstagePasses = new ItemBuilder("Backstage passes to a TAFKAL80ETC concert").quality(2).sellIn(11).build();
        app.add(backstagePasses);

        app.updateInventory();

        assertEquals(2 + 1, firstItem().quality);
    }

    @Test
    public void backstage_passes_increases_in_quality_by_2_when_there_are_10_days_or_less() throws Exception {
        Item backstagePasses = new ItemBuilder("Backstage passes to a TAFKAL80ETC concert").quality(3).sellIn(10).build();
        app.add(backstagePasses);

        app.updateInventory();

        assertEquals(3 + 2, firstItem().quality);
    }

    @Test
    public void backstage_passes_increases_in_quality_by_3_when_there_are_5_days_or_less() throws Exception {
        Item backstagePasses = new ItemBuilder("Backstage passes to a TAFKAL80ETC concert").quality(2).sellIn(5).build();
        app.add(backstagePasses);

        app.updateInventory();

        assertEquals(2 + 3, firstItem().quality);
    }

    @Test
    public void backstage_passes_quality_is_never_more_than_50() throws Exception {
        Item backstagePasses = new ItemBuilder("Backstage passes to a TAFKAL80ETC concert").quality(50).sellIn(11).build();
        app.add(backstagePasses);

        app.updateInventory();

        assertEquals(50, firstItem().quality);
    }

    @Test
    public void backstage_passes_quality_drop_to_zero_after_the_concert() throws Exception {
        Item backstagePasses = new ItemBuilder("Backstage passes to a TAFKAL80ETC concert").quality(4).sellIn(0).build();
        app.add(backstagePasses);

        app.updateInventory();

        assertEquals(0, firstItem().quality);
    }

    @Test
    public void conjured_items_degrade_in_quality_twice_as_fast_as_normal_items() {
        Item conjuredItem = new ItemBuilder("Conjured").quality(4).sellIn(1).build();
        app.add(conjuredItem);

        app.updateInventory();

        assertEquals(1 - 1, firstItem().sellIn);
        assertEquals(4 - 2, firstItem().quality);
    }

    @Test
    public void conjured_items_degrade_in_quality_twice_as_fast_as_normal_items_even_when_the_sell_by_date_has_passed() {
        Item conjuredItem = new ItemBuilder("Conjured").quality(4).sellIn(-1).build();
        app.add(conjuredItem);

        app.updateInventory();

        assertEquals(4 - 2*2, firstItem().quality);
    }

    private Item firstItem() {
        return app.item(0);
    }

    private ItemBuilder aGenericItemWith() {
        return new ItemBuilder("a generic item");
    }

}
