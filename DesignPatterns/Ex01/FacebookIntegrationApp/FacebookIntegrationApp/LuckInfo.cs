using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Web.Script.Serialization;

namespace FacebookIntegrationApp
{
    class LuckInfo
    {
        private WebClient m_MyWebClient;
        private string m_LuckSign;
        private string m_UrlOfAllLuckSign = "http://widgets.fabulously40.com/horoscope.json?sign=";

        public LuckInfo(string i_LuckSign)
        {
            m_LuckSign = i_LuckSign;
            m_MyWebClient = new WebClient();
        }
        private void GenerateHoroscope()
        { 
            string json;
            using (WebClient wc = new WebClient())
            {
                json = wc.DownloadString(m_UrlOfAllLuckSign + m_LuckSign);
            }

            JavaScriptSerializer j = new JavaScriptSerializer();

            horoscopeOutsideWrapper wrapper = j.Deserialize<horoscopeOutsideWrapper>(json);

            m_DailyHoroscopeTitle = Utilities.FirstLetterToUpperCase(wrapper.horoscope.sign);
            m_DailyHoroscopeContent = wrapper.horoscope.horoscope;

        }

        private string m_DailyHoroscopeContent;
        private string m_DailyHoroscopeTitle;

        public string DailyHoroscopeContent
        {
            get
            {
                return m_DailyHoroscopeContent;
            }
        }
        public string DailyHoroscopeTitle
        {
            get
            {
                return m_DailyHoroscopeTitle;
            }
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
    }
}
