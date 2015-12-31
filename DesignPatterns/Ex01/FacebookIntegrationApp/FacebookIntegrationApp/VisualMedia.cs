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
        private string imageUri;

        public VisualMedia(string name, string imageUri)
        {
            this.name = name;
            this.imageUri = imageUri;
        }
        public abstract void Add(VisualMedia media);
        public abstract void Remove(VisualMedia media);
        public abstract int Size();
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
