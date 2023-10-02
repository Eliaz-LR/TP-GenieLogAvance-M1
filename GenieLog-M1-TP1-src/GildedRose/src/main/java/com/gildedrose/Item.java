package com.gildedrose;

public class Item {

  public String name;

  public int sellIn;

  public int quality;

  public Item(String name, int sellIn, int quality) {
    this.name = name;
    this.sellIn = sellIn;
    this.quality = quality;
  }

  static final String sulfuras = "Sulfuras, Hand of Ragnaros";
  static final String backstage = "Backstage passes to a TAFKAL80ETC concert";
  static final String brie = "Aged Brie";

  public void updateQuality() {
    if (!name.equals(sulfuras)) {
      sellIn -= 1;
      update();
      ensureQualityBounds();
    }
  }

  private void update() {
    boolean outOfDate = sellIn < 0;
    switch (name) {
      case brie:
        quality += 1;
        if (outOfDate) {
          quality += 1;
        }
        break;
      case backstage:
        if (sellIn < 5) {
          quality += 3;
        } else if (sellIn < 10) {
          quality += 2;
        } else {
          quality += 1;
        }
        if (outOfDate) {
          quality = 0;
        }
        break;
      default:
        quality -= 1;
        if (outOfDate) {
          quality -= 1;
        }
        break;
    }
  }

  private void ensureQualityBounds() {
    if (quality < 0) {
      quality = 0;
    } else if (quality > 50) {
      quality = 50;
    }
  }

  @Override
  public String toString() {
    return this.name + ", " + this.sellIn + ", " + this.quality;
  }
}
