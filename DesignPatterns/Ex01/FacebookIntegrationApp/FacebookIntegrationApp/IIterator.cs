namespace FacebookIntegrationApp
{
    public interface IIterator<T>
    {
        T Current { get; }

        int CurrentIndex { get; }
        bool hasNext();
        T Next();
        bool hasPrev();
        T Prev();
        void Reset();

    }
}