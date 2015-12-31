using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;
using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    class PhotoAlbum : VisualMedia
    {
        private List<Photo> contentList = new List<Photo>();

        public PhotoAlbum(string name, string coverPhotoUri, Action<string> commentFunc, Action likeFunc) : base(name, coverPhotoUri, commentFunc, likeFunc)
        {
        }

        public override void Add(VisualMedia media)
        {
            this.contentList.Add(media);
        }

        public override void Comment(string comment)
        {
            this.commentFunc(comment);
        }

        public override void Like()
        {
            this.likeFunc();
        }

        public override void Remove(VisualMedia media)
        {
            this.contentList.Remove(media);
        }
        public override int Size()
        {
            return this.contentList.Count;
        }
    }
}
