package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose() {
        this(new ArrayList<Item>());
    }

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item eachItem: items) {
            update(eachItem);
        }
    }

    private void update(Item eachItem) {
        if (!eachItem.name.equals("Aged Brie")
                && !eachItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (eachItem.quality > 0) {
                if (!eachItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                    eachItem.quality = eachItem.quality - 1;
                }
            }
        } else {
            if (eachItem.quality < 50) {
                eachItem.quality = eachItem.quality + 1;

                if (eachItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (eachItem.sellIn < 11) {
                        if (eachItem.quality < 50) {
                            eachItem.quality = eachItem.quality + 1;
                        }
                    }

                    if (eachItem.sellIn < 6) {
                        if (eachItem.quality < 50) {
                            eachItem.quality = eachItem.quality + 1;
                        }
                    }
                }
            }
        }

        if (!eachItem.name.equals("Sulfuras, Hand of Ragnaros")) {
            eachItem.sellIn = eachItem.sellIn - 1;
        }

        if (eachItem.sellIn < 0) {
            if (!eachItem.name.equals("Aged Brie")) {
                if (!eachItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (eachItem.quality > 0) {
                        if (!eachItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                            eachItem.quality = eachItem.quality - 1;
                        }
                    }
                } else {
                    eachItem.quality = eachItem.quality - eachItem.quality;
                }
            } else {
                if (eachItem.quality < 50) {
                    eachItem.quality = eachItem.quality + 1;
                }
            }
        }
    }

    void add(Item item) {
        items.add(item);
    }
}
