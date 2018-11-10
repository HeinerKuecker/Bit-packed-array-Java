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
 * Es wird ein long-Array
 * als interner Speicher verwendet.
 *
 * Beim Schreiben {@link #setUnsigned}
 * und Lesen{@link #getUnsigned} wird
 * der zu speichernde Wert um die
 * notwendige Anzahl Bits verschoben
 * und beim Überschreiten der Grenze
 * zum nächsten internen long-Array-Wert
 * auf 2 long-Array-Werte aufgeteilt.
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
 * @author Heiner Kücker
 */
abstract public class BitPackedLongArray
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

	//private final long bitMask;
	// bessere Performance über konstante Werte je konkreter Klasse
	private final long bitMask()
	{
		// der JIT sieht dies als konstanten Wert an und optimiert die wiederholte Berechnung weg
		return ( 1L << dataElementWidth() ) - 1;
	}

	private final long[] data;

	protected BitPackedLongArray(
			//final int dataElementWidth ,
			final int bitPackedArraySize )
	{
		//if ( dataElementWidth < 1 )
		//{
		//	throw new IllegalArgumentException( "dataElementWidth: " + dataElementWidth );
		//}
		//if ( dataElementWidth >= Long.SIZE )
		//{
		//	throw new IllegalArgumentException( "dataElementWidth: " + dataElementWidth );
		//}

		if ( bitPackedArraySize < 1 )
		{
			throw new IllegalArgumentException( "bitPackedArraySize: " + bitPackedArraySize );
		}

		//this.dataElementWidth = dataElementWidth;

		this.bitPackedArraySize = bitPackedArraySize;

		//this.bitMask = ( 1L << dataElementWidth ) - 1;

		final int dataArrayLength =
				computeLongDataArrayLength(
						dataElementWidth() ,
						bitPackedArraySize );

		this.data = new long[ dataArrayLength ];
	}

	/**
	 * Diese Methode ist nur wegen Testbarkeit ausserhalb sichtbar.
	 *
	 * @param dataElementWidth
	 * @param bitPackedArraySize
	 * @return
	 */
	static int computeLongDataArrayLength(
			final int dataElementWidth ,
			final int bitPackedArraySize )
	{
		// TODO alle Math.toIntExact nach kompletten Test durch ungeprüften cast ersetzen für bessere Performance
		int dataArrayLength =
				Math.toIntExact( ( ( (long) bitPackedArraySize ) * dataElementWidth )
						// ganzzahlige Division mit Abrunden bei positivem gebrochenem Ergebnis
						/ Long.SIZE );

		if ( ( ( (long) dataArrayLength ) * Long.SIZE ) / dataElementWidth < bitPackedArraySize )
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
			final long value )
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

		final long firstBitIndex = ( (long) index ) * dataElementWidth();
		final long lastBitIndex  = ( ( ( (long) index ) + 1 ) * dataElementWidth() ) - 1;

		final int firstLongIndex = Math.toIntExact( firstBitIndex / Long.SIZE );
		final int lastLongIndex  = Math.toIntExact( lastBitIndex  / Long.SIZE );

		final int firstBitInLongValueIndex = Math.toIntExact( firstBitIndex % Long.SIZE );

		{
			final long shiftedFirstValue = /*maskedValue*/value << firstBitInLongValueIndex;
			//System.out.println( "shiftedFirstValue " + toBinaryString( shiftedFirstValue ) );

			// Maske zum Löschen der Bits des vorher gesetzten Wertes
			final long shiftedFirstResetMask = ~( bitMask() << firstBitInLongValueIndex );
			//System.out.println( "shiftedFirstResetMask " + toBinaryString( shiftedFirstResetMask ) );

			final long readedFirstLongValue = data[ firstLongIndex ];

			final long writeFirstLongValue = ( readedFirstLongValue & shiftedFirstResetMask ) | shiftedFirstValue;
			//System.out.println( "writeFirstLongValue " + toBinaryString( writeFirstLongValue ) );

			data[ firstLongIndex ] = writeFirstLongValue;
		}

		if ( firstLongIndex == lastLongIndex )
		{
			return;
		}

		final int lastBitInLongValueIndex = Math.toIntExact( lastBitIndex % Long.SIZE );

		final long shiftedLastValue = /*maskedValue*/value >>> ( dataElementWidth() - 1 ) - lastBitInLongValueIndex;
		//System.out.println( "shiftedLastValue " + toBinaryString( shiftedLastValue ) );

		// Maske zum Löschen der Bits des vorher gesetzten Wertes
		final long shiftedLastResetMask = ~( bitMask() >>> ( dataElementWidth() - 1 ) - lastBitInLongValueIndex );
		//System.out.println( "shiftedLastResetMask " + toBinaryString( shiftedLastResetMask ) );

		final long readedLastLongValue = data[ lastLongIndex ];

		final long writeLastLongValue = ( readedLastLongValue & shiftedLastResetMask ) | shiftedLastValue;
		//System.out.println( "writeLastLongValue " + toBinaryString( writeLastLongValue ) );

		data[ lastLongIndex ] = writeLastLongValue;
	}

	/**
	 * Get unsigned value.
	 *
	 * Zurückgeben des gespeicherten Wertes als long.
	 * Achtung, Vorzeichen wird nicht wie in long
	 * gesetzt.
	 *
	 * @param index index of virtual array of non octal width data
	 * @return value of non octal width data without consideration highest bit as sign
	 */
	public long getUnsigned(
			final int index )
	{
		if ( index < 0 || index >= bitPackedArraySize )
		{
			throw new ArrayIndexOutOfBoundsException( index );
		}

		final long firstBitIndex = ( (long) index ) * dataElementWidth();
		final long lastBitIndex  = ( ( ( (long) index ) + 1 ) * dataElementWidth() ) - 1;

		final int firstLongIndex = Math.toIntExact( firstBitIndex / Long.SIZE );
		final int lastLongIndex  = Math.toIntExact( lastBitIndex  / Long.SIZE );

		final int firstBitInLongValueIndex = Math.toIntExact( firstBitIndex % Long.SIZE );

		final long readedFirstLongValue = data[ firstLongIndex ];
		//System.out.println( "readedFirstLongValue " + toBinaryString( readedFirstLongValue ) );

		final long shiftedFirstValue = ( readedFirstLongValue >>> firstBitInLongValueIndex );
		//System.out.println( "shiftedFirstValue " + toBinaryString( shiftedFirstValue ) );

		final long maskedShiftedFirstValue = shiftedFirstValue & bitMask();
		//System.out.println( "maskedShiftedFirstValue " + toBinaryString( maskedShiftedFirstValue ) );

		if ( firstLongIndex == lastLongIndex )
		{
			return maskedShiftedFirstValue;
		}

		final long readedLastLongValue = data[ lastLongIndex ];
		final int lastBitInLongValueIndex = Math.toIntExact( lastBitIndex % Long.SIZE );

		// Maske zum Ausfiltern der Bits des vorher gesetzten Wertes
		final long shiftedLastMask = bitMask() >>> ( dataElementWidth() - 1 ) - lastBitInLongValueIndex;
		//System.out.println( "shiftedLastMask " + toBinaryString( shiftedLastMask ) );

		final long shiftedLastValue = ( readedLastLongValue & shiftedLastMask ) << ( dataElementWidth() - 1 ) - lastBitInLongValueIndex;
		//System.out.println( "shiftedLastValue " + toBinaryString( shiftedLastValue ) );

		final long value = shiftedFirstValue | shiftedLastValue;
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
			final long value )
	{
		final long maxSignedValue = bitMask() / 2;

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
	public long getSigned(
			final int index )
	{
		final long maxSignedValue = bitMask() / 2;

		final long unsigned =
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
	 * Copy values in self primitive long array
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
					if ( bitIndexIsBitInLongIndex63( currentSrcEndBitIndex ) )
						// src int limit reached
					{
						break;
					}

					final long currentDstBitIndex = currentSrcEndBitIndex + bitOffset;
					if ( bitIndexIsBitInLongIndex63( currentDstBitIndex ) )
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

				final int currentSrcEndLongIndex = Math.toIntExact( currentSrcEndBitIndex / Long.SIZE );
				final long currentSrcStartBitIndex = lastSrcEndBitIndex + 1;
				final int currentSrcStartBitInLongIndex = Math.toIntExact( currentSrcStartBitIndex % Long.SIZE );
				final int currentPartBitLength = Math.toIntExact( currentSrcEndBitIndex - lastSrcEndBitIndex );
				final long currentDstStartBitIndex = currentSrcStartBitIndex + bitOffset;
				final int currentDstLongIndex = Math.toIntExact( currentDstStartBitIndex / Long.SIZE );
				final int currentDstBitInLongIndex = Math.toIntExact( currentDstStartBitIndex % Long.SIZE );

				final long readedValue = data[ currentSrcEndLongIndex ];
				//System.out.println( "readedValue " + toBinaryString( readedValue ) );

				final long writeValue =
						BitCopy.bitCopy(
								//src
								readedValue ,
								//dst
								data[ currentDstLongIndex ] ,
								//srcPos
								currentSrcStartBitInLongIndex ,
								//dstPos
								currentDstBitInLongIndex ,
								//length
								currentPartBitLength );
				//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

				data[ currentDstLongIndex ] = writeValue;

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
					if ( bitIndexIsBitInLongIndex0( currentSrcBitIndex ) )
						// src int limit reached
					{
						break;
					}

					final long currentDstBitIndex = currentSrcBitIndex + bitOffset;
					if ( bitIndexIsBitInLongIndex0( currentDstBitIndex ) )
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

				final int currentSrcLongIndex = Math.toIntExact( currentSrcBitIndex / Long.SIZE );
				final int currentSrcBitInLongIndex = Math.toIntExact( currentSrcBitIndex % Long.SIZE );
				final int currentPartBitLength = Math.toIntExact( lastSrcBitIndex - currentSrcBitIndex );
				final long currentDstBitIndex = currentSrcBitIndex + bitOffset;
				final int currentDstLongIndex = Math.toIntExact( currentDstBitIndex / Long.SIZE );
				final int currentDstBitInLongIndex = Math.toIntExact( currentDstBitIndex % Long.SIZE );

				final long readedValue = data[ currentSrcLongIndex ];
				//System.out.println( "readedValue " + toBinaryString( readedValue ) );

				final long writeValue =
						BitCopy.bitCopy(
								//src
								readedValue ,
								//dst
								data[ currentDstLongIndex ] ,
								//srcPos
								currentSrcBitInLongIndex ,
								//dstPos
								currentDstBitInLongIndex ,
								//length
								currentPartBitLength );
				//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

				data[ currentDstLongIndex ] = writeValue;

				lastSrcBitIndex = currentSrcBitIndex;
				currentSrcBitIndex--;
			}
			return;
		}
		//throw new RuntimeException( "not implemented" );
	}

	private static boolean bitIndexIsBitInLongIndex0(
			final long value )
	{
		return ( value & ( Long.SIZE - 1 ) ) == 0;
	}

	private static boolean bitIndexIsBitInLongIndex63(
			final long value )
	{
		return ( value & ( Long.SIZE - 1 ) ) == ( Long.SIZE - 1 );
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
			final BitPackedLongArray from ,
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
				if ( bitIndexIsBitInLongIndex63( currentSrcEndBitIndex ) )
					// src int limit reached
				{
					break;
				}

				final long currentDstBitIndex = currentSrcEndBitIndex + bitOffset;
				if ( bitIndexIsBitInLongIndex63( currentDstBitIndex ) )
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

			final int  currentSrcEndLongIndex = Math.toIntExact( currentSrcEndBitIndex / Long.SIZE );
			final long currentSrcStartBitIndex = lastSrcEndBitIndex + 1;
			final int  currentSrcStartBitInLongIndex = Math.toIntExact( currentSrcStartBitIndex % Long.SIZE );
			final int  currentPartBitLength = Math.toIntExact( currentSrcEndBitIndex - lastSrcEndBitIndex );
			final long currentDstStartBitIndex = currentSrcStartBitIndex + bitOffset;
			final int  currentDstLongIndex = Math.toIntExact( currentDstStartBitIndex / Long.SIZE );
			final int  currentDstBitInLongIndex = Math.toIntExact( currentDstStartBitIndex % Long.SIZE );

			final long readedValue = from.data[ currentSrcEndLongIndex ];
			//System.out.println( "readedValue " + toBinaryString( readedValue ) );

			final long writeValue =
					BitCopy.bitCopy(
							//src
							readedValue ,
							//dst
							this.data[ currentDstLongIndex ] ,
							//srcPos
							currentSrcStartBitInLongIndex ,
							//dstPos
							currentDstBitInLongIndex ,
							//length
							currentPartBitLength );
			//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

			this.data[ currentDstLongIndex ] = writeValue;

			lastSrcEndBitIndex = currentSrcEndBitIndex;
			currentSrcEndBitIndex++;
		}
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
			final BitPackedLongArray to ,
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
				if ( bitIndexIsBitInLongIndex63( currentSrcEndBitIndex ) )
					// src int limit reached
				{
					break;
				}

				final long currentDstBitIndex = currentSrcEndBitIndex + bitOffset;
				if ( bitIndexIsBitInLongIndex63( currentDstBitIndex ) )
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

			final int  currentSrcEndLongIndex = Math.toIntExact( currentSrcEndBitIndex / Long.SIZE );
			final long currentSrcStartBitIndex = lastSrcEndBitIndex + 1;
			final int  currentSrcStartBitInLongIndex = Math.toIntExact( currentSrcStartBitIndex % Long.SIZE );
			final int  currentPartBitLength = Math.toIntExact( currentSrcEndBitIndex - lastSrcEndBitIndex );
			final long currentDstStartBitIndex = currentSrcStartBitIndex + bitOffset;
			final int  currentDstLongIndex = Math.toIntExact( currentDstStartBitIndex / Long.SIZE );
			final int  currentDstBitInLongIndex = Math.toIntExact( currentDstStartBitIndex % Long.SIZE );

			final long readedValue = this.data[ currentSrcEndLongIndex ];
			//System.out.println( "readedValue " + toBinaryString( readedValue ) );

			final long writeValue =
					BitCopy.bitCopy(
							//src
							readedValue ,
							//dst
							to.data[ currentDstLongIndex ] ,
							//srcPos
							currentSrcStartBitInLongIndex ,
							//dstPos
							currentDstBitInLongIndex ,
							//length
							currentPartBitLength );
			//System.out.println( "writeValue  " + toBinaryString( writeValue ) );

			to.data[ currentDstLongIndex ] = writeValue;

			lastSrcEndBitIndex = currentSrcEndBitIndex;
			currentSrcEndBitIndex++;
		}
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

			final long unsigned =
					getUnsigned(
							bitPackedArrayIndex );

			final long signed =
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

	public static BitPackedLongArray instanceOfLongArrayUnsigned(
			final int dataElementWidth ,
			final long[] values )
	{
		final BitPackedLongArray array =
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

	public static BitPackedLongArray instanceOfLongArraySigned(
			final int dataElementWidth ,
			final int[] values )
	{
		final BitPackedLongArray array =
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

	public static BitPackedLongArray instanceOf(
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

		case 32:
			return new _32( bitPackedArraySize );
			//break;

		case 33:
			return new _33( bitPackedArraySize );
			//break;

		case 34:
			return new _34( bitPackedArraySize );
			//break;

		case 35:
			return new _35( bitPackedArraySize );
			//break;

		case 36:
			return new _36( bitPackedArraySize );
			//break;

		case 37:
			return new _37( bitPackedArraySize );
			//break;

		case 38:
			return new _38( bitPackedArraySize );
			//break;

		case 39:
			return new _39( bitPackedArraySize );
			//break;

		case 40:
			return new _40( bitPackedArraySize );
			//break;

		case 41:
			return new _41( bitPackedArraySize );
			//break;

		case 42:
			return new _42( bitPackedArraySize );
			//break;

		case 43:
			return new _43( bitPackedArraySize );
			//break;

		case 44:
			return new _44( bitPackedArraySize );
			//break;

		case 45:
			return new _45( bitPackedArraySize );
			//break;

		case 46:
			return new _46( bitPackedArraySize );
			//break;

		case 47:
			return new _47( bitPackedArraySize );
			//break;

		case 48:
			return new _48( bitPackedArraySize );
			//break;

		case 49:
			return new _49( bitPackedArraySize );
			//break;

		case 50:
			return new _50( bitPackedArraySize );
			//break;

		case 51:
			return new _51( bitPackedArraySize );
			//break;

		case 52:
			return new _52( bitPackedArraySize );
			//break;

		case 53:
			return new _53( bitPackedArraySize );
			//break;

		case 54:
			return new _54( bitPackedArraySize );
			//break;

		case 55:
			return new _55( bitPackedArraySize );
			//break;

		case 56:
			return new _56( bitPackedArraySize );
			//break;

		case 57:
			return new _57( bitPackedArraySize );
			//break;

		case 58:
			return new _58( bitPackedArraySize );
			//break;

		case 59:
			return new _59( bitPackedArraySize );
			//break;

		case 60:
			return new _60( bitPackedArraySize );
			//break;

		case 61:
			return new _61( bitPackedArraySize );
			//break;

		case 62:
			return new _62( bitPackedArraySize );
			//break;

		case 63:
			return new _63( bitPackedArraySize );
			//break;

		default:
			throw new IllegalArgumentException( "dataElementWidth: " + dataElementWidth );
			//break;
		}
	}

	private static class _01
	extends BitPackedLongArray
	{
		protected _01(
				final int bitPackedArraySize )
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 1;
		}
	}

	private static class _02
	extends BitPackedLongArray
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
	}

	private static class _03
	extends BitPackedLongArray
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
	}

	private static class _04
	extends BitPackedLongArray
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
	}

	private static class _05
	extends BitPackedLongArray
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
	}

	private static class _06
	extends BitPackedLongArray
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
	}

	private static class _07
	extends BitPackedLongArray
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
	}

	private static class _08
	extends BitPackedLongArray
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
	}

	private static class _09
	extends BitPackedLongArray
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
	}

	private static class _10
	extends BitPackedLongArray
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
	}

	private static class _11
	extends BitPackedLongArray
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
	}

	private static class _12
	extends BitPackedLongArray
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
	}

	private static class _13
	extends BitPackedLongArray
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
	}

	private static class _14
	extends BitPackedLongArray
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
	}

	private static class _15
	extends BitPackedLongArray
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
	}

	private static class _16
	extends BitPackedLongArray
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
	}

	private static class _17
	extends BitPackedLongArray
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
	}

	private static class _18
	extends BitPackedLongArray
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
	}

	private static class _19
	extends BitPackedLongArray
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
	}

	private static class _20
	extends BitPackedLongArray
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
	}

	private static class _21
	extends BitPackedLongArray
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
	}

	private static class _22
	extends BitPackedLongArray
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
	}

	private static class _23
	extends BitPackedLongArray
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
	}

	private static class _24
	extends BitPackedLongArray
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
	}

	private static class _25
	extends BitPackedLongArray
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
	}

	private static class _26
	extends BitPackedLongArray
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
	}

	private static class _27
	extends BitPackedLongArray
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
	}

	private static class _28
	extends BitPackedLongArray
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
	}

	private static class _29
	extends BitPackedLongArray
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
	}

	private static class _30
	extends BitPackedLongArray
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
	}

	private static class _31
	extends BitPackedLongArray
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
	}

	private static class _32
	extends BitPackedLongArray
	{
		protected _32(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 32;
		}
	}

	private static class _33
	extends BitPackedLongArray
	{
		protected _33(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 33;
		}
	}

	private static class _34
	extends BitPackedLongArray
	{
		protected _34(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 34;
		}
	}

	private static class _35
	extends BitPackedLongArray
	{
		protected _35(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 35;
		}
	}

	private static class _36
	extends BitPackedLongArray
	{
		protected _36(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 36;
		}
	}

	private static class _37
	extends BitPackedLongArray
	{
		protected _37(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 37;
		}
	}

	private static class _38
	extends BitPackedLongArray
	{
		protected _38(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 38;
		}
	}

	private static class _39
	extends BitPackedLongArray
	{
		protected _39(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 39;
		}
	}

	private static class _40
	extends BitPackedLongArray
	{
		protected _40(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 40;
		}
	}

	private static class _41
	extends BitPackedLongArray
	{
		protected _41(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 41;
		}
	}

	private static class _42
	extends BitPackedLongArray
	{
		protected _42(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 42;
		}
	}

	private static class _43
	extends BitPackedLongArray
	{
		protected _43(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 43;
		}
	}

	private static class _44
	extends BitPackedLongArray
	{
		protected _44(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 44;
		}
	}

	private static class _45
	extends BitPackedLongArray
	{
		protected _45(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 45;
		}
	}

	private static class _46
	extends BitPackedLongArray
	{
		protected _46(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 46;
		}
	}

	private static class _47
	extends BitPackedLongArray
	{
		protected _47(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 47;
		}
	}

	private static class _48
	extends BitPackedLongArray
	{
		protected _48(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 48;
		}
	}

	private static class _49
	extends BitPackedLongArray
	{
		protected _49(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 49;
		}
	}

	private static class _50
	extends BitPackedLongArray
	{
		protected _50(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 50;
		}
	}

	private static class _51
	extends BitPackedLongArray
	{
		protected _51(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 51;
		}
	}

	private static class _52
	extends BitPackedLongArray
	{
		protected _52(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 52;
		}
	}

	private static class _53
	extends BitPackedLongArray
	{
		protected _53(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 53;
		}
	}

	private static class _54
	extends BitPackedLongArray
	{
		protected _54(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 54;
		}
	}

	private static class _55
	extends BitPackedLongArray
	{
		protected _55(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 55;
		}
	}

	private static class _56
	extends BitPackedLongArray
	{
		protected _56(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 56;
		}
	}

	private static class _57
	extends BitPackedLongArray
	{
		protected _57(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 57;
		}
	}

	private static class _58
	extends BitPackedLongArray
	{
		protected _58(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 58;
		}
	}

	private static class _59
	extends BitPackedLongArray
	{
		protected _59(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 59;
		}
	}

	private static class _60
	extends BitPackedLongArray
	{
		protected _60(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 60;
		}
	}

	private static class _61
	extends BitPackedLongArray
	{
		protected _61(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 61;
		}
	}

	private static class _62
	extends BitPackedLongArray
	{
		protected _62(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 62;
		}
	}

	private static class _63
	extends BitPackedLongArray
	{
		protected _63(
				final int bitPackedArraySize)
		{
			super( bitPackedArraySize );
		}

		@Override
		public int dataElementWidth()
		{
			return 63;
		}
	}

}
