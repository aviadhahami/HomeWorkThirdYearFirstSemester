﻿using System;
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

        public AlbumView(Album m_selectedAlbum)
        {
            InitializeComponent();
            this.m_album = m_selectedAlbum;
            setImage(m_album.Photos[0]);
        }

        private void setImage(Photo photo)
        {
            currentImageImageBox.Source = new BitmapImage(new Uri(photo.PictureNormalURL));// photo.PictureNormalURL;//new BitmapImage(new Uri(photo.URL));
        }

        private void LeftArrowClick(object sender, MouseButtonEventArgs e)
        {

        }

        private void RightArrowClick(object sender, MouseButtonEventArgs e)
        {

        }
    }
}