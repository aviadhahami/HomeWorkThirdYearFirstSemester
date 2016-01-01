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

        public VisualMedia(string i_name, string i_imageUri, Func<string, Comment> i_commentFunc, Func<bool> i_likeFunc)
        {
            this.name = i_name;
            this.imageUri = i_imageUri;
            this.commentFunc = i_commentFunc;
            this.likeFunc = i_likeFunc;
        }
        public abstract void Add(VisualMedia i_media);
        public abstract void Remove(VisualMedia i_media);
        public abstract int Size();
        public void Comment(string io_comment)
        {
            Application.Current.Dispatcher.BeginInvoke(new Action(() =>
            {
                commentFunc(io_comment);
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
        public abstract VisualMedia this[int i_index]
        {
            get;
        }
    }
}
