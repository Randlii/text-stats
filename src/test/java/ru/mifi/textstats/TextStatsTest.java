package ru.mifi.textstats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TextStatsTest {

  @Test
  void countsLinesWordsAndChars() {
    TextStats s = TextStats.of("Привет, мир!\nМир прекрасен.");
    assertEquals(2, s.lines());
    assertEquals(4, s.words());
    assertEquals(27, s.chars());
    assertEquals(3, s.uniqueWords());
  }

  @Test
  void topWordsSortedByFrequencyThenAlphabet() {
    TextStats s = TextStats.of("b a c a b a");
    assertEquals("a", s.top(3).get(0).getKey());
    assertEquals(3, s.top(3).get(0).getValue());
    assertEquals("b", s.top(3).get(1).getKey());
    assertEquals(1, s.top(1).size());
  }

  @Test
  void emptyText() {
    TextStats s = TextStats.of("");
    assertEquals(0, s.words());
    assertEquals(0, s.top(5).size());
  }
}
