# API Auth Facade

This plugin transparently adds an "Authorization" header to incoming requests to allow access to specific dotCMS APIs that normally require client side authorization.  This allows access to specific APIs without exposing a JWT token in client side code.

### To use this plugin
Generate an API token for a user who has the permissions that you want to allow and add that token to your plugin.properties:

https://github.com/dotcms-plugins/com.dotcms.api.facade/blob/master/src/main/resources/plugin.properties#L2

You can also set what apis to allow via a regex.



## DISCLAIMER
Plugins are code outside of the dotCMS core code and unless explicitly stated otherwise, are not covered or warrantied  under dotCMS support engagements. However, support and customization for plugins is available through our Enterprise Services department. Contact us for more information.
