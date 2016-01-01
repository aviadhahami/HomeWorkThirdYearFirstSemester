using FacebookWrapper.ObjectModel;
using System.Collections.Generic;

namespace FacebookIntegrationApp
{
    class FacebookFacade
    {
        private static User m_LoggedInUser;

        public FacebookFacade(User i_LoggedInUser)
        {
            m_LoggedInUser = i_LoggedInUser;
        }

        public static List<Album> Albums { get; set; }
        public static string UserFirstName
        {
            get
            {
                return m_LoggedInUser.FirstName;
            }
        }
    }
}
