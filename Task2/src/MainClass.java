import java.util.*;
import java.util.function.Predicate;

public class MainClass {

    public static void main(String[] args) {

           /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */


         /*
        Если ответ предполагает пару уникальных чисел, то лучше решение через searchAddTermsFromSet,
                оно отработает за O(n)
        Если ответом может являться пара одинаковых чисел, тогда решение searchAddTermsFromList

          */

        Integer[] array1 = {3,4,2,7};
        Integer[] array2 = {1,2,3,5,5};

        System.out.println(searchAddTermsFromSet(array1, 10));
        System.out.println(searchAddTermsFromList(array2, 10));
        System.out.println(searchAddTermsFromSet(array1,6));

        assert List.of(3,7).equals(searchAddTermsFromSet(array1, 10)) : "ожидался список: [3, 7]";
        assert List.of(2,4).equals(searchAddTermsFromSet(array1, 6)) : "ожидался список: [2, 4]";
        assert List.of(5,5).equals(searchAddTermsFromList(array2, 10)) : "ожидался список: [5, 5]";
        assert List.of().equals(searchAddTermsFromSet(null, 10)) : "ожидался пустой список";

    }

    private static List<Integer> searchAddTermsFromSet(Integer[] array, int sum) {

        if (array == null) {
            return Collections.EMPTY_LIST;
        }

        Set<Integer> set = new HashSet<>(Arrays.asList(array));

        Predicate<Integer> condition = (x) -> Objects.nonNull(x) && set.contains(sum-x) && x != (sum - x);

        return set.stream()
                .filter(condition)
                .map(x -> List.of(x, sum - x))
                .findFirst()
                .orElse(null);

    }

    private static List<Integer> searchAddTermsFromList(Integer[] array, int sum) {

        if (array == null) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> list = Arrays.asList(array);

        Predicate<Integer> condition = (x) -> {
            return Objects.nonNull(x) && list.contains(sum-x) && (x != (sum - x) || list.indexOf(x) != list.lastIndexOf(x));
        };

        return list.stream()
                .filter(condition)
                .map(x -> List.of(x, sum - x))
                .findFirst()
                .orElse(null);

    }

}
