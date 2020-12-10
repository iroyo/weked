package settings

external class FirefoxBrowserSettings {
    /**
     * Determines whether the browser cache is enabled or not.
     */
    val cacheEnabled: BooleanSetting

    /**
     * Determines whether code running in web pages can display popups in response to user events.
     */
    val allowPopupsForUserEvents: BooleanSetting

    /**
     * Determines whether the selected tab can be closed with a double click.
     */
    val closeTabsByDoubleClick: BooleanSetting

    /**
     * Determines the mouse event that triggers a context menu popup.
     * Its underlying value is a string that may be either "mouseup" or "mousedown".
     */
    val contextMenuShowEvent: Setting<String>

    /**
     * Determines whether the FTP protocol is enabled.
     */
    val ftpProtocolEnabled: BooleanSetting

    /**
     * Read the value of the browser's home page.
     * Note that this is a read-only setting. To change the homepage, see chrome_settings_overrides.
     */
    val homepageOverride: Setting<String>

    /**
     * Determines how the browser treats animated images.
     * The underlying value is a string that can take one of three values:
     * * "normal": the default. Play animated images as normal.
     * * "none": don't animate images at all.
     * * "once": play the animation once.
     */
    val imageAnimationBehavior: Setting<String>

    /**
     * Reads the value of the browser's new tab page.
     * Note that this is a read-only setting.
     */
    val newTabPageOverride: Setting<String>

    /**
     * A BrowserSetting object that can be used to control the position of newly opened tabs relative to already open tabs.
     * * The underlying value is a string that can take one of three values:
     * * "afterCurrent": Open all new tabs next to the current tab.
     * * "relatedAfterCurrent": The default. Open new tabs next to the current tab if they are related to the current tab (for example, if they were opened via a link in the current tab). Otherwise, open new tabs at the end of the tabstrip
     * * "atEnd": Open all tabs at the end of the tabstrip.
     */
    val newTabPosition: Setting<String>

    /**
     * A BrowserSetting object whose underlying value is a boolean.
     * If set to true, then when the user selects a bookmark, it will be opened in a new tab. If set to false (the default) bookmarks are opened in the current tab.
     */
    val openBookmarksInNewTabs: BooleanSetting

    /**
     * Determines whether search results are opened in the current tab or a new tab.
     * If set to true, then when the user selects a term in the browser's search box, the search results are displayed in a new tab. If set to false (the default) the search results are shown in the current tab.
     * Note that this does not affect the behavior when selecting items from the omnibox/awesomebar,only the dedicated search box.
     */
    val openSearchResultsInNewTabs: BooleanSetting

    /**
     * Determines whether address bar autocomplete suggestions are opened in the current tab or a new tab.
     * When the user focuses the address bar and starts typing, the browser offers autocomplete suggestions: a dropdown list of web pages based on the user's incomplete input and their browsing history.
     */
    val openUrlbarResultsInNewTabs: BooleanSetting

    /**
     * Controls whether the user-chosen colors override the page's colors.
     * Its underlying value is a string that may take any one of the following values:
     * * "high-contrast-only": Apply the user's choices only when a high-contrast theme is selected. This is the default.
     * * "never":  Never apply the user's choices.
     * * "always": Always apply the user's choices.
     */
    val overrideDocumentColors: Setting<String>

    /**
     * Controls whether the browser will use the fonts specified by a web page or use only built-in fonts.
     */
    val useDocumentFonts: BooleanSetting

    /**
     * Prevents websites from showing notifications using the Notification Web API.
     * * Note that this setting has no effect on notifications created by extensions using the notifications API.
     * * Note that this won't affect sites for which the user has set a per-site preference.
     */
    val webNotificationsDisabled: BooleanSetting

    /**
     * Controls whether zoom is applied to the entire page or to text only.
     */
    val zoomFullPage: BooleanSetting

    /**
     * Controls whether page zoom is applied on a per-site or per-tab basis. If privacy.websites.resistFingerprinting is true, this setting has no effect and zoom is applied on a per-tab basis.
     */
    val zoomSiteSpecific: BooleanSetting

}