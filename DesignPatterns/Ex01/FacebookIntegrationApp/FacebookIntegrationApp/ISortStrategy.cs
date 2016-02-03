using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    public interface ISortStrategy
    {
        FacebookObjectCollection<Album> Sort(FacebookObjectCollection<Album> o_albums);
    }
}