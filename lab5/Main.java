package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Задание 1:");
            int numerator = readInt(scanner, "Введите числитель: ");
            int denominator = readInt(scanner, "Введите знаменатель: ");

            Fraction fraction = new Fraction(numerator, denominator);
            System.out.println("Обычная дробь: " + fraction);
            System.out.println("Её вещественное значение: " + fraction.getValue());

            CachedFraction cachedFraction = new CachedFraction(numerator, denominator);
            System.out.println("\nКэшируемая дробь: " + cachedFraction);
            System.out.println("Первый расчёт: " + cachedFraction.getValue());
            System.out.println("Получение из кэша: " + cachedFraction.getValue());

            System.out.println("\nИзменяем числитель на 10:");
            cachedFraction.setNumerator(10);
            System.out.println("После изменения: " + cachedFraction);
            System.out.println("Новое значение: " + cachedFraction.getValue());

            Fraction another = new Fraction(10, denominator);
            System.out.println("\nСравнение дробей:");
            System.out.println("Результат сравнения: " + cachedFraction.equals(another));


            System.out.println("\nЗадание 2:");
            System.out.print("Введите имя кота: ");
            String catName = scanner.nextLine();

            Cat cat = new Cat(catName);
            MeowCounter catCounter = new MeowCounter(cat);

            MeowUtils.makeAllMeow(catCounter, catCounter, catCounter);
            System.out.println("\nКот мяукнул всего " + catCounter.getCount() + " раз(а).");


            System.out.println("\nЗадание 3: ");
            System.out.print("Введите элементы списка через пробел: ");
            String line = scanner.nextLine();

            List<String> items = Arrays.asList(line.split("\\s+"));
            List<String> unique = new ArrayList<>();
            Set<String> seen = new HashSet<>();

            for (String item : items) {
                if (seen.add(item)) {
                    unique.add(item);
                }
            }

            System.out.println("Только первые вхождения: " + unique);


            System.out.println("\nЗадание 4:");
            String fileName = "labs/src/lab5/sportsmens.txt";
            Competition competition = new Competition();

            try (Scanner fileScanner = new Scanner(new File(fileName))) {
                if (!fileScanner.hasNextLine()) {
                    System.out.println("Файл пустой!");
                    return;
                }
                int N = Integer.parseInt(fileScanner.nextLine().trim());

                if (!fileScanner.hasNextLine()) {
                    System.out.println("Нет информации о количестве видов спорта!");
                    return;
                }
                int M = Integer.parseInt(fileScanner.nextLine().trim());

                for (int i = 0; i < N; i++) {
                    if (!fileScanner.hasNextLine()) break;

                    String lineInput = fileScanner.nextLine().trim();
                    String[] parts = lineInput.split(" ");
                    if (parts.length != M + 2) {
                        System.out.println("Ошибка: некорректная строка -> " + lineInput);
                        continue;
                    }

                    String lastName = parts[0];
                    String firstName = parts[1];
                    int[] scores = new int[M];
                    for (int j = 0; j < M; j++) {
                        scores[j] = Integer.parseInt(parts[j + 2]);
                    }

                    competition.addParticipant(lastName, firstName, scores);
                }

                List<String> results = competition.getResults();
                System.out.println("Результаты соревнований:");
                for (String res : results) {
                    System.out.println(res);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + fileName);
            }


            System.out.println("\nЗадание 5:");
            String fileName5 = "labs/src/lab5/sportsmens.txt";
            Set<Character> russianLetters = new HashSet<>();

            try (Scanner fileScanner = new Scanner(new File(fileName5))) {

                while (fileScanner.hasNext()) {
                    String word = fileScanner.next();

                    for (char c : word.toCharArray()) {
                        if ((c >= 'А' && c <= 'Я') || (c >= 'а' && c <= 'я')) {
                            russianLetters.add(Character.toLowerCase(c));
                        }
                    }
                }

                System.out.println("Количество различных русских букв в тексте: " + russianLetters.size());

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + fileName5);
            }


            System.out.println("\nЗадание 6:");

            Scanner scanner6 = new Scanner(System.in);
            LinkedList<Integer> queue = new LinkedList<>();

            System.out.print("Введите элементы очереди через пробел: ");
            String queueLine = scanner6.nextLine();
            for (String s : queueLine.split("\\s+")) {
                try {
                    queue.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода: " + s);
                }
            }

            if (queue.isEmpty()) {
                System.out.println("Очередь пуста!");
            } else {
                System.out.print("Элементы в обратном порядке: ");
                Iterator<Integer> descending = queue.descendingIterator();
                while (descending.hasNext()) {
                    System.out.print(descending.next() + " ");
                }
                System.out.println();
            }


            System.out.println("\nЗадание 7.1:");

            Point[] points = {
                    new Point(1, -2),
                    new Point(3, 4),
                    new Point(2, -1),
                    new Point(1, -2),
                    new Point(5, -3)
            };

            Polyline polyline = new Polyline(
                    Arrays.stream(points)
                            .distinct()
                            .map(p -> new Point(p.getX(), Math.abs(p.getY())))
                            .sorted(Comparator.comparingDouble(Point::getX))
                            .toArray(Point[]::new)
            );

            System.out.println("Результат: " + polyline);


            System.out.println("\nЗадание 7.2:");

            String fileName8 = "labs/src/lab5/persons.txt";

            try {
                Map<Integer, List<String>> grouped = Files.lines(Paths.get(fileName8))
                        .map(ln -> ln.split(":", 2))
                        .filter(parts -> parts.length == 2 && !parts[1].isEmpty())
                        .collect(Collectors.groupingBy(
                                parts -> Integer.parseInt(parts[1].trim()),
                                Collectors.mapping(
                                        parts -> {
                                            String name = parts[0].trim().toLowerCase();
                                            return name.substring(0, 1).toUpperCase() + name.substring(1);
                                        },
                                        Collectors.toList()
                                )
                        ));

                System.out.println("Группировка по номеру: " + grouped);

            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }
}
