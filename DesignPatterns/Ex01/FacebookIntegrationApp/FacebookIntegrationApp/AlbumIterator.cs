using System;
using System.Collections.Generic;
using System.Windows;

namespace FacebookIntegrationApp
{
    internal class AlbumIterator : IIterator<VisualMedia>
    {
        private List<VisualMedia> m_Collection;
        private int m_CurrentIndex;
        private VisualMedia m_CurrentItem;

        public AlbumIterator(List<VisualMedia> i_Collection)
        {
            m_Collection = i_Collection;
            m_CurrentIndex = 0;
            m_CurrentItem = i_Collection[m_CurrentIndex];
        }
        public VisualMedia Current
        {
            get
            {
                return m_CurrentItem;
            }
        }

        public int CurrentIndex
        {
            get
            {
                return m_CurrentIndex;
            }
        }

        public bool hasNext()
        {
            return m_CurrentIndex+1< m_Collection.Count;
        }

        public VisualMedia Next()
        {

            if (hasNext())
            {
                m_CurrentIndex++;
            }
            m_CurrentItem = m_Collection[m_CurrentIndex];
            return m_CurrentItem;
        }

        public void Reset()
        {
            m_CurrentIndex = 0;
        }

        public VisualMedia Prev()
        {
            if (hasPrev())
            {
                m_CurrentIndex--;
            }
            m_CurrentItem = m_Collection[m_CurrentIndex];

            return m_CurrentItem;
        }
        public bool hasPrev()
        {
            return m_CurrentIndex - 1 >= 0;
        }


    }
}