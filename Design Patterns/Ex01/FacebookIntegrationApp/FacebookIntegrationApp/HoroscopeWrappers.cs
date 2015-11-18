using System;
using System.Collections.Generic;
using System.Text;

namespace FacebookIntegrationApp
{
    public class HoroscopeOutsideWrapper
    {
        public HoroscopeInnerWrapper horoscope
        {
            get;
            set;
        }
    }

    public class HoroscopeInnerWrapper
    {
        public string sign
        {
            get;
            set;
        }

        public string horoscope
        {
            get;
            set;
        }
    }
}
