#!/usr/bin/env bash

# references
# 1. http://deathstartup.com/?p=81
# 2. https://gist.github.com/KioKrofovitch/716e6a681acb33859d16
# 3. https://stackoverflow.com/questions/35440907/can-circle-ci-reference-gradle-properties-credentials

export GRADLE_PROPERTIES=$HOME"/gradle.properties"
export KEYSTORE_PROPERTIES=$HOME"/repo/keystores/keystore.properties"
export PUBLISH_KEY_FILE=$HOME"/repo/keystores/chatbot_publish_key.json"
export PUBLISH_KEY_FILE_P12=$HOME"/repo/keystores/chatbot_publish_key.p12"
export STORE_FILE_LOCATION=$HOME"/repo/chatbot.jks"
export GOOGLE_SERVICES_DEBUG_LOCATION=$HOME"/repo/app/debug/google-services.json"
export GOOGLE_SERVICES_RELEASE_LOCATION=$HOME"/repo/app/main/google-services.json"


function copyEnvVarsToProperties {

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

    if [ ! -f "$PUBLISH_KEY_FILE_P12" ] ; then
        echo "Downloading p12 file"
        touch ${PUBLISH_KEY_FILE_P12}

        curl -L -o ${PUBLISH_KEY_FILE_P12} ${CHATBOT_PUBLISH_P12_KEY_URI}
    fi
}


# download key store file from remote location
# keystore URI will be the location uri for the *.jks file for signing application
function downloadKeyStoreFile {
    # use curl to download a keystore from $KEYSTORE_URI, if set,
    # to the path/filename set in $KEYSTORE.
    echo "Looking for $STORE_FILE_LOCATION ..."

    if [ ! -f ${STORE_FILE_LOCATION} ] ; then
        echo "Keystore file is missing, performing download"
        # we're using curl instead of wget because it will not
        # expose the sensitive uri in the build logs:
        curl -L -o ${STORE_FILE} ${KEY_STORE_URI}
    else
            echo "Keystore uri not set.  .APK artifact will not be signed."
    fi
}

# this will download the google-services.json files from secure locations and store them for
# the CI to build successfully
function setupGoogleServicesJsonFiles {

    echo "Checking for ${GOOGLE_SERVICES_RELEASE_LOCATION}..."

    if [ ! -f ${GOOGLE_SERVICES_RELEASE_LOCATION} ]; then
        echo "google-services.json for release not found, downloading...."
        curl -L -o ${GOOGLE_SERVICES_RELEASE_LOCATION} ${GOOGLE_SERVICES_RELEASE_URI}
    else
        echo "google-services.json not downloaded for release."
    fi

    echo "Checking for ${GOOGLE_SERVICES_DEBUG_LOCATION}..."

    if [ ! -f ${GOOGLE_SERVICES_DEBUG_LOCATION} ]; then
        echo "google-services.json for debug not found, downloading...."
        curl -L -o ${GOOGLE_SERVICES_DEBUG_LOCATION} ${GOOGLE_SERVICES_DEBUG_URI}
    else
        echo "google-services.json not downloaded for debug"
    fi
}

# execute functions
copyEnvVarsToProperties
downloadKeyStoreFile
setupGoogleServicesJsonFiles
