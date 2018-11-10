package de.heinerkuecker.primitive.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit4 test case for {@link BitCopy}.
 *
 * @author Heiner K&uuml;cker
 */
public class BitCopyLongTest
{
	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_0_0_1_1()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						0 ,
						//srcPos
						0 ,
						//dstPos
						1 ,
						//length
						1 );

		final long expected = 0;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_1_0_1_1()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						1 ,
						//srcPos
						0 ,
						//dstPos
						1 ,
						//length
						1 );

		final long expected = 3;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_0_1_2_1()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						0 ,
						//srcPos
						1 ,
						//dstPos
						2 ,
						//length
						1 );

		final long expected = 0;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_1_1_2_1()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						1 ,
						//srcPos
						1 ,
						//dstPos
						2 ,
						//length
						1 );

		final long expected = 1;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_3_0_2_2()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						3 ,
						//srcPos
						0 ,
						//dstPos
						2 ,
						//length
						2 );

		final long expected = 15;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_3_0_3_2()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						0b11 /*3*/ ,
						//srcPos
						0 ,
						//dstPos
						3 ,
						//length
						2 );

		final long expected = 0b11011;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_0_8_9_1()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						0 ,
						//srcPos
						8 ,
						//dstPos
						9 ,
						//length
						1 );

		final long expected = 0;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_256_8_9_1()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						256 ,
						//srcPos
						8 ,
						//dstPos
						9 ,
						//length
						1 );

		final long expected = 512 + 256;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_256_7_8_1()
	{
		final long actual =
				// 0 wird auf die 1 drauf kopiert
				BitCopy.selfBitCopy(
						//src
						256 ,
						//srcPos
						7 ,
						//dstPos
						8 ,
						//length
						1 );

		final long expected = 0;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#selfBitCopy(long, int, int, int)}.
	 */
	@Test
	public void testSelfBitCopy_255_0_8_8()
	{
		final long actual =
				BitCopy.selfBitCopy(
						//src
						//255
						0b11111111 ,
						//srcPos
						0 ,
						//dstPos
						8 ,
						//length
						8 );

		final long expected =
				//65535
				0b1111111111111111;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	@Test
	public void testBitCopy_255_0_8_8()
	{
		final long actual =
				BitCopy.bitCopy(
						//src
						//255
						0b11111111 ,
						//dst
						0 ,
						//srcPos
						0 ,
						//dstPos
						8 ,
						//length
						8 );

		final long expected =
				//65535 - 255
				0b1111111100000000;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	@Test
	public void testBitCopy_b10101010101010101010101010101010_0_1_31()
	{
		final long actual =
				BitCopy.bitCopy(
						//src
						0b10101010101010101010101010101010 ,
						//dst
						0 ,
						//srcPos
						0 ,
						//dstPos
						1 ,
						//length
						31 );

		final long expected =
				0b01010101010101010101010101010100;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	@Test
	public void testBitCopy_minus2147483648_0_1_1()
	{
		final long actual =
				BitCopy.bitCopy(
						//src
						-2147483648 ,
						//dst TODO auch mal andere Werte (zum Beispiel 0) nehmen
						-2147483648 ,
						//srcPos
						0 ,
						//dstPos
						1 ,
						//length
						1 );

		final long expected =
				-2147483648;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	@Test
	public void testBitCopy_minus2147483648_0_1_31()
	{
		final long actual =
				BitCopy.bitCopy(
						//src
						-2147483648 ,
						//dst TODO auch mal andere Werte (zum Beispiel 0) nehmen
						-2147483648 ,
						//srcPos
						0 ,
						//dstPos
						1 ,
						//length
						31 );

		final long expected =
				0;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	@Test
	public void testBitCopy_minus2147483648_0_minus2147483647_0_0()
	{
		final long actual =
				BitCopy.bitCopy(
						//src
						-2147483648 ,
						//dst
						-2147483647 ,
						//srcPos
						0 ,
						//dstPos
						0 ,
						//length
						0 );

		final long expected =
				-2147483647;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	@Test
	public void testBitCopy_minus2_0_1_31()
	{
		final long actual =
				BitCopy.bitCopy(
						//src
						-2L ,
						//dst
						-2147483647L ,
						//srcPos
						0 ,
						//dstPos
						1 ,
						//length
						31 );

		final long expected =
				-3L;

		Assert.assertEquals(
				expected ,
				actual );

		//Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	//@Ignore
	@Test
	public void testSelfBitCopy_All()
	{
		for ( int srcPos = 0 ; srcPos < Long.SIZE ; srcPos++ )
		{
			System.out.println( "srcPos: " + srcPos );
			for ( int dstPos = 0 ; dstPos < Long.SIZE ; dstPos++ )
			{
				System.out.println( "dstPos: " + dstPos );

				if ( srcPos != dstPos )
				{
					//for ( int length = 0 ; length < Long.SIZE ; length++ )
					for ( int length = /*0*/1 ; srcPos + length <= Long.SIZE &&
							dstPos + length <= Long.SIZE ; length++ )
					{
						System.out.println( "length: " + length );
						//if ( srcPos + length <= Long.SIZE && dstPos + length <= Long.SIZE )
						{
							//final long minSrcValue =
							//		Math.max(
							//				(long) Integer.MIN_VALUE ,
							//				( ( (long) Integer.MAX_VALUE ) + 1 ) >> Math.min(
							//						Long.SIZE - ( srcPos + length ) ,
							//						Long.SIZE - ( dstPos + length ) ) );
							//System.out.println( "minSrcValue: " + minSrcValue );
							//System.out.println( "minSrcValue: " + BitUtil.toBinaryString( minSrcValue ) );
							//
							//final long maxSrcValue =
							//		Math.min(
							//				(long) Integer.MAX_VALUE ,
							//				1L << Math.max(
							//						srcPos ,
							//						dstPos ) );
							//System.out.println( "maxSrcValue: " + maxSrcValue );
							//System.out.println( "maxSrcValue: " + BitUtil.toBinaryString( maxSrcValue ) );

							//for ( int srcValue = /*Integer.MIN_VALUE*//*0*//*Math.toIntExact( -maxSrc )*/(int) minSrcValue ; srcValue <= /*Integer.MAX_VALUE*/maxSrcValue ; srcValue++ )
							final BitCopyTestValueLongIterator testValueIterator = new BitCopyTestValueLongIterator();
							while ( testValueIterator.hasNext() )
							{
								final long srcValue = testValueIterator.next();
								//System.out.println( "srcValue: " + srcValue );
								//System.out.println( "srcPos: " + srcPos );
								//System.out.println( "dstPos: " + dstPos );
								//System.out.println( "length: " + length );

								final boolean[] srcValueReferenceArr =
										// TODO Array nur einmal erzeugen, behalten und immer wieder neu belegen
										convertLongBitsToBooleanArray(
												srcValue );
								//System.out.println( "srcValueReferenceArr: " + Arrays.toString( srcReference ) );

								// reference copy
								System.arraycopy(
										//src
										srcValueReferenceArr ,
										srcPos,
										//dest
										srcValueReferenceArr ,
										dstPos ,
										length );
								//System.out.println( "expectets: " + Arrays.toString( srcReference ) );

								long expected = 0;
								{
									long mask = 1;
									for ( int index = 0 ; index < Long.SIZE ; index++ )
									{
										if ( srcValueReferenceArr[ index ] )
										{
											expected =
													//BitUtil.setBit(
													//		expected ,
													//		index );
													expected | mask;
											mask = mask << 1;
										}
									}
									//System.out.println( "expected: " + expected );
								}

								final long actual =
										//BitCopy.bitCopy(
										BitCopy.selfBitCopy(
												srcValue ,
												//dst TODO auch mal andere Werte (zum Beispiel 0) nehmen
												//src ,
												srcPos ,
												dstPos ,
												length );
								//System.out.println( "actual: " + actual );

								long mask = 1;
								for ( int index = 0 ; index < Long.SIZE ; index++ )
								{
									if (
											//expected
											srcValueReferenceArr[ index ] !=
											//actual
											//BitUtil.isBit(
											//		//intValue
											//		actual ,
											//		index )
											( ( actual & mask ) != 0 )
											)
									{
										System.out.println( "srcValue: " + srcValue );
										System.out.println( "srcPos: " + srcPos );
										System.out.println( "dstPos: " + dstPos );
										System.out.println( "length: " + length );
										System.out.println( "expected: " + expected );
										System.out.println( "actual: " + actual );

										Assert.fail( "actual: " + actual );
									}
									mask = mask << 1;
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link BitCopy#bitCopy}.
	 */
	//@Ignore
	@Test
	public void testBitCopy_All_All()
	{
		final long[] testValues =
			{
					 0L ,
					~0L ,
					 0b1010101010101010101010101010101010101010101010101010101010101010L ,
					~0b1010101010101010101010101010101010101010101010101010101010101010L ,
					 0b1100110011001100110011001100110011001100110011001100110011001100L ,
					~0b1100110011001100110011001100110011001100110011001100110011001100L ,
					 0b1110001110001110001110001110001110001110001110001110001110001110L ,
					~0b1110001110001110001110001110001110001110001110001110001110001110L ,
					 0b1111000011110000111100001111000011110000111100001111000011110000L ,
					~0b1111000011110000111100001111000011110000111100001111000011110000L
			};
		for ( int srcPos = 0 ; srcPos < Long.SIZE ; srcPos++ )
		{
			System.out.println( "srcPos: " + srcPos );
			for ( int dstPos = 0 ; dstPos < Long.SIZE ; dstPos++ )
			{
				System.out.println( "dstPos: " + dstPos );

				if ( srcPos != dstPos )
				{
					//for ( int length = 0 ; length < Long.SIZE ; length++ )
					for ( int length = /*0*/1 ; srcPos + length <= Long.SIZE &&
							dstPos + length <= Long.SIZE ; length++ )
					{
						System.out.println( "length: " + length );
						if ( srcPos + length <= Long.SIZE &&
								dstPos + length <= Long.SIZE )
						{
							//for ( int srcValue = Integer.MIN_VALUE ; srcValue <= Integer.MAX_VALUE ; srcValue++ )
							//final BitCopyTestValueLongIterator srcValueIterator = new BitCopyTestValueLongIterator();
							//while ( srcValueIterator.hasNext() )
							for ( final long srcValue : testValues )
							{
								//final int srcValue = srcValueIterator.next();
								//for ( int dst = Integer.MIN_VALUE ; dst <= Integer.MAX_VALUE ; dst++ )
								//final BitCopyTestValueLongIterator dstValueIterator = new BitCopyTestValueLongIterator();
								//while ( dstValueIterator.hasNext() )
								for ( final long dstValue : testValues )
								{
									//final int dstValue = dstValueIterator.next();
									//System.out.println( "srcValue: " + srcValue );
									//System.out.println( "srcPos: " + srcPos );
									//System.out.println( "dstPos: " + dstPos );
									//System.out.println( "length: " + length );

									final boolean[] srcValueReferenceArr =
											// TODO Array nur einmal erzeugen, behalten und immer wieder neu belegen
											convertLongBitsToBooleanArray(
													srcValue );

									final boolean[] dstValueReferenceArr =
											// TODO Array nur einmal erzeugen, behalten und immer wieder neu belegen
											convertLongBitsToBooleanArray(
													dstValue );

									// reference copy
									System.arraycopy(
											//src
											srcValueReferenceArr ,
											srcPos,
											//dest
											dstValueReferenceArr ,
											dstPos ,
											length );

									long expected = 0;
									for ( int index = 0 ; index < Long.SIZE ; index++ )
									{
										if ( dstValueReferenceArr[ index ] )
										{
											expected =
													BitUtil.setBit(
															expected ,
															index );
										}
									}
									//System.out.println( "expected: " + expected );

									final long actual =
											BitCopy.bitCopy(
													srcValue ,
													dstValue ,
													srcPos ,
													dstPos ,
													length );

									for ( int index = 0 ; index < Long.SIZE ; index++ )
									{
										if (
												//expected
												dstValueReferenceArr[ index ] !=
												//actual
												// TODO mask verwenden
												BitUtil.isBit(
														//intValue
														actual ,
														index ) )
										{
											System.out.println( "srcValue: " + srcValue );
											System.out.println( "srcPos: " + srcPos );
											System.out.println( "dstValue: " + dstValue );
											System.out.println( "dstPos: " + dstPos );
											System.out.println( "length: " + length );
											System.out.println( "expected: " + expected );
											System.out.println( "actual: " + actual );

											Assert.fail();
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

	private static boolean[] convertLongBitsToBooleanArray(
			final long bitsLong )
	{
		final boolean[] booleanArray = new boolean[ Long.SIZE ];

		long mask = 1;
		for ( int index = 0 ; index < Long.SIZE ; index++ )
		{
			final boolean bit =
					//BitUtil.isBit(
					//		bitsInt ,
					//		index )
					( ( bitsLong & mask ) != 0 )
					;

			booleanArray[ index ] = bit;
			mask = mask << 1;
		}

		return booleanArray;
	}

}
