//  http://code.google.com/p/android-color-picker/

/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.graph89.controls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

public class AmbilWarnaKotak extends View {
	Paint paint;
	Shader luar;
	Shader dalam;
	ComposeShader shader;
	final float[] color = { 1.f, 1.f, 1.f };

	public AmbilWarnaKotak(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AmbilWarnaKotak(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		luar = new LinearGradient(0.f, 0.f, 0.f, this.getMeasuredHeight(),
				0xffffffff, 0xff000000, TileMode.CLAMP);
		dalam = new LinearGradient(0.f, 0.f, this.getMeasuredWidth(), 0.f,
				0xffffffff, Color.HSVToColor(color), TileMode.CLAMP);
		shader = new ComposeShader(luar, dalam, PorterDuff.Mode.MULTIPLY);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (paint == null) {
			paint = new Paint();
		}
		paint.setShader(shader);
		canvas.drawRect(0.f, 0.f, this.getMeasuredWidth(), this.getMeasuredHeight(),
				paint);
	}

	void setHue(float hue) {
		color[0] = hue;
		invalidate();
	}
}
