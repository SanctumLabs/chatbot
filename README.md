# ChatBot

Small simple Chatbot made with [Firebase](https://firebase.google.com/), [API.AI](https://api.ai/) and [Kotlin](https://kotlinlang.org/).

## Prerequisites

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

2. [API.AI SDK]()

