package com.gildedrose;

import com.gildedrose.item.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class GildedRose {
    private static final String CONJURED = "Conjured";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private final List<Item> items;

    public GildedRose() {
        this(new ArrayList<>());
    }

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item eachItem : items) {
            update(eachItem);
        }
    }

    public Item item(int index) {
        return items.get(index);
    }

    public void add(Item item) {
        items.add(item);
    }

    private void update(Item item) {
        HashMap<String, ItemUpdater> updaters = new HashMap<>();
        updaters.put(SULFURAS, new NullUpdater(item));
        updaters.put(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, new BackstagePassesUpdater(item));
        updaters.put(AGED_BRIE, new AgedBrieUpdater(item));
        updaters.put(CONJURED, new ConjuredUpdater(item));

        ItemUpdater updater = getFrom(updaters, item.name, new GenericItemUpdater(item));
        updater.update();
    }

    private ItemUpdater getFrom(HashMap<String, ItemUpdater> itemUpdaters, String key, ItemUpdater fallbackUpdater) {
        return itemUpdaters.getOrDefault(key, fallbackUpdater);
    }

}
