#!/usr/bin/env bash


./gradlew clean instant-mvp:build bintrayUpload -PbintrayUser=${USERID_BINTRAY} -PbintrayKey=${APIKEY_BINTRAY} -PdryRun=false
