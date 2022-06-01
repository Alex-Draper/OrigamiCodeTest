import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForgettingMapTest {

    @Test
    @DisplayName("Set maxSize of forgetting map with invalid value")
    void setMaxSize_withInvalidValue_thenThrowException() {
        int invalidMaxSize = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ForgettingMap<String, String>(invalidMaxSize),
                "Forgetting Map constructor did not throw exception");

    }

    // String tests
    @Test
    @DisplayName("String: Find value with valid key")
    void findEntry_withStringValidKey_thenSuccess() {
        ForgettingMap<String, String> fm = new ForgettingMap<>(5);
        fm.add("Hello", "World");
        assertEquals("World", fm.find("Hello"));;
    }

    @Test
    @DisplayName("String: Find value with non associated key")
    void findEntry_withNonStringAssociatedKey_thenReturnNull() {
        ForgettingMap<String, String> fm = new ForgettingMap<>(5);
        fm.add("Hello", "World");
        assertNull(fm.find("Not a key"));
    }

    @Test
    @DisplayName("String: Add key value success")
    void addEntry_withValidStringKeyAndValue_thenReturnValue() {
        ForgettingMap<String, String> fm = new ForgettingMap<>(5);
        assertEquals("World", fm.add("Hello", "World"));
    }

    @Test
    @DisplayName("String: Add key value success with blank string key")
    void addEntry_withValidEmptyStringKey_thenReturnValue() {
        ForgettingMap<String, String> fm = new ForgettingMap<>(5);
        assertEquals("World", fm.add("", "World"));
    }

    // Int test
    @Test
    @DisplayName("Int: Find value with valid key")
    void findEntry_withIntValidKey_thenSuccess() {
        ForgettingMap<Integer, String> fm = new ForgettingMap<>(5);
        fm.add(56, "World");
        assertEquals("World", fm.find(56));;
    }

    @Test
    @DisplayName("Int: Find value with non associated key")
    void findEntry_withNonIntAssociatedKey_thenReturnNull() {
        ForgettingMap<Integer, String> fm = new ForgettingMap<>(5);
        fm.add(23, "World");
        assertNull(fm.find(0));
    }

    @Test
    @DisplayName("Int: Add key value success")
    void addEntry_withValidIntKeyAndValue_thenReturnValue() {
        ForgettingMap<Integer, String> fm = new ForgettingMap<>(5);
        assertEquals("World", fm.add(100, "World"));
    }


    // Automatic removal tests
    @Test
    @DisplayName("Test add when map is full and with tie break")
    void addEntry_whenMapIsFullAndWithTieBreak_expectOldestItemRemoved() {
        ForgettingMap<String, String> fm = new ForgettingMap<>(3);
        fm.add("Oldest Key", "Oldest Value");
        fm.add("2nd Oldest Key", "2nd Oldest Value");
        fm.add("3rd Oldest Key", "3rd Oldest Value");
        fm.add("4th Oldest Key", "4th Oldest Value");
        assertNull(fm.find("Oldest Key"));
    }

    @Test
    @DisplayName("Test add when map is full")
    void addEntry_whenMapIsFullWithNoTieBreak_expectOldestItemRemoved() {
        ForgettingMap<String, String> fm = new ForgettingMap<>(3);
        fm.add("Oldest Key", "Oldest Value");
        fm.find("Oldest Key");
        fm.add("2nd Oldest Key", "2nd Oldest Value");
        fm.add("3rd Oldest Key", "3rd Oldest Value");
        fm.find("3rd Oldest Key");
        fm.add("4th Oldest Key", "4th Oldest Value");
        assertNull(fm.find("2nd Oldest Key"));
    }
}
