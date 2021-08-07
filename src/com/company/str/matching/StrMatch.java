package com.company.str.matching;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public abstract class StrMatch {
    protected String text = "";
    protected String pattern = "";
    protected int textLen = 0;
    protected int patternLen = 0;

    public StrMatch() {

    }

    public StrMatch(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    private int preCheck() {
        if (text == null) {
            return pattern == null ? 0 : -1;
        }
        if (pattern == null) {
            return -1;
        }
        countLen();
        if (textLen == 0) {
            return patternLen == 0 ? 0 : -1;
        }
        if (patternLen == 0) {
            return 0;
        }
        if (patternLen > textLen) {
            return -1;
        }
        return 1;
    }

    private void countLen() {
        textLen = text.length();
        patternLen = pattern.length();
    }

    protected abstract int doMatch();

    public int match() {
        int preCheckRes = preCheck();
        return preCheckRes == 1 ? doMatch() : preCheckRes;
    }

    public int match(String pattern) {
        setPattern(pattern);
        return match();
    }
}
