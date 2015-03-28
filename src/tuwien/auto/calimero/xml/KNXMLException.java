/*
    Calimero 2 - A library for KNX network access
    Copyright (c) 2006, 2011 B. Malinowsky

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

    Linking this library statically or dynamically with other modules is
    making a combined work based on this library. Thus, the terms and
    conditions of the GNU General Public License cover the whole
    combination.

    As a special exception, the copyright holders of this library give you
    permission to link this library with independent modules to produce an
    executable, regardless of the license terms of these independent
    modules, and to copy and distribute the resulting executable under terms
    of your choice, provided that you also meet, for each linked independent
    module, the terms and conditions of the license of that module. An
    independent module is a module which is not derived from or based on
    this library. If you modify this library, you may extend this exception
    to your version of the library, but you are not obligated to do so. If
    you do not wish to do so, delete this exception statement from your
    version.
*/

package tuwien.auto.calimero.xml;

import tuwien.auto.calimero.exception.KNXException;

/**
 * Indicates a problem with XML processing.
 * <p>
 * 
 * @author B. Malinowsky
 */
public class KNXMLException extends KNXException
{
	private static final long serialVersionUID = 1L;

	private final String item;
	private final int line;

	/**
	 * Constructs a new <code>KNXMLException</code> without a detail message.
	 * <p>
	 */
	public KNXMLException()
	{
		item = null;
		line = 0;
	}

	/**
	 * Constructs a new <code>KNXMLException</code> with the specified detail message.
	 * <p>
	 * 
	 * @param s the detail message
	 */
	public KNXMLException(final String s)
	{
		super(s);
		item = null;
		line = 0;
	}

	/**
	 * Constructs a new <code>KNXMLException</code> with the specified detail message,
	 * the problematic/erroneous processed XML item together with the line number it
	 * occurred on.
	 * <p>
	 * 
	 * @param s the detail message
	 * @param badItem the problematic/erroneous processed XML tag, text or content
	 * @param lineNumber line number in the processed resource, 0 for no line number
	 */
	public KNXMLException(final String s, final String badItem, final int lineNumber)
	{
		super(s);
		item = badItem;
		line = lineNumber;
	}

	/**
	 * Returns the processed item which caused this exception.
	 * <p>
	 * 
	 * @return bad item as string, or <code>null</code> if no item available
	 */
	public final String getBadItem()
	{
		return item;
	}

	/**
	 * Returns the line number in the processed resource.
	 * <p>
	 * The first line is referred to with line number 1.
	 * 
	 * @return line number, 0 if no line number is available
	 */
	public final int getLineNumber()
	{
		return line;
	}
}
