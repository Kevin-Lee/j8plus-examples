package cc.kevinlee.functional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static cc.kevinlee.functional.Funs.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import static cc.kevinlee.functional.Data.*;
import static cc.kevinlee.functional.Data.Product.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-26
 */
public class FunsExamples {
  public static void main(String[] args) {
    isNullExamples();
    isNotNullExamples();
    reversedExamples();
    toStringOfExamples();
    satisfyingExamples();
    applyingExamples();
  }

  private static void isNullExamples() {
    System.out.println("\nFunsExamples.isNullExamples");

    System.out.println(isNull().test(null));   // true
    System.out.println(isNull().test(1));      // false
    System.out.println(isNull().test("abc"));  // flase

    final List<String> listOfNull =
        Arrays.asList("a", "b", null, "c", null, null, "d")
            .stream()
            .filter(isNull())
            .collect(toList());

    System.out.println("null found: " + listOfNull.size());  // null found: 3

  }

  private static void isNotNullExamples() {
    System.out.println("\nFunsExamples.isNotNullExamples");

    System.out.println(isNotNull().test(null));   // false
    System.out.println(isNotNull().test(1));      // true
    System.out.println(isNotNull().test("abc"));  // true

    final List<String> listOfNotNullString =
        Arrays.asList("a", "b", null, "c", null, null, "d")
            .stream()
            .filter(isNotNull())
            .collect(toList());

    System.out.println("list of not null String values: " + listOfNotNullString);  // [a,b,c,d]

  }

  private static void reversedExamples() {
    System.out.println("\nFunsExamples.reversedExamples");

    final List<Integer> numbers = Arrays.asList(4, 2, 5, 3, 1);

    final Comparator<Integer> intCmp = Integer::compareTo;
    final Comparator<Integer> reversedIntCmp = reversed(intCmp);

    final List<Integer> numbersInAsc =
        numbers.stream()
            .sorted(intCmp)
            .collect(toList());
    System.out.println("Number in ascending order: " + numbersInAsc);
    // Number in ascending order: [1, 2, 3, 4, 5]

    final List<Integer> numbersInDsc =
        numbers.stream()
            .sorted(reversedIntCmp)
            .collect(toList());
    System.out.println("Number in descending order: " + numbersInDsc);
    // Number in descending order: [5, 4, 3, 2, 1]


    final List<Product> products = Arrays.asList(product(1L, "A", $("30.00")), product(2L, "B", $("12.50")), product(3L, "C", $("5.45")));
    final List<Product> productsSortedByPriceInAsc =
        products
            .stream()
            .sorted(comparing(Product::getPrice))
            .collect(toList());
    System.out.println("Products sorted by price in ascending order: \n" + productsSortedByPriceInAsc);
    // Products sorted by price in ascending order:
    // [Product{id=3, name='C', price=5.45}, Product{id=2, name='B', price=12.50}, Product{id=1, name='A', price=30.00}]


    final List<Product> productsSortedByPriceInDsc =
        products
            .stream()
            .sorted(reversed(comparing(Product::getPrice)))
            .collect(toList());
    System.out.println("Products sorted by price in descending order: \n" + productsSortedByPriceInDsc);
    // Products sorted by price in descending order:
    // [Product{id=1, name='A', price=30.00}, Product{id=2, name='B', price=12.50}, Product{id=3, name='C', price=5.45}]

    final List<BigDecimal> bigDecimalsInDsc =
        Arrays.asList(new BigDecimal("3"), new BigDecimal("1"), new BigDecimal("2"))
            .stream()
//          .sorted(BigDecimal::compareTo.reversed()) // compile-time error
            .sorted(reversed(BigDecimal::compareTo))
            .collect(toList());
    System.out.println("bigDecimalsInDsc: " + bigDecimalsInDsc);
    // bigDecimalsInDsc: [3, 2, 1]

  }

  private static void toStringOfExamples() {

    System.out.println("\nFunsExamples.toStringOfExamples");

    final List<Product> products = Arrays.asList(product(1L, "A", $("30.00")), product(2L, "B", $("12.50")), product(3L, "C", $("5.45")));

//    System.out.println(products.stream()
//                               .map(Product::getPrice)
//                               .collect(joining(", "))); // compile-time error

    System.out.println(
        products.stream()
            .map(toStringOf(Product::getPrice))
            .collect(joining(", "))
    );
    // 30.00, 12.50, 5.45
  }

  private static void satisfyingExamples() {
    System.out.println("\nFunsExamples.satisfyingExamples");

    System.out.println(
        Arrays.asList("Hello world", "Hello Kevin", "Hi world", "Hey", "Hello")
            .stream()
            .filter(satisfying(String::startsWith, "Hello"))
            .collect(toList())
    );
  }

  private static void applyingExamples() {
    System.out.println("\nFunsExamples.applyingExamples");

    final List<Product> products = Arrays.asList(product(1L, "A", $("30.00")), product(2L, "B", $("12.50")), product(3L, "C", $("5.45")));

    final BigDecimal specialPrice = new BigDecimal("10.00");
    System.out.println(
        products
            .stream()
            .map(applying(Product::price, specialPrice))
            .collect(toList())
    );
    // [Product{id=1, name='A', price=10.00}, Product{id=2, name='B', price=10.00}, Product{id=3, name='C', price=10.00}]
  }
}
