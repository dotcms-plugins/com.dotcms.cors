# API Auth Facade

This plugin transparently adds an "Authorization: Bearer {TOKEN}" header to selected incoming API requests to allow access to APIs that normally require client side authorization.  This allows access to specific APIs without exposing a JWT token in client side code.  

### To use this plugin
1. Generate an API token for a user who has the permissions that you want to allow and add that token to your plugin.properties. The permissions granted to this user will be respected in all intercepted API calls, also, any IP restrictions that you add to the user token will be respected.

2. Edit the `src/main/resources/plugin.properties` file. Add your newly generated token 
https://github.com/dotcms-plugins/com.dotcms.api.facade/blob/master/src/main/resources/plugin.properties#L2
 and set the regex for what APIs you want this auth facade to work against. You can do this by setting the property `com.dotcms.api.allowed.uri.regex` to a regex that needs to be matched in order for the Authorization to be added.

3. Build Plugin
You can build this plugin by running `./gradlew jar` in this directory.  The plugin jars will be found under the `./build/libs`.  These should be uploaded to dotCMS in the plugin screen.

## DISCLAIMER
Plugins are code outside of the dotCMS core code and unless explicitly stated otherwise, are not covered or warrantied  under dotCMS support engagements. However, support and customization for plugins is available through our Enterprise Services department. Contact us for more information.
