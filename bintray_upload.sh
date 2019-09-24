#!/usr/bin/env bash


./gradlew clean build bintrayUpload -PbintrayUser=${USERID_BINTRAY} -PbintrayKey=${APIKEY_BINTRAY} -PdryRun=false
