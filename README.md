# Add Headers to Requests
This plugin adds a web interceptor that appends headers to selected incoming requests, based on a regex. While it was built for cors headers, it can be used to add arbitrary headers to requests, such as cache control headers.


### To use this plugin
1. Edit the `src/main/resources/plugin.properties` file. Add the urls you wish to intercept (regex pattern) and then the headers you wish to set.  This will automatically set the headers for matching requests.

3. Build Plugin
You can build this plugin by running `./gradlew jar` in this directory.  The plugin jars will be found under the `./build/libs`.  These should be uploaded to dotCMS in the plugin screen.

## DISCLAIMER
Plugins are code outside of the dotCMS core code and unless explicitly stated otherwise, are not covered or warrantied  under dotCMS support engagements. However, support and customization for plugins is available through our Enterprise Services department. Contact us for more information.
