package de.heinerkuecker.iterator.primitive;

import java.util.Iterator;

/**
 * Interface for Collections
 * to ensure consistence
 * between {@link Iterator}s
 * and Collection.
 *  
 * @author Heiner Kücker
 */
public interface ModificationCountbl
{
	int modificationCounter();
}
