using System;
using System.Collections.Generic;
using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    public class PhotoAlbum : VisualMedia
    {
        private List<VisualMedia> contentList = new List<VisualMedia>();

        public PhotoAlbum(string io_name, string io_coverPhotoUri, Func<string, Comment> io_commentFunc, Func<bool> io_likeFunc) : base(io_name, io_coverPhotoUri, io_commentFunc, io_likeFunc)
        {
        }

        public override VisualMedia this[int i_index]
        {
            get
            {
                VisualMedia media = null;
                int i = 0;
                foreach (Photo pic in contentList)
                {
                    if (i == i_index)
                    {
                        media = pic;
                    }
                    i++;
                }
                return media;
            }
        }

        public override void Add(VisualMedia i_media)
        {
            this.contentList.Add(i_media as Photo);
        }

        public override void Remove(VisualMedia i_media)
        {
            this.contentList.Remove(i_media as Photo);
        }
        public override int Size()
        {
            return this.contentList.Count;
        }

    }
}
