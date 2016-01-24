using FacebookWrapper.ObjectModel;
using System;

namespace FacebookIntegrationApp
{
    class Photo : VisualMedia
    {
        public Photo(string io_name, string io_imageUri, Func<string, Comment> io_commentFunc, Func<bool> io_likeFunc) : base(io_name, io_imageUri, io_commentFunc, io_likeFunc)
        {
        }

        public override VisualMedia this[int i_index]
        {
            // This is the leaf so we don't implement this
            get
            {
                return null;
            }
        }

        public override void Add(VisualMedia i_media)
        {
            // We do not implement this as it's a leaf in the composite
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
