/*
    Calimero 2 - A library for KNX network access
    Copyright (c) 2010, 2011 B. Malinowsky

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tuwien.auto.calimero.dptxlator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import tuwien.auto.calimero.exception.KNXFormatException;
import tuwien.auto.calimero.log.LogLevel;

/**
 * Translator for KNX DPTs with main number 2, type <b>1 Bit controlled</b>.
 * <p>
 * The KNX data type width is 1 Bit, using the lowest Bit of 1 byte, with an additional 1 bit
 * boolean control field.<br>
 * The type structure is [1 Bit control field][1 Bit value field]. The default return value after
 * creation is control flag and boolean value field set to false (0). The value field is according
 * to DPT 1.x {@link DPTXlatorBoolean}.
 * <p>
 * In value methods expecting string types, the item is composed of the control field
 * representation, followed by whitespace and the corresponding DPT 1.x value.<br>
 * This translator requires {@link DPTXlatorBoolean}.
 * 
 * @see DPTXlatorBoolean
 */
public class DPTXlator1BitControlled extends DPTXlator
{
	/**
	 * A DPT for 1 Bit controlled, describing 1 control field and 1 value field.
	 * <p>
	 * This DPT references the corresponding 1.x DPTs, with the value field of a DPT 2.x being
	 * interpreted by the 1.x DPT of the same DPT sub number. For example, the value part of DPT
	 * 2.001 is formatted according to DPT 1.001.
	 * 
	 * @author B. Malinowsky
	 */
	public static class DPT1BitControlled extends DPT
	{
		private final DPT value;

		/**
		 * Creates a new datapoint type information structure for the 1 Bit controlled DPT.
		 * <p>
		 * 
		 * @param typeID {@inheritDoc}
		 * @param description {@inheritDoc}
		 * @param value the DPT of the control information
		 */
		public DPT1BitControlled(final String typeID, final String description, final DPT value)
		{
			super(typeID, description, "0 " + value.getLowerValue(), "1 " + value.getUpperValue());
			this.value = value;
		}

		/**
		 * Returns the DPT used to represent the value information of this DPT.
		 * <p>
		 * 
		 * @return the DPT for the value information
		 */
		public final DPT getValueDPT()
		{
			return value;
		}
	}

	/**
	 * DPT ID 2.001, Switch Controlled; values are {@link DPTXlatorBoolean#DPT_SWITCH}.
	 * <p>
	 */
	public static final DPT DPT_SWITCH_CONTROL = new DPT1BitControlled("2.001",
			"Switch Controlled", DPTXlatorBoolean.DPT_SWITCH);

	/**
	 * DPT ID 2.002, Bool Controlled; values are {@link DPTXlatorBoolean#DPT_BOOL}.
	 * <p>
	 */
	public static final DPT DPT_BOOL_CONTROL = new DPT1BitControlled("2.002", "Boolean Controlled",
			DPTXlatorBoolean.DPT_BOOL);

	/**
	 * DPT ID 2.003, Enable Controlled; values are {@link DPTXlatorBoolean#DPT_ENABLE}.
	 * <p>
	 */
	public static final DPT DPT_ENABLE_CONTROL = new DPT1BitControlled("2.003",
			"Enable Controlled", DPTXlatorBoolean.DPT_ENABLE);

	/**
	 * DPT ID 2.004, Ramp Controlled; values are {@link DPTXlatorBoolean#DPT_RAMP}.
	 * <p>
	 */
	public static final DPT DPT_RAMP_CONTROL = new DPT1BitControlled("2.004", "Ramp Controlled",
			DPTXlatorBoolean.DPT_RAMP);

	/**
	 * DPT ID 2.005, Alarm Controlled; values are {@link DPTXlatorBoolean#DPT_ALARM}.
	 * <p>
	 */
	public static final DPT DPT_ALARM_CONTROL = new DPT1BitControlled("2.005", "Alarm Controlled",
			DPTXlatorBoolean.DPT_ALARM);

	/**
	 * DPT ID 2.006, Binary Controlled; values are {@link DPTXlatorBoolean#DPT_BINARYVALUE}.
	 * <p>
	 */
	public static final DPT DPT_BINARY_CONTROL = new DPT1BitControlled("2.006",
			"Binary Controlled", DPTXlatorBoolean.DPT_BINARYVALUE);

	/**
	 * DPT ID 2.007, Step Controlled; values are {@link DPTXlatorBoolean#DPT_STEP}.
	 * <p>
	 */
	public static final DPT DPT_STEP_CONTROL = new DPT1BitControlled("2.007", "Step Controlled",
			DPTXlatorBoolean.DPT_STEP);

	/**
	 * DPT ID 2.008, Up/Down Controlled; values are {@link DPTXlatorBoolean#DPT_UPDOWN}.
	 * <p>
	 */
	public static final DPT DPT_UPDOWN_CONTROL = new DPT1BitControlled("2.008",
			"Up/Down Controlled", DPTXlatorBoolean.DPT_UPDOWN);

	/**
	 * DPT ID 2.009, Open/Close Controlled; values are {@link DPTXlatorBoolean#DPT_OPENCLOSE}.
	 * <p>
	 */
	public static final DPT DPT_OPENCLOSE_CONTROL = new DPT1BitControlled("2.009",
			"Open/Close Controlled", DPTXlatorBoolean.DPT_OPENCLOSE);

	/**
	 * DPT ID 2.010, Start Controlled; values are {@link DPTXlatorBoolean#DPT_START}.
	 * <p>
	 */
	public static final DPT DPT_START_CONTROL = new DPT1BitControlled("2.010", "Start Controlled",
			DPTXlatorBoolean.DPT_START);

	/**
	 * DPT ID 2.011, State Controlled; values are {@link DPTXlatorBoolean#DPT_STATE}.
	 * <p>
	 */
	public static final DPT DPT_STATE_CONTROL = new DPT1BitControlled("2.011", "State Controlled",
			DPTXlatorBoolean.DPT_STATE);

	/**
	 * DPT ID 2.012, Invert Controlled; values are {@link DPTXlatorBoolean#DPT_INVERT}.
	 * <p>
	 */
	public static final DPT DPT_INVERT_CONTROL = new DPT1BitControlled("2.012",
			"Invert Controlled", DPTXlatorBoolean.DPT_INVERT);

	private static final Map types;

	static {
		types = new HashMap(15);
		final Field[] fields = DPTXlator1BitControlled.class.getFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				final Object o = fields[i].get(null);
				if (o instanceof DPT) {
					types.put(((DPT) o).getID(), o);
				}
			}
			catch (final IllegalAccessException e) {}
		}
	}

	/**
	 * Creates a translator for the given datapoint type.
	 * <p>
	 * 
	 * @param dpt the requested datapoint type
	 * @throws KNXFormatException on not supported or not available DPT
	 */
	public DPTXlator1BitControlled(final DPT dpt) throws KNXFormatException
	{
		this(dpt.getID());
	}

	/**
	 * Creates a translator for the given datapoint type ID.
	 * <p>
	 * 
	 * @param dptId available implemented datapoint type ID
	 * @throws KNXFormatException on wrong formatted or not expected (available)
	 *         <code>dptID</code>
	 */
	public DPTXlator1BitControlled(final String dptId) throws KNXFormatException
	{
		super(0);
		setTypeID(types, dptId);
		data = new short[1];
	}

	/**
	 * Sets one new translation item, replacing any old items.
	 * <p>
	 * 
	 * @param control control field, <code>false</code> is <i>no control</i>, <code>true</code> is
	 *        <i>control</i>
	 * @param value value field
	 * @see #setControlBit(boolean)
	 */
	public final void setValue(final boolean control, final boolean value)
	{
		data = new short[1];
		setControlBit(control);
		setValueBit(value);
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getAllValues()
	 */
	public String[] getAllValues()
	{
		final String[] buf = new String[data.length];
		for (int i = 0; i < data.length; ++i)
			buf[i] = fromDPT(i);
		return buf;
	}

	/**
	 * Sets the control field for the first translation item.
	 * <p>
	 * A value of <code>false</code> stands for <i>no control</i>, <code>true</code> for
	 * <i>control</i>.<br>
	 * This method does not reset other item data or discard other translation items.
	 * 
	 * @param control control direction
	 */
	public final void setControlBit(final boolean control)
	{
		if (control)
			data[0] |= 0x02;
		else
			data[0] &= ~0x02;
	}

	/**
	 * Returns the control field of the first translation item.
	 * <p>
	 * A value of <code>false</code> stands for decrease / up, <code>true</code> for
	 * increase / down.
	 * 
	 * @return control bit as boolean
	 */
	public final boolean getControlBit()
	{
		return control(0);
	}

	/**
	 * Sets the value field for the first translation item.
	 * <p>
	 * This method does not reset other item data or discard other translation items.
	 * 
	 * @param value the value interpreted according to DPT 1.x
	 */
	public final void setValueBit(final boolean value)
	{
		if (value)
			data[0] |= 0x01;
		else
			data[0] &= ~0x01;
	}

	/**
	 * Returns the value field of the first translation item.
	 * <p>
	 * 
	 * @return value field
	 */
	public final boolean getValueBit()
	{
		return value(0);
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#setData(byte[], int)
	 */
	public void setData(final byte[] data, final int offset)
	{
		super.setData(data, offset);
		// only keep the lower two bits
		for (int i = 0; i < this.data.length; ++i)
			this.data[i] = (short) (this.data[i] & 0x03);
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getData(byte[], int)
	 */
	public byte[] getData(final byte[] dst, final int offset)
	{
		final int end = Math.min(data.length, dst.length - offset);
		for (int i = 0; i < end; ++i)
			dst[offset + i] = (byte) (dst[offset + i] & 0xFC | data[i]);
		return dst;
	}

	/* (non-Javadoc)
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getSubTypes()
	 */
	public final Map getSubTypes()
	{
		return types;
	}

	/**
	 * @return the subtypes of the 3 Bit controlled translator type
	 * @see DPTXlator#getSubTypesStatic()
	 */
	protected static Map getSubTypesStatic()
	{
		return types;
	}

	private boolean value(final int index)
	{
		return (data[index] & 0x01) == 0x01;
	}

	private boolean control(final int index)
	{
		return (data[index] & 0x02) != 0 ? true : false;
	}

	private String fromDPT(final int index)
	{
		final String ctrl = control(index) ? "1 " : "0 ";
		final DPT val = ((DPT1BitControlled) dpt).getValueDPT();
		return ctrl + (value(index) ? val.getUpperValue() : val.getLowerValue());
	}

	protected void toDPT(final String value, final short[] dst, final int index)
		throws KNXFormatException
	{
		if (value.length() < 3)
			logThrow(LogLevel.WARN, "wrong value format " + value, null, value);
		final DPT val = ((DPT1BitControlled) dpt).getValueDPT();
		final DPTXlatorBoolean x = new DPTXlatorBoolean(val);
		x.setValue(value.substring(2));
		int c = 0x0;
		if (value.startsWith("1 "))
			c = 0x2;
		else if (!value.startsWith("0 "))
			throw logThrow(LogLevel.WARN, "invalid control bit " + value, null, value);
		dst[index] = (short) (c + (x.getValueBoolean() ? 1 : 0));
	}
}
