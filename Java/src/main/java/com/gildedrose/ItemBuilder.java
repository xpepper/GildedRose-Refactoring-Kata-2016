package com.gildedrose;

public class ItemBuilder {

    private final Item item;

    public ItemBuilder(String itemName) {
        item = new Item(itemName, 0, 0);
    }

    public ItemBuilder quality(int quality) {
        item.quality = quality;
        return this;
    }

    public ItemBuilder sellIn(int sellInDays) {
        item.sellIn = sellInDays;
        return this;
    }

    public Item build() {
        return item;
    }

}
