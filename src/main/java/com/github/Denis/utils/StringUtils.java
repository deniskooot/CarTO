package com.github.Denis.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Set<String> ABBREVIATIONS = new HashSet<>(Arrays.asList(
            "ГРМ", "МБ", "ABS", "ESP", "АКПП", "МКПП"
    ));

    private StringUtils(){}

    public static String normalizeServiceScheduleName(String input){
        if (input == null || input.isEmpty()){
            return input;
        }
        String result = capitalizeFirstLetter(input);
        for (String abbr: ABBREVIATIONS) {

// (?i) — делает регулярное выражение регистронезависимым.
// \\b — обозначает границы слова, чтобы не заменять части других слов.
// "аббревиатура" остается в верхнем регистре, если она есть в списке

            result = input.replaceAll("\\b(?)" + abbr + "\\b", abbr);
        }
        return result;
    }

    public static String capitalizeFirstLetter(String input){

        return input.substring(0,1).toUpperCase() + input.substring(1);
    }
}
