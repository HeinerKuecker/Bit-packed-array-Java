package de.heinerkuecker.primitive.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;

import de.heinerkuecker.iterator.primitive.IntIterator;

/**
 * Generator für Werte für den Test
 * von {@link BitCopy}.
 * <br/><br/>
 * Um nicht alle Werte des 32-Bit
 * bzw. 64-Bit Wertbereiches testen
 * zu müssen, wird ein Bitmuster
 * aufgebaut und als Testdaten
 * verwendet.
 * <br/><br/>
 * Im Bitmuster werden gleiche Bits
 * in einer bestimmten Anzahl gruppiert
 * <pre>
 * 10101010101010101010101010101010
 *
 * 11001100110011001100110011001100
 * <pre>
 * Ich gehe davon aus, dass ich mit
 * Gruppen bis maximal 4 gleichen
 * Bits alle Fehler finde.
 * <br/><br/>
 * Um alle Kombinationen von Bitgruppen
 * abzulaufen, lege ich einen Zähler
 * an.
 * <br/><br/>
 * Ausserdem werden alle Bitmuster
 * noch einmal negiert angewendet.
 * <br/><br/>
 * Aufgrund der variablen Länge
 * der sich aus den Bitgruppen
 * ergebenden Werte könnten sich
 * viele gleiche Werte ergeben.
 * <br/><br/>
 * Um dies zu vermeiden, beende
 * ich beim Erreichen einer
 * bestimmten Zähler-Stellung
 * mit den höchsten möglichen
 * Werten, die zusammen die
 * gesamte Breite des Ergebnisses
 * einnnehmen.
 *
 * @author Heiner K&uuml;cker
 */
public final class BitCopyTestValueIntIterator
implements IntIterator
{
	private static final int MIN_BIT_GROUP_LEN = 1;

	private static final int MAX_BIT_GROUP_LEN = 4;

	private final int[] counter;

	private boolean hasNext = true;

	private int currentValue;

	private boolean isReverse = true;
	private boolean isInvers = true;

	/**
	 * Konstruktor.
	 */
	public BitCopyTestValueIntIterator()
	{
		counter = new int[ Integer.SIZE ];

		Arrays.fill( counter , MIN_BIT_GROUP_LEN );

		this.currentValue = toValue();
	}

	@Override
	public boolean hasNext()
	{
		//for ( int digitIndex = 0 ; digitIndex < MAX_FULL_BIT_GROUP_DOGIT_COUNT + 1 ; digitIndex++ )
		//{
		//	if ( counter[ digitIndex ] < MAX_BIT_GROUP_LEN )
		//	{
		//		return true;
		//	}
		//}
		//return false;
		return
				this.isReverse ||
				this.isInvers ||
				this.hasNext;
	}

	@Override
	public int next()
	{
		if ( ! this.hasNext() )
		{
			throw new NoSuchElementException();
		}

		final int result = currentValue;
		//skipValue();

		if ( isReverse )
		{
			final int reverseResult = Integer.reverse( result );

			if ( isInvers )
			{
				isInvers = false;
				return ~reverseResult;
			}
			isReverse = false;
			isInvers = true;
			return reverseResult;
		}

		if ( isInvers )
		{
			isInvers = false;
			// TODO hier sollte stehen: return ~result;
			return ~this.currentValue;
		}
		isReverse = true;
		isInvers = true;

		if ( result ==
				// ein bisschen Betrug, weil ich nicht wusste, wie ich hasNext richtig codieren sollte
				0b11110000111100001111000011110000 )
		{
			this.hasNext = false;
		}
		else
		{
			skipCounter();
			this.currentValue = toValue();
		}
		return result;
	}

	//private void skipValue()
	//{
	//	int lastValue = this.currentValue;
	//
	//	while ( lastValue == this.currentValue )
	//	{
	//		if ( ! skipCounter() )
	//		{
	//			return;
	//		}
	//		this.currentValue = toValue();
	//	}
	//}

	private boolean skipCounter()
	{
		//for ( int digitIndex = counter.length - 1 ; digitIndex >= 0 ; digitIndex-- )
		for ( int digitIndex = 0 ; digitIndex < counter.length ; digitIndex++ )
		{
			if ( counter[ digitIndex ] < MAX_BIT_GROUP_LEN )
			{
				counter[ digitIndex ]++;
				return true;
			}
			else
			{
				counter[ digitIndex ] = MIN_BIT_GROUP_LEN;
			}
		}
		// overflow
		//this.hasNext = false;
		return false;
	}

	private int toValue()
	{
		int result = 0;

		boolean bitSet = false;
		int mask = 1;
		//for ( int digitIndex = counter.length - 1 ; digitIndex >= 0 ; digitIndex-- )
		for ( int digitIndex = 0 ; digitIndex < counter.length ; digitIndex++ )
		{
			final int digit = counter[ digitIndex ];

			for ( int bitPerGroupCount = 0 ; bitPerGroupCount < digit ; bitPerGroupCount++ )
			{
				//result <<= 1;
				if ( bitSet )
				{
					result |= mask;
				}
				mask <<= 1;
				if ( mask == 0 )
					// alle Bits gesetzt
				{
					return result;
				}
			}
			bitSet = ! bitSet;
		}
		return result;
	}

	//public static void main(
	//		final String... args )
	//{
	//	final HashSet<Integer> set = new HashSet<>();
	//
	//	final BitCopyTestValueIntIterator iterator = new BitCopyTestValueIntIterator();
	//
	//	int counter = 0;
	//	while ( iterator.hasNext() )
	//	{
	//		final int value = iterator.next();
	//		set.add( value );
	//		System.out.println( BitUtil.toBinaryString( value ) );
	//		counter++;
	//	}
	//	System.out.println( counter );
	//	System.out.println( set.size() );
	//}

}
