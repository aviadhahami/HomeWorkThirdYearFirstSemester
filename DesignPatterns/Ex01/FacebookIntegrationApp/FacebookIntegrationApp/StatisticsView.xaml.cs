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
    /// Interaction logic for StatisticsView.xaml
    /// </summary>
    public partial class StatisticsView : Window
    {
        //private FacebookObjectCollection<Status> statuses;

        public StatisticsView(FacebookObjectCollection<Status> statuses)
        {
            InitializeComponent();
            this.statisticsDataTextBox.Text = "Please wait while we calculate your statistics....";
            Application.Current.Dispatcher.BeginInvoke(new Action(() => getStatistics(statuses)));

        }

        private void getStatistics(FacebookObjectCollection<Status> i_statuses)
        {
            StatisticsData = FBHandler.PostStatistics(i_statuses);
            this.statisticsDataTextBox.Text = StatisticsData;
        }

        public string StatisticsData { get; set; }
    }
}
