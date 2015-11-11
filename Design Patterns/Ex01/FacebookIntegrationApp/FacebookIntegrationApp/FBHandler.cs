﻿using FacebookWrapper.ObjectModel;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;

namespace FacebookIntegrationApp
{
    public static class FBHandler
    {
        private static string m_preferredMonth = null;
        private static string m_preferredDay = null;
        private static string m_preferredHour = null;
        private static string[] s_WeekDayStringArray = { "Sunday", "Monday", "Tuesday", "Wenesday", "Thursday", "Friday", "Saturday" };
        private static string[] s_MonthNameStringArray = { "January", "February", "March", "April", "May", "June", 
                                                            "July","August","September","October","November","December"};
        public static string PostStatistics(FacebookObjectCollection<Status> i_Statuses)
        {
            // Make sure we initiate this procedure only once
            if (m_preferredMonth == null || m_preferredHour == null || m_preferredDay == null)
            {

                // Init arrays as hash tables
                int[] hours = new int[24];
                int[] days = new int[7];
                int[] months = new int[12];
                foreach (var status in i_Statuses)
                {
                    DateTime time = Convert.ToDateTime(status.CreatedTime);
                    days[(int)time.DayOfWeek] += status.LikedBy.Count;
                    hours[time.Hour] += status.LikedBy.Count;
                    months[time.Month] += status.LikedBy.Count;
                }

                m_preferredDay = s_WeekDayStringArray[findIndexOfMaxValueInArray(days)];
                m_preferredMonth = s_MonthNameStringArray[findIndexOfMaxValueInArray(months)];
                m_preferredHour = hoursToAmPm(findIndexOfMaxValueInArray(hours));
            }
            string finalStr = "According to your statuses statistics we found that:" + Environment.NewLine +
                    "Usualy on " + m_preferredDay + "s you get the most likes." + Environment.NewLine +
                    "Time-wise, " + m_preferredHour + " is the best time" + Environment.NewLine +
                    "and " + m_preferredMonth + " is the best month.";
            return finalStr;
        }
        // Convert hour string in 24 hrs format to 12 hrs format
        private static string hoursToAmPm(int hour)
        {
            return DateTime.ParseExact(hour.ToString(), "HH", CultureInfo.CurrentCulture).ToString("hh:mm tt");
        }

        private static int findIndexOfMaxValueInArray(int[] days)
        {
            int maxIndex = 0;
            int maxValue = 0;
            for (int i = 0; i < days.Length; i++)
            {
                if (maxValue < days[i])
                {
                    maxValue = days[i];
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
    }
}
