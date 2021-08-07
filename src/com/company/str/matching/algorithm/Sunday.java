package com.company.str.matching.algorithm;

import com.company.str.matching.StrMatch;

public class Sunday extends StrMatch {
    public Sunday(){
        super();
    }

    public Sunday(String text, String pattern) {
        super(text, pattern);
    }

    @Override
    protected int doMatch() {
        char[] textArr = text.toCharArray();
        char[] patternArr = pattern.toCharArray();

        int j = 0;
        for (int i = 0; i < textLen; ) {
            if (patternArr[j] == textArr[i]) {
                ++j;
                ++i;
                if (j == patternLen) {
                    return i - patternLen;
                }
            } else {
                int nextIndex = i + (patternLen - j);
                if (nextIndex >= textLen) {
                    return -1;
                }
                char next = textArr[nextIndex];
                int k = patternLen - 1;
                while (k >= 0 && patternArr[k] != next) {
                    --k;
                }
                i += patternLen - k - j;
                j = 0;
            }
        }
        return -1;
    }
}
