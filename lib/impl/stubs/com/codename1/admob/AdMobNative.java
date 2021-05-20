package com.codename1.admob;


/**
 * 
 *  @author RAM
 */
public interface AdMobNative extends com.codename1.system.NativeInterface {

	/**
	 *  Method javadoc information
	 */
	public void init(String adID);

	/**
	 *  Method javadoc information
	 */
	public boolean loadAd();

	/**
	 *  Method javadoc information
	 */
	public boolean isLoaded();

	/**
	 *  Method javadoc information
	 */
	public void showAd();
}
