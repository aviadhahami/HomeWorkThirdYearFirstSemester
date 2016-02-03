﻿using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    class SortByDate : ISortStrategy
    {
        public FacebookObjectCollection<Album> Sort(FacebookObjectCollection<Album> i_albums)
        {
            FacebookObjectCollection<Album> tempAlbum = i_albums;
            Album curr;
            Album nxt;
            for (int i = 0; i < i_albums.Count - 1; i++)
            {
                for (int j = 0; j < i_albums.Count - 1; j++)
                {
                    curr = i_albums[j];
                    nxt = i_albums[j + 1];
                    if (curr.CreatedTime > nxt.CreatedTime)
                    {
                        tempAlbum.Move(j, j + 1);
                    }
                }
            }
            return tempAlbum;
        }
    }
}
