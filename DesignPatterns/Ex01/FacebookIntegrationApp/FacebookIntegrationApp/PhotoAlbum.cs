using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;
using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    public class PhotoAlbum : VisualMedia
    {
        private List<Photo> contentList = new List<Photo>();

        public PhotoAlbum(string name, string coverPhotoUri, Func<string, Comment> commentFunc, Func<bool> likeFunc) : base(name, coverPhotoUri, commentFunc, likeFunc)
        {
        }

        public override VisualMedia this[int index]
        {
            get
            {
                VisualMedia media = null;
                int i = 0;
                foreach (Photo pic in contentList)
                {
                    if (i == index)
                    {
                        media = pic;
                    }
                    i++;
                }
                return media;
            }
        }

        public override void Add(VisualMedia media)
        {
            this.contentList.Add(media as Photo);
        }

        public override void Remove(VisualMedia media)
        {
            this.contentList.Remove(media as Photo);
        }
        public override int Size()
        {
            return this.contentList.Count;
        }

    }
}
