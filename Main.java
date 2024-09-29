import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // Метод для поиска символов из первой строки, не входящих во вторую
    public static String duplicateChars(String str1, String str2) {
        return str1.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(c -> !str2.contains(c))
                .collect(Collectors.joining());
    }

    // Метод для подсчета нечётных чисел, кратных 3
    public static long dividedByThree(int[] arr) {
        return Arrays.stream(arr)
                .filter(num -> num % 2 != 0 && num % 3 == 0)
                .count();
    }

// Метод для форматирования ФИО в библиографическом формате
public static String getInitials(String fullName) {
    String[] parts = fullName.split(" ");
    return parts[1].charAt(0) + "." + parts[2].charAt(0) + "." + capitalize(parts[0]);
}
// Вспомогательная функция для капитализации фамилии
private static String capitalize(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
}

    // Метод для нормализации массива
    public static double[] normalizator(double[] arr) {
        double min = Arrays.stream(arr).min().orElse(0);
        double max = Arrays.stream(arr).max().orElse(0);
        return Arrays.stream(arr)
                .map(num -> (max == min) ? 0 : (num - min) / (max - min))
                .toArray();
    }

    // Метод для «сжатия» массива вещественных чисел
    public static List<Integer> compressedNums(double[] arr) {
        return Arrays.stream(arr)
                .filter(num -> num != 0)
                .mapToInt(num -> (int) num)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    // Метод для преобразования строки из CamelCase в SnakeCase
    public static String camelToSnake(String camel) {
        return camel.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    // Метод для нахождения второго по величине элемента массива
    public static Integer secondBiggest(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    // Метод для локального реверса части строки между двумя маркерными символами
    public static String localReverse(String s, char marker) {
        int first = s.indexOf(marker);
        int last = s.lastIndexOf(marker);
        if (first == -1 || first == last) return s;

        String reversedPart = new StringBuilder(s.substring(first + 1, last)).reverse().toString();
        return s.substring(0, first + 1) + reversedPart + s.substring(last);
    }

    // Метод для подсчета одинаковых целых чисел
    public static int equal(int a, int b, int c) {
        Set<Integer> uniqueNumbers = new HashSet<>(Arrays.asList(a, b, c));
        return 4 - uniqueNumbers.size();
    }

    // Метод для определения анаграмм
    public static boolean isAnagram(String str1, String str2) {
        String cleanedStr1 = str1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String cleanedStr2 = str2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleanedStr1.length() == cleanedStr2.length() &&
               Arrays.equals(sortedChars(cleanedStr1), sortedChars(cleanedStr2));
    }

    // Вспомогательный метод для сортировки символов строки
    private static char[] sortedChars(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return arr;
    }

    // Точка входа в программу
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("UTF-8 encoding is not supported!");
        }

        // Примеры использования:
        System.out.println(duplicateChars("Barack", "Obama"));  // ➞ "rck"
        System.out.println(dividedByThree(new int[]{3, 12, 7, 81, 52}));  // ➞ 2
        System.out.println(getInitials("simonov sergei evgenievich"));  // ➞ "S.E.Simonov"
        System.out.println(getInitials("kozhevnikova tatiana vitalevna"));  // ➞ "T.V.Kozhevnikova"
        System.out.println(Arrays.toString(normalizator(new double[]{3.5, 7.0, 1.5, 9.0, 5.5})));  // ➞ [0.3333, 0.8333, 0.0, 1.0, 0.6667]
        System.out.println(Arrays.toString(normalizator(new double[]{10.0, 10.0, 10.0, 10.0})));  // ➞ [0.0, 0.0, 0.0, 0.0]
        System.out.println(compressedNums(new double[]{1.6, 0, 212.3, 34.8, 0, 27.5}));  // ➞ [1, 27, 34, 212]
        System.out.println(camelToSnake("helloWorld"));  // ➞ "hello_world"
        System.out.println(secondBiggest(new int[]{3, 5, 8, 1, 2, 4}));  // ➞ 5
        System.out.println(localReverse("baobab", 'b'));  // ➞ "baboab"
        System.out.println(localReverse("Hello, I’m under the water, please help me", 'e'));  // ➞ " Hednu m’I ,oller thetaw er, plesae hem ple"
        System.out.println(equal(8, 1, 8));  // ➞ 2
        System.out.println(equal(5, 5, 5));  // ➞ 3
        System.out.println(equal(4, 9, 6));  // ➞ 0
        System.out.println(isAnagram("LISTEN", "silent"));  // ➞ true
        System.out.println(isAnagram("Eleven plus two?", "Twelve plus one!"));  // ➞ true
        System.out.println(isAnagram("hello", "world"));  // ➞ false
    }
}
