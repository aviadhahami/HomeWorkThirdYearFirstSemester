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

            MessageBox.Show(FBHandler.PostStatistics(m_loggedInUser.Statuses));
        }

    }
}
