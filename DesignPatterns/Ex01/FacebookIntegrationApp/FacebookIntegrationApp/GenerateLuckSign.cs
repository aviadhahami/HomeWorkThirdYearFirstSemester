using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace FacebookIntegrationApp
{
    class GenerateLuckSign
    {
        private string m_Birthday;
        private string[] m_NamesOfLucksign = { "capricorn", "aquarius", "pisces", "aries", "taurus", "gemini", "cancer", "leo",
                                                "virgo", "libra", "scorpio", "sargittarius"};

        public GenerateLuckSign(string i_Birthday)
        {
            m_Birthday = i_Birthday;
        }

        internal HoroscopeFacade HoroscopeFacade
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        public string CalcLuckySign()
        {
            string[] splitBirthdayToDayMounthYear = new string[3];
            splitBirthdayToDayMounthYear = Regex.Split(m_Birthday, "/");
            int day = LoseTheStartZero(splitBirthdayToDayMounthYear[1]);
            int month = LoseTheStartZero(splitBirthdayToDayMounthYear[0]);
            int midleOfMonth = 20;
            string symbleNumberOfLuck = "";
            int[] dayToReduceFromEachMonth = { 1, 2, 0, 1, 0, 0, -2, -2, -2, -2, -1, -1 };
            for (int i = 1; i < 13; i++)
            {
                bool dayBiggerThanMiddle = day < (midleOfMonth - dayToReduceFromEachMonth[i - 1]);
                bool ifLuckyMonth = month == i;
                bool iflastMonth = i == 12;
                if (ifLuckyMonth)
                {
                    if (iflastMonth)
                    {
                        symbleNumberOfLuck = (dayBiggerThanMiddle) ? m_NamesOfLucksign[i - 1] : m_NamesOfLucksign[0];
                    }
                    else
                    {
                        symbleNumberOfLuck = (dayBiggerThanMiddle) ? m_NamesOfLucksign[i - 1] : m_NamesOfLucksign[i];
                    }
                }
            }
            return symbleNumberOfLuck;
        }

        private int LoseTheStartZero(string i_DayMothYear)
        {
            int numberWithoutZero = 0;
            bool ifZeroBeforeNumber = i_DayMothYear[0] == 0;
            if (ifZeroBeforeNumber)
            {
                numberWithoutZero = int.Parse(i_DayMothYear[1].ToString());
            }
            else
            {
                numberWithoutZero = int.Parse(i_DayMothYear);
            }

            return numberWithoutZero;
        }
    }

}

