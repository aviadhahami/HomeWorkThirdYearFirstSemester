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
        private Album m_selectedAlbum;

        public AlbumView(Album m_selectedAlbum)
        {
            InitializeComponent();
            this.m_selectedAlbum = m_selectedAlbum;

        }

        private void LeftArrowClick(object sender, MouseButtonEventArgs e)
        {

        }

        private void RightArrowClick(object sender, MouseButtonEventArgs e)
        {

        }
    }
}