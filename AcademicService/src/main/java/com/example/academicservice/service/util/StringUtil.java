package com.example.academicservice.service.util;

public class StringUtil {

    public static String toSlug(String input) {
        if (input == null) {
            return null;
        }
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-")
                .replaceAll("^-|-$", "");
    }
}
