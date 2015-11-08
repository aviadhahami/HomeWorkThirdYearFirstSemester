using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Facebook;
using FacebookWrapper;
using FacebookWrapper.ObjectModel;
using System.Globalization;

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class MainView : Window
    {
        private User m_loggedInUser;

        private string m_preferredMonth = null;
        private string m_preferredDay = null;
        private string m_preferredHour = null;
        private static string[] s_WeekDayStringArray = { "Sunday", "Monday", "Tuesday", "Wenesday", "Thursday", "Friday", "Saturday" };
        private static string[] s_MonthNameStringArray = { "January", "February", "March", "April", "May", "June", 
                                                            "July","August","September","October","November","December"};



        public MainView(User LoggedInUser)
        {
            InitializeComponent();
            this.m_loggedInUser = LoggedInUser;
            init();

        }


        // Populate fields around the app
        private void init()
        {
            // Fetch photo
            ProfilePic.Source = new BitmapImage(new Uri(m_loggedInUser.PictureNormalURL));

            // Fetch name for title
            UserName.Text = m_loggedInUser.FirstName;
        }

        private void PostStatus(object sender, RoutedEventArgs e)
        {
            String statusText = StatusText.Text;
            try
            {
                Status postedStatus = m_loggedInUser.PostStatus(statusText);
                MessageBox.Show("Posted! id: " + postedStatus.Id);
            }
            catch (Exception exeption)
            {
                MessageBox.Show("something went wrong!" + Environment.NewLine + exeption.Message);
            }

        }

        private void PickASongFunction(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("pick a song clicked");
        }

        private void PostStatistics(object sender, RoutedEventArgs e)
        {
            if (m_preferredMonth == null || m_preferredHour == null || m_preferredDay == null)
            {


                int[] hours = new int[24];
                int[] days = new int[7];
                int[] months = new int[12];
                foreach (var status in m_loggedInUser.Statuses)
                {
                    DateTime time = Convert.ToDateTime(status.CreatedTime);
                    days[(int)time.DayOfWeek] += status.LikedBy.Count;
                    hours[time.Hour] += status.LikedBy.Count;
                    months[time.Month] += status.LikedBy.Count;
                }

                m_preferredDay = s_WeekDayStringArray[findIndexOfMaxValueInArray(days)];
                m_preferredMonth = s_MonthNameStringArray[findIndexOfMaxValueInArray(months)];
                m_preferredHour = hoursToAmPm(findIndexOfMaxValueInArray(hours));
            }

            MessageBox.Show(
                "According to your statuses statistics we found that:" + Environment.NewLine +
                "Usualy on " + m_preferredDay + "s you get the most likes." + Environment.NewLine +
                "Time-wise, " + m_preferredHour + " is the best time" + Environment.NewLine +
                "and " + m_preferredMonth + " is the best month."
                );
        }

        private string hoursToAmPm(int hour)
        {
            return DateTime.ParseExact(hour.ToString(), "HH", CultureInfo.CurrentCulture).ToString("hh:mm tt");
        }

        private int findIndexOfMaxValueInArray(int[] days)
        {
            int maxIndex = 0;
            int maxValue = 0;
            for (int i = 0; i < days.Length; i++)
            {
                if (maxValue < days[i])
                {
                    maxValue = days[i];
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
    }
}
