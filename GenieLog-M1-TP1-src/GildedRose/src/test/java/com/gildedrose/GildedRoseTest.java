package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals("foo", element.name, "the name changed");
  }
  /* 0% INCREASING JACOCO */
  @DisplayName("Test that the quality of all item except Sulfura" +
   "is between 0-50, sulfura is always at 80")
   void testValueQualityExtr() {
    Item element1 = new Item("foo", 3, 0);
    Item element2 = new Item("Aged Brie", 4, 50);
    Item element3 = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50);

    Item element4 = new Item("Sulfuras, Hand of Ragnaros", 20, 80);

    GildedRose app1 = new GildedRose(new Item[] {element1});
    GildedRose app2 = new GildedRose(new Item[] {element2});
    GildedRose app3 = new GildedRose(new Item[] {element3});
    GildedRose app4 = new GildedRose(new Item[] {element4});

    app1.updateQuality();
    app2.updateQuality();
    app3.updateQuality();
    app4.updateQuality();

    assertEquals(0, element1.quality, "The quality is always positive");
    assertEquals(50, element2.quality, "The quality can't be higher than 50");
    assertEquals(50, element3.quality, "The quality can't be higher than 50");
    assertEquals(80, element4.quality, "Sulfuras quality is always 80");
   }
   

 

}
