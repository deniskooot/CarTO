package com.github.Denis.utils;

import java.util.*;

public class StringUtils {

    private static final Map<String, String> ABBREVIATIONS_MAP = createAbbreviationsMap();

    private static Map<String, String> createAbbreviationsMap(){
        Set<String> abbreviations = Set.of("ГРМ", "МБ", "ABS", "ESP", "АКПП", "МКПП");
        Map<String, String> map = new HashMap<>();
        for (String abbr : abbreviations){
            map.put(abbr.toLowerCase(), abbr.toUpperCase());
        }
        return map;
    }

    private StringUtils(){}

    public static String normalizeServiceScheduleName(String input) {
        if (input == null || input.isEmpty()) return input;

//             Разбиваем строку на слова
//        \\ экранирует обратный слеш в строке Java.
//          \s в регулярном выражении означает любой пробельный символ (пробел, табуляция и т.д.).
//          + означает "один или более".
        String[] words = input.split("\\s+");
        StringJoiner result = new StringJoiner(" ");
        for (String word : words) {
            result.add(normalizeWord(word));
        }
        String finalResult = result.toString();
        return finalResult.isEmpty() ? finalResult :
                Character.toUpperCase(finalResult.charAt(0)) + finalResult.substring(1);
    }
    private static String normalizeWord(String word){
        return ABBREVIATIONS_MAP.getOrDefault(word.toLowerCase(), word);
    }
}
