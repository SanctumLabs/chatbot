# ChatBot

[![CircleCI](https://circleci.com/gh/Wyvarn/chatbot.svg?style=svg)](https://circleci.com/gh/Wyvarn/chatbot)

Small simple Chatbot made with [Firebase](https://firebase.google.com/), [API.AI](https://api.ai/) and [Kotlin](https://kotlinlang.org/).

### Prerequisites

1. [Firebase SDK](https://firebase.google.com/)

You will need to setup a Firebase account and create a new project.

Follow the instructions and download the `google-services.json` file and copy it to both the debug and main folders in the src directory.
This is the tree structure you should get

```plain
app/src
    ├── debug
    │   └── google-services.json
    ├── main
    │   ├── AndroidManifest.xml
    │   ├── google-services.json
    │   ├── kotlin
    │   │   └── com
    │   │       └── chatbot
    .   .       .
```
> Tree structure for placing the google-services.json files

This allows the googleServices plugin to build for both debug and release versions with ease.
Also, ensure that the google-services.json file placed within the debug folder has the `.debug` suffix on the application id of the project, e.g, find all references for `package_name` or all references to the application id and update them:

```json
{
  "package_name": "<APPLICATION_ID>.debug"
}
```

The `googleservices.gradle` will take care of copying the right json file to the root of the app folder.

2. [API.AI SDK](https://console.api.ai/api-client/#/editAgent/)

You will need to configure API AI to get a client access token and a dev access token.

Once you have those configured, create a `gradle.properties file` in the root of the project.

You will need a service account email setup for automated deployments, read [this](./keystores/README.md) for more context.

```properties
API_AI_CLIENT_ACCESS_TOKEN=<CLIENT_ACCESS_TOKEN>
API_AI_DEV_ACCESS_TOKEN=<DEV_ACCESS_TOKEN>
CHATBOT_SERVICE_ACCOUNT_EMAIL=<SERVICE_ACCOUNT_EMAIL>
```
> properties to setup in the gradle.properties file

3. ***keystore.properties file***

This is necessary, especially for signing of the application for release.

Create a `keystore.properties` file in `keystores`directory and place these in the file

```properties
keyAlias=<KEY_ALIAS>
keyPassword=<KEY_PASSWORD>
storeFile=<STORE_FILE_NAME>
storePassword=<STORE_PASSWORD>
```
> Properties file for keystore, place in your values.

Refer to [this](https://developer.android.com/studio/publish/app-signing.html) article on how to create a release build with Android Studio.

That is all you will need to setup and start and even deploy this minimal project.
