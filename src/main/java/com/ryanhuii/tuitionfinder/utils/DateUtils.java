package com.ryanhuii.tuitionfinder.utils;

import com.calendarfx.model.Entry;
import com.ryanhuii.tuitionfinder.model.Assignment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    public static List<LocalDate> getDatesForDayOfWeek(String dayOfWeek, int months) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < months; i++) {
            LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

            DayOfWeek desiredDayOfWeek = DayOfWeek.valueOf(dayOfWeek.toUpperCase());

            LocalDate tempDate = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(desiredDayOfWeek));
            while (!tempDate.isAfter(lastDayOfMonth)) {
                dates.add(tempDate);
                tempDate = tempDate.with(TemporalAdjusters.next(desiredDayOfWeek));
            }
            currentDate = currentDate.plusMonths(1); // Move to the next month
        }

        return dates;
    }

    public static List<Entry<String>> createEntriesFromLessonDay(String subject, String day, int duration) {
        List<Entry<String>> entries = new ArrayList<>();
        List<LocalDate> dates = DateUtils.getDatesForDayOfWeek(day,duration);
        System.out.println("We found " + dates.size() + " dates");
        for (LocalDate date : dates) {
            Entry<String> entry = new Entry<>(subject + " tuition");
            entry.setFullDay(true);
            entry.changeStartDate(date,true);
            entries.add(entry);
        }
        return entries;
    }

    public static List<Entry<String>> extractEntriesFromAllLessons(List<Assignment> myAssignments) {
        List<Entry<String>> allLessons = new ArrayList<>();
        for (Assignment assignment : myAssignments) {
            for (String day : assignment.getLessonSchedule()) {
                List<Entry<String>> lessonsOnThatDay = createEntriesFromLessonDay(assignment.getSubject(),day,assignment.getDuration());
                allLessons.addAll(lessonsOnThatDay);
            }
        }
        return allLessons;
    }
}
