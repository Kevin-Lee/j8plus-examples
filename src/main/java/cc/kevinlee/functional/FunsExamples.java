package cc.kevinlee.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static cc.kevinlee.functional.Funs.*;
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

    final List<Integer> numbersInDsc =
        numbers.stream()
            .sorted(reversedIntCmp)
            .collect(toList());
    System.out.println("Number in descending order: " + numbersInDsc);


    final List<Product> products = Arrays.asList(product(1L, "A", $("30.00")), product(2L, "B", $("12.50")), product(3L, "C", $("5.45")));
    final List<Product> productsSortedByPriceInAsc =
        products
            .stream()
            .sorted(comparing(Product::getPrice))
            .collect(toList());
    System.out.println("Products sorted by price in ascending order: \n" + productsSortedByPriceInAsc);

    final List<Product> productsSortedByPriceInDsc =
        products
            .stream()
            .sorted(reversed(comparing(Product::getPrice)))
            .collect(toList());
    System.out.println("Products sorted by price in descending order: \n" + productsSortedByPriceInDsc);
  }
}
