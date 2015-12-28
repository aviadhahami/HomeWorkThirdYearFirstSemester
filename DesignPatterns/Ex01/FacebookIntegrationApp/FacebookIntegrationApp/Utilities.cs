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
            string parsedHour;
            if (hour < 12)
            {
                parsedHour = hour.ToString() + " AM";
            }
            else
            {
                parsedHour = (hour - 12).ToString() + " PM";
            }
            return parsedHour;
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
        internal static string FirstLetterToUpperCase(string io_word)
        {

            return io_word == null ? "" : char.ToUpper(io_word[0]) + io_word.Substring(1);

        }
    }
}
