package de.heinerkuecker.primitive.array;

public final class BitUtil
{

	public static String toBinaryString(
			final int value ,
			final int digitCount )
	{
		String result = Integer.toBinaryString( value );
		while ( result.length() < digitCount )
		{
			result = "0" + result;
		}
		return result;
	}

	public static String toBinaryString(
			final int value )
	{
		return toBinaryString(
				value,
				//digitCount
				Integer.SIZE );
	}

	public static String toBinaryString(
			final long value ,
			final int digitCount )
	{
		String result = Long.toBinaryString( value );
		while ( result.length() < digitCount )
		{
			result = "0" + result;
		}
		return result;
		//final char[] chars = new char[ Long.SIZE ];
		//long mask = 1;
		//for ( int bitIndex = Long.SIZE - 1 ; bitIndex >= 0  ; bitIndex-- )
		//{
		//	if ( ( value & mask ) == 0 )
		//	{
		//		chars[ bitIndex ] = '0';
		//	}
		//	else
		//	{
		//		chars[ bitIndex ] = '1';
		//	}
		//	mask <<= 1;
		//}
		//return new String( chars );
	}

	public static String toBinaryString(
			final long value )
	{
		return toBinaryString(
				value,
				//digitCount
				Long.SIZE );
	}

	public static boolean isBit(
			final int intValue ,
			final int index )
	{
		//if ( index < 0 ||
		//		index >= Integer.SIZE )
		//{
		//	throw new IllegalArgumentException( "index: " + index );
		//}
		//return ( intValue & ( 1 << index ) ) != 0;

		// TODO vielleicht ist die Variante mit switch schneller
		// der Compiler oder der JIT kann festellen, dass index in jedem Zweig konstant ist und das Ergebnis der verschiebung als konstanten Wert berechnen
		// TODO Durchfall von 0 bis 31 oder konstanter Verschiebewert
		switch ( index )
		{
		case 0:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 1:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 2:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 3:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 4:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 5:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 6:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 7:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 8:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 9:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 10:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 11:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 12:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 13:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 14:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 15:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 16:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 17:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 18:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 19:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 20:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 21:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 22:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 23:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 24:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 25:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 26:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 27:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 28:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 29:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 30:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}
		case 31:
		{
			return ( intValue & ( 1 << index ) ) != 0;
			//break;
		}

		default:
		{
			throw new IllegalArgumentException( "index: " + index );
			//break;
		}
		}
	}

	public static boolean isBit(
			final long longValue ,
			final int index )
	{
		//if ( index < 0 ||
		//		index >= Long.SIZE )
		//{
		//	throw new IllegalArgumentException( "index: " + index );
		//}
		//return ( longValue & ( 1L << index ) ) != 0;

		// TODO vielleicht ist die Variante mit switch schneller
		// der Compiler oder der JIT kann festellen, dass index in jedem Zweig konstant ist und das Ergebnis der verschiebung als konstanten Wert berechnen
		// TODO Durchfall von 0 bis 31 oder konstanter Verschiebewert
		switch ( index )
		{
		case 0:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 1:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 2:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 3:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 4:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 5:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 6:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 7:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 8:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 9:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 10:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 11:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 12:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 13:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 14:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 15:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 16:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 17:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 18:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 19:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 20:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 21:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 22:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 23:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 24:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 25:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 26:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 27:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 28:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 29:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 30:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 31:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 32:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 33:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 34:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 35:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 36:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 37:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 38:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 39:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 40:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 41:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 42:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 43:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 44:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 45:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 46:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 47:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 48:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 49:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 50:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 51:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 52:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 53:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 54:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 55:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 56:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 57:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 58:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 59:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 60:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 61:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 62:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}
		case 63:
		{
			return ( longValue & ( 1L << index ) ) != 0;
			//break;
		}

		default:
		{
			throw new IllegalArgumentException( "index: " + index );
			//break;
		}
		}
	}

	public static int setBit(
			final int intValue ,
			final int index ,
			final boolean bitValue )
	{
		if ( bitValue )
		{
			return setBit(intValue, index);
		}
		return resetBit(intValue, index);
	}

	public static int setBit(
			final int intValue ,
			final int index )
	{
		//if ( index < 0 ||
		//		index >= Integer.SIZE )
		//{
		//	throw new IllegalArgumentException( "index: " + index );
		//}
		//return intValue | ( 1 << index );

		// TODO vielleicht ist die Variante mit switch schneller
		// der Compiler oder der JIT kann festellen, dass index in jedem Zweig konstant ist und das Ergebnis der verschiebung als konstanten Wert berechnen
		// TODO Durchfall von 0 bis 31 oder konstanter Verschiebewert
		switch ( index )
		{
		case 0:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 1:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 2:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 3:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 4:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 5:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 6:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 7:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 8:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 9:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 10:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 11:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 12:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 13:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 14:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 15:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 16:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 17:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 18:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 19:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 20:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 21:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 22:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 23:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 24:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 25:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 26:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 27:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 28:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 29:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 30:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}
		case 31:
		{
			return ( intValue | ( 1 << index ) );
			//break;
		}

		default:
		{
			throw new IllegalArgumentException( "index: " + index );
			//break;
		}
		}
	}

	public static long setBit(
			final long longValue ,
			final int index )
	{
		//if ( index < 0 ||
		//		index >= Long.SIZE )
		//{
		//	throw new IllegalArgumentException( "index: " + index );
		//}
		//return longValue | ( 1L << index );

		// TODO vielleicht ist die Variante mit switch schneller
		// der Compiler oder der JIT kann festellen, dass index in jedem Zweig konstant ist und das Ergebnis der verschiebung als konstanten Wert berechnen
		// TODO Durchfall von 0 bis 31 oder konstanter Verschiebewert
		switch ( index )
		{
		case 0:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 1:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 2:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 3:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 4:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 5:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 6:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 7:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 8:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 9:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 10:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 11:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 12:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 13:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 14:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 15:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 16:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 17:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 18:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 19:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 20:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 21:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 22:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 23:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 24:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 25:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 26:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 27:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 28:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 29:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 30:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 31:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 32:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 33:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 34:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 35:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 36:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 37:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 38:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 39:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 40:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 41:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 42:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 43:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 44:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 45:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 46:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 47:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 48:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 49:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 50:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 51:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 52:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 53:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 54:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 55:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 56:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 57:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 58:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 59:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 60:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 61:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 62:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}
		case 63:
		{
			return ( longValue | ( 1L << index ) );
			//break;
		}

		default:
		{
			throw new IllegalArgumentException( "index: " + index );
			//break;
		}
		}
	}

	public static int resetBit(
			final int intValue ,
			final int index )
	{
		//if ( index < 0 ||
		//		index >= Integer.SIZE )
		//{
		//	throw new IllegalArgumentException( "index: " + index );
		//}
		//return intValue & ~( 1 << index );

		// TODO vielleicht ist die Variante mit switch schneller
		// der Compiler oder der JIT kann festellen, dass index in jedem Zweig konstant ist und das Ergebnis der verschiebung als konstanten Wert berechnen
		// TODO Durchfall von 0 bis 31 oder konstanter Verschiebewert
		switch ( index )
		{
		case 0:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 1:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 2:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 3:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 4:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 5:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 6:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 7:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 8:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 9:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 10:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 11:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 12:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 13:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 14:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 15:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 16:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 17:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 18:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 19:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 20:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 21:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 22:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 23:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 24:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 25:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 26:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 27:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 28:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 29:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 30:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}
		case 31:
		{
			return ( intValue & ~( 1 << index ) );
			//break;
		}

		default:
		{
			throw new IllegalArgumentException( "index: " + index );
			//break;
		}
		}
	}

}
