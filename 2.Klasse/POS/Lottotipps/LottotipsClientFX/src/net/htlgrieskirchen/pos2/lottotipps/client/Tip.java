package net.htlgrieskirchen.pos2.lottotipps.client;

import com.sun.javafx.UnmodifiableArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kimme
 */
public class Tip {
    
    private final static String NUMBER_SEPARATOR = ",";
    
    private List<Integer> numbers;
    
    public Tip(List<Integer> numbers) {
        this.numbers = numbers;
    }
    
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
    
    @Override
    public String toString() {
        return numbers.stream().map(Object::toString).collect(Collectors.joining(NUMBER_SEPARATOR));
    }
    
    public static Tip fromString(String tipString) {
        if (tipString == null || tipString.isEmpty()) {
            throw new RuntimeException("Tipps waren leer.");
        }
        String[] parts = tipString.split(NUMBER_SEPARATOR);
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < parts.length; i++) {
            try {
                numbers.add(Integer.parseInt(parts[i]));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Tipps im ungültigen Format.");
            }
        }
        return new Tip(numbers);
    }
}
