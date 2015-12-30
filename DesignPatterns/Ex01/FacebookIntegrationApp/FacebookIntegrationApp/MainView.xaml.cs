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
        private Album m_selectedAlbum;

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
            new Thread(fetchPhotoAlbums).Start();
        }

        private void fetchPhotoAlbums()
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() => albumListListView.ItemsSource = m_loggedInUser.Albums));
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
            sendStatus();

        }

        private void sendStatus()
        {

            try
            {
                m_loggedInUser.PostStatus(StatusText.Text);
                MessageBox.Show("Posted!");

            }
            catch (FacebookApiException e)
            {
                MessageBox.Show("something went wrong!" + Environment.NewLine + e.Message);
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
        }

        private void AlbumSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            m_selectedAlbum = ((sender as ListBox).SelectedItem as Album);
            albumCommentTextBox.Text = "Add album comment";
        }

        private void LikeAlbumButtonClick(object sender, RoutedEventArgs e)
        {
            if (m_selectedAlbum != null)
            {
                Application.Current.Dispatcher.BeginInvoke(new Action(() => m_selectedAlbum.Like()));

            }
        }

        private void AddAlbumCommentClick(object sender, RoutedEventArgs e)
        {

            if (albumCommentTextBox.Text != "" && m_selectedAlbum != null)
            {
                Application.Current.Dispatcher.BeginInvoke(new Action(() => m_selectedAlbum.Comment(albumCommentTextBox.Text)));

            }
        }
        private void ListView_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {

            AlbumView albumView = new AlbumView(m_selectedAlbum);
            albumView.Show();
        }
    }
}