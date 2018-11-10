package de.heinerkuecker.primitive.array;

/**
 * Klasse zum Speichern von
 * ganzzahligen Daten mit einer
 * Breite, die kein ganzzahliges
 * Vielfaches von 8 ist,
 * zum Beispiel von 7-Bit-Zahlen.
 *
 * Ziel ist das Einsparen von
 * Speicher durch das Benutzen
 * nur der notwendigen Bits
 * statt des Typs mit der nächsthöheren
 * ganzzahlig vielfachen Größe von 8 Bits.
 *
 * Weil in Java sowieso ein
 * 32-Bit-/4-Byte-Alignment im Speicher
 * erfolgt, wird ein int-Array
 * als interner Speicher verwendet.
 *
 * Beim Schreiben {@link #setUnsigned(int, int)}
 * und Lesen{@link #getUnsigned(int)} wird
 * der zu speichernde Wert um die
 * notwendige Anzahl Bits verschoben
 * und beim Überschreiten der Grenze
 * zum nächsten internen int-Array-Wert
 * auf 2 int-Array-Werte aufgeteilt.
 *
 * Um im speziellen Fall der extremen
 * Speichereinsparung mit fester
 * bekannter Datenbreite noch den
 * Speicherplatz für Objekte dieser
 * Klasse zu sparen, kann man die
 * Methoden zu statischen Methoden
 * statt Instanz-Methoden umwandeln
 * und die erforderlichen Informationen
 * wie {@link #dataElementWidth}
 * per Parameter übergeben oder
 * konstant kodieren.
 *
 * TODO Prinzipiell kann durch die
 * Speicherung von mehr als einem
 * Wert in einem int eine
 * Array-Größe von mehr als
 * {@link Integer#MAX_VALUE}
 * erreicht werden.
 * Demzufolge könnten die im
 * Konstruktor übergebene Größe
 * sowie der Index in den get-
 * und set-Methoden long sein.
 *
 * @author Heiner K&uuml;cker
 *
 * TODO mal ansehen:
 * https://lemire.me/blog/2012/03/06/how-fast-is-bit-packing/
 * https://www.cs.waikato.ac.nz/~tcs/COMP317/bitpacking.html
 * https://github.com/svzdvd/bitutils
 * https://lemire.me/blog/2012/03/06/how-fast-is-bit-packing/
 * https://lemire.me/blog/2013/07/08/fast-integer-compression-in-java/
 * https://github.com/lemire/JavaFastPFOR
 * http://www.kinematicsoup.com/news/2016/9/6/data-compression-bit-packing-101
 * https://www.ibm.com/support/knowledgecenter/en/SSYKE2_7.1.0/com.ibm.java.aix.71.doc/user/packed_optimizing.html
 *
 * https://liveramp.com/engineering/memory-efficient-sparse-bitsets/
 * https://code.google.com/archive/p/compressedbitset/
 * https://github.com/lemire/javaewah
 *
 * http://java-performance.com/
 * http://java-performance.info/use-case-how-to-compact-a-long-to-long-mapping/
 *
 * https://stackoverflow.com/questions/35772813/varying-value-bit-length-array
 * https://stackoverflow.com/questions/3878890/how-to-store-many-small-integers-into-a-byte-array
 * https://github.com/lemire/javaewah
 * https://github.com/Autopawn/gelasia-compacter
 * https://stackoverflow.com/questions/5935239/best-data-structure-to-store-lots-one-bit-data
 * https://stackoverflow.com/questions/tagged/bit-packing
 * https://stackoverflow.com/questions/23252933/java-resizable-bit-array
 *
 * https://www.google.com/search?q=java+bit+packed+array
 * https://stackoverflow.com/questions/26730955/bit-packing-in-java
 * https://lingpipe-blog.com/2010/07/28/big-bit-packed-array-abstraction-for-java-c-etc/
 * http://java-performance.info/string-packing-converting-characters-to-bytes/
 *
 * https://www.google.com/search?q=compute+memory+need+for+java+object
 * https://www.baeldung.com/java-size-of-object
 * https://stackoverflow.com/questions/757300/programmatically-calculate-memory-occupied-by-a-java-object-including-objects-it
 * http://btoddb-java-sizing.blogspot.com/
 */
abstract public class BitPackedIntArray
{
	/**
	 * Ob die ersten Daten auf der
	 * Least-Significant-Bit-Seite
	 * im Byte sitzen.
	 */
	// TODO Interner Wert, erst mal konstant: private final boolean startLeastSignificantBit = true;

	//public final int dataElementWidth;
	// Versuch über konstante Werte je konkreter Klasse eine bessere Performance zu erreichen
	abstract public int dataElementWidth();

	public final int bitPackedArraySize;

	//private final int bitMask;
	// bessere Performance über konstante Werte je konkreter Klasse
	private final int bitMask()
	{
		// der JIT sieht dies als konstanten Wert an und optimiert die wiederholte Berechnung weg
		return ( 1 << dataElementWidth() ) - 1;
	}

	private final int[] data;

	protected BitPackedIntArray(
			//final int dataElementWidth ,
			final int bitPackedArraySize )
	{
		//if ( dataElementWidth < 1 )
		//{
		//	throw new IllegalArgumentException( "dataElementWidth: " + dataElementWidth );
		//}
		//if ( dataElementWidth >= Integer.SIZE )
		//{
		//	throw new IllegalArgumentException( "dataElementWidth: " + dataElementWidth );
		//}

		if ( bitPackedArraySize < 1 )
		{
			throw new IllegalArgumentException( "bitPackedArraySize: " + bitPackedArraySize );
		}

		//this.dataElementWidth = dataElementWidth;

		this.bitPackedArraySize = bitPackedArraySize;

		//this.bitMask = ( 1 << dataElementWidth ) - 1;

		final int dataArrayLength =
				computeIntDataArrayLength(
						dataElementWidth() ,
						bitPackedArraySize );

		this.data = new int[ dataArrayLength ];
	}

	/**
	 * Diese Methode ist nur wegen Testbarkeit ausserhalb sichtbar.
	 *
	 * @param dataElementWidth
	 * @param bitPackedArraySize
	 * @return
	 */
	static int computeIntDataArrayLength(
			final int dataElementWidth ,
			final int bitPackedArraySize )
	{
		// TODO alle Math.toIntExact nach kompletten Test durch ungeprüften cast ersetzen für bessere Performance
		int dataArrayLength =
				Math.toIntExact( ( ( (long) bitPackedArraySize ) * dataElementWidth )
						// ganzzahlige Division mit Abrunden bei positivem gebrochenem Ergebnis
						/ Integer.SIZE );

		if ( ( ( (long) dataArrayLength ) * Integer.SIZE ) / dataElementWidth < bitPackedArraySize )
			// es wurde abgerundet
		{
			// aufrunden der Größe bei gebrochener Anzahl
			dataArrayLength++;
		}

		return dataArrayLength;
	}

	/**
	 * Set unsigned value.
	 *
	 * @param index index of virtual array of non octal width data
	 * @param value value of non octal width data without consideration highest bit as sign
	 * @throws IllegalArgumentException if value out of range for defined width
	 */
	public void setUnsigned(
			final int index ,
			final int value )
	{
		//System.out.println( "bitMask " + toBinaryString( bitMask ) );
		//System.out.println( "value " + toBinaryString( value , dataElementWidth ) );
		if ( index < 0 || index >= bitPackedArraySize )
		{
			throw new ArrayIndexOutOfBoundsException( index );
		}

		if ( value < 0 || value > bitMask() )
		{
			throw new IllegalArgumentException( "value: " + value );
		}

		// falls höherwertigere Bits, als in der Wert-Breite angegeben, gesetzt waren, diese hier löschen
		// TODO nicht mehr notwendig durch Parameter-Prüfung oben: final int maskedValue = value & andBitMask;

		final long firstBitIndex = index * dataElementWidth();
		final long lastBitIndex  = ( ( index + 1 ) * dataElementWidth() ) - 1;

		final int firstIntIndex = Math.toIntExact( firstBitIndex / Integer.SIZE );
		final int lastIntIndex  = Math.toIntExact( lastBitIndex  / Integer.SIZE );

		final int firstBitInIntValueIndex = Math.toIntExact( firstBitIndex % Integer.SIZE );

		{
			final int shiftedFirstValue = /*maskedValue*/value << firstBitInIntValueIndex;
			//System.out.println( "shiftedFirstValue " + toBinaryString( shiftedFirstValue ) );

			// Maske zum Löschen der Bits des vorher gesetzten Wertes
			final int shiftedFirstResetMask = ~( bitMask() << firstBitInIntValueIndex );
			//System.out.println( "shiftedFirstResetMask " + toBinaryString( shiftedFirstResetMask ) );

			final int readedFirstIntValue = data[ firstIntIndex ];

			final int writeFirstIntValue = ( readedFirstIntValue & shiftedFirstResetMask ) | shiftedFirstValue;
			//System.out.println( "writeFirstIntValue " + toBinaryString( writeFirstIntValue ) );

			data[ firstIntIndex ] = writeFirstIntValue;
		}

		if ( firstIntIndex == lastIntIndex )
		{
			return;
		}

		final int lastBitInIntValueIndex = Math.toIntExact( lastBitIndex % Integer.SIZE );

		final int shiftedLastValue = /*maskedValue*/value >>> ( dataElementWidth() - 1 ) - lastBitInIntValueIndex;
		//System.out.println( "shiftedLastValue " + toBinaryString( shiftedLastValue ) );

		// Maske zum Löschen der Bits des vorher gesetzten Wertes
		final int shiftedLastResetMask = ~( bitMask() >>> ( dataElementWidth() - 1 ) - lastBitInIntValueIndex );
		//System.out.println( "shiftedLastResetMask " + toBinaryString( shiftedLastResetMask ) );

		final int readedLastIntValue = data[ lastIntIndex ];

		final int writeLastIntValue = ( readedLastIntValue & shiftedLastResetMask ) | shiftedLastValue;
		//System.out.println( "writeLastIntValue " + toBinaryString( writeLastIntValue ) );

		data[ lastIntIndex ] = writeLastIntValue;
	}

	/**
	 * Get unsigned value.
	 *
	 * Zurückgeben des gespeicherten Wertes als int.
	 * Achtung, Vorzeichen wird nicht wie in int
	 * gesetzt.
	 *
	 * @param index index of virtual array of non octal width data
	 * @return value of non octal width data without consideration highest bit as sign
	 */
	public int getUnsigned(
			final int index )
	{
		if ( index < 0 || index >= bitPackedArraySize )
		{
			throw new ArrayIndexOutOfBoundsException( index );
		}

		final long firstBitIndex = ( (long) index ) * dataElementWidth();
		final long lastBitIndex  = ( ( ( (long) index ) + 1 ) * dataElementWidth() ) - 1;

		final int firstIntIndex = Math.toIntExact( firstBitIndex / Integer.SIZE );
		final int lastIntIndex  = Math.toIntExact( lastBitIndex  / Integer.SIZE );

		final int firstBitInIntValueIndex = Math.toIntExact( firstBitIndex % Integer.SIZE );

		final int readedFirstIntValue = data[ firstIntIndex ];
		//System.out.println( "readedFirstIntValue " + toBinaryString( readedFirstIntValue ) );

		final int shiftedFirstValue = ( readedFirstIntValue >>> firstBitInIntValueIndex );
		//System.out.println( "shiftedFirstValue " + toBinaryString( shiftedFirstValue ) );

		final int maskedShiftedFirstValue = shiftedFirstValue & bitMask();
		//System.out.println( "maskedShiftedFirstValue " + toBinaryString( maskedShiftedFirstValue ) );

		if ( firstIntIndex == lastIntIndex )
		{
			return maskedShiftedFirstValue;
		}

		final int readedLastIntValue = data[ lastIntIndex ];
		final int lastBitInIntValueIndex = Math.toIntExact( lastBitIndex % Integer.SIZE );

		// Maske zum Ausfiltern der Bits des vorher gesetzten Wertes
		final int shiftedLastMask = bitMask() >>> ( dataElementWidth() - 1 ) - lastBitInIntValueIndex;
		//System.out.println( "shiftedLastMask " + toBinaryString( shiftedLastMask ) );

		final int shiftedLastValue = ( readedLastIntValue & shiftedLastMask ) << ( dataElementWidth() - 1 ) - lastBitInIntValueIndex;
		//System.out.println( "shiftedLastValue " + toBinaryString( shiftedLastValue ) );

		final int value = shiftedFirstValue | shiftedLastValue;
		//System.out.println( "value " + toBinaryString( value , dataElementWidth ) );
		return value;
	}

	/**
	 * Set signed value.
	 *
	 * @param index index of virtual array of non octal width data
	 * @param value signed int value in the value range for defined width, highest bit is the sign
	 * @throws IllegalArgumentException if value out of range for defined width
	 */
	public void setSigned(
			final int index ,
			final int value )
	{
		final int maxSignedValue = bitMask() / 2;

		if ( value > maxSignedValue )
		{
			throw new IllegalArgumentException( "value: " + value );
		}

		if ( value < 0 )
		{
			if ( value < -maxSignedValue )
			{
				throw new IllegalArgumentException( "value: " + value );
			}

			// TODO eine zusätzliche private Methode ohne Parameter-Prüfung machen und aufrufen, um doppelte Parameter-Prüfung zu sparen
			setUnsigned(
					index ,
					// negative Zahl führt zu Subtraktion
					maxSignedValue - value );
		}
		else
		{
			// TODO eine zusätzliche private Methode ohne Parameter-Prüfung machen und aufrufen, um doppelte Parameter-Prüfung zu sparen
			setUnsigned(
					index ,
					value );
		}
	}

	/**
	 * Get signed value.
	 *
	 * @param index index of virtual array of non octal width data
	 * @return signed int value in the value range for defined width, highest bit is the sign
	 */
	public int getSigned(
			final int index )
	{
		final int maxSignedValue = bitMask() / 2;

		final int unsigned =
				getUnsigned(
						index );

		if ( unsigned > maxSignedValue )
			// negative Zahl
		{
			return -( unsigned - ( maxSignedValue ) );
		}
		return unsigned;
	}

	/**
	 * Copy values in self primitive int array
	 * like {@link System#arraycopy(Object, int, Object, int, int)}.
	 * Source array and destination array is this array.
	 *
	 * @param srcPos starting position in the source array.
	 * @param dstPos starting position in the destination data.
	 * @param length the number of array elements to be copied.
	 */
	public void selfArrayCopy(
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		if ( srcPos < 0 )
		{
			throw new IllegalArgumentException( "srcPos: " + srcPos );
		}

		if ( srcPos >= this.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "srcPos: " + srcPos );
		}

		if ( srcPos + length > this.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "srcPos + length: " + ( srcPos + length ) );
		}

		if ( dstPos < 0 )
		{
			throw new IllegalArgumentException( "dstPos: " + dstPos );
		}

		if ( dstPos + length > this.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "dstPos + length: " + ( dstPos + length ) );
		}

		if ( length < 0 )
		{
			throw new IllegalArgumentException( "length: " + length );
		}

		if ( length == 0 )
		{
			return;
		}

		if ( srcPos == dstPos )
		{
			return;
		}

		final long srcStartFirstBitIndex = ( (long) srcPos ) * dataElementWidth();
		final long dstStartFirstBitIndex = ( (long) dstPos ) * dataElementWidth();

		final long bitLength = ( (long) length ) * dataElementWidth();
		final long bitOffset = dstStartFirstBitIndex - srcStartFirstBitIndex;
		final long srcEndLastBitIndex = srcStartFirstBitIndex + bitLength - 1;

		if ( srcPos > dstPos )
			// ziel kleiner als quelle
			// mit dem kleinsten Index beginnen
		{
			long currentSrcEndBitIndex = srcStartFirstBitIndex;
			long lastSrcEndBitIndex = currentSrcEndBitIndex - 1;

			while ( currentSrcEndBitIndex <= srcEndLastBitIndex )
			{
				for ( ; currentSrcEndBitIndex < srcEndLastBitIndex ; currentSrcEndBitIndex++ )
					// TODO statt for-schleife ueber positionen mit Math.min die naechste Position finden
				{
					if ( bitIndexIsBitInIntIndex31( currentSrcEndBitIndex ) )
						// src int limit reached
					{
						break;
					}

					final long currentDstBitIndex = currentSrcEndBitIndex + bitOffset;
					if ( bitIndexIsBitInIntIndex31( currentDstBitIndex ) )
						// dst int limit reached
					{
						break;
					}
				}

				//currentSrcEndBitIndex =
				//		Math.min(
				//				srcEndLastBitIndex ,
				//				Math.min(
				//						// auf volle 31 Bit erhoeht
				//						currentSrcEndBitIndex | 0x1F ,
				//						// auf volle 31 Bit erhoeht
				//						( currentSrcEndBitIndex + bitOffset ) | 0x1F ) );

				final int  currentSrcEndIntIndex = Math.toIntExact( currentSrcEndBitIndex / Integer.SIZE );
				final long currentSrcStartBitIndex = lastSrcEndBitIndex + 1;
				final int  currentSrcStartBitInIntIndex = Math.toIntExact( currentSrcStartBitIndex % Integer.SIZE );
				final int  currentPartBitLength = Math.toIntExact( currentSrcEndBitIndex - lastSrcEndBitIndex );
				final long currentDstStartBitIndex = currentSrcStartBitIndex + bitOffset;
				final int  currentDstIntIndex = Math.toIntExact( currentDstStartBitIndex / Integer.SIZE );
				final int  currentDstBitInIntIndex = Math.toIntExact( currentDstStartBitIndex % Integer.SIZE );

				final int readedValue = data[ currentSrcEndIntIndex ];
				//System.out.println( "readedValue " + toBinaryString( readedValue ) );

				final int writeValue =
						BitCopy.bitCopy(
								//src
								readedValue ,
								//dst
								data[ currentDstIntIndex ] ,
								//srcPos
								currentSrcStartBitInIntIndex ,
								//dstPos
								currentDstBitInIntIndex ,
								//length
								currentPartBitLength );
				//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

				data[ currentDstIntIndex ] = writeValue;

				lastSrcEndBitIndex = currentSrcEndBitIndex;
				currentSrcEndBitIndex++;
			}
			return;
		}

		//if ( dstPos > srcPos )
			// ziel grösser als quelle
			// mit dem grössten Index beginnen
			// TODO hier immer satisfied
		{
			long currentSrcBitIndex = srcEndLastBitIndex;
			long lastSrcBitIndex = currentSrcBitIndex + 1;

			while ( currentSrcBitIndex >= srcStartFirstBitIndex )
			{
				for ( ; currentSrcBitIndex > srcStartFirstBitIndex ; currentSrcBitIndex-- )
					// TODO statt for-schleife ueber positionen mit Math.max die naechste Position finden
				{
					if ( bitIndexIsBitInIntIndex0( currentSrcBitIndex ) )
						// src int limit reached
					{
						break;
					}

					final long currentDstBitIndex = currentSrcBitIndex + bitOffset;
					if ( bitIndexIsBitInIntIndex0( currentDstBitIndex ) )
						// dst int limit reached
					{
						break;
					}
				}

				//currentSrcBitIndex =
				//		Math.max(
				//				srcStartFirstBitIndex ,
				//				Math.max(
				//						// auf naechst-kleinere Vielfache von 32 verkleinert
				//						currentSrcBitIndex & ~ 0x1F ,
				//						// auf naechst-kleinere Vielfache von 32 verkleinert
				//						( currentSrcBitIndex + bitOffset ) & ~ 0x1F ) );

				final int  currentSrcIntIndex = Math.toIntExact( currentSrcBitIndex / Integer.SIZE );
				final int  currentSrcBitInIntIndex = Math.toIntExact( currentSrcBitIndex % Integer.SIZE );
				final int  currentPartBitLength = Math.toIntExact( lastSrcBitIndex - currentSrcBitIndex );
				final long currentDstBitIndex = currentSrcBitIndex + bitOffset;
				final int  currentDstIntIndex = Math.toIntExact( currentDstBitIndex / Integer.SIZE );
				final int  currentDstBitInIntIndex = Math.toIntExact( currentDstBitIndex % Integer.SIZE );

				final int readedValue = data[ currentSrcIntIndex ];
				//System.out.println( "readedValue " + toBinaryString( readedValue ) );

				final int writeValue =
						BitCopy.bitCopy(
								//src
								readedValue ,
								//dst
								data[ currentDstIntIndex ] ,
								//srcPos
								currentSrcBitInIntIndex ,
								//dstPos
								currentDstBitInIntIndex ,
								//length
								currentPartBitLength );
				//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

				data[ currentDstIntIndex ] = writeValue;

				lastSrcBitIndex = currentSrcBitIndex;
				currentSrcBitIndex--;
			}
			return;
		}
	}

	private static boolean bitIndexIsBitInIntIndex0(
			final long value )
	{
		return ( value & ( Integer.SIZE - 1 ) ) == 0;
	}

	private static boolean bitIndexIsBitInIntIndex31(
			final long value )
	{
		return ( value & ( Integer.SIZE - 1 ) ) == ( Integer.SIZE - 1 );
	}

	/**
	 * Copy values from specified {@link BitPackedIntArray}
	 * to this {@link BitPackedIntArray}
	 * like {@link System#arraycopy(Object, int, Object, int, int)}.
	 * Destination array is this array.
	 *
	 * @param srcPos starting position in the source array.
	 * @param dstPos starting position in the destination data.
	 * @param length the number of array elements to be copied.
	 */
	protected void innerFromArrayCopy(
			final BitPackedIntArray from ,
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		if ( from.dataElementWidth() != this.dataElementWidth() )
		{
			throw new IllegalArgumentException( "incomptible data element width: from: " + from.dataElementWidth() + " this: " + this.dataElementWidth() );
		}

		if ( srcPos < 0 )
		{
			throw new IllegalArgumentException( "srcPos: " + srcPos );
		}

		if ( srcPos >= from.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "srcPos: " + srcPos + " maximum expected: " + ( from.bitPackedArraySize - 1 ) );
		}

		if ( srcPos + length > from.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "srcPos + length: " + ( srcPos + length ) );
		}

		if ( dstPos < 0 )
		{
			throw new IllegalArgumentException( "dstPos: " + dstPos );
		}

		if ( dstPos + length > this.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "dstPos + length: " + ( dstPos + length ) );
		}

		if ( length < 0 )
		{
			throw new IllegalArgumentException( "length: " + length );
		}

		if ( length == 0 )
		{
			return;
		}

		// TODO kann man den nachfolgenden Code in innerFromArrayCopy und innerToArrayCopy zusammenfassen?
		final long srcStartFirstBitIndex = ( (long) srcPos ) * dataElementWidth();
		final long dstStartFirstBitIndex = ( (long) dstPos ) * dataElementWidth();

		final long bitLength = ( (long) length ) * dataElementWidth();
		final long bitOffset = dstStartFirstBitIndex - srcStartFirstBitIndex;
		final long srcEndLastBitIndex = srcStartFirstBitIndex + bitLength - 1;

		long currentSrcEndBitIndex = srcStartFirstBitIndex;
		long lastSrcEndBitIndex = currentSrcEndBitIndex - 1;

		while ( currentSrcEndBitIndex <= srcEndLastBitIndex )
		{
			for ( ; currentSrcEndBitIndex < srcEndLastBitIndex ; currentSrcEndBitIndex++ )
				// TODO statt for-schleife ueber positionen mit Math.min die naechste Position finden
			{
				if ( bitIndexIsBitInIntIndex31( currentSrcEndBitIndex ) )
					// src int limit reached
				{
					break;
				}

				final long currentDstBitIndex = currentSrcEndBitIndex + bitOffset;
				if ( bitIndexIsBitInIntIndex31( currentDstBitIndex ) )
					// dst int limit reached
				{
					break;
				}
			}

			//currentSrcEndBitIndex =
			//		Math.min(
			//				srcEndLastBitIndex ,
			//				Math.min(
			//						// auf volle 31 Bit erhoeht
			//						currentSrcEndBitIndex | 0x1F ,
			//						// auf volle 31 Bit erhoeht
			//						( currentSrcEndBitIndex + bitOffset ) | 0x1F ) );

			final int  currentSrcEndIntIndex = Math.toIntExact( currentSrcEndBitIndex / Integer.SIZE );
			final long currentSrcStartBitIndex = lastSrcEndBitIndex + 1;
			final int  currentSrcStartBitInIntIndex = Math.toIntExact( currentSrcStartBitIndex % Integer.SIZE );
			final int  currentPartBitLength = Math.toIntExact( currentSrcEndBitIndex - lastSrcEndBitIndex );
			final long currentDstStartBitIndex = currentSrcStartBitIndex + bitOffset;
			final int  currentDstIntIndex = Math.toIntExact( currentDstStartBitIndex / Integer.SIZE );
			final int  currentDstBitInIntIndex = Math.toIntExact( currentDstStartBitIndex % Integer.SIZE );

			final int readedValue = from.data[ currentSrcEndIntIndex ];
			//System.out.println( "readedValue " + toBinaryString( readedValue ) );

			final int writeValue =
					BitCopy.bitCopy(
							//src
							readedValue ,
							//dst
							data[ currentDstIntIndex ] ,
							//srcPos
							currentSrcStartBitInIntIndex ,
							//dstPos
							currentDstBitInIntIndex ,
							//length
							currentPartBitLength );
			//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

			this.data[ currentDstIntIndex ] = writeValue;

			lastSrcEndBitIndex = currentSrcEndBitIndex;
			currentSrcEndBitIndex++;
		}

		//throw new RuntimeException( "not implemented" );
	}

	/**
	 * Copy values to specified {@link BitPackedIntArray}
	 * to this {@link BitPackedIntArray}
	 * like {@link System#arraycopy(Object, int, Object, int, int)}.
	 * Source array is this array.
	 *
	 * @param srcPos starting position in the source array.
	 * @param dstPos starting position in the destination data.
	 * @param length the number of array elements to be copied.
	 */
	protected void innerToArrayCopy(
			final BitPackedIntArray to ,
			final int srcPos ,
			final int dstPos ,
			final int length )
	{
		if ( to.dataElementWidth() != this.dataElementWidth() )
		{
			throw new IllegalArgumentException( "incomptible data element width: to: " + to.dataElementWidth() + " this: " + this.dataElementWidth() );
		}

		if ( srcPos < 0 )
		{
			throw new IllegalArgumentException( "srcPos: " + srcPos );
		}

		if ( srcPos >= this.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "srcPos: " + srcPos + " maximum expected: " + ( this.bitPackedArraySize - 1 ) );
		}

		if ( srcPos + length > this.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "srcPos + length: " + ( srcPos + length ) );
		}

		if ( dstPos < 0 )
		{
			throw new IllegalArgumentException( "dstPos: " + dstPos );
		}

		if ( dstPos + length > to.bitPackedArraySize )
		{
			throw new IndexOutOfBoundsException( "dstPos + length: " + ( dstPos + length ) );
		}

		if ( length < 0 )
		{
			throw new IllegalArgumentException( "length: " + length );
		}

		if ( length == 0 )
		{
			return;
		}

		// TODO kann man den nachfolgenden Code in innerFromArrayCopy und innerToArrayCopy zusammenfassen?
		final long srcStartFirstBitIndex = ( (long) srcPos ) * dataElementWidth();
		final long dstStartFirstBitIndex = ( (long) dstPos ) * dataElementWidth();

		final long bitLength = ( (long) length ) * dataElementWidth();
		final long bitOffset = dstStartFirstBitIndex - srcStartFirstBitIndex;
		final long srcEndLastBitIndex = srcStartFirstBitIndex + bitLength - 1;

		long currentSrcEndBitIndex = srcStartFirstBitIndex;
		long lastSrcEndBitIndex = currentSrcEndBitIndex - 1;

		while ( currentSrcEndBitIndex <= srcEndLastBitIndex )
		{
			for ( ; currentSrcEndBitIndex < srcEndLastBitIndex ; currentSrcEndBitIndex++ )
				// TODO statt for-schleife ueber positionen mit Math.min die naechste Position finden
			{
				if ( bitIndexIsBitInIntIndex31( currentSrcEndBitIndex ) )
					// src int limit reached
				{
					break;
				}

				final long currentDstBitIndex = currentSrcEndBitIndex + bitOffset;
				if ( bitIndexIsBitInIntIndex31( currentDstBitIndex ) )
					// dst int limit reached
				{
					break;
				}
			}

			//currentSrcEndBitIndex =
			//		Math.min(
			//				srcEndLastBitIndex ,
			//				Math.min(
			//						// auf volle 31 Bit erhoeht
			//						currentSrcEndBitIndex | 0x1F ,
			//						// auf volle 31 Bit erhoeht
			//						( currentSrcEndBitIndex + bitOffset ) | 0x1F ) );

			final int  currentSrcEndIntIndex = Math.toIntExact( currentSrcEndBitIndex / Integer.SIZE );
			final long currentSrcStartBitIndex = lastSrcEndBitIndex + 1;
			final int  currentSrcStartBitInIntIndex = Math.toIntExact( currentSrcStartBitIndex % Integer.SIZE );
			final int  currentPartBitLength = Math.toIntExact( currentSrcEndBitIndex - lastSrcEndBitIndex );
			final long currentDstStartBitIndex = currentSrcStartBitIndex + bitOffset;
			final int  currentDstIntIndex = Math.toIntExact( currentDstStartBitIndex / Integer.SIZE );
			final int  currentDstBitInIntIndex = Math.toIntExact( currentDstStartBitIndex % Integer.SIZE );

			final int readedValue = this.data[ currentSrcEndIntIndex ];
			//System.out.println( "readedValue " + toBinaryString( readedValue ) );

			final int writeValue =
					BitCopy.bitCopy(
							//src
							readedValue ,
							//dst
							data[ currentDstIntIndex ] ,
							//srcPos
							currentSrcStartBitInIntIndex ,
							//dstPos
							currentDstBitInIntIndex ,
							//length
							currentPartBitLength );
			//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

			to.data[ currentDstIntIndex ] = writeValue;

			lastSrcEndBitIndex = currentSrcEndBitIndex;
			currentSrcEndBitIndex++;
		}

		//throw new RuntimeException( "not implemented" );
	}

	@Override
	public String toString()
	{
		final StringBuilder buff = new StringBuilder();

		for ( int bitPackedArrayIndex = 0 ; bitPackedArrayIndex < bitPackedArraySize ; bitPackedArrayIndex++ )
			// TODO eventuell Länge des Ergebnis-Strings begrenzen
		{
			if ( buff.length() != 0 )
			{
				buff.append( ", " );
			}

			final int unsigned =
					getUnsigned(
							bitPackedArrayIndex );

			final int signed =
					getSigned(
							bitPackedArrayIndex );

			if ( unsigned == signed )
			{
				buff.append(
						unsigned );
			}
			else
			{
				buff.append(
						unsigned + "(" + signed + ")" );
			}
		}

		return this.getClass().getSimpleName() + "[" + buff + "]";
	}

	public static BitPackedIntArray instanceOfIntArrayUnsigned(
			final int dataElementWidth ,
			final int[] values )
	{
		final BitPackedIntArray array =
				instanceOf(
						dataElementWidth ,
						//bitPackedArraySize
						values.length );

		for ( int i = 0 ; i < values.length ; i++ )
		{
			array.setUnsigned(
					i ,
					values[i] );
		}

		return array;
	}

	public static BitPackedIntArray instanceOfIntArraySigned(
			final int dataElementWidth ,
			final int[] values )
	{
		final BitPackedIntArray array =
				instanceOf(
						dataElementWidth ,
						//bitPackedArraySize
						values.length );

		for ( int i = 0 ; i < values.length ; i++ )
		{
			array.setSigned(
					i ,
					values[i] );
		}

		return array;
	}

	public static BitPackedIntArray instanceOf(
			final int dataElementWidth ,
			final int bitPackedArraySize )
	{
		switch ( dataElementWidth ) {

		case 1:
			return new _01( bitPackedArraySize );
			//break;

		case 2:
			return new _02( bitPackedArraySize );
			//break;

		case 3:
			return new _03( bitPackedArraySize );
			//break;

		case 4:
			return new _04( bitPackedArraySize );
			//break;

		case 5:
			return new _05( bitPackedArraySize );
			//break;

		case 6:
			return new _06( bitPackedArraySize );
			//break;

		case 7:
			return new _07( bitPackedArraySize );
			//break;

		case 8:
			return new _08( bitPackedArraySize );
			//break;

		case 9:
			return new _09( bitPackedArraySize );
			//break;

		case 10:
			return new _10( bitPackedArraySize );
			//break;

		case 11:
			return new _11( bitPackedArraySize );
			//break;

		case 12:
			return new _12( bitPackedArraySize );
			//break;

		case 13:
			return new _13( bitPackedArraySize );
			//break;

		case 14:
			return new _14( bitPackedArraySize );
			//break;

		case 15:
			return new _15( bitPackedArraySize );
			//break;

		case 16:
			return new _16( bitPackedArraySize );
			//break;

		case 17:
			return new _17( bitPackedArraySize );
			//break;

		case 18:
			return new _18( bitPackedArraySize );
			//break;

		case 19:
			return new _19( bitPackedArraySize );
			//break;

		case 20:
			return new _20( bitPackedArraySize );
			//break;

		case 21:
			return new _21( bitPackedArraySize );
			//break;

		case 22:
			return new _22( bitPackedArraySize );
			//break;

		case 23:
			return new _23( bitPackedArraySize );
			//break;

		case 24:
			return new _24( bitPackedArraySize );
			//break;

		case 25:
			return new _25( bitPackedArraySize );
			//break;

		case 26:
			return new _26( bitPackedArraySize );
			//break;

		case 27:
			return new _27( bitPackedArraySize );
			//break;

		case 28:
			return new _28( bitPackedArraySize );
			//break;

		case 29:
			return new _29( bitPackedArraySize );
			//break;

		case 30:
			return new _30( bitPackedArraySize );
			//break;

		case 31:
			return new _31( bitPackedArraySize );
			//break;

		default:
			throw new IllegalArgumentException( "dataElementWidth: " + dataElementWidth );
			//break;
		}
	}

	public static class _01
	extends BitPackedIntArray
	{
		protected _01(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 1;
		}

		public void fromArrayCopy(
				final _01 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _01 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _02
	extends BitPackedIntArray
	{
		protected _02(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 2;
		}

		public void fromArrayCopy(
				final _02 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _02 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _03
	extends BitPackedIntArray
	{
		protected _03(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 3;
		}

		public void fromArrayCopy(
				final _03 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _03 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _04
	extends BitPackedIntArray
	{
		protected _04(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 4;
		}

		public void fromArrayCopy(
				final _04 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _04 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _05
	extends BitPackedIntArray
	{
		protected _05(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 5;
		}

		public void fromArrayCopy(
				final _05 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _05 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _06
	extends BitPackedIntArray
	{
		protected _06(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 6;
		}

		public void fromArrayCopy(
				final _06 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _06 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _07
	extends BitPackedIntArray
	{
		protected _07(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 7;
		}

		public void fromArrayCopy(
				final _07 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _07 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _08
	extends BitPackedIntArray
	{
		protected _08(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 8;
		}

		public void fromArrayCopy(
				final _08 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _08 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _09
	extends BitPackedIntArray
	{
		protected _09(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 9;
		}

		public void fromArrayCopy(
				final _09 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _09 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _10
	extends BitPackedIntArray
	{
		protected _10(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 10;
		}

		public void fromArrayCopy(
				final _10 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _10 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _11
	extends BitPackedIntArray
	{
		protected _11(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 11;
		}

		public void fromArrayCopy(
				final _11 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _11 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _12
	extends BitPackedIntArray
	{
		protected _12(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 12;
		}

		public void fromArrayCopy(
				final _12 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _12 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _13
	extends BitPackedIntArray
	{
		protected _13(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 13;
		}

		public void fromArrayCopy(
				final _13 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _13 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _14
	extends BitPackedIntArray
	{
		protected _14(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 14;
		}

		public void fromArrayCopy(
				final _14 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _14 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _15
	extends BitPackedIntArray
	{
		protected _15(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 15;
		}

		public void fromArrayCopy(
				final _15 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _15 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _16
	extends BitPackedIntArray
	{
		protected _16(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 16;
		}

		public void fromArrayCopy(
				final _16 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _16 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _17
	extends BitPackedIntArray
	{
		protected _17(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 17;
		}

		public void fromArrayCopy(
				final _17 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _17 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _18
	extends BitPackedIntArray
	{
		protected _18(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 18;
		}

		public void fromArrayCopy(
				final _18 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _18 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _19
	extends BitPackedIntArray
	{
		protected _19(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 19;
		}

		public void fromArrayCopy(
				final _19 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _19 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _20
	extends BitPackedIntArray
	{
		protected _20(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 20;
		}

		public void fromArrayCopy(
				final _20 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _20 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _21
	extends BitPackedIntArray
	{
		protected _21(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 21;
		}

		public void fromArrayCopy(
				final _21 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _21 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _22
	extends BitPackedIntArray
	{
		protected _22(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 22;
		}

		public void fromArrayCopy(
				final _22 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _22 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _23
	extends BitPackedIntArray
	{
		protected _23(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 23;
		}

		public void fromArrayCopy(
				final _23 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _23 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _24
	extends BitPackedIntArray
	{
		protected _24(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 24;
		}

		public void fromArrayCopy(
				final _24 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _24 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _25
	extends BitPackedIntArray
	{
		protected _25(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 25;
		}

		public void fromArrayCopy(
				final _25 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _25 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _26
	extends BitPackedIntArray
	{
		protected _26(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 26;
		}

		public void fromArrayCopy(
				final _26 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _26 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _27
	extends BitPackedIntArray
	{
		protected _27(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 27;
		}

		public void fromArrayCopy(
				final _27 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _27 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _28
	extends BitPackedIntArray
	{
		protected _28(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 28;
		}

		public void fromArrayCopy(
				final _28 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _28 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _29
	extends BitPackedIntArray
	{
		protected _29(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 29;
		}

		public void fromArrayCopy(
				final _29 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _29 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _30
	extends BitPackedIntArray
	{
		protected _30(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 30;
		}

		public void fromArrayCopy(
				final _30 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _30 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

	public static class _31
	extends BitPackedIntArray
	{
		protected _31(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 31;
		}

		public void fromArrayCopy(
				final _31 from ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerFromArrayCopy(
					from ,
					srcPos ,
					dstPos ,
					length );
		}

		public void toArrayCopy(
				final _31 to ,
				final int srcPos ,
				final int dstPos ,
				final int length )
		{
			innerToArrayCopy(
					to ,
					srcPos ,
					dstPos ,
					length );
		}
	}

}
