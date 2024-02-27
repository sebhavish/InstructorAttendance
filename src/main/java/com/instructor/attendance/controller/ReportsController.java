package com.instructor.attendance.controller;

import com.instructor.attendance.paging.PageWrapper;
import com.instructor.attendance.paging.PagingConstants;
import com.instructor.attendance.service.IReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    @Autowired
    IReportsService reportsService;

    @GetMapping("/monthly")
    public ResponseEntity<PageWrapper> getMonthlyAttendance(
            @RequestParam("month") Month month,
            @RequestParam("year") Year year,
            @RequestParam(value = "pageNo", defaultValue = PagingConstants.DEFAULT_PAGE_NO) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PagingConstants.DEFAULT_PAGE_SIZE) int pageSize) {
        PageWrapper attendanceReport = reportsService.getMonthlyAttendance(month, year, pageNo, pageSize);
        return new ResponseEntity<>(attendanceReport, HttpStatus.OK);
    }
}
