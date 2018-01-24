#!/usr/bin/env bash

SFDC_API_VERSION=42.0

mkdir tmp
mkdir lib
git clone https://github.com/forcedotcom/wsc.git tmp
pushd tmp
mvn clean package -Dgpg.skip
cp target/force-wsc-*-uber.jar ../lib/force-wsc-uber.jar
popd
rm -rf tmp

java -jar lib/force-wsc-uber.jar \
    client/src/main/resources/wsdl/sfdc-partner-$SFDC_API_VERSION.wsdl \
    lib/partner-wsdl-$SFDC_API_VERSION.jar