using System;
using System.Windows;
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

        public StatisticsView()
        {
            InitializeComponent();
            this.statisticsDataTextBox.Text = "Please wait while we calculate your statistics....";
            new Thread(splitTasks).Start();

        }

        private void splitTasks()
        {
            while (FBHandler.Flag)
            {
                Thread.CurrentThread.Join();
            }
            string StatisticsData = FBHandler.CalcStatistics(FacebookSingleton.Instance.Statuses);
            Application.Current.Dispatcher.BeginInvoke(new Action(() => this.statisticsDataTextBox.Text = StatisticsData));
        }
        public string StatisticsData { get; set; }
    }
}
