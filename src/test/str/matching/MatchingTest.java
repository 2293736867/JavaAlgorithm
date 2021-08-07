package test.str.matching;

import com.company.str.matching.StrMatch;
import com.company.str.matching.algorithm.Sunday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class MatchingTest {
    private static final int TIMES = 1_000_000;
    private static final List<Character> randomChar = new ArrayList<>(62);
    private static final List<Character> repeatedChar = new ArrayList<>();
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        fillChar();
        boolean errors = doTest(randomChar,new Sunday());
        if(errors){
            System.out.println("Error!");
            return;
        }
        errors = doTest(repeatedChar,new Sunday());
        if(errors){
            System.out.println("Error!");
        }
    }

    private static void fillChar() {
        for (int i = 0; i < 62; i++) {
            if (i < 26) {
                randomChar.add((char) ('a' + i));
            } else if (i < 52) {
                randomChar.add((char) ('A' + (i - 26)));
            } else {
                randomChar.add((char) ('0' + (i - 52)));
            }
        }
        repeatedChar.add('a');
        repeatedChar.add('b');
    }

    private static boolean doTest(List<Character> chars, StrMatch match){
        Random random = new Random() ;
        int len = chars.size();
        AtomicReference<Boolean> errors = new AtomicReference<>(false);
        IntStream.range(0, TIMES).parallel().forEach(i->{
            Collections.shuffle(chars);
            int textLen = random.nextInt(500);
            int patternLen = random.nextInt(300);
            char[] textArray = new char[textLen];
            char[] patternArray = new char[patternLen];
            for (int j = 0; j < textLen; j++) {
                textArray[j] = chars.get(random.nextInt(len));
            }
            for (int j = 0; j < patternLen; j++) {
                patternArray[j] = chars.get(random.nextInt(len));
            }

            String text = new String(textArray);
            String pattern = new String(patternArray);
            int libraryRes = text.indexOf(pattern);
            lock.lock();
            match.setText(text);
            match.setPattern(pattern);
            int matchRes = match.match();
            lock.unlock();
            if(libraryRes != matchRes){
                System.out.println("------------------------------Error-----------------------------");
                System.out.println("text is "+text);
                System.out.println("pattern is "+pattern);
                System.out.println("libraryRes is "+libraryRes);
                System.out.println("matchRes is "+matchRes);
                System.out.println("------------------------------Error finish-----------------------------");
                errors.set(true);
            }
        });
        return errors.get();
    }
}
