package com.ryanhuii.tuitionfinder.utils;

import com.ryanhuii.tuitionfinder.model.Assignment;

public class TagUtils {
    public static String setTagSubject(Assignment assignment) {
        String tag;
        switch (assignment.getSubject()) {
            case "English":
                tag = "Language";
                break;
            case "Chinese":
                tag = "Language";
                break;
            case "A Math":
                tag = "Mathematics";
                break;
            case "E Math":
                tag = "Mathematics";
                break;
            case "Math (Lower-Sec)":
                tag = "Mathematics";
                break;
            case "Biology":
                tag = "Science";
                break;
            case "Chemistry":
                tag = "Science";
                break;
            case "Physics":
                tag = "Science";
                break;
            case "Science (Lower-Sec)":
                tag = "Science";
                break;
            case "Computing":
                tag = "Applied Sciences";
                break;
            case "D&T":
                tag = "Applied Sciences";
                break;
            case "Art":
                tag = "Humanities";
                break;
            case "History":
                tag = "Humanities";
                break;
            case "Geography":
                tag = "Humanities";
                break;
            case "Social Studies":
                tag = "Humanities";
                break;
            default:
                tag = "something went wrong";
        }
        return tag;
    }

    public static String setTagLevel(Assignment assignment) {
        String tag;
        if (assignment.getLevel().contains("Primary")) {
            tag = "Primary";
        } else if (assignment.getLevel().contains("Secondary")) {
            tag = "Secondary";
        } else {
            tag = "Higher Education";
        }
        return tag;
    }
}
