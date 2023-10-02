package com.gildedrose;

class GildedRose {

  Item[] items;

  static final String sulfuras = "Sulfuras, Hand of Ragnaros";
  static final String backstage = "Backstage passes to a TAFKAL80ETC concert";
  static final String brie = "Aged Brie";

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      Item item = items[i];

      if (!item.name.equals(sulfuras)) {
        item.sellIn -= 1;
        update(item);
        ensureQualityBounds(item);
      }
    }
  }

  //Traitement avant mis-à-jour de la date d'expiration
  private void update(Item item) {
    boolean outOfDate = item.sellIn < 0;
    switch (item.name) {
      case brie:
        item.quality += 1;
        if (outOfDate) {
          item.quality += 1;
        }
        break;
      case backstage:
        if (item.sellIn < 5) {
          item.quality += 3;
        } else if (item.sellIn < 10) {
          item.quality += 2;
        } else {
          item.quality += 1;
        }
        if (outOfDate) {
          item.quality = 0;
        }
        break;
      default:
        item.quality -= 1;
        if (outOfDate) {
          item.quality -= 1;
        }
        break;
    }
  }

  private void ensureQualityBounds(Item item) {
    if (item.quality < 0) {
      item.quality = 0;
    } else if (item.quality > 50) {
      item.quality = 50;
    }
  }
}
