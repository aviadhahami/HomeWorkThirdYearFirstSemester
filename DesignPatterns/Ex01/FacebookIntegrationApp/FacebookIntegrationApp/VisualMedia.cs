using FacebookWrapper.ObjectModel;
using System;
using System.Windows.Media.Imaging;
using System.Windows;

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
        public void Comment(string comment)
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() =>
            {
                commentFunc(comment);
            }));
        }
        public void Like()
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() =>
            {
                likeFunc();
            }));
        }
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
