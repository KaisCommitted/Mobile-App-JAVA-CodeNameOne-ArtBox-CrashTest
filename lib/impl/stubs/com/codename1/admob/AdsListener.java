package com.codename1.admob;


/**
 * 
 *  @author Chen
 */
public interface AdsListener {

	/**
	 *  Called when the user is about to return to the application after clicking
	 *  on an ad.
	 */
	public void onAdClosed();

	/**
	 *  Called when an ad request failed.
	 */
	public void onAdFailedToLoad(int errorCode);

	/**
	 *  Called when an ad leaves the application (e.g., to go to the browser).
	 */
	public void onAdLeftApplication();

	/**
	 *  Called when an ad is received.
	 */
	public void onAdLoaded();

	/**
	 *  Called when an ad opens an overlay that covers the screen.
	 */
	public void onAdOpened();
}
