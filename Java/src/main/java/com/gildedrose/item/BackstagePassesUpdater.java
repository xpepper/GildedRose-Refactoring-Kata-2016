package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePassesUpdater implements ItemUpdater {

    private Item item;

    public BackstagePassesUpdater(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
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
    }

}
