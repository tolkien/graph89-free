/*
 *   Graph89 - Emulator for Android
 *  
 *	 Copyright (C) 2012-2013  Dritan Hashorva
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.

 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.graph89.common;

import java.util.ArrayList;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import com.graph89.emulationcore.EmulatorActivity;

public class V200Specific
{
	private static KeyCharacterMap	sKeyCharacterMap	= null;
	private static int				sKeyboardDeviceID	= -1;

	public static void AddAppExtensions(ArrayList<String> extensions)
	{
		extensions.add(".v2k"); // Apps
		extensions.add(".v2z"); // Assembly Program
		extensions.add(".v2f"); // Y=/Function/Equation
		extensions.add(".v2p"); // Program
		extensions.add(".v2l"); // List
		extensions.add(".v2g"); // Group
		extensions.add(".v2q"); // Certificate
		extensions.add(".v2m"); // Matrix
		extensions.add(".v2i"); // Picture
		extensions.add(".v2c"); // Data Variable
		extensions.add(".v2t"); // Text Object
		extensions.add(".v2y"); // AppVars
		extensions.add(".v2x"); // Geometry Macro
		extensions.add(".v2a"); // Geometry Figure
		extensions.add(".v2s"); // String
		extensions.add(".v2e"); // Expression
		extensions.add(".v2d"); // Graph Database
		extensions.add(".tig"); // tig
	}

	public static boolean ProcessKeyPress(int keyCode, KeyEvent event)
	{
		int deviceID = event.getDeviceId();

		if (sKeyCharacterMap == null || sKeyboardDeviceID != deviceID)
		{
			sKeyCharacterMap = KeyCharacterMap.load(deviceID);
			sKeyboardDeviceID = deviceID;
		}

		char c = (char) sKeyCharacterMap.get(event.getKeyCode(), event.getMetaState());

		boolean isAlpha = Character.isLetter(c);

		if (isAlpha)
		{
			boolean isUpper = Character.isUpperCase(c);
			int shiftKey = isUpper ? 5 : 0xFF;

			char letter = Character.toLowerCase(c);

			switch (letter)
			{
				case 'a':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 75 });
					return true;
				case 'b':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 44 });
					return true;
				case 'c':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 28 });
					return true;
				case 'd':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 20 });
					return true;
				case 'e':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 19 });
					return true;
				case 'f':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 27 });
					return true;
				case 'g':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 35 });
					return true;
				case 'h':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 43 });
					return true;
				case 'i':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 58 });
					return true;
				case 'j':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 51 });
					return true;
				case 'k':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 59 });
					return true;
				case 'l':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 67 });
					return true;
				case 'm':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 60 });
					return true;
				case 'n':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 52 });
					return true;
				case 'o':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 66 });
					return true;
				case 'p':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 46 });
					return true;
				case 'q':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 74 });
					return true;
				case 'r':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 26 });
					return true;
				case 's':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 13 });
					return true;
				case 't':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 34 });
					return true;
				case 'u':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 50 });
					return true;
				case 'v':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 36 });
					return true;
				case 'w':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 12 });
					return true;
				case 'x':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 21 });
					return true;
				case 'y':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 42 });
					return true;
				case 'z':
					EmulatorActivity.SendKeysToCalc(new int[] { shiftKey, 14 });
					return true;
			}
		}

		switch (c)
		{
			case '1':
				EmulatorActivity.SendKeysToCalc(new int[] { 10 });
				return true;
			case '2':
				EmulatorActivity.SendKeysToCalc(new int[] { 9 });
				return true;
			case '3':
				EmulatorActivity.SendKeysToCalc(new int[] { 8 });
				return true;
			case '4':
				EmulatorActivity.SendKeysToCalc(new int[] { 17 });
				return true;
			case '5':
				EmulatorActivity.SendKeysToCalc(new int[] { 16 });
				return true;
			case '6':
				EmulatorActivity.SendKeysToCalc(new int[] { 15 });
				return true;
			case '7':
				EmulatorActivity.SendKeysToCalc(new int[] { 24 });
				return true;
			case '8':
				EmulatorActivity.SendKeysToCalc(new int[] { 23 });
				return true;
			case '9':
				EmulatorActivity.SendKeysToCalc(new int[] { 22 });
				return true;
			case '0':
				EmulatorActivity.SendKeysToCalc(new int[] { 72 });
				return true;
			case '*':
				EmulatorActivity.SendKeysToCalc(new int[] { 54 });
				return true;
			case '-':
				EmulatorActivity.SendKeysToCalc(new int[] { 77 });
				return true;
			case '+':
				EmulatorActivity.SendKeysToCalc(new int[] { 65 });
				return true;
			case '/':
				EmulatorActivity.SendKeysToCalc(new int[] { 45 });
				return true;
			case '_':
				EmulatorActivity.SendKeysToCalc(new int[] { 70 });
				return true;
			case '[':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 30 });
				return true;
			case ']':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 45 });
				return true;
			case '(':
				EmulatorActivity.SendKeysToCalc(new int[] { 32 });
				return true;
			case ')':
				EmulatorActivity.SendKeysToCalc(new int[] { 31 });
				return true;
			case '{':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 32 });
				return true;
			case '}':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 31 });
				return true;
			case ' ':
				EmulatorActivity.SendKeysToCalc(new int[] { 37 });
				return true;
			case ';':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 60 });
				return true;
			case ':':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 68 });
				return true;
			case '<':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 72 });
				return true;
			case '>':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 71 });
				return true;
			case '\\':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 61 });
				return true;
			case '"':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 67 });
				return true;
			case '\'':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 44 });
				return true;
			case '.':
				EmulatorActivity.SendKeysToCalc(new int[] { 71 });
				return true;
			case '|':
				EmulatorActivity.SendKeysToCalc(new int[] { 7, 59 });
				return true;
			case ',':
				EmulatorActivity.SendKeysToCalc(new int[] { 30 });
				return true;
			case '^':
				EmulatorActivity.SendKeysToCalc(new int[] { 53 });
				return true;
			case '=':
				EmulatorActivity.SendKeysToCalc(new int[] { 61 });
				return true;
			case '!':
				EmulatorActivity.SendKeysToCalc(new int[] { 69 });
				return true;
			case '~':
				EmulatorActivity.SendKeysToCalc(new int[] { 56 });
				return true;
		}

		switch (keyCode)
		{
			case KeyEvent.KEYCODE_ENTER:
				EmulatorActivity.SendKeysToCalc(new int[] { 76 });
				return true;
			case KeyEvent.KEYCODE_DEL:
				EmulatorActivity.SendKeysToCalc(new int[] { 69 });
				return true;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				EmulatorActivity.SendKeysToCalc(new int[] { 0 });
				return true;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				EmulatorActivity.SendKeysToCalc(new int[] { 1 });
				return true;
			case KeyEvent.KEYCODE_DPAD_UP:
				EmulatorActivity.SendKeysToCalc(new int[] { 2 });
				return true;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				EmulatorActivity.SendKeysToCalc(new int[] { 3 });
				return true;
			case KeyEvent.KEYCODE_CLEAR:
				EmulatorActivity.SendKeysToCalc(new int[] { 56 });
				return true;
		}

		return false;
	}
}
