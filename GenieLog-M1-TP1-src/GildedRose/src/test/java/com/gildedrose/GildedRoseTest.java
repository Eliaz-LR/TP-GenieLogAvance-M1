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
  @DisplayName("On gère d'abord les comportements des items normaux")
  /*
   * Propriétés à vérifier pour les cartes normales:
   * Si SellIn < 0 alors un objet se dégrade 2x plus vite.
   * La qualité est comprise entre 0 et 50 
   */
  void testBasicItem(){
    Item element = new Item("foo", 1, 8);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(0, element.sellIn, "sellIn decreasing test");
    assertEquals(7, element.quality, "quality decreasing test");
    app.updateQuality();
    assertEquals(-1, element.sellIn, "negative sellIn normal test");
    assertEquals(5, element.quality, "negative sellIn / decreasing quality double");
    element.quality = 0;
    app.updateQuality();
    assertEquals(0, element.quality, "quality negativity test");
    

  }

}