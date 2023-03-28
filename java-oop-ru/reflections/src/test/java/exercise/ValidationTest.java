package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    @Test
    void testAdvancedValidate() {
        Address a = new Address(null, "20", "22222", "3", "0");
        List<String> listOfNullError = List.of("can not be null");
        List<String> listOfMinLengthError = List.of("length less than 3");
        Map<String, List<String>> expected = Map.of("country", listOfNullError, "city", listOfMinLengthError);
        Map<String, List<String>> result = Validator.advancedValidate(a);
        assertThat(result).isEqualTo(expected);
    }
}
