package ru.mifi.textstats;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/** Подсчёт статистики по тексту: строки, слова, символы и самые частые слова. */
public final class TextStats {

  private static final Pattern WORD_SPLIT = Pattern.compile("[^\\p{L}\\p{N}'-]+");

  private final long lines;
  private final long words;
  private final long chars;
  private final Map<String, Integer> frequencies;

  private TextStats(long lines, long words, long chars, Map<String, Integer> frequencies) {
    this.lines = lines;
    this.words = words;
    this.chars = chars;
    this.frequencies = frequencies;
  }

  public static TextStats of(String text) {
    if (text.isEmpty()) {
      return new TextStats(0, 0, 0, Map.of());
    }
    long lines = text.lines().count();
    Map<String, Integer> freq = new HashMap<>();
    long words = 0;
    for (String token : WORD_SPLIT.split(text.toLowerCase(Locale.ROOT))) {
      if (!token.isBlank()) {
        words++;
        freq.merge(token, 1, Integer::sum);
      }
    }
    return new TextStats(lines, words, text.length(), freq);
  }

  public long lines() {
    return lines;
  }

  public long words() {
    return words;
  }

  public long chars() {
    return chars;
  }

  public int uniqueWords() {
    return frequencies.size();
  }

  /** Самые частые слова: по убыванию частоты, при равенстве по алфавиту. */
  public List<Map.Entry<String, Integer>> top(int n) {
    return frequencies.entrySet().stream()
        .sorted(
            Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()))
        .limit(n)
        .toList();
  }
}
