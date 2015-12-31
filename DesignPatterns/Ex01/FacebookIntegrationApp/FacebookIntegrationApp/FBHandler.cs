using FacebookWrapper.ObjectModel;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading;

namespace FacebookIntegrationApp
{
    public class FBHandler
    {
        private static string m_preferredMonth = null;
        private static string m_preferredDay = null;
        private static string m_preferredHour = null;
        private static string[] s_WeekDayStringArray = { "Sunday", "Monday", "Tuesday", "Wenesday", "Thursday", "Friday", "Saturday" };
        private static string[] s_MonthNameStringArray = { "January", "February", "March", "April", "May", "June", 
                                                            "July","August","September","October","November","December"};


        private static volatile FBHandler instance;
        private static object syncRoot = new Object();

        private FBHandler() { }

        public static FBHandler Instance
        {
            get
            {
                if (instance == null)
                {
                    lock (syncRoot)
                    {
                        if (instance == null)
                            instance = new FBHandler();
                    }
                }

                return instance;
            }
        }

        public FacebookObjectCollection<Status> UserStatuses { get; set; }
        public string StatusStatistics { get; set; }
        public bool Flag { get; private set; }

        public void CalcStatistics()
        {
            // Make sure we initiate this procedure only once
            if (m_preferredMonth == null || m_preferredHour == null || m_preferredDay == null)
            {

                // Init arrays as hash tables
                int[] hours = new int[24];
                int[] days = new int[7];
                int[] months = new int[12];
                foreach (var status in UserStatuses)
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
            StatusStatistics = finalStr;
            Flag = true;
        }

    }
}
