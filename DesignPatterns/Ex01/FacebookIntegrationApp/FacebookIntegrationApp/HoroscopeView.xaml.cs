using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for HoroscopeView.xaml
    /// </summary>
    public partial class HoroscopeView : Window
    {
        public HoroscopeView(string i_Birthday)
        {
            InitializeComponent();
            Horoscope clientHoroscope = new Horoscope(i_Birthday);
            HoroscopeContent.Text = clientHoroscope.DailyHoroscopeContent;
            HoroscopeSign.Text = clientHoroscope.DailyHoroscopeTitle;
        }
    }
}
