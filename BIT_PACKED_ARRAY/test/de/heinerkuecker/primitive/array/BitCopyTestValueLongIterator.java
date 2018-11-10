package de.heinerkuecker.primitive.array;

import java.util.HashSet;
import java.util.NoSuchElementException;

import de.heinerkuecker.iterator.primitive.LongIterator;

/**
 * Idee mit Schieberegister
 * damit nicht zu viele
 * Werte getestet werden müssen.
 *
 * @author Heiner K&uuml;cker
 */
public class BitCopyTestValueLongIterator
implements LongIterator
{
	private long value0 = 0;

	private long value1 = 0b1010101010101010101010101010101010101010101010101010101010101010L;

	private long value2 = 0b1100110011001100110011001100110011001100110011001100110011001100L;

	private long value3 = 0b1110001110001110001110001110001110001110001110001110001110001110L;

	private long value4 = 0b1111000011110000111100001111000011110000111100001111000011110000L;

	private long value5 =
			// alle Bits 1
			~0L;

	private int counter = 0;

	private static final int MAX_COUNTER = ( 6 * Long.SIZE ) - 1;

	private boolean isReverse = true;
	private boolean isInverse = true;

	public BitCopyTestValueLongIterator()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasNext()
	{
		return
				this.isReverse ||
				this.isInverse ||
				counter < MAX_COUNTER;
	}

	@Override
	public long next()
	{
		if ( ! hasNext() )
		{
			throw new NoSuchElementException();
		}

		final long result = value0;

		if ( isReverse )
		{
			final long reverseResult = Long.reverse( result );

			if ( isInverse )
			{
				isInverse = false;
				return ~reverseResult;
			}
			isReverse = false;
			isInverse = true;
			return reverseResult;
		}

		if ( isInverse )
		{
			isInverse = false;
			return ~result;
		}
		isReverse = true;
		isInverse = true;

		skip();
		return result;
	}

	private void skip()
	{
		/*
		 * zirkulaeres Schieben
		 */
		final boolean highBit0 = value0 < 0;
		value0 <<= 1;

		final boolean highBit1 = value1 < 0;
		value1 <<= 1;

		final boolean highBit2 = value2 < 0;
		value2 <<= 1;

		final boolean highBit3 = value3 < 0;
		value3 <<= 1;

		final boolean highBit4 = value4 < 0;
		value4 <<= 1;

		final boolean highBit5 = value5 < 0;
		value5 <<= 1;

		if ( highBit0 )
		{
			value1 |= 1;
		}

		if ( highBit1 )
		{
			value2 |= 1;
		}

		if ( highBit2 )
		{
			value3 |= 1;
		}

		if ( highBit3 )
		{
			value4 |= 1;
		}

		if ( highBit4 )
		{
			value5 |= 1;
		}

		if ( highBit5 )
		{
			value0 |= 1;
		}

		counter++;
	}

	public static void main(
			final String... args )
	{
		final HashSet<Long> set = new HashSet<>();

		final BitCopyTestValueLongIterator iterator = new BitCopyTestValueLongIterator();

		long counter = 0;
		while ( iterator.hasNext() )
		{
			final long value = iterator.next();
			set.add( value );
			System.out.println( BitUtil.toBinaryString( value ) );
			counter++;
		}
		System.out.println( counter );
		System.out.println( set.size() );
	}

}
