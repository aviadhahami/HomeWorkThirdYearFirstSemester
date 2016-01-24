using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using Facebook;
using FacebookWrapper.ObjectModel;
using System.Timers;
using System.Threading;

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class MainView : Window
    {
        private PhotoAlbum m_selectedAlbum;

        public MainView()

        {
            InitializeComponent();
            confirmationGrid.Visibility = Visibility.Hidden;
        }


        private void PostStatus(object sender, RoutedEventArgs e)
        {
            try
            {
                FacebookSingleton.Instance.PostStatus(StatusText.Text);
                showConfirmationSequence();
            }
            catch (FacebookApiException e1)
            {
                MessageBox.Show("something went wrong!" + Environment.NewLine + e1.Message);
            }
            catch (Exception exeption)
            {
                MessageBox.Show("something went wrong!" + Environment.NewLine + exeption.Message);
            }
        }

        private void LuckFunction(object sender, RoutedEventArgs e)
        {

            FacebookSingleton.Instance.GenerateHoroscope();
        }

        private void PostStatistics(object sender, RoutedEventArgs e)
        {

            FacebookSingleton.Instance.Statistics();
        }

        private void AlbumSelectionChangeListener(object sender, SelectionChangedEventArgs e)
        {
            Album album = ((sender as ListBox).SelectedItem as Album);
            m_selectedAlbum = new PhotoAlbum(album.Name, album.PictureSmallURL, album.Comment, album.Like);
            albumCommentTextBox.Text = "Add album comment";
        }

        private void LikeAlbumButtonClickListener(object sender, RoutedEventArgs e)
        {
            if (m_selectedAlbum != null)
            {
                Application.Current.Dispatcher.BeginInvoke(new Action(() => m_selectedAlbum.Like()));
                showConfirmationSequence();
            }
        }

        private void AddAlbumCommentClickListener(object sender, RoutedEventArgs e)
        {

            if (albumCommentTextBox.Text != "" && m_selectedAlbum != null)
            {
                Application.Current.Dispatcher.BeginInvoke(new Action(() =>
                {
                    m_selectedAlbum.Comment(albumCommentTextBox.Text);
                    showConfirmationSequence();
                }));

            }
        }

        private void showConfirmationSequence()
        {
            confirmationGrid.Visibility = Visibility.Visible;
        }

        private void ListViewMouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            Album selectedAlbum = albumListListView.SelectedItem as Album;
            if (selectedAlbum.Photos.Count > 0)
            {
                // We now populate the album with photos from the real facebook api album
                foreach (FacebookWrapper.ObjectModel.Photo pic in selectedAlbum.Photos)
                {
                    m_selectedAlbum.Add(new Photo(pic.Name, pic.PictureNormalURL, pic.Comment, pic.Like));
                }

                AlbumView albumView = new AlbumView(m_selectedAlbum);
                albumView.Show();
            }
            else
            {
                MessageBox.Show("No photos to display");
            }

        }

        private void ClearSucessNotification(object sender, MouseButtonEventArgs e)
        {
            confirmationGrid.Visibility = Visibility.Hidden;

        }
    }
}