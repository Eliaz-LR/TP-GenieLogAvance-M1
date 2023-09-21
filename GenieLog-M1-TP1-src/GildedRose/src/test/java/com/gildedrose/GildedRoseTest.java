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

  @Test
  @DisplayName("Test toString function")
  void testToString(){
    Item element = new Item("foo", 0, 0);
    String nameItem = element.toString();
    assertEquals("foo, 0, 0", nameItem, "toString test");
  }
  
  /* Test functions to test properties of basic items
   * 1 test.
   */



  @Test
  @DisplayName("Test decreasing quality neg")
  void diminutionsNegQual(){
    Item element = new Item("foo", 0, 7);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(5, element.quality, "sellIn decreasing test");
  }

  /* Test functions to test properties of the special item:
   * "backstage passes to a TAFKAL80ETC concert"
   * 4 tests. 
   */

  @Test
  @DisplayName("Test passes when they expiring")
  void expiringPasses(){
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 17);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(0, element.quality, "0 value backstages test");
  }

  @Test
  @DisplayName("Test passes when quality is 48 and sellIn between 1 and 5")
  void VeryNearPassesWithHighQuality(){
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", 3, 48);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(50, element.quality, "50 quality test (backstage)");
  }

  @Test
  @DisplayName("Test passes when quality is near 49 and sellIn between 6 and 10")
  void NearPassesWithHighQuality(){
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", 8, 49);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(50, element.quality, "50 quality test (backstage)");
  }

  @Test
  @DisplayName("Test passes when quality is near 49 and sellIn and superior to 10")
  void PassesWithHighQuality(){
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", 12, 49);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(50, element.quality, "50 quality test (backstage)");
  }


  /* Test functions to test properties of the special item:
   * "Sulfuras, Hand of Ragnaros"
   * 1 test. 
   */



  @Test
  @DisplayName("contraint value sulfuras (neg) test")
  void ConstantQualNegSulf() {
    Item element = new Item("Sulfuras, Hand of Ragnaros", -5, 80);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(80, element.quality, "contraint value sulfuras (neg) test");
  }


  /* Test functions to test properties of the special item:
   * "Aged Brie"
   * 2 tests. 
   */


  @Test
  @DisplayName("quality neg increase Brie test")
  void testNegQualityIncrBrie(){
    Item element = new Item("Aged Brie", -5, 8);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(10, element.quality, "negative increasing brie Test");

  }

  @Test
  @DisplayName("quality increase Brie test")
  void testNegMaxQualityBrie(){
    Item element = new Item("Aged Brie", -5, 50);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(50, element.quality, "negative increasing brie Test");
  }

}
