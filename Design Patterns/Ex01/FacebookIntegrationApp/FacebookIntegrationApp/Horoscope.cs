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

        private void GenerateHoroscope(WebClient o_myWebClient, string o_BirthDay)
        {
            string luckSign = CalcLuckySign(o_BirthDay);
            string json;
            using (WebClient wc = new WebClient())
            {
                json = wc.DownloadString(m_UrlOfAllLuckSign + luckSign);
            }

            JavaScriptSerializer j = new JavaScriptSerializer();

            HoroscopeOutsideWrapper wrapper = j.Deserialize<HoroscopeOutsideWrapper>(json);

            m_DailyHoroscopeTitle = wrapper.Horoscope.sign;
            m_DailyHoroscopeContent = wrapper.Horoscope.horoscope;

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

        private string CalcLuckySign(string o_birthday)
        {
            string[] splitBirthdayToDayMounthYear = new string[3];
            splitBirthdayToDayMounthYear = Regex.Split(o_birthday, "/");
            int day = LoseTheStartZero(splitBirthdayToDayMounthYear[1]);
            int month = LoseTheStartZero(splitBirthdayToDayMounthYear[0]);
            int midleOfMonth = 20;
            string symbleNumberOfLuck = "";
            int[] dayToReduceFromEachMonth = { 1, 2, 0, 1, 0, 0, -2, -2, -2, -2, -1, -1 };
            for (int i = 1; i < 13; i++)
            {
                if (month == i)
                {
                    symbleNumberOfLuck = (day < (midleOfMonth - dayToReduceFromEachMonth[i])) ? m_NamesOfLucksign[i] : m_NamesOfLucksign[i + 1];
                }
            }
            return symbleNumberOfLuck;
        }

        private int LoseTheStartZero(string o_DayMothYear)
        {
            int numberWithoutZero = 0;
            if (o_DayMothYear[0] == 0)
            {
                numberWithoutZero = int.Parse(o_DayMothYear[1].ToString());
            }
            else
            {
                numberWithoutZero = int.Parse(o_DayMothYear);
            }

            return numberWithoutZero;
        }
    }
}
