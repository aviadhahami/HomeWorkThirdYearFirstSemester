using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacebookIntegrationApp
{
    class PhotoAlbum : VisualMedia
    {
        private List<VisualMedia> contentList = new List<VisualMedia>();
        public PhotoAlbum(string name) : base(name)
        {
            this.name = name;
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
