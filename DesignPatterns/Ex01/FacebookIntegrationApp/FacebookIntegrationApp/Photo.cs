using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;

namespace FacebookIntegrationApp
{
    class Photo : VisualMedia
    {
        private string imageUri;

        public Photo(string name, string uri) : base(name)
        {
            this.imageUri = uri;
        }
        public override void Add(VisualMedia media)
        {
            //Should not implement this as this is the leaf
        }

        public override void Remove(VisualMedia media)
        {
            //should not implement this as this is the leaf
        }

        public override int Size()
        {
            // We return 1 here as we're on a leaf
            return 1;
        }
        public BitmapImage getBMP()
        {
            return new BitmapImage(new Uri(this.imageUri));
        }
    }
}
