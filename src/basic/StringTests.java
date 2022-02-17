package basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTests {

    @Test
    @DisplayName("stringBuilder reverse")
    void stringBuilder() {
        StringBuilder sb = new StringBuilder("abcdefg");
        String reversed = sb.reverse().toString();
        System.out.println(reversed);
        assertEquals("gfedcba", reversed);
    }

    @Test
    @DisplayName("Insert는 그 인덱스에 문자열을 추가한다.")
    void stringBuilderInsert() {
        StringBuilder sb = new StringBuilder("abcdefg");
        String inserted = sb.insert(2, "z").toString();
        System.out.println(inserted);

        StringBuilder sb2 = new StringBuilder("abcdefg");
        String inserted2 = sb2.insert(2, 2).toString();
        System.out.println(inserted2);

        assertEquals("abzcdefg", inserted);
        assertEquals("ab2cdefg", inserted2);
    }
}
