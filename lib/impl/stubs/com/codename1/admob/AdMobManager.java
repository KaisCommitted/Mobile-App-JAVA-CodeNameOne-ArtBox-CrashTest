package com.codename1.admob;


/**
 *  Utility class to show full screen admob Ads.
 */
public class AdMobManager {

	/**
	 *  Creates an AdMobManager instance
	 *  @param adID admob AD ID
	 */
	public AdMobManager(String adID) {
	}

	/**
	 *  Show the Ad if loaded
	 *  @return true if the ad was shown false if the Ad wasn't shown, could happen
	 *  if the loadAd didn't ended
	 */
	public boolean showAdIfLoaded() {
	}

	/**
	 *  This method loads the Ads, this could take some time and should be called 
	 *  each time before showAdIfLoaded().
	 *  @return true if Ad was loaded successfully false otherwise
	 */
	public boolean loadAd() {
	}

	/**
	 *  This is a utility method that will do the load and show.
	 */
	public void loadAndShow() {
	}

	/**
	 *  Sets an AdsListener
	 */
	public void setAdsListener(AdsListener l) {
	}
}
