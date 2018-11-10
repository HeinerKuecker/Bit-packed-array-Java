package de.heinerkuecker.iterator.primitive;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Iterator for primitive int values.
 *
 * @see java.util.Iterator
 * @author Heiner K&uuml;cker
 */
public interface IntIterator
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
	int next();

	/**
	 * Get empty int iterator.
	 *
	 * @param modificationCountbl underlying collection
	 * @return empty {@link IntIterator} with modification counter
	 */
	public static IntIterator empty(
			final ModificationCountbl modificationCountbl )
	{
		return
				new IntIterator()
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
					public int next()
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
