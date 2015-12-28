using System.Net;
using System.Text.RegularExpressions;
using System.Web.Script.Serialization;


namespace FacebookIntegrationApp
{
    class Horoscope
    {

        private string m_DailyHoroscopeContent;
        private string m_DailyHoroscopeTitle;
        private string[] m_NamesOfLucksign = { "capricorn", "aquarius", "pisces", "aries", "taurus", "gemini", "cancer", "leo",
                                                "virgo", "libra", "scorpio", "sargittarius"};
        private string m_UrlOfAllLuckSign = "http://widgets.fabulously40.com/horoscope.json?sign=";
        public Horoscope(string i_BirthDay)
        {
            WebClient myWebClient = new WebClient();
            GenerateHoroscope(myWebClient, i_BirthDay);
        }

        private void GenerateHoroscope(WebClient i_myWebClient, string i_BirthDay)
        {
            string luckSign = CalcLuckySign(i_BirthDay);
            string json;
            using (WebClient wc = new WebClient())
            {
                json = wc.DownloadString(m_UrlOfAllLuckSign + luckSign);
            }

            JavaScriptSerializer j = new JavaScriptSerializer();

            horoscopeOutsideWrapper wrapper = j.Deserialize<horoscopeOutsideWrapper>(json);

            m_DailyHoroscopeTitle = Utilities.FirstLetterToUpperCase(wrapper.horoscope.sign);
            m_DailyHoroscopeContent = wrapper.horoscope.horoscope;

        }


        public string DailyHoroscopeContent
        {
            get
            {
                return m_DailyHoroscopeContent;
            }
        }
        public string DailyHoroscopeTitle {
            get
            {
                return m_DailyHoroscopeTitle;
            }
        }

        private string CalcLuckySign(string i_birthday)
        {
            string[] splitBirthdayToDayMounthYear = new string[3];
            splitBirthdayToDayMounthYear = Regex.Split(i_birthday, "/");
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
                    } else
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
