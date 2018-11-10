package de.heinerkuecker.primitive.array;

/**
 * Util class to copy bits from one
 * int or long primitive value to
 * other.
 *
 * @author Heiner K&uuml;cker
 */
public class BitCopy
{
	/**
	 * Copy bits in self primitive int value like array elements
	 * with {@link System#arraycopy(Object, int, Object, int, int)}.
	 * Destination value is the equal to source value.
	 *
	 * @param srcPos starting position in the source value.
	 * @param dstPos starting position in the destination value.
	 * @param length the number of bits to be copied.
	 * @return result of the bit copy
	 */
	public static int selfBitCopy(
			final int src ,
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		if ( length == 0 )
		{
			return src;
		}

		if ( srcPos == dstPos )
		{
			return src;
		}

		return bitCopy(
				//src ,
				src ,
				//dst ,
				src ,
				srcPos ,
				dstPos ,
				length );
	}

	/**
	 * Copy bits in self primitive int value like array elements
	 * with {@link System#arraycopy(Object, int, Object, int, int)}.
	 *
	 * @param srcPos
	 * @param dstPos
	 * @param length
	 * @return result of the bit copy
	 */
	public static int bitCopy(
			final int src ,
			final int dst ,
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		// einfache Implementierung, aber langsam
		//int result = dst;
		//for ( int pos = srcPos ; pos < srcPos + length ; pos++ )
		//{
		//	final boolean bitValue =
		//			getBit(
		//					src ,
		//					pos );
		//	result =
		//			setBit(
		//					result ,
		//					bitValue ,
		//					pos );
		//}

		//System.out.println( "src                " + toBinaryString( src ) );

		//if ( length < 0 || length >= Integer.SIZE )
		// TODO bei srcPos == dstPos ist length egal
		if ( length < 0 )
		{
			throw new IllegalArgumentException( "length: " + length );
		}

		if ( srcPos < 0 || srcPos >= Integer.SIZE )
		{
			throw new IllegalArgumentException( "srcPos: " + srcPos );
		}

		if ( dstPos < 0 || dstPos >= Integer.SIZE )
		{
			throw new IllegalArgumentException( "dstPos: " + dstPos );
		}

		//if ( srcPos + length < 0 || srcPos + length > Integer.SIZE )
		if ( /* nach der Prüfung von srcPos und length eigentlich nicht mehr möglich: srcPos + length < 0 ||*/ srcPos + length > Integer.SIZE )
		{
			throw new IllegalArgumentException( "srcPos + length: " + ( srcPos + length ) );
		}

		//if ( dstPos + length < 0 || dstPos + length >= Integer.SIZE )
		final int dstFalseMaskLeftShiftCount = dstPos + length;
		//if ( dstFalseMaskLeftShiftCount < 0 || dstFalseMaskLeftShiftCount > Integer.SIZE )
		if ( /* nach der Prüfung von dstPos und length eigentlich nicht mehr möglich: dstFalseMaskLeftShiftCount < 0 ||*/ dstFalseMaskLeftShiftCount > Integer.SIZE )
		{
			throw new IllegalArgumentException( "dstPos + length: " + dstFalseMaskLeftShiftCount );
		}

		if ( length == 0 )
		{
			return dst;
		}

		if ( length == Integer.SIZE )
		{
			return src;
		}

		if ( srcPos == dstPos &&
				src == dst )
		{
			return dst;
		}

		final int dstFalseMask;
		if ( dstFalseMaskLeftShiftCount == Integer.SIZE )
			/*
			 *  Verschiebung geht nur von 1 bis 31.
			 *
			 *  https://stackoverflow.com/questions/1023373/findbugs-warning-integer-shift-by-32-what-does-it-mean
			 *
			 *  From the Java Language Specification:
			 *  https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.19
			 *  
			 *  If the promoted type of the left-hand operand is int, 
			 *  then only the five lowest-order bits of the right-hand operand 
			 *  are used as the shift distance. 
			 *  It is as if the right-hand operand were subjected to a 
			 *  bitwise logical AND operator & (§15.22.1) with the mask value 0x1f (0b11111). 
			 *  The shift distance actually used is therefore always in the range 0 to 31, inclusive.
			 */
		{
			dstFalseMask = ~( 0xFFFFFFFF << dstPos );
		}
		else
		{
			dstFalseMask = ( 0xFFFFFFFF << dstFalseMaskLeftShiftCount ) | ~( 0xFFFFFFFF << dstPos );
		}
		//System.out.println( "dstFalseMask       " + toBinaryString( dstFalseMask ) );

		final int shiftedSrc;
		if ( dstPos < srcPos )
		{
			// shift right
			shiftedSrc = src >>> ( srcPos - dstPos );
		}
		else
		{
			// shift left
			shiftedSrc = src << ( dstPos - srcPos );
		}
		//System.out.println( "shiftedSrc " + toBinaryString( shiftedSrc ) );

		//System.out.println( "dst                " + toBinaryString( dst ) );

		final int result = ( dst & dstFalseMask ) | ( shiftedSrc & ~dstFalseMask );
		//System.out.println( "result             " + toBinaryString( result ) );

		return result;
		//throw new RuntimeException( "not implemented" );
	}

	/**
	 * Copy bits in self primitive long value like array elements
	 * with {@link System#arraycopy(Object, int, Object, int, int)}.
	 * Destination value is the equal to source value.
	 *
	 * @param srcPos starting position in the source value.
	 * @param dstPos starting position in the destination value.
	 * @param length the number of bits to be copied.
	 * @return result of the bit copy
	 */
	public static long selfBitCopy(
			final long src ,
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		if ( length == 0 )
		{
			return src;
		}

		if ( srcPos == dstPos )
		{
			return src;
		}

		return bitCopy(
				//src ,
				src ,
				//dst ,
				src ,
				srcPos ,
				dstPos ,
				length );
	}

	/**
	 * Copy bits in self primitive int value like array elements
	 * with {@link System#arraycopy(Object, int, Object, int, int)}.
	 *
	 * @param srcPos
	 * @param dstPos
	 * @param length
	 * @return result of the bit copy
	 */
	public static long bitCopy(
			final long src ,
			final long dst ,
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		//System.out.println( "src                " + BitUtil.toBinaryString( src ) );

		//if ( length < 0 || length >= Integer.SIZE )
		// TODO bei srcPos == dstPos ist length egal
		if ( length < 0 )
		{
			throw new IllegalArgumentException( "length: " + length );
		}

		if ( srcPos < 0 || srcPos >= Long.SIZE )
		{
			throw new IllegalArgumentException( "srcPos: " + srcPos );
		}

		if ( dstPos < 0 || dstPos >= Long.SIZE )
		{
			throw new IllegalArgumentException( "dstPos: " + dstPos );
		}

		//if ( srcPos + length < 0 || srcPos + length > Long.SIZE )
		if ( /* nach der Prüfung von srcPos und length eigentlich nicht mehr möglich: srcPos + length < 0 ||*/ srcPos + length > Long.SIZE )
		{
			throw new IllegalArgumentException( "srcPos + length: " + ( srcPos + length ) );
		}

		//if ( dstPos + length < 0 || dstPos + length >= Long.SIZE )
		final int dstFalseMaskLeftShiftCount = dstPos + length;
		//if ( dstFalseMaskLeftShiftCount < 0 || dstFalseMaskLeftShiftCount > Long.SIZE )
		if ( /* nach der Prüfung von dstPos und length eigentlich nicht mehr möglich: dstFalseMaskLeftShiftCount < 0 ||*/ dstFalseMaskLeftShiftCount > Long.SIZE )
		{
			throw new IllegalArgumentException( "dstPos + length: " + dstFalseMaskLeftShiftCount );
		}

		if ( length == 0 )
		{
			return dst;
		}

		if ( length == Long.SIZE )
		{
			return src;
		}

		if ( srcPos == dstPos &&
				src == dst )
		{
			return dst;
		}

		final long dstFalseMask;
		if ( dstFalseMaskLeftShiftCount == Long.SIZE )
			/*
			 *  Verschiebung geht nur von 1 bis 63.
			 *
			 *  From the Java Language Specification:
			 *  https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.19
			 *  
			 *  If the promoted type of the left-hand operand is long, 
			 *  then only the six lowest-order bits of the right-hand operand 
			 *  are used as the shift distance. 
			 *  It is as if the right-hand operand were subjected to a 
			 *  bitwise logical AND operator & (§15.22.1) with the mask value 0x3f (0b111111). 
			 *  The shift distance actually used is therefore always in the range 0 to 63, inclusive.
			 */
		{
			dstFalseMask = ~( 0xFFFFFFFFFFFFFFFFL << dstPos );
		}
		else
		{
			dstFalseMask = ( 0xFFFFFFFFFFFFFFFFL << dstFalseMaskLeftShiftCount ) | ~( 0xFFFFFFFFFFFFFFFFL << dstPos );
		}
		//System.out.println( "dstFalseMask       " + BitUtil.toBinaryString( dstFalseMask ) );

		final long shiftedSrc;
		if ( dstPos < srcPos )
		{
			// shift right
			shiftedSrc = src >>> ( srcPos - dstPos );
		}
		else
		{
			// shift left
			shiftedSrc = src << ( dstPos - srcPos );
		}
		//System.out.println( "shiftedSrc " + BitUtil.toBinaryString( shiftedSrc ) );

		//System.out.println( "dst                " + BitUtil.toBinaryString( dst ) );

		final long result = ( dst & dstFalseMask ) | ( shiftedSrc & ~dstFalseMask );
		//System.out.println( "result             " + BitUtil.toBinaryString( result ) );

		return result;
		//throw new RuntimeException( "not implemented" );
	}

}
