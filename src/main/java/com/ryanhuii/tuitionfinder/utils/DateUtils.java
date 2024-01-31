package com.ryanhuii.tuitionfinder.utils;

import com.calendarfx.model.Entry;
import com.ryanhuii.tuitionfinder.model.Assignment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    public static List<LocalDate> getDatesForDayOfWeekInCurrentMonth(String dayOfWeek) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

        DayOfWeek desiredDayOfWeek = DayOfWeek.valueOf(dayOfWeek.toUpperCase());

        LocalDate tempDate = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(desiredDayOfWeek));
        while (!tempDate.isAfter(lastDayOfMonth)) {
            dates.add(tempDate);
            tempDate = tempDate.with(TemporalAdjusters.next(desiredDayOfWeek));
        }

        return dates;
    }

    public static List<Entry<String>> createEntries(String subject, String day) {
        List<Entry<String>> entries = new ArrayList<>();
        List<LocalDate> dates = DateUtils.getDatesForDayOfWeekInCurrentMonth(day);
        System.out.println("We found " + dates.size() + " dates");
        for (LocalDate date : dates) {
            Entry<String> entry = new Entry<>(subject);
            entry.setFullDay(true);
            entry.changeStartDate(date,true);
            entries.add(entry);
        }
        return entries;
    }

    public static List<Entry<String>> extractEntries(List<Assignment> myAssignments) {
        List<Entry<String>> allLessons = new ArrayList<>();
        for (Assignment assignment : myAssignments) {
            for (String day : assignment.getLessonSchedule()) {
                List<Entry<String>> lessonsOnThatDay = createEntries(assignment.getSubject(),day);
                allLessons.addAll(lessonsOnThatDay);
            }
        }
        return allLessons;
    }
}
