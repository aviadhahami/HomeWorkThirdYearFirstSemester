using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media.Imaging;

namespace FacebookIntegrationApp
{
    class PhotoAlbum : VisualMedia
    {
        private List<VisualMedia> contentList = new List<VisualMedia>();
        public PhotoAlbum(string name, string coverPhotoUri) : base(name, coverPhotoUri)
        {
        }

        public override void Add(VisualMedia media)
        {
            this.contentList.Add(media);
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
