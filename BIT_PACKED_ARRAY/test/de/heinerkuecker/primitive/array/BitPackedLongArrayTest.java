package de.heinerkuecker.primitive.array;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit4 test for {@link BitPackedLongArray}.
 *
 * TODO Test IllegalArgumentException
 *
 * @author Heiner Kücker
 */
public class BitPackedLongArrayTest
{
	/**
	 * Test method for {@link BitPackedLongArray#BitPackedDataLongArray(int, int)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testBitPackedDataLongArray_Size_0()
	{
		BitPackedLongArray.instanceOf(
				//dataElementWidth
				7 ,
				//bitPackedArraySize
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#BitPackedDataLongArray(int, int)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testBitPackedDataLongArray_Size_Minus_1()
	{
		BitPackedLongArray.instanceOf(
				//dataElementWidth
				7 ,
				//bitPackedArraySize
				-1 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#BitPackedDataLongArray(int, int)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testBitPackedDataLongArray_dataElementWidth_0()
	{
		BitPackedLongArray.instanceOf(
				//dataElementWidth
				0 ,
				//bitPackedArraySize
				1 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#BitPackedDataLongArray(int, int)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testBitPackedDataLongArray_dataElementWidth_Minus_1()
	{
		BitPackedLongArray.instanceOf(
				//dataElementWidth
				-1 ,
				//bitPackedArraySize
				1 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#BitPackedDataLongArray(int, int)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testBitPackedDataLongArray_dataElementWidth_to_big_1()
	{
		BitPackedLongArray.instanceOf(
				//dataElementWidth
				Long.SIZE ,
				//bitPackedArraySize
				1 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#BitPackedDataLongArray(int, int)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testBitPackedDataLongArray_dataElementWidth_to_big_2()
	{
		BitPackedLongArray.instanceOf(
				//dataElementWidth
				Long.SIZE + 1 ,
				//bitPackedArraySize
				1 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#computeLongDataArrayLength(int, int)}.
	 */
	@Test
	public void testComputeDataArrayLength()
	{
		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						2 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						3 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						4 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						5 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						6 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						7 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						8 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						9 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						10 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						11 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						12 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						13 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						14 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						15 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						16 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						17 ) );

		Assert.assertEquals(
				//expected
				2 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						18 ) );

		Assert.assertEquals(
				//expected
				3 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						19 ) );

		Assert.assertEquals(
				//expected
				3 ,
				//actual
				BitPackedLongArray.computeLongDataArrayLength(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						20 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test( expected = ArrayIndexOutOfBoundsException.class )
	public void testSetUnsigned_negative_index_to_low_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetUnsigned(
				array ,
				// index
				-1 ,
				// value
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test( expected = ArrayIndexOutOfBoundsException.class )
	public void testSetUnsigned_negative_index_to_high_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testSetUnsigned_negative_value_to_low_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				-1 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testSetUnsigned_negative_value_to_high_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				128 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setSigned(int, long)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testSetSigned_negative_value_to_low_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				-64 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setSigned(int, long)}.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void testSetSigned_negative_value_to_high_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				64 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				1 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				2 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				3 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				126 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				127 );

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_7_2()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						2 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				1 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				2 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				3 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				126 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				127 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_7_3()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						3 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				1 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				2 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				3 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				126 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				127 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_7_3_avoid_change_neighborhood_values()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						3 );

		final int allBits = ( 1 << 7 ) - 1;

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				allBits );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				allBits );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						1 ) );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						2 ) );

		// ----

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				allBits );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				0 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						0 ) );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						1 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_33_3_avoid_change_neighborhood_values()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						33 ,
						//bitPackedArraySize
						3 );

		final long allBits = ( 1L << 33 ) - 1;

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				allBits );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				allBits );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						1 ) );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						2 ) );

		// ----

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				allBits );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				0 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						0 ) );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						1 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_63_3_avoid_change_neighborhood_values()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						63 ,
						//bitPackedArraySize
						3 );

		//final long allBits = ( 1L << 63 ) - 1;
		final long allBits = Long.MAX_VALUE;

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				allBits );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				allBits );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						1 ) );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						2 ) );

		// ----

		innerTestSetUnsigned(
				array ,
				// index
				0 ,
				// value
				0 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				allBits );

		innerTestSetUnsigned(
				array ,
				// index
				2 ,
				// value
				0 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						0 ) );

		Assert.assertEquals(
				//expected
				allBits ,
				//actual
				array.getUnsigned(
						// index
						1 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						// index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setSigned(int, long)}.
	 */
	@Test
	public void testSetSigned_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				-1 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				-2 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				-3 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				-63 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				0 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				1 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				2 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				3 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				63 );

		innerTestSetSigned(
				array ,
				// index
				0 ,
				// value
				0 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned(int, long)}.
	 */
	@Test
	public void testSetUnsigned_dataElementWidth_17__bitPackedArraySize_2__index_1__value_32768()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						17 ,
						//bitPackedArraySize
						2 );

		innerTestSetUnsigned(
				array ,
				// index
				1 ,
				// value
				32768 );
	}

	/**
	 * Test method for {@link BitPackedLongArray#setUnsigned}.
	 */
	@Ignore
	@Test
	public void testSetUnsigned_All_1_to_100()
	{
		for ( int arraySize = 1 ; arraySize <= 100 ; arraySize++ )
		{
			System.out.println( "arraySize: " + arraySize );
			for ( int dataWith = 1 ; dataWith < Long.SIZE ; dataWith++ )
			{
				//System.out.println( "arraySize: " + arraySize );
				System.out.println( "dataWith: " + dataWith );
				final BitPackedLongArray array =
						BitPackedLongArray.instanceOf(
								//dataElementWidth
								dataWith ,
								//bitPackedArraySize
								arraySize );

				for ( int arrayIndex = 0 ; arrayIndex < arraySize ; arrayIndex++ )
				{
					//System.out.println( "arrayIndex: " + arrayIndex );
					//System.out.println( "BigInteger.ONE.shiftLeft( dataWith ): " + BigInteger.ONE.shiftLeft( dataWith ) );
					for ( BigInteger value = BigInteger.ZERO ; value.compareTo( BigInteger.ONE.shiftLeft( dataWith ) ) < 0 ; value = value.add( BigInteger.ONE ) )
						// value als BigInteger notwendig, weil mit value als long die schleife bei dataWith == 63 wegen überlauf nicht terminiert
					{
						//System.out.println( "value: " + value );
						innerTestSetUnsigned(
								array ,
								// index
								arrayIndex ,
								// value
								value.longValueExact() );
					}
				}

				// --- Test mit value und arrayindex vertauscht um überschreiben benachbarter werte zu entdecken

				for ( BigInteger value = BigInteger.ZERO ; value.compareTo( BigInteger.ONE.shiftLeft( dataWith ) ) < 0 ; value = value.add( BigInteger.ONE ) )
					// value als BigInteger notwendig, weil mit value als long die schleife bei dataWith == 63 wegen überlauf nicht terminiert
				{
					//System.out.println( "value: " + value );
					for ( int arrayIndex = 0 ; arrayIndex < arraySize ; arrayIndex++ )
					{
						//System.out.println( "arrayIndex: " + arrayIndex );
						//System.out.println( "( 1L << dataWith ): " + ( 1L << dataWith ) );
						innerTestSetUnsigned(
								array ,
								// index
								arrayIndex ,
								// value
								value.longValueExact() );
					}
				}

				// ---

				for ( int arrayIndex = arraySize - 1 ; arrayIndex >= 0 ; arrayIndex-- )
				{
					//System.out.println( "arrayIndex: " + arrayIndex );
					for ( BigInteger value = BigInteger.ONE.shiftLeft( dataWith ).subtract( BigInteger.ONE ) ; value.compareTo( BigInteger.ZERO ) >= 0 ; value = value.subtract( BigInteger.ONE ) )
					{
						//System.out.println( "value: " + value );
						innerTestSetUnsigned(
								array ,
								// index
								arrayIndex ,
								// value
								value.longValueExact() );
					}
				}
			}
		}
	}

	private static void innerTestSetUnsigned(
			final BitPackedLongArray array ,
			final int index ,
			final long value )
	{
		array.setUnsigned(
				index ,
				value );

		Assert.assertEquals(
				"dataElementWidth=" + array.dataElementWidth() + ", " +
				"bitPackedArraySize=" + array.bitPackedArraySize + ", " +
				"index=" + index + ", " +
				"value=" + value + ", " +
				array ,
				//expected
				value ,
				//actual
				array.getUnsigned(
						index ) );
	}

	private static void innerTestSetSigned(
			final BitPackedLongArray array ,
			final int index ,
			final long value )
	{
		array.setSigned(
				index ,
				value );

		Assert.assertEquals(
				//expected
				value ,
				//actual
				array.getSigned(
						index ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#getUnsigned(int)}.
	 */
	@Test
	public void testGetUnsigned()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		// initial value is 0
		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						1 );

		array.setUnsigned(
				//index
				0 ,
				//value
				( 1 << 7 ) - 1 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				0 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				( 1 << 7 ) - 1 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_2_a()
	{
		final int allBitsTrue = ( 1 << 7 ) - 1;

		final BitPackedLongArray array =
				BitPackedLongArray.instanceOfLongArrayUnsigned(
						//dataElementWidth
						7 ,
						//values
						new long[] { allBitsTrue , 0 } );


		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						1 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_2_b()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						2 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				allBitsTrue );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		// ---

		array.setUnsigned(
				//index
				0 ,
				//value
				0 );

		array.setUnsigned(
				//index
				1 ,
				//value
				allBitsTrue );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_3_a()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						3 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				0 );

		array.setUnsigned(
				//index
				1 ,
				//value
				allBitsTrue );

		array.setUnsigned(
				//index
				2 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				2 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_3_b()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						3 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				allBitsTrue );

		array.setUnsigned(
				//index
				1 ,
				//value
				0 );

		array.setUnsigned(
				//index
				2 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				2 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_3_c()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						3 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				allBitsTrue );

		array.setUnsigned(
				//index
				1 ,
				//value
				0 );

		array.setUnsigned(
				//index
				2 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				2 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_5_a()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						5 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				allBitsTrue );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				4 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						2 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						3 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						4 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_8_a()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						8 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				allBitsTrue );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				7 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						6 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						7 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_7_12()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						7 ,
						//bitPackedArraySize
						12 );

		final int allBitsTrue = ( 1 << 7 ) - 1;

		array.setUnsigned(
				//index
				0 ,
				//value
				allBitsTrue );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				5 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						4 ) );

		Assert.assertEquals(
				//expected
				allBitsTrue ,
				//actual
				array.getUnsigned(
						//index
						5 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						6 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_1_32_1_0_31()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						1 ,
						//bitPackedArraySize
						32 );

		array.setUnsigned(
				//index
				0 ,
				//value
				1 );

		array.setUnsigned(
				//index
				1 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				1 ,
				//destPos
				0 ,
				//length
				31 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						4 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						5 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						6 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_1_32_0_1_31()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						1 ,
						//bitPackedArraySize
						32 );

		array.setUnsigned(
				//index
				0 ,
				//value
				1 );

		array.setUnsigned(
				//index
				1 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				31 );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						4 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						5 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						6 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_1_2_1_0_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						1 ,
						//bitPackedArraySize
						2 );

		array.setUnsigned(
				//index
				0 ,
				//value
				0 );

		array.setUnsigned(
				//index
				1 ,
				//value
				1 );

		array.selfArrayCopy(
				//srcPos
				1 ,
				//destPos
				0 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	@Test
	public void testSelfArrayCopy_1_3_1_2_1()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						1 ,
						//bitPackedArraySize
						3 );

		array.setUnsigned(
				//index
				0 ,
				//value
				0 );

		array.setUnsigned(
				//index
				1 ,
				//value
				1 );

		array.setUnsigned(
				//index
				2 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				1 ,
				//destPos
				2 ,
				//length
				1 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						2 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	//@Ignore
	@Test
	public void testSelfArrayCopy_1_33_value_0_1_32()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						1 ,
						//bitPackedArraySize
						33 );


		/*
index: 0 value: 0
index: 1 value: 1
index: 2 value: 0
index: 3 value: 1
index: 4 value: 0
index: 5 value: 1
index: 6 value: 0
index: 7 value: 1
index: 8 value: 0
index: 9 value: 1
index: 10 value: 0
index: 11 value: 1
index: 12 value: 0
index: 13 value: 1
index: 14 value: 0
index: 15 value: 1
index: 16 value: 0
index: 17 value: 1
index: 18 value: 0
index: 19 value: 1
index: 20 value: 0
index: 21 value: 1
index: 22 value: 0
index: 23 value: 1
index: 24 value: 0
index: 25 value: 1
index: 26 value: 0
index: 27 value: 1
index: 28 value: 0
index: 29 value: 1
index: 30 value: 0
index: 31 value: 1
index: 32 value: 0
		 */

		array.setUnsigned(
				//index
				0 ,
				//value
				0 );

		array.setUnsigned(
				//index
				1 ,
				//value
				1 );

		array.setUnsigned(
				//index
				2 ,
				//value
				0 );

		array.setUnsigned(
				//index
				3 ,
				//value
				1 );

		array.setUnsigned(
				//index
				4 ,
				//value
				0 );

		array.setUnsigned(
				//index
				5 ,
				//value
				1 );

		array.setUnsigned(
				//index
				6 ,
				//value
				0 );

		array.setUnsigned(
				//index
				7 ,
				//value
				1 );

		array.setUnsigned(
				//index
				8 ,
				//value
				0 );

		array.setUnsigned(
				//index
				9 ,
				//value
				1 );

		array.setUnsigned(
				//index
				10 ,
				//value
				0 );

		array.setUnsigned(
				//index
				11 ,
				//value
				1 );

		array.setUnsigned(
				//index
				12 ,
				//value
				0 );

		array.setUnsigned(
				//index
				13 ,
				//value
				1 );

		array.setUnsigned(
				//index
				14 ,
				//value
				0 );

		array.setUnsigned(
				//index
				15 ,
				//value
				1 );

		array.setUnsigned(
				//index
				16 ,
				//value
				0 );

		array.setUnsigned(
				//index
				17 ,
				//value
				1 );

		array.setUnsigned(
				//index
				18 ,
				//value
				0 );

		array.setUnsigned(
				//index
				19 ,
				//value
				1 );

		array.setUnsigned(
				//index
				20 ,
				//value
				0 );

		array.setUnsigned(
				//index
				21 ,
				//value
				1 );

		array.setUnsigned(
				//index
				22 ,
				//value
				0 );

		array.setUnsigned(
				//index
				23 ,
				//value
				1 );

		array.setUnsigned(
				//index
				24 ,
				//value
				0 );

		array.setUnsigned(
				//index
				25 ,
				//value
				1 );

		array.setUnsigned(
				//index
				26 ,
				//value
				0 );

		array.setUnsigned(
				//index
				27 ,
				//value
				1 );

		array.setUnsigned(
				//index
				28 ,
				//value
				0 );

		array.setUnsigned(
				//index
				29 ,
				//value
				1 );

		array.setUnsigned(
				//index
				30 ,
				//value
				0 );

		array.setUnsigned(
				//index
				31 ,
				//value
				1 );

		array.setUnsigned(
				//index
				32 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				1 ,
				//length
				32 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						2 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						3 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						4 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						5 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						6 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						7 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						8 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						9 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						10 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						11 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						12 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						13 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						14 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						15 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						16 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						17 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						18 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						19 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						20 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						21 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						22 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						23 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						24 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						25 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						26 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						27 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						28 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						29 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						30 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						31 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						32 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	//@Ignore
	@Test
	public void testSelfArrayCopy_1_33_value_0_2_31()
	{
		final BitPackedLongArray array =
				BitPackedLongArray.instanceOf(
						//dataElementWidth
						1 ,
						//bitPackedArraySize
						33 );

		/*
index: 0 value: 0
index: 1 value: 1
index: 2 value: 0
index: 3 value: 1
index: 4 value: 0
index: 5 value: 1
index: 6 value: 0
index: 7 value: 1
index: 8 value: 0
index: 9 value: 1
index: 10 value: 0
index: 11 value: 1
index: 12 value: 0
index: 13 value: 1
index: 14 value: 0
index: 15 value: 1
index: 16 value: 0
index: 17 value: 1
index: 18 value: 0
index: 19 value: 1
index: 20 value: 0
index: 21 value: 1
index: 22 value: 0
index: 23 value: 1
index: 24 value: 0
index: 25 value: 1
index: 26 value: 0
index: 27 value: 1
index: 28 value: 0
index: 29 value: 1
index: 30 value: 0
index: 31 value: 1
index: 32 value: 0
		 */

		array.setUnsigned(
				//index
				0 ,
				//value
				0 );

		array.setUnsigned(
				//index
				1 ,
				//value
				1 );

		array.setUnsigned(
				//index
				2 ,
				//value
				0 );

		array.setUnsigned(
				//index
				3 ,
				//value
				1 );

		array.setUnsigned(
				//index
				4 ,
				//value
				0 );

		array.setUnsigned(
				//index
				5 ,
				//value
				1 );

		array.setUnsigned(
				//index
				6 ,
				//value
				0 );

		array.setUnsigned(
				//index
				7 ,
				//value
				1 );

		array.setUnsigned(
				//index
				8 ,
				//value
				0 );

		array.setUnsigned(
				//index
				9 ,
				//value
				1 );

		array.setUnsigned(
				//index
				10 ,
				//value
				0 );

		array.setUnsigned(
				//index
				11 ,
				//value
				1 );

		array.setUnsigned(
				//index
				12 ,
				//value
				0 );

		array.setUnsigned(
				//index
				13 ,
				//value
				1 );

		array.setUnsigned(
				//index
				14 ,
				//value
				0 );

		array.setUnsigned(
				//index
				15 ,
				//value
				1 );

		array.setUnsigned(
				//index
				16 ,
				//value
				0 );

		array.setUnsigned(
				//index
				17 ,
				//value
				1 );

		array.setUnsigned(
				//index
				18 ,
				//value
				0 );

		array.setUnsigned(
				//index
				19 ,
				//value
				1 );

		array.setUnsigned(
				//index
				20 ,
				//value
				0 );

		array.setUnsigned(
				//index
				21 ,
				//value
				1 );

		array.setUnsigned(
				//index
				22 ,
				//value
				0 );

		array.setUnsigned(
				//index
				23 ,
				//value
				1 );

		array.setUnsigned(
				//index
				24 ,
				//value
				0 );

		array.setUnsigned(
				//index
				25 ,
				//value
				1 );

		array.setUnsigned(
				//index
				26 ,
				//value
				0 );

		array.setUnsigned(
				//index
				27 ,
				//value
				1 );

		array.setUnsigned(
				//index
				28 ,
				//value
				0 );

		array.setUnsigned(
				//index
				29 ,
				//value
				1 );

		array.setUnsigned(
				//index
				30 ,
				//value
				0 );

		array.setUnsigned(
				//index
				31 ,
				//value
				1 );

		array.setUnsigned(
				//index
				32 ,
				//value
				0 );

		array.selfArrayCopy(
				//srcPos
				0 ,
				//destPos
				2 ,
				//length
				31 );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						0 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						1 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						2 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						3 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						4 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						5 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						6 ) );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				array.getUnsigned(
						//index
						31 ) );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				array.getUnsigned(
						//index
						32 ) );
	}

	/**
	 * Test method for {@link BitPackedLongArray#selfArrayCopy}.
	 */
	//@Ignore
	@Test
	public void testSelfArrayCopy_All()
	{
		for ( int dataElementWidth = 1 ; dataElementWidth < Long.SIZE ; dataElementWidth++ )
		{
			System.out.println( "dataElementWidth: " + dataElementWidth );
			for ( int arraySize = 1 ; arraySize <= /*37*//* Primzahl > Integer.SIZE */13 ; arraySize++ )
			{
				System.out.println( "arraySize: " + arraySize );
				for ( int srcPos = 0 ; srcPos < arraySize ; srcPos++ )
				{
					//System.out.println( "srcPos: " + srcPos );
					for ( int dstPos = 0 ; dstPos < arraySize ; dstPos++ )
					{
						//System.out.println( "dstPos: " + dstPos );
						for ( int length = 0 ; length <= arraySize && length <= arraySize - srcPos && length <= arraySize - dstPos ; length++ )
						{
							//System.out.println( "length: " + length );
							for ( final long[] values : new BitPackedLongArrayArraycopyTestValueIterable( dataElementWidth , arraySize ) )
							{
								//System.out.println();
								//System.out.println( "dataElementWidth: " + dataElementWidth );
								//System.out.println( "arraySize: " + arraySize );
								//System.out.println( "srcPos: " + srcPos );
								//System.out.println( "dstPos: " + dstPos );
								//System.out.println( "length: " + length );
								//System.out.println( "values: " + Arrays.toString( values ) );

								final BitPackedLongArray array =
										BitPackedLongArray.instanceOf(
												//dataElementWidth
												dataElementWidth ,
												//bitPackedArraySize
												arraySize );

								// Vergleichs-Array
								final long[] expectets =
										values.clone();

								for ( int index = 0 ; index < arraySize ; index++ )
								{
									array.setUnsigned(
											//index
											index ,
											//value
											values[ index ] );
									//System.out.println( "index: " + index + " value: " + values[ index ] );
								}

								// Vergleichs-Arraycopy
								System.arraycopy(
										//src
										expectets ,
										//srcPos
										srcPos ,
										//dest
										expectets ,
										//destPos
										dstPos ,
										//length
										length );

								array.selfArrayCopy(
										//srcPos
										srcPos  ,
										//dstPos
										dstPos ,
										//length
										length );

								// Assert
								for ( int index = 0 ; index < arraySize ; index++ )
								{
									if ( //expected
											expectets[ index ] !=
											//actual
											array.getUnsigned(
													//index
													index ) )
									{
										System.out.println();
										System.out.println( "dataElementWidth: " + dataElementWidth );
										System.out.println( "arraySize: " + arraySize );
										System.out.println( "srcPos: " + srcPos );
										System.out.println( "dstPos: " + dstPos );
										System.out.println( "length: " + length );
										for ( int valueIndex = 0 ; valueIndex < arraySize ; valueIndex++ )
										{
											System.out.println( "index: " + index + " value: " + values[ valueIndex ] );
										}

										Assert.assertEquals(
												"index " + index ,
												//expected
												expectets[ index ] ,
												//actual
												array.getUnsigned(
														//index
														index ) );
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link BitPackedLongArray#fromArrayCopy}.
	 */
	//@Ignore
	@Test
	public void testFromArrayCopy_All()
	{
		for ( int dataElementWidth = 1 ; dataElementWidth < Integer.SIZE ; dataElementWidth++ )
		{
			System.out.println( "dataElementWidth: " + dataElementWidth );
			for ( int arraySize = 1 ; arraySize <= /*37*//* Primzahl > Integer.SIZE */11 ; arraySize++ )
			{
				System.out.println( "arraySize: " + arraySize );
				for ( int srcPos = 0 ; srcPos < arraySize ; srcPos++ )
				{
					//System.out.println( "srcPos: " + srcPos );
					for ( int dstPos = 0 ; dstPos < arraySize ; dstPos++ )
					{
						//System.out.println( "dstPos: " + dstPos );
						for ( int length = 0 ; length <= arraySize && length <= arraySize - srcPos && length <= arraySize - dstPos ; length++ )
						{
							//System.out.println( "length: " + length );
							for ( final long[] thisValues : new BitPackedLongArrayArraycopyTestValueIterable( dataElementWidth , arraySize ) )
							{
								for ( final long[] fromValues : new BitPackedLongArrayArraycopyTestValueIterable( dataElementWidth , arraySize ) )
								{
									//System.out.println();
									//System.out.println( "dataElementWidth: " + dataElementWidth );
									//System.out.println( "arraySize: " + arraySize );
									//System.out.println( "srcPos: " + srcPos );
									//System.out.println( "dstPos: " + dstPos );
									//System.out.println( "length: " + length );
									//System.out.println( "thisValues: " + Arrays.toString( thisValues ) );

									final BitPackedLongArray thisArray =
											BitPackedLongArray.instanceOf(
													//dataElementWidth
													dataElementWidth ,
													//bitPackedArraySize
													arraySize );

									for ( int index = 0 ; index < arraySize ; index++ )
									{
										thisArray.setUnsigned(
												//index
												index ,
												//value
												thisValues[ index ] );
										//System.out.println( "index: " + index + " value: " + values[ index ] );
									}

									final BitPackedLongArray fromArray =
											BitPackedLongArray.instanceOf(
													//dataElementWidth
													dataElementWidth ,
													//bitPackedArraySize
													arraySize );

									for ( int index = 0 ; index < arraySize ; index++ )
									{
										fromArray.setUnsigned(
												//index
												index ,
												//value
												fromValues[ index ] );
										//System.out.println( "index: " + index + " value: " + values[ index ] );
									}

									// Vergleichs-Arraycopy
									System.arraycopy(
											//src
											fromValues ,
											//srcPos
											srcPos ,
											//dest
											thisValues ,
											//destPos
											dstPos ,
											//length
											length );

									thisArray.innerFromArrayCopy(
											fromArray ,
											//srcPos
											srcPos  ,
											//dstPos
											dstPos ,
											//length
											length );

									// Assert
									for ( int index = 0 ; index < arraySize ; index++ )
									{
										if ( //expected
												thisValues[ index ] !=
												//actual
												thisArray.getUnsigned(
														//index
														index ) )
										{
											System.out.println();
											System.out.println( "dataElementWidth: " + dataElementWidth );
											System.out.println( "arraySize: " + arraySize );
											System.out.println( "srcPos: " + srcPos );
											System.out.println( "dstPos: " + dstPos );
											System.out.println( "length: " + length );
											for ( int valueIndex = 0 ; valueIndex < arraySize ; valueIndex++ )
											{
												System.out.println( "index: " + index + " value: " + thisValues[ valueIndex ] );
											}

											Assert.assertEquals(
													"index " + index ,
													//expected
													//expectets[ index ]
													thisValues[ index ] ,
													//actual
													thisArray.getUnsigned(
															//index
															index ) );
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link BitPackedLongArray#toArrayCopy}.
	 */
	//@Ignore
	@Test
	public void testToArrayCopy_All()
	{
		for ( int dataElementWidth = 1 ; dataElementWidth < Integer.SIZE ; dataElementWidth++ )
		{
			System.out.println( "dataElementWidth: " + dataElementWidth );
			for ( int arraySize = 1 ; arraySize <= /*37*//* Primzahl > Integer.SIZE *//*11*/7 ; arraySize++ )
			{
				System.out.println( "arraySize: " + arraySize );
				for ( int srcPos = 0 ; srcPos < arraySize ; srcPos++ )
				{
					//System.out.println( "srcPos: " + srcPos );
					for ( int dstPos = 0 ; dstPos < arraySize ; dstPos++ )
					{
						//System.out.println( "dstPos: " + dstPos );
						for ( int length = 0 ; length <= arraySize && length <= arraySize - srcPos && length <= arraySize - dstPos ; length++ )
						{
							//System.out.println( "length: " + length );
							for ( final long[] thisValues : new BitPackedLongArrayArraycopyTestValueIterable( dataElementWidth , arraySize ) )
							{
								for ( final long[] toValues : new BitPackedLongArrayArraycopyTestValueIterable( dataElementWidth , arraySize ) )
								{
									//System.out.println();
									//System.out.println( "dataElementWidth: " + dataElementWidth );
									//System.out.println( "arraySize: " + arraySize );
									//System.out.println( "srcPos: " + srcPos );
									//System.out.println( "dstPos: " + dstPos );
									//System.out.println( "length: " + length );
									//System.out.println( "thisValues: " + Arrays.toString( thisValues ) );

									final BitPackedLongArray thisArray =
											BitPackedLongArray.instanceOf(
													//dataElementWidth
													dataElementWidth ,
													//bitPackedArraySize
													arraySize );

									for ( int index = 0 ; index < arraySize ; index++ )
									{
										thisArray.setUnsigned(
												//index
												index ,
												//value
												thisValues[ index ] );
										//System.out.println( "index: " + index + " value: " + values[ index ] );
									}

									final BitPackedLongArray toArray =
											BitPackedLongArray.instanceOf(
													//dataElementWidth
													dataElementWidth ,
													//bitPackedArraySize
													arraySize );

									for ( int index = 0 ; index < arraySize ; index++ )
									{
										toArray.setUnsigned(
												//index
												index ,
												//value
												toValues[ index ] );
										//System.out.println( "index: " + index + " value: " + values[ index ] );
									}

									// Vergleichs-Arraycopy
									System.arraycopy(
											//src
											thisValues ,
											//srcPos
											srcPos ,
											//dest
											toValues ,
											//destPos
											dstPos ,
											//length
											length );

									thisArray.innerToArrayCopy(
											toArray ,
											//srcPos
											srcPos  ,
											//dstPos
											dstPos ,
											//length
											length );

									// Assert
									for ( int index = 0 ; index < arraySize ; index++ )
									{
										if ( //expected
												//expectets[ index ] !=
												thisValues[ index ] !=
												//actual
												thisArray.getUnsigned(
														//index
														index ) )
										{
											System.out.println();
											System.out.println( "dataElementWidth: " + dataElementWidth );
											System.out.println( "arraySize: " + arraySize );
											System.out.println( "srcPos: " + srcPos );
											System.out.println( "dstPos: " + dstPos );
											System.out.println( "length: " + length );
											for ( int valueIndex = 0 ; valueIndex < arraySize ; valueIndex++ )
											{
												System.out.println( "index: " + index + " value: " + thisValues[ valueIndex ] );
											}

											Assert.assertEquals(
													"index " + index ,
													//expected
													//expectets[ index ]
													thisValues[ index ] ,
													//actual
													thisArray.getUnsigned(
															//index
															index ) );
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
