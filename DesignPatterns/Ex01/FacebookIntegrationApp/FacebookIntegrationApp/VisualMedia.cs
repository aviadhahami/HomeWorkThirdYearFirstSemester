using FacebookWrapper.ObjectModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;

namespace FacebookIntegrationApp
{
    public abstract class VisualMedia
    {
        protected string name;
        protected string imageUri;
        protected Func<string, Comment> commentFunc;
        protected Func<bool> likeFunc;

        public VisualMedia(string name, string imageUri, Func<string, Comment> commentFunc, Func<bool> likeFunc)
        {
            this.name = name;
            this.imageUri = imageUri;
            this.commentFunc = commentFunc;
            this.likeFunc = likeFunc;
        }
        public abstract void Add(VisualMedia i_media);
        public abstract void Remove(VisualMedia i_media);
        public abstract int Size();
        public abstract void Comment(string comment);
        public abstract void Like();
        public string Name
        {
            get
            {
                return this.name;
            }
        }
        public BitmapImage getBMP
        {
            get
            {
                return new BitmapImage(new Uri(this.imageUri));
            }
        }
        public abstract VisualMedia this[int index]
        {
            get;
        }
    }
}
