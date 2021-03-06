﻿using FacebookWrapper.ObjectModel;
using System;

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

        static FBHandler() { }
        public static bool Flag { get; private set; }

        public static string CalcStatistics(FacebookObjectCollection<Status> i_UserStatuses)
        {
            Flag = false;
            // Make sure we initiate this procedure only once
            if (m_preferredMonth == null || m_preferredHour == null || m_preferredDay == null)
            {

                // Init arrays as hash tables
                int[] hours = new int[24];
                int[] days = new int[7];
                int[] months = new int[12];
                foreach (var status in i_UserStatuses)
                {
                    DateTime time = Convert.ToDateTime(status.CreatedTime);
                    days[(int)time.DayOfWeek] += status.LikedBy.Count;
                    hours[time.Hour] += status.LikedBy.Count;
                    months[time.Month-1] += status.LikedBy.Count;
                }

                m_preferredDay = s_WeekDayStringArray[Utilities.FindIndexOfMaxValueInArray(days)];
                m_preferredMonth = s_MonthNameStringArray[Utilities.FindIndexOfMaxValueInArray(months)];
                m_preferredHour = Utilities.HoursToAmPm(Utilities.FindIndexOfMaxValueInArray(hours));
            }

            // Generate final string
            string finalStr = "According to your statuses statistics we found that:" + Environment.NewLine +
                    "Usualy on " + m_preferredDay + "s you get the most likes." + Environment.NewLine +
                    "Time-wise, " + m_preferredHour + " is the best time" + Environment.NewLine +
                    "and " + m_preferredMonth + " is the best month.";
            Flag = false;

            return finalStr;
        }

    }
}
