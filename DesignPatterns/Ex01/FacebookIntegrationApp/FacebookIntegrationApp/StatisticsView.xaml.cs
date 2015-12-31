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
using System.Threading;

namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for StatisticsView.xaml
    /// </summary>
    public partial class StatisticsView : Window
    {
        //private FacebookObjectCollection<Status> statuses;

        //private string m_StatisticsData;

        public StatisticsView(FacebookObjectCollection<Status> i_Statuses)
        {
            InitializeComponent();
            FBHandler.Instance.UserStatuses = i_Statuses;
            this.statisticsDataTextBox.Text = "Please wait while we calculate your statistics....";
            new Thread(splitTasks).Start();
            //StatisticsData = FBHandler.Instance.StatusStatistics;
        }

        private void splitTasks()
        {
            FBHandler.Instance.CalcStatistics();
            while (FBHandler.Instance.Flag)
            {
                Thread.Sleep(1000);
            }
            StatisticsData = FBHandler.Instance.StatusStatistics;
            Application.Current.Dispatcher.BeginInvoke(new Action(() => this.statisticsDataTextBox.Text = StatisticsData));
        }
        public string StatisticsData { get; set; }
    }
}
