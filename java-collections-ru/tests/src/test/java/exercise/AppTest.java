package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testApp1() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();

        int count = 10;
        for(int i = 0; i < count; i++) {
            list1.add(i);
        }

        list3.add(100);

        assertThat(App.take(list1, count)).isEqualTo(list1).contains(100);
        assertThat(App.take(list2, 0)).isEqualTo(list2);


    }

}
