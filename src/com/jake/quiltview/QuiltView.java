package com.jake.quiltview;

import gws.grottworkshop.gwsomega.R;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;


import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;


@SuppressLint("Recycle")
public class QuiltView extends FrameLayout implements OnGlobalLayoutListener {

	public QuiltViewBase quilt;
	public ViewGroup scroll;
	public int padding = 5;
	public boolean isVertical = false;
	public ArrayList<View> views;
	
	public QuiltView(Context context,boolean isVertical) {
		super(context);
		this.isVertical = isVertical;
		setup();
	}
	
	public QuiltView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
			    R.styleable.QuiltView);
			 
		String orientation = a.getString(R.styleable.QuiltView_scrollOrientation);
		if(orientation != null){
			if(orientation.equals("vertical")){
				isVertical = true;
			} else {
				isVertical = false;
			}
		}
		setup();
	}
	
	public void setup(){
		views = new ArrayList<View>();
		
		if(isVertical){
			scroll = new ScrollView(this.getContext());
		} else {
			scroll = new HorizontalScrollView(this.getContext());
		}
		quilt = new QuiltViewBase(getContext(), isVertical);
		scroll.addView(quilt);
		this.addView(scroll);
		
	}
	
	public void addPatchImages(ArrayList<ImageView> images){
		
		for(ImageView image: images){
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
			image.setLayoutParams(params);
			
			LinearLayout wrapper = new LinearLayout(this.getContext());
			wrapper.setPadding(padding, padding, padding, padding);
			wrapper.addView(image);
			quilt.addPatch(wrapper);
		}
	}
	
	public void addPatchViews(ArrayList<View> views_a){
		for(View view: views_a){
			quilt.addPatch(view);
		}
	}
	
	public void addPatchesOnLayout(){
		for(View view: views){
			quilt.addPatch(view);
		}
	}
	
	public void removeQuilt(View view){
		quilt.removeView(view);
	}
	
	public void setChildPadding(int padding){
		this.padding = padding;
	}
	
	public void refresh(){
		quilt.refresh();
	}
	
	public void setOrientation(boolean isVertical){
		this.isVertical = isVertical;
	}

	
	@Override
	public void onGlobalLayout() {
		//addPatchesOnLayout();
	}
}
