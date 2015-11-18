using FacebookWrapper;
using FacebookWrapper.ObjectModel;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace FacebookIntegrationApp
{
	/// <summary>
	/// Interaction logic for MainView.xaml
	/// </summary>
	public partial class LoginView : Window
	{
		
		public LoginView ()
		{
			InitializeComponent ();
		}

		private void Login (object sender, RoutedEventArgs e)
		{
			string appId = "1056989264331616";
			LoginResult result = FacebookWrapper.FacebookService.Login (appId,
				                     "user_about_me",
				                     "user_friends",
				                     "publish_actions",
				                     "user_events",
				                     "user_posts",
				                     "user_photos",
				                     "user_status",
                                     "user_birthday");
			if (!string.IsNullOrEmpty (result.AccessToken)) {

				// If we're good with the data, we pass it to the next view
				User LoggedInUser = result.LoggedInUser;
				MainView mainView = new MainView (LoggedInUser);
				mainView.Show ();
				this.Close ();
			} else {
				MessageBox.Show (result.ErrorMessage);
			}
		}
	}
}
