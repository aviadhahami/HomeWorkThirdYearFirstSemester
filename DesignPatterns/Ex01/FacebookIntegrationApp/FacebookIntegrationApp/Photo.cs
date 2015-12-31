using FacebookWrapper.ObjectModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;

namespace FacebookIntegrationApp
{
    class Photo : VisualMedia
    {
        public Photo(string name, string imageUri, Func<string, Comment> commentFunc, Func<bool> likeFunc) : base(name, imageUri, commentFunc, likeFunc)
        {
        }

        public override void Add(VisualMedia i_media)
        {
            // We do not implement this as it's a leaf in the composite
        }

        public override void Comment(string comment)
        {
            this.commentFunc(comment);
        }

        public override void Like()
        {
            this.likeFunc();
        }

        public override void Remove(VisualMedia i_media)
        {
            // We do not implement this as it's a leaf in the composite
        }

        public override int Size()
        {
            return 0;
        }
    }
}
