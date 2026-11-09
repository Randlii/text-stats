# text-stats

Небольшая консольная утилита на Java 17: считает строки, слова, символы и самые частые
слова в текстовом файле. Используется как учебный проект для настройки CI/CD в GitHub Actions.

```bash
mvn clean verify
java -jar target/text-stats-1.0.0.jar README.md 5
```

Пайплайн `.github/workflows/ci-cd.yml` на каждый push и pull request в `main`
собирает проект, запускает тесты, кэширует зависимости Maven и сохраняет jar как артефакт.
