#!/usr/bin/env bash

# references
# 1. http://deathstartup.com/?p=81
# 2. https://gist.github.com/KioKrofovitch/716e6a681acb33859d16
# 3. https://stackoverflow.com/questions/35440907/can-circle-ci-reference-gradle-properties-credentials

function copyEnvVarsToProperties {
    GRADLE_PROPERTIES=$HOME"/gradle.properties"
    KEYSTORE_PROPERTIES=$HOME"/keystores/keystore.properties"
    PUBLISH_KEY_FILE=$HOME"/keystores/chatbot_publish_key.json"

    export GRADLE_PROPERTIES
    export KEYSTORE_PROPERTIES
    export PUBLISH_KEY_FILE

    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"
    echo "Keystore Properties should exist at $KEYSTORE_PROPERTIES"

    if [ ! -f "$KEYSTORE_PROPERTIES" ]
    then
        echo "${KEYSTORE_PROPERTIES} does not exist...Creating file"

        touch ${KEYSTORE_PROPERTIES}

        echo "keyAlias=$KEY_ALIAS" >> ${KEYSTORE_PROPERTIES}
        echo "keyPassword=$KEY_PASSWORD" >> ${KEYSTORE_PROPERTIES}
        echo "storeFile=$STORE_FILE" >> ${KEYSTORE_PROPERTIES}
        echo "storePassword=$STORE_PASSWORD" >> ${KEYSTORE_PROPERTIES}
    fi

    if [ ! -f "$GRADLE_PROPERTIES" ]
    then
        echo "${GRADLE_PROPERTIES} does not exist...Creating Properties file"

        touch ${GRADLE_PROPERTIES}
        echo "API_AI_CLIENT_ACCESS_TOKEN=$API_AI_DEV_ACCESS_TOKEN" >> ${GRADLE_PROPERTIES}
        echo "API_AI_DEV_ACCESS_TOKEN=$API_AI_DEV_ACCESS_TOKEN" >> ${GRADLE_PROPERTIES}
        echo "CHATBOT_SERVICE_ACCOUNT_EMAIL=$API_AI_DEV_ACCESS_TOKEN" >> ${GRADLE_PROPERTIES}
    fi

    if [ ! -f "$PUBLISH_KEY_FILE" ]
    then
        echo "${PUBLISH_KEY_FILE} does not exist...creating properties file"

        touch ${PUBLISH_KEY_FILE}

        echo "$CHATBOT_PUBLISH_KEY" >> ${PUBLISH_KEY_FILE}
    fi
}


# download key store file from remote location
# keystore URI will be the location uri for the *.jks file for signing application
function downloadKeyStoreFile {
    # use curl to download a keystore from $KEYSTORE_URI, if set,
    # to the path/filename set in $KEYSTORE.
    if [[ ${KEYSTORE_URI} ]]
    then
        echo "Keystore detected - downloading..."
        # we're using curl instead of wget because it will not
        # expose the sensitive uri in the build logs:
        curl -L -o ${STORE_FILE} ${KEYSTORE_URI}
    else
        echo "Keystore uri not set.  .APK artifact will not be signed."
    fi
}

# execute functions
copyEnvVarsToProperties
downloadKeyStoreFile
