package ru.mifi.textstats;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/** Использование: java -jar text-stats.jar <файл> [кол-во слов в топе]. */
public final class Main {

  private Main() {}

  public static void main(String[] args) throws IOException {
    System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    if (args.length < 1) {
      System.out.println("Использование: java -jar text-stats.jar <файл> [N]");
      System.exit(1);
    }
    int topN = args.length > 1 ? Integer.parseInt(args[1]) : 5;
    String text = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);
    TextStats stats = TextStats.of(text);
    System.out.println("Строк: " + stats.lines());
    System.out.println("Слов: " + stats.words());
    System.out.println("Символов: " + stats.chars());
    System.out.println("Уникальных слов: " + stats.uniqueWords());
    System.out.println("Топ-" + topN + " слов:");
    for (Map.Entry<String, Integer> e : stats.top(topN)) {
      System.out.println("  " + e.getKey() + " — " + e.getValue());
    }
  }
}
