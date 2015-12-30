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
using System.Text.RegularExpressions;
using System.Net;
using System.Threading;

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class MainView : Window
    {
        private User m_loggedInUser;

        public MainView(User LoggedInUser)
        {
            InitializeComponent();
            this.m_loggedInUser = LoggedInUser;
            init();

        }

        // Populate fields around the app
        private void init()
        {
            new Thread(fetchName).Start();
            new Thread(setNameForTitle).Start();
        }

        private void fetchName()
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() => UserName.Text = m_loggedInUser.FirstName));
        }

        private void setNameForTitle()
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() => ProfilePic.Source = new BitmapImage(new Uri(m_loggedInUser.PictureNormalURL))));
        }

        private void PostStatus(object sender, RoutedEventArgs e)
        {
            new Thread(sendStatus).Start();

        }

        private void sendStatus()
        {
            
            try
            {
                Status postedStatus;
                Application.Current.Dispatcher.BeginInvoke(new Action(() => postedStatus = m_loggedInUser.PostStatus(StatusText.Text)));
                MessageBox.Show("Posted!");
            }
            catch (Exception exeption)
            {
                MessageBox.Show("something went wrong!" + Environment.NewLine + exeption.Message);
            }
        }

        private void LuckFunction(object sender, RoutedEventArgs e)
        {

            HoroscopeView horoscopeView = new HoroscopeView(m_loggedInUser.Birthday);
            horoscopeView.Show();
        }

        private void PostStatistics(object sender, RoutedEventArgs e)
        {
            StatisticsView statisticsView = new StatisticsView(m_loggedInUser.Statuses);
            statisticsView.Show();
            //MessageBox.Show(FBHandler.PostStatistics(m_loggedInUser.Statuses));
        }

    }


}