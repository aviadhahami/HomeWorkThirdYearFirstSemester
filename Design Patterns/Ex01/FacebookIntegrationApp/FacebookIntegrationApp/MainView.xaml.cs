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

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class MainView : Window
    {
        public MainView()
        {
            InitializeComponent();
            // Need to call FB here
        }

        private void PostStatus(object sender, RoutedEventArgs e)
        {
            String statusText = StatusText.Text;
            MessageBox.Show(statusText);
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
