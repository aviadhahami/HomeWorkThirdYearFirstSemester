using System;
using System.Collections.Generic;
using FacebookWrapper.ObjectModel;

namespace FacebookIntegrationApp
{
    public class PhotoAlbum : VisualMedia
    {
        private List<VisualMedia> m_ContentList = new List<VisualMedia>();
        private IIterator<VisualMedia> m_mediaIterator = null;

        //TODO : Add the iterator thing
        public PhotoAlbum(string io_name, string io_coverPhotoUri, Func<string, Comment> io_commentFunc, Func<bool> io_likeFunc) : base(io_name, io_coverPhotoUri, io_commentFunc, io_likeFunc)
        {
        }

        public override VisualMedia this[int i_index]
        {
            get
            {
                VisualMedia media = null;
                int i = 0;
                foreach (Photo pic in m_ContentList)
                {
                    if (i == i_index)
                    {
                        media = pic;
                    }
                    i++;
                }
                return media;
            }
        }

        public override void Add(VisualMedia i_media)
        {
            this.m_ContentList.Add(i_media as Photo);
        }

        public override void Remove(VisualMedia i_media)
        {
            this.m_ContentList.Remove(i_media as Photo);
        }

        public override int Size()
        {
            return this.m_ContentList.Count;
        }

        public IIterator<VisualMedia> Iterator
        {
            get {
                if (m_mediaIterator.Equals(null))
                {
                    m_mediaIterator = new AlbumIterator(m_ContentList);
                }
                return m_mediaIterator;
            }
        }


    }
}
