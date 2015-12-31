using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;

namespace FacebookIntegrationApp
{
    abstract class VisualMedia
    {
        protected string name;
        protected string imageUri;
        protected Action<string> commentFunc;
        protected Action likeFunc;

        public VisualMedia(string name, string imageUri, Action<string> commentFunc, Action likeFunc)
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
    }
}
