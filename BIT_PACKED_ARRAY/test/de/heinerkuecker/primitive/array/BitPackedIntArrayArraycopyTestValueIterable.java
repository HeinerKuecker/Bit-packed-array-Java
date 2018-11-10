package de.heinerkuecker.primitive.array;

import java.util.Iterator;

/**
 * Arbeit mit Schieben, weil
 * der Test bei Arbeit als Zähler
 * zu lange dauert.
 *
 * @author Heiner Kücker
 */
public class BitPackedIntArrayArraycopyTestValueIterable
implements Iterable<int[]>
{
	private final int dataElementWidth;

	private final int arraySize;

	final int maxValuePerElement;

	public BitPackedIntArrayArraycopyTestValueIterable(
			final int dataElementWidth ,
			final int arraySize )
	{
		this.dataElementWidth = dataElementWidth;
		this.arraySize = arraySize;
		this.maxValuePerElement = ~( -1 /*all bits 1 */ << dataElementWidth );
	}

	@Override
	public Iterator<int[]> iterator()
	{
		if ( dataElementWidth < 3 )
		{
			return new AllValueCountIterable();
		}
		if ( dataElementWidth * arraySize < 65 )
		{
			return new OnlyMinMaxValueCountIterable();
		}
		return new PatternShiftIterator();
	}

	private class PatternShiftIterator
	implements Iterator<int[]>
	{
		private int counter;

		private final int[] currentValues =
				// pattern 0000000
				new int[ arraySize ];

		@Override
		public boolean hasNext()
		{
			return counter <= ( dataElementWidth * arraySize * 5 );
		}

		@Override
		public int[] next()
		{
			final int[] result = currentValues.clone();
			shift();
			counter++;
			return result;
		}

		private void shift()
		{
			//for ( int currentValuesIndex = 0; currentValuesIndex < currentValues.length; currentValuesIndex++ )
			for ( int currentValuesIndex = currentValues.length - 1 ; currentValuesIndex >= 0 ; currentValuesIndex-- )
			{
				//System.out.println( BitUtil.toBinaryString( currentValues[ currentValuesIndex ] ) );
				currentValues[ currentValuesIndex ] <<= 1;
				//System.out.println( BitUtil.toBinaryString( currentValues[ currentValuesIndex ] ) );
				//nur um die dataElementWidth schieben
				//System.out.println( BitUtil.toBinaryString( maxValuePerElement ) );
				currentValues[ currentValuesIndex ] &= maxValuePerElement;

				if ( currentValuesIndex > 0 )
				{
					//System.out.println( BitUtil.toBinaryString( currentValues[ currentValuesIndex - 1 ] ) );
					//System.out.println( BitUtil.toBinaryString( ( 1 << ( dataElementWidth - 1 ) ) ) );
					if ( ( currentValues[ currentValuesIndex - 1 ] & ( 1 << ( dataElementWidth - 1 ) ) ) != 0 )
						// highest bit of next value is 1
					{
						// set lowest bit of current value
						currentValues[ currentValuesIndex ] |= 1;
					}
				}
				//System.out.println();
			}

			if ( counter <= ( dataElementWidth * arraySize ) )
				// pattern 0101
			{
				if ( counter % 2 == 0 )
				{
					currentValues[ 0 ] |= 1;
				}
				return;
			}
			if ( counter <= ( dataElementWidth * arraySize ) * 2 )
				// pattern 00110011
			{
				if ( ( counter / 2 ) % 2 == 0 )
				{
					currentValues[ 0 ] |= 1;
				}
				return;
			}
			if ( counter <= ( dataElementWidth * arraySize ) * 3 )
				// pattern 000111000111
			{
				if ( ( counter / 3 ) % 2 == 0 )
				{
					currentValues[ 0 ] |= 1;
				}
				return;
			}
			if ( counter <= ( dataElementWidth * arraySize ) * 4 )
				// pattern 0000111100001111
			{
				if ( ( counter / 4 ) % 2 == 0 )
				{
					currentValues[ 0 ] |= 1;
				}
				return;
			}
			// pattern 1111111
			currentValues[ 0 ] |= 1;
		}

	}

	private class AllValueCountIterable
	implements Iterator<int[]>
	{
		private final int[] currentValues = new int[ arraySize ];

		private boolean hasNext = true;

		@Override
		public boolean hasNext()
		{
			return this.hasNext;
		}

		@Override
		public int[] next()
		{
			final int[] result = currentValues.clone();
			skip();
			return result;
		}

		private void skip()
		{
			for ( int index = 0 ; index < currentValues.length ; index++ )
			{
				if ( currentValues[ index ] < maxValuePerElement )
				{
					// damit dauert der Test viel zu lange:
					currentValues[ index ]++;

					// skipped
					return;
				}
				else
				{
					currentValues[ index ] = 0;
				}
			}

			// overflow
			this.hasNext = false;
		}
	}

	private class OnlyMinMaxValueCountIterable
	implements Iterator<int[]>
	{
		private final int[] currentValues = new int[ arraySize ];

		private boolean hasNext = true;

		@Override
		public boolean hasNext()
		{
			return this.hasNext;
		}

		@Override
		public int[] next()
		{
			final int[] result = currentValues.clone();
			skip();
			return result;
		}

		private void skip()
		{
			for ( int index = 0 ; index < currentValues.length ; index++ )
			{
				if ( currentValues[ index ] < maxValuePerElement )
				{
					// sofort zum maximalen Wert weiterschalten, damit der Test nicht zu lange dauert
					currentValues[ index ] = maxValuePerElement;

					// skipped
					return;
				}
				else
				{
					currentValues[ index ] = 0;
				}
			}

			// overflow
			this.hasNext = false;
		}
	}

	public static void main(
			final String... args )
	{
		subMain(
				//dataElementWidth
				1 ,
				//arraySize
				64 );

		System.out.println();

		subMain(
				//dataElementWidth
				4 ,
				//arraySize
				16 );

		System.out.println();

		subMain(
				//dataElementWidth
				16 ,
				//arraySize
				4 );

		System.out.println();

		subMain(
				//dataElementWidth
				31 ,
				//arraySize
				4 );
	}

	private static void subMain(
			final int dataElementWidth ,
			final int arraySize )
	{
		BitPackedIntArrayArraycopyTestValueIterable iterable =
				new BitPackedIntArrayArraycopyTestValueIterable(
				dataElementWidth ,
				arraySize );

		for ( final int[] currentValues : iterable)
		{
			//for ( final int currentValue : currentValues )
			for ( int currentValuesIndex = currentValues.length - 1 ; currentValuesIndex >= 0 ; currentValuesIndex-- )
			{
				final int currentValue = currentValues[ currentValuesIndex ];
				System.out.print( BitUtil.toBinaryString( currentValue ) + " " );
			}
			System.out.println();
		}
	}
}
