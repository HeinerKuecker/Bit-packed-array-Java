package de.heinerkuecker.iterator.primitive;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Iterator for primitive long values.
 *
 * @see java.util.Iterator
 * @author Heiner K&uuml;cker
 */
public interface LongIterator
{
	/**
	 * Method to get the information
	 * whether a next element exists.
	 *
	 * @return has next value or not
	 * @see java.util.Iterator#hasNext()
	 */
	boolean hasNext();

	/**
	 * Method to get the next element.
	 * @return next element
	 * @throws NoSuchElementException when no next element exists
	 */
	long next();

	/**
	 * Get empty long iterator.
	 *
	 * @param modificationCountbl underlying collection
	 * @return empty {@link LongIterator} with modification counter
	 */
	public static LongIterator empty(
			final ModificationCountbl modificationCountbl )
	{
		return
				new LongIterator()
				{
					private final int modificationCounter = modificationCountbl.modificationCounter();

					@Override
					public boolean hasNext()
					{
						if ( this.modificationCounter != modificationCountbl.modificationCounter() )
						{
							throw new ConcurrentModificationException();
						}
						return false;
					}

					@Override
					public long next()
					{
						if ( this.modificationCounter != modificationCountbl.modificationCounter() )
						{
							throw new ConcurrentModificationException();
						}
						throw new NoSuchElementException();
					}
				};
	}

}
