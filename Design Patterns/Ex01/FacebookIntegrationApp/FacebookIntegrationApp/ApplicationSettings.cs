using System.Drawing;
using System.IO;
using System.Windows;
using System.Xml.Serialization;

namespace BasicFacebookFeatures.WithSingltonAppSettings
{
    public class ApplicationSettings
    {
        private static readonly string sr_FileName;

        static ApplicationSettings()
        {
            sr_FileName = System.Reflection.Assembly.GetExecutingAssembly().Location + ".settings.xml";
        }

       
        private ApplicationSettings()
        {
        }

       
        private static ApplicationSettings s_This;
       
        public static ApplicationSettings Instance
        {
            get
            {
                if (s_This == null)
                {
                    s_This = ApplicationSettings.FromFileOrDefault();
                }

                return s_This;
            }
        }

       
        public bool AutoLogin { get; set; }
        public System.Windows.Size LastWindowSize { get; set; }
        public WindowState LastWindowState { get; set; }
        public System.Windows.Point LastWindowLocation { get; set; }
        public string AccessToken { get; set; }

        public void Save()
        {
            using (FileStream stream = new FileStream(sr_FileName, FileMode.Create))
            {
                XmlSerializer serializer = new XmlSerializer(typeof(ApplicationSettings));
                serializer.Serialize(stream, this);
            }
        }

        public static ApplicationSettings FromFileOrDefault()
        {
            ApplicationSettings loadedThis = null;

            if (File.Exists(sr_FileName))
            {
                using (FileStream stream = new FileStream(sr_FileName, FileMode.OpenOrCreate))
                {
                    XmlSerializer serializer = new XmlSerializer(typeof(ApplicationSettings));
                    loadedThis = (ApplicationSettings)serializer.Deserialize(stream);
                }
            }
            else
            {
                
                loadedThis = new ApplicationSettings()
                {
                    AutoLogin = false,
                    LastWindowSize = new System.Windows.Size(800, 500),
                    LastWindowState = WindowState.Normal
                };
            }

            return loadedThis;
        }
    }
}
