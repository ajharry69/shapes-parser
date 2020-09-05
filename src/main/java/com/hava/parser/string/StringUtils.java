package com.hava.parser.string;

import java.util.Stack;

public class StringUtils {
    public static String trimStart(String str) {
        if (str == null) return null;
        boolean isEmpty = true;
        String _str = "";
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                if (!isEmpty) _str = String.format("%s%s", _str, c);
            } else {
                _str = String.format("%s%s", _str, c);
                isEmpty = false;
            }
        }
        return _str;
    }

    public static String trimEnd(String str) {
        if (str == null) return null;
        boolean isEmpty = true;
        String _str = "";
        char[] chars = new StringBuilder(str).reverse().toString().toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                if (!isEmpty) _str = String.format("%s%s", c, _str);
            } else {
                _str = String.format("%s%s", c, _str);
                isEmpty = false;
            }
        }
        return _str;
    }

    public static String trimStartAndEnd(String str) {
        if (str == null) return null;
        Stack<Character> chars = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                if (!chars.empty()) chars.push(c);
            } else {
                chars.push(c);
            }
        }
        boolean isEmpty = true;
        String _str = "";
        while (chars.size() > 0) {
            char c = chars.pop();
            if (c == ' ') {
                if (!isEmpty) _str = String.format("%s%s", c, _str);
            } else {
                _str = String.format("%s%s", c, _str);
                isEmpty = false;
            }
        }
        return _str;
    }
}
