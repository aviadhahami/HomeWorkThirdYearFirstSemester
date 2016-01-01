using BasicFacebookFeatures.WithSingltonAppSettings;
using FacebookWrapper;
using FacebookWrapper.ObjectModel;
using System;
using System.Threading;
using System.Windows;
namespace FacebookIntegrationApp
{
    /// <summary>
    /// Interaction logic for MainView.xaml
    /// </summary>
    public partial class LoginView : Window
    {

        private User m_LoggedInUser;
        private MainView m_MainView;

        public LoginView()
        {
        }

        private void autoLogin()
        {
            try
            {
                LoginResult result = FacebookService.Connect(ApplicationSettings.Instance.AccessToken);
                Application.Current.Dispatcher.BeginInvoke(new Action(() => logAndGoToMainView(result)));
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

        private void onLoginClicked()
        {
            if (this.checkAutoLogIn.IsChecked.Value && ApplicationSettings.Instance.AccessToken != null)
            {
                new Thread(autoLogin).Start();
            }
            else
            {
                LoginResult result = ApplicationSettings.Instance.loginResult;
                if (result.AccessToken == null)
                {
                    return;
                }
                else {
                    logAndGoToMainView(result);
                }
            }
        }

        private void logAndGoToMainView(LoginResult result)
        {
            m_LoggedInUser = result.LoggedInUser;
            new FacebookFacade();
            FacebookFacade.init(m_LoggedInUser);
            m_MainView = new MainView();
            m_MainView.Show();
            this.Close();
        }

        private void Login(object sender, RoutedEventArgs e)
        {
            onLoginClicked();
        }
    }
}
