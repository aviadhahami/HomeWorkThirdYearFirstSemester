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
using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for AlbumView.xaml
    /// </summary>
    public partial class AlbumView : Window
    {
        private Album m_album;
        private int m_PhotoIndex;

        public AlbumView(Album m_selectedAlbum)
        {
            InitializeComponent();
            this.m_album = m_selectedAlbum;
            m_PhotoIndex = 0;
            updateImageAndIndexIndicator();
        }

        private void updateImage()
        {
            currentImageImageBox.Source = new BitmapImage(new Uri(m_album.Photos[m_PhotoIndex].PictureNormalURL));// photo.PictureNormalURL;//new BitmapImage(new Uri(photo.URL));
        }

        //This part we go down in images count
        private void LeftArrowClick(object sender, MouseButtonEventArgs e)
        {
            m_PhotoIndex = m_PhotoIndex <= 0 ? 0 : m_PhotoIndex - 1;
            updateImageAndIndexIndicator();

        }

        //This part we go up in images count
        private void RightArrowClick(object sender, MouseButtonEventArgs e)
        {
            m_PhotoIndex = m_PhotoIndex == m_album.Count - 1 ? m_PhotoIndex : m_PhotoIndex + 1;
            updateImageAndIndexIndicator();
        }

        private void updateImageAndIndexIndicator()
        {
            updateImage();
            updateIndexIndicator();
        }

        private void updateIndexIndicator()
        {
            amountIndicatorLabel.Content = (m_PhotoIndex + 1) + " / " + m_album.Count;
        }

        private void photoLikeClicked(object sender, MouseButtonEventArgs e)
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() => m_album.Photos[m_PhotoIndex].Like()));
        }

        private void photoCommentClicked(object sender, RoutedEventArgs e)
        {
            string commentInput = photoCommentTextBox.Text;
            if (commentInput.Length > 0)
            {
                Application.Current.Dispatcher.BeginInvoke(new Action(() => m_album.Photos[m_PhotoIndex].Comment(commentInput)));
            }
            else
            {
                MessageBox.Show("No comment was submitted");
            }
        }
    }
}