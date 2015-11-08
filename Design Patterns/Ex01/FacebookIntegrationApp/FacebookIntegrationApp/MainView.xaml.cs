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
            Status postedStatus = m_loggedInUser.PostStatus(statusText);
            MessageBox.Show("Posted! id: " + postedStatus.Id);
        }

        private void PickASongFunction(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("pick a song clicked");
        }

        private void WinterFunction(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Winter's button clicked");
        }
    }
}
