using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacebookIntegrationApp
{
    abstract class VisualMedia
    {
        protected string name;
        public VisualMedia(string name)
        {
            this.name = name;

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
    }
}
