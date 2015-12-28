using BasicFacebookFeatures.WithSingltonAppSettings;
using FacebookWrapper;
using FacebookWrapper.ObjectModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using System.Threading;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace FacebookIntegrationApp
{
	/// <summary>
	/// Interaction logic for MainView.xaml
	/// </summary>
	public partial class LoginView : Window
	{

        private User m_LoggedInUser;
        private MainView m_MainView;

        public LoginView ()
		{
            Loaded += Window_Loaded;
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            this.RenderSize = ApplicationSettings.Instance.LastWindowSize;
            this.WindowState = ApplicationSettings.Instance.LastWindowState;
            this.Left = ApplicationSettings.Instance.LastWindowLocation.X;
            this.Top = ApplicationSettings.Instance.LastWindowLocation.Y;
            this.checkAutoLogIn.IsChecked = ApplicationSettings.Instance.AutoLogin;

            if (ApplicationSettings.Instance.AutoLogin)
            {
                new Thread(autoLogin).Start();
            }
        }

        private void autoLogin()
        {
            try
            {
                LoginResult result = FacebookService.Connect(ApplicationSettings.Instance.AccessToken);
                Application.Current.Dispatcher.BeginInvoke(new Action (() => logAndGoToMainView(result)));
            }
            catch (Exception ex)
            {
                ///(OAuthException - #190) Error validating access token: Session has expired..
                MessageBox.Show(ex.Data.ToString());
                if (ex.Message.Contains("#190"))
                {
                    Application.Current.Dispatcher.BeginInvoke((Action)onLoginClicked);
                }
                else
                {
                    Application.Current.Dispatcher.BeginInvoke(new Action(() => MessageBox.Show("Could not connect to Facebook server. Please try again later..")));
                }
            }
        }

    

        private void logAndGoToMainView(LoginResult result)
        {
            m_LoggedInUser = result.LoggedInUser;
            m_MainView = new MainView(m_LoggedInUser);
            m_MainView.Show();
            this.Close();
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);
            ApplicationSettings.Instance.LastWindowState = this.WindowState;
            ApplicationSettings.Instance.LastWindowSize = this.RenderSize;
            ApplicationSettings.Instance.LastWindowLocation = new Point(this.Left, this.Top); 
            ApplicationSettings.Instance.AutoLogin = this.checkAutoLogIn.IsChecked.Value;
            ApplicationSettings.Instance.Save();
        }


        private void Login (object sender, RoutedEventArgs e)
		{
            onLoginClicked();
		}
        private void onLoginClicked()
        {
            string appId = "1056989264331616";
            LoginResult result = FacebookWrapper.FacebookService.Login(appId,
                                     "user_about_me",
                                     "user_friends",
                                     "publish_actions",
                                     "user_events",
                                     "user_posts",
                                     "user_photos",
                                     "user_status",
                                     "user_birthday");
            if (!string.IsNullOrEmpty(result.AccessToken))
            {

                // If we're good with the data, we pass it to the next view
                ApplicationSettings.Instance.AccessToken = result.AccessToken;
                logAndGoToMainView(result);
            }
            else
            {
                MessageBox.Show(result.ErrorMessage);
            }
        }
    }
}
