using System;
using System.Collections.Generic;
using System.Text;

namespace FacebookIntegrationApp
{
    public class horoscopeOutsideWrapper
    {
        public horoscopeInnerWrapper horoscope
        {
            get;
            set;
        }
    }

    public class horoscopeInnerWrapper
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
