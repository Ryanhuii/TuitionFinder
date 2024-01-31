package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AssignmentApplicationService;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.utils.DateUtils;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class DashboardScheduleController {

    List<Assignment> myAssignments;

    @Autowired
    AssignmentApplicationService applicationService;
    @Autowired
    AssignmentService assignmentService;

    @FXML
    private Label titlePendingApplications;
    @FXML
    private Label btnLessons;
    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater(() -> vBoxFocus.requestFocus());

        // get all my applications, as well as the corresponding assignment it is attached to
        List<AssignmentApplication> pendingApplications = applicationService.getPendingApplications(TutorUtils.getTutor().getUid());
        List<Assignment> assignmentsAppliedTo = assignmentService.getAssignmentsAppliedTo(pendingApplications);
        titlePendingApplications.setText("Pending (" + assignmentsAppliedTo.size() + ")");

        getMyOngoingAssignments();
        setupCalendar();
    }

    private void setupCalendar() {
        // testing for calendar view
        CalendarView calendarView = new CalendarView(); // (1)
        Calendar assignmentCalender = new Calendar("assignments-lol"); // (2)
        //Entry<String> lesson = new Entry<>("Computing lesson");

        // get a list of calendar entries based on my lessons
        List<Entry<String>> lessons = DateUtils.extractEntries(myAssignments);
        System.out.println("lessons array size is: " + lessons.size());
        for (Entry<String> lesson : lessons) {
            assignmentCalender.addEntry(lesson);
        }

        assignmentCalender.setStyle(Calendar.Style.STYLE1); // (3)
        CalendarSource myCalendarSource = new CalendarSource("My Assignments"); // (4)
        myCalendarSource.getCalendars().addAll(assignmentCalender);

        calendarView.getCalendarSources().addAll(myCalendarSource); // (5)
        calendarView.setRequestedTime(LocalTime.now());

        // fix that visual bug with the week view
        calendarView.setPrefHeight(520);
        calendarView.setMinHeight(520);
        calendarView.setMaxHeight(520);
        calendarView.setPrefWidth(1150);
        calendarView.setMinWidth(1150);
        calendarView.setMaxWidth(1150);

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        vBoxFocus.getChildren().add(calendarView);
    }

    @FXML
    void onLessonsClicked(MouseEvent event) {
        TutorUtils.switchScene("dashboard-lessons.fxml",event,getClass()); // todo
    }

    private void getMyOngoingAssignments() {
        Optional<List<Assignment>> getOngoingAssignments = assignmentService.getTutorOngoingAssignments(TutorUtils.getTutor().getUid());
        getOngoingAssignments.ifPresent(assignments -> btnLessons.setText("Lessons (" + assignments.size() + ")"));
        myAssignments = getOngoingAssignments.get();
    }

    @FXML
    void onPendingApplicationsClicked(MouseEvent event) {
        TutorUtils.switchScene("dashboard-pending-applications.fxml",event,getClass());
    }

    @FXML
    void onDashboardClick(MouseEvent event) {
        TutorUtils.switchScene("dashboard-my-schedule.fxml",event,getClass());
    }

    @FXML
    void onFindAssignmentsClick(MouseEvent event) {
        TutorUtils.switchScene("find-assignments.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
