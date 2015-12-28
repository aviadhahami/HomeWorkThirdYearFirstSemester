using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;

namespace FacebookIntegrationApp
{
    class Utilities
    {
        // Convert hour string in 24 hrs format to 12 hrs format
        public static string HoursToAmPm(int hour)
        {
            return DateTime.ParseExact(hour.ToString(), "HH", CultureInfo.CurrentCulture).ToString("hh:mm tt");
        }

        internal static int FindIndexOfMaxValueInArray(int[] i_Array)
        {
            int maxIndex = 0;
            int maxValue = 0;
            for (int i = 0; i < i_Array.Length; i++)
            {
                if (maxValue < i_Array[i])
                {
                    maxValue = i_Array[i];
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
    }
}
