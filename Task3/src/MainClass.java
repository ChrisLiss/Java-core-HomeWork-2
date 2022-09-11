import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false

        assert fuzzySearch("car", "ca6$$#_rtwheel")  : "ожидалось значение true";
        assert !fuzzySearch("lw", "cartwheel") : "ожидалось значение false";
        assert !fuzzySearch(null, "cartwheel") :  "ожидалось значение false";
        assert !fuzzySearch("ree", null) :  "ожидалось значение false";

    }

    private static boolean fuzzySearch(String searchString, String input) {

        if (searchString == null || input == null) {
            return false;
        }

        String regEx = searchString.chars().mapToObj(x -> (char) x)
                .map(x -> x + ".*")
                .collect(Collectors.joining());

        return input.matches(regEx);
    }

}
