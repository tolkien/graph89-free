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

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.graph89.emulationcore.Graph89ActivityBase;

public class Directories
{
	public static String getTempDirectory(Graph89ActivityBase activity)
	{
		String tmp = null;
		String folder = Util.GetInternalAppStorage(activity);

		if (folder != null)
		{
			tmp = folder + "tmp";
			Util.CreateDirectory(tmp);
		}

		return tmp;
	}
	
	public static String GetInternalAppStorage(Graph89ActivityBase context)
	{
		return Util.GetInternalAppStorage(context);
	}

	public static String getScreenShotDirectory(Graph89ActivityBase activity)
	{
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
			return Util.GetMediaRootFolder(activity) + "graph89/screenshots/";
		}
		else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
			/*
			return ((Context)activity).getExternalFilesDir(
					Environment.DIRECTORY_PICTURES).getAbsolutePath();
			 */
			return Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
		}
		return Environment.DIRECTORY_PICTURES;
	}

	public static String getReceivedDirectory(Graph89ActivityBase activity)
	{
		return Util.GetMediaRootFolder(activity) + "graph89/received/";
	}
}
