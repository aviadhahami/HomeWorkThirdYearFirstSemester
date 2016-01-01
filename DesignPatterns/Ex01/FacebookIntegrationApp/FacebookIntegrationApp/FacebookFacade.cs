using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    class FacebookFacade
    {
        private static User m_LoggedInUser;

        public FacebookFacade()
        {

        }
        public static void init(User i_LoggedInUser)
        {
            m_LoggedInUser = i_LoggedInUser;
        }

        public static FacebookObjectCollection<Album> Albums
        {
            get
            {
                return m_LoggedInUser.Albums;
            }
        }

        public static string Birthday
        {
            get
            {
                return m_LoggedInUser.Birthday;
            }
        }

        public static string FirstName
        {
            get
            {
                return m_LoggedInUser.FirstName;
            }
        }
        public static string PictureNormalURL
        {
            get
            {
                return m_LoggedInUser.PictureNormalURL;
            }
        }

        public static FacebookObjectCollection<Status> Statuses
        {
            get
            {
                return m_LoggedInUser.Statuses;
            }
        }

        public static void PostStatus(string io_status)
        {
            m_LoggedInUser.PostStatus(io_status);
        }
    }
}
