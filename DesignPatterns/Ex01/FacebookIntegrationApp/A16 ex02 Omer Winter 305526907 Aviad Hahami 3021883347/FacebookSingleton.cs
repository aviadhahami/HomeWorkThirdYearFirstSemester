using FacebookWrapper.ObjectModel;
using System;

namespace FacebookIntegrationApp
{
    class FacebookSingleton
    {
        private static User m_LoggedInUser;


        private static volatile FacebookSingleton instance;
        private static object syncRoot = new Object();


        public static FacebookSingleton Instance
        {
            get
            {
                if (instance == null)
                {
                    lock (syncRoot)
                    {
                        if (instance == null)
                            instance = new FacebookSingleton();
                    }
                }

                return instance;
            }
        }

        public User LoginUser
        {
            set
            {
                m_LoggedInUser = value;
            }
        }

        public FacebookObjectCollection<Album> Albums
        {
            get
            {
                return m_LoggedInUser.Albums;
            }
        }

        public string Birthday
        {
            get
            {
                return m_LoggedInUser.Birthday;
            }
        }

        public string FirstName
        {
            get
            {
                return m_LoggedInUser.FirstName;
            }
        }
        public string PictureNormalURL
        {
            get
            {
                return m_LoggedInUser.PictureNormalURL;
            }
        }

        public FacebookObjectCollection<Status> Statuses
        {
            get
            {
                return m_LoggedInUser.Statuses;
            }
        }

        public HoroscopeView HoroscopeView
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        public StatisticsView StatisticsView
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        internal HoroscopeFacade HoroscopeFacade
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        internal FacebookSingleton FacebookSingleton1
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        public void PostStatus(string io_status)
        {
            m_LoggedInUser.PostStatus(io_status);
        }

        public void Statistics()
        {
            StatisticsView statisticsView = new StatisticsView();
            statisticsView.Show();
        }

        public void GenerateHoroscope()
        {
            HoroscopeView horoscopeView = new HoroscopeView(FacebookIntegrationApp.FacebookSingleton.Instance.Birthday);
            horoscopeView.Show();
        }
    }
}
