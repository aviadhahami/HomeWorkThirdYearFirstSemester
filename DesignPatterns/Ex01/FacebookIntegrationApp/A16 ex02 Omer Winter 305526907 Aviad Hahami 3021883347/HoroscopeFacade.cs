using System.Net;
using System.Text.RegularExpressions;
using System.Web.Script.Serialization;


namespace FacebookIntegrationApp
{
    class HoroscopeFacade
    {
        private GenerateLuckSign m_LuckSign;
        private LuckInfo m_LuckInfo;

        public HoroscopeFacade(string i_Birthday)
        {
            m_LuckSign = new GenerateLuckSign(i_Birthday);
            m_LuckInfo = new LuckInfo(m_LuckSign.CalcLuckySign());
        }

        public string HoroscopeInfo
        {
            get 
            {
                return m_LuckInfo.DailyHoroscopeContent;
            }
        }

        public string HoroscopeSing
        {
            get
            {
                return m_LuckInfo.DailyHoroscopeTitle;
            }
        }

    }
}
