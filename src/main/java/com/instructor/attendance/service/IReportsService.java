package com.instructor.attendance.service;

import com.instructor.attendance.paging.PageWrapper;

import java.time.Month;
import java.time.Year;

/**
 * Contains methods that generates several reports
 * @see ReportsService
 * @see PageWrapper
 */
public interface IReportsService {
    public PageWrapper getMonthlyAttendance(Month month, Year year, int pageNo, int pageSize);
}
