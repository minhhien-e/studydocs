package com.example.academicservice.service.util;

import java.text.Normalizer;

public class StringUtil {

    public static String toSlug(String input) {
        if (input == null) {
            return null;
        }

        // Normalize Vietnamese characters to their base forms
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        return normalized
                .toLowerCase()
                // Remove diacritical marks (accents)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                // Replace đ and Đ with d
                .replaceAll("[đĐ]", "d")
                // Remove all non-alphanumeric except space and dash
                .replaceAll("[^a-z0-9\\s-]", "")
                // Replace multiple spaces with single space
                .replaceAll("\\s+", " ")
                // Replace spaces with dash
                .replaceAll("\\s", "-")
                // Remove multiple consecutive dashes
                .replaceAll("-{2,}", "-")
                // Remove leading/trailing dashes
                .replaceAll("^-|-$", "");
    }
}
