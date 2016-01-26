package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePassesUpdater extends AbstractItemUpdater {

    public BackstagePassesUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.sellIn > 10) {
            if (item.quality < 50) {
                changeQualityBy(+1);
            }
        }

        if (item.sellIn > 5 && item.sellIn <= 10) {
            if (item.quality < 50) {
                changeQualityBy(+2);
            }
        }

        if (item.sellIn <= 5) {
            if (item.quality < 50) {
                changeQualityBy(+3);
            }
        }

        if (item.sellIn <= 0) {
            item.quality = 0;
        }

        decrementSellIn();
    }

}
