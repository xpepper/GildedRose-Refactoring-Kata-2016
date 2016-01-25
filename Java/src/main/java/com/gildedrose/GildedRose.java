package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    private List<Item> items;

    public GildedRose() {
        this(new ArrayList<Item>());
    }

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item eachItem : items) {
            update(eachItem);
        }
    }

    Item item(int index) {
        return items.get(index);
    }

    void add(Item item) {
        items.add(item);
    }

    private void update(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn > 10) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn > 5 && item.sellIn <= 10) {
                if (item.quality < 50) {
                    item.quality = item.quality + 2;
                }
            }

            if (item.sellIn <= 5) {
                if (item.quality < 50) {
                    item.quality = item.quality + 3;
                }
            }

            if (item.sellIn <= 0) {
                item.quality = 0;
            }

            item.sellIn = item.sellIn - 1;
            return;
        }

        if (item.name.equals("Aged Brie")) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }

            if (item.sellIn <= 0) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            item.sellIn = item.sellIn - 1;
            return;
        }

        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        if (item.sellIn <= 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
        item.sellIn = item.sellIn - 1;
    }
}
